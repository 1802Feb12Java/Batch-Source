package com.revature.core.java;
import java.util.*;
/**
 * 
 * @author johne
 * Q10. Find the minimum of two numbers using ternary operators.
 */

public class Q10_MinOfTwoNum {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter two numbers: ");
		int num1 = input.nextInt();
		int num2 = input.nextInt();
		
		//use ternary to find min of two numbers
		int minVal = num1 > num2 ? num2 : num1;
		System.out.println("Min is " + minVal);
	}
}
