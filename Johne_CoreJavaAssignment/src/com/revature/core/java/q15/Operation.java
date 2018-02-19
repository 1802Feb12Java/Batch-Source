package com.revature.core.java.q15;

/**
 * 
 * @author johne
 * 
 * Q15. Write a program that defines an interface having 
 * the following methods: addition, subtraction, multiplication, and division. 
 *  
 * Create a class that implements this interface and provides 
 * appropriate functionality to carry out the required operations. 
 * Hard code two operands in a test class having a main method that calls the implementing class.
 */

public interface Operation {
	
	/**
	 * 
	 * @param n number to add with
	 * @return result of obj added with n
	 */
	public double addition(double n);
	
	/**
	 * 
	 * @param n number to subtract with
	 * @return result of obj subtract with n
	 */
	public double subtraction(double n);
	
	/**
	 * 
	 * @param n number to multiply by
	 * @return result of obj multiply with n
	 */
	public double multiply(double n);
	
	/**
	 * 
	 * @param n number to divide by
	 * @return result of obj divided with n
	 */
	public double division(double n);
}
