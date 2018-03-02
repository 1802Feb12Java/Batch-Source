package com.revature;

public class CodeBlocks {
	//runs when object instantiated before constructor
	{
		System.out.println("Code Block:\tBefore constructor.");
	}
	
	CodeBlocks() {
		System.out.println("Constructor:\tAfter code block.");
	}
	
	//runs once when class is loaded before main method
	static {
		System.out.println("Static:\t\tShould run 1st.");		
	} 
	
	/*public static void main (String[] args) {
		System.out.println("Main method");
		new CodeBlocks();
	}*/
}