package com.revature.corejavaassignment;

public class Q18 {
/*
 * Q18. Write a program having a concrete ;subclass that inherits three abstract methods 
 * from a superclass.  Provide the following three implementations in the subclass
 *  corresponding to the abstract methods in the superclass:
 * 
 * 1. 	Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending if 
 * 		any are found.
 * 2. 	Convert all of the lower case characters to uppercase in the input string, and return 
 * 		the result.
 * 3. 	Convert the input string to integer and add 10, output the result to the console.
 * 
 * Create an appropriate class having a main method to test the above setup.

 */
	public static void answer() {
		System.out.println("Q18. Write a program having a concrete ;subclass that inherits three abstract methods \r\n" + 
				" * from a superclass.  Provide the following three implementations in the subclass\r\n" + 
				" *  corresponding to the abstract methods in the superclass:\r\n" + 
				" * \r\n" + 
				" * 1. 	Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending if \r\n" + 
				" * 		any are found.\r\n" + 
				" * 2. 	Convert all of the lower case characters to uppercase in the input string, and return \r\n" + 
				" * 		the result.\r\n" + 
				" * 3. 	Convert the input string to integer and add 10, output the result to the console.\r\n" + 
				" * \r\n" + 
				" * Create an appropriate class having a main method to test the above setup.");
		testSubclass();
		
	}
	/*
	 * This method takes in a subclass class that inherits methods from a superclass class
	 * that has 3 abstract methods. This method then prints the results
	 * method 1 - checks for uppercase characters in a string and returns a boolean
	 * value on whether any are found
	 * method 2 - converts a string to uppercase
	 * method 3 - Convert a string to an integer , adds 10
	 */
	public static void testSubclass() {
		subClass test = new subClass();
		String string= "Roll Tide!:)";
		System.out.println("Does \"" + string +"\" contain uppercase letters?");
		System.out.println(test.checkUpper(string));
		System.out.println("\nWhat is \"" + string + "\" in all lowercase?");
		System.out.println(test.convertLowerCase(string));
		String num = "23434";
		System.out.println("What is the string \"" + num + "\" plus 10");
		System.out.println(test.string2Int(num) + "\n");
	}
}
abstract class superClass {
	abstract boolean checkUpper(String string);
	abstract String convertLowerCase(String string);
	abstract int string2Int(String string);
}
class subClass extends superClass{

	@Override
	boolean checkUpper(String string) {
		char[] chars = new char[string.length()];
		string.getChars(0, string.length()-1, chars, 0);
		for(char i: chars) {
			if (Character.isUpperCase(i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	String convertLowerCase(String string) {
		return string.toUpperCase();
	}

	@Override
	int string2Int(String string) {
		return Integer.parseInt(string) + 10;
	}
	
}