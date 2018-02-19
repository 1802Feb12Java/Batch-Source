package com.revature.corejavaassignment;

public class Q3 {
/*
 * Q3. Reverse a string without using a temporary variable.
 * Do NOT use reverse() in the StringBuffer or the StringBuilder APIs. 
 */
	public static void answer() {
		System.out.println("\nQ3.\tReverse a string without using a temporary variable.\r\n" + 
				"\tDo NOT use reverse() in the StringBuffer or the StringBuilder APIs.\n ");
		reverse("Roll Tide!:)");
	}
	/*
	 * This method reverses a string by swapping the characters in the manner of similar
	 * characters in a palindrome.
	 * with a value denoting the start index, and another denoting the end index of a string,
	 * the method swaps the first and last character then increments the start index
	 * and decrements the end index while making sure that the start is always less than
	 * the end index
	 */
	public static void reverse(String string){
		//single end reversal
//		for(int end=string.length(); end>0; end--) {
//			System.out.println("Reversing:\t" +string);
//			string =  string.substring(1, end) + string.substring(0, 1) + string.substring(end);
//		}
//		System.out.println(string);
		//Double end reverse
		for(int start=0, end=string.length(); end>start; end--, start++) {
			System.out.println("Reversing:\t" +string);
			string = string.substring(0,start) + string.substring(end-1, end) + string.substring(start+1, end-1) + string.substring(start, start+1) + string.substring(end);
		}
		System.out.println("Reversed:\t" +string);
	}
}
