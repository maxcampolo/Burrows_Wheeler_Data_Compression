
public class MoveToFront {
	
	public static int numChars = 256;  //number of ASCII characters 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			StdOut.println("Please enter one argument for MoveToFront");
			System.exit(0);
		}
		
		if (args[0].equals("-")) {
			
			encode();              //apply move to front encoding
			
		} else if (args[0].equals("+")) {
			
			decode();              //apply move to front decoding
			
		} else {
			StdOut.println("Please enter a valid argument '+' or '-' for MoveToFront");
		}

	}
	
	//Method to apply move to front encoding, output will be standard output
	public static void encode() {
		char[] charArray = newAlphabet();
		char c, i, tempIn, tempOut;
		
		while (!BinaryStdIn.isEmpty()) {
			
			c = BinaryStdIn.readChar();
			for (i = 0, tempOut = charArray[0]; c != charArray[i]; i++) {
				tempIn = charArray[i];
				charArray[i] = tempOut;
				tempOut = tempIn;
			}
			
			charArray[i] = tempOut;   //set new position of 0th index of char array
			BinaryStdOut.write(i);    //write output position
			charArray[0] = c;         //move current char to front
		}
		BinaryStdOut.close();
	}
	
	//Method to apply move to front decoding, output will be standard output
	public static void decode() {
		char[] charArray = newAlphabet();
		char c, i;
		
		while (!BinaryStdIn.isEmpty()) {
			i = BinaryStdIn.readChar(); 
			for (c = charArray[i]; i > 0; i--) {
				charArray[i] = charArray[i-1];		
			}
			
			charArray[i] = c;
			BinaryStdOut.write(c);
		}
		BinaryStdOut.close();
	}
	
	//makes an array of chars of every ASCII character 0-256
	private static char[] newAlphabet() {
		char[] alphabet = new char[numChars];
		for (char i = 0; i < numChars; i++) {
			alphabet[i] = i;
		}
		return alphabet;
	}

}
