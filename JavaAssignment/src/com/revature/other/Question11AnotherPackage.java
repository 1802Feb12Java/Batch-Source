/*  This class file has 2 variables of type float that will be accessed by a different class and package
 * 
 */

package com.revature.other;

public class Question11AnotherPackage {

	// constructor
	public Question11AnotherPackage() {
		
	}
	
	/*  This method has 2 variables of type float
	 * 
	 */
	public void Method() {
		float variable1 = 10;
		float variable2 = 20;
		
		System.out.println("Q11: variable1: " + variable1 + ", variable2: " + variable2);
	}
}
