/*  A class file that accesses a method with another package
 * 
 * @author Dominic Nguyen
 */

package com.revature;
import com.revature.other.Question11AnotherPackage;

public class Question11 {

	// constructor
	public Question11() {
		
	}
	
	/* This method accesses a method with another package
	 * 
	 */
	public void AccessPackage() {
		Question11AnotherPackage q11 = new Question11AnotherPackage();
		
		q11.Method();
	}
}
