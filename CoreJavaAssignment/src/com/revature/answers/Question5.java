package com.revature.answers;

public class Question5 {

	public static void main(String[] args) {
		
		System.out.println(cutString("abcdefghij", 5));
		
	}

	public static String cutString(String str, int idx) {
		String og = "";									//new string to store the substring
		char[] holder = new char[str.length()];			//makes an array to hold the characters in the string
		
		str.getChars(0, str.length()-1, holder, 0);		//gets the characters from the indexes and places them in
														//the holder array starting at index 0.
		for(int i = 0; i < idx; i++) {
			og += holder[i];							//Builds the string from the characters in the array.
		}
		return og;
	}
}
