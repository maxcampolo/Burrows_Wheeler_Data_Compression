import java.util.Arrays;


public class BurrowsWheeler {
	
	//apply Burrows Wheeler encoding - read from standard input and write to standard output
	public static void encode() {
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		char[] chars = new char[input.length];
		
		CircularSuffixArray array = new CircularSuffixArray(s);
		int first = -1;
		for (int i = 0; i < array.length(); i++) {
			int currentIndex = array.index(i);
			int position = currentIndex - 1;
			
			if (array.index(i) == 0) {
				first = i;
			}
			
			if (position < 0) {
				position = input.length - 1;
			}
			chars[i] = input[position];
		}
		
		BinaryStdOut.write(first);
		for (int i = 0; i < chars.length; i++) {
			BinaryStdOut.write(chars[i], 8);
		}
		
		BinaryStdOut.close();
	}
	
	//apply Burrows Wheeler decoding, read from standard input and write to standard output
	public static void decode() {
		int first = BinaryStdIn.readInt();
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		char[] sortedChars = s.toCharArray();
		int[] next = new int[input.length];
		
		Arrays.sort(sortedChars);
		
		for (int i = 0; i < input.length; i++) {
			int position = 0;
			if (i > 0 && sortedChars[i] == sortedChars[i-1]) {
				position = next[i-1] + 1;
			}
			
			for (int j = position; j < input.length; j++) {
				position = j;
				if (input[j] == sortedChars[i]) {
					break;
				}
			}
			next[i] = position;
		}
		
		//StdOut.println(next);
		
		int newFirst = first;
		for (int i = 0; i < input.length; i++) {
			BinaryStdOut.write(sortedChars[newFirst], 8);
			newFirst = next[newFirst];
		}
		BinaryStdOut.close();
	}
	
	public static void main(String[] args) {
		
		if (args.length != 1) {
			StdOut.println("Please enter one argument for BurrowsWheeler");
			System.exit(0);
		}
		
		if (args[0].equals("-")) {
			
			encode();              //apply Burrows Wheeler encoding
			
		} else if (args[0].equals("+")) {
			
			decode();              //apply Burrows Wheeler decoding
			
		} else {
			StdOut.println("Please enter a valid argument '+' or '-' for BurrowsWheeler");
		}

	}

}
