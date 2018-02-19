/*  This is a class file for using an array list for storing integer values
 * 
 *  @author Dominic Nguyen
 */

package com.revature;

import java.util.ArrayList;

public class Question19 {

	// constructor
	public Question19() {
		
	}
	
	/*  This is a method for storing integer values in an array list
	 *  
	 */
	public void integerArray() {
		int i = 0;
		int j = 0;
		int k = 0;
		int count = 0;
		ArrayList<Integer> array = new ArrayList<>();
		Integer result = 0, result2 = 0;
		int number[] = new int[10];
		
		System.out.print("Q19: ");
		
		// add integer values in array list
		for(i = 1; i <= 10; i++) {
			array.add(i);
		}
		
		System.out.println(array);
		
		// Calculate the sum of even numbers
		for(Integer a: array) {
			if(a % 2 == 0) {
				result = result + a;
			}
		}
		System.out.println("Sum of even numbers: " + result);
		
		// Calculate the sum of odd numbers
		for(Integer b: array) {
			if(b % 2 != 0) {
				result2 = result2 + b;
			}
		}
		System.out.println("Sum of odd numbers: " + result2);
	
		// Find the prime numbers
		for(Integer c: array) {
			j = 0;
			count = 0;
			for(i = 1; i <= 10; i++) {
				if(c % i == 0) {
					count++;
				}
			}
			if(count == 2) {
				number[k] = c;
				k++;
				//System.out.println(c + " ");
			}
			j++;
		}
		
		count = k;
		
		// remove the prime numbers from the array list
		loop: while(count > 0) {
			j = 0;
			for(Integer d : array) {
				for(i = 0; i < k; i++) {
					if(d == number[i]) {
						array.remove(j);
						//System.out.println(array);
						count--;
						continue loop;
					}
				}
				j++;
			}
		}
		
		// display the modified array list
		System.out.println(array);
	}
}
