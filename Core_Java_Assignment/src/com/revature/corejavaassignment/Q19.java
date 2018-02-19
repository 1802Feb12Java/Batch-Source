package com.revature.corejavaassignment;

import java.util.ArrayList;
import java.util.List;

public class Q19 {
/*
 * Q19. Create an ArrayList and insert integers 1 through 10. Display the ArrayList. 
 * Add all the even numbers up and display the result. Add all the odd numbers up and 
 * display the result. Remove the prime numbers from the ArrayList and print out the 
 * remaining ArrayList.

 */
	public static void answer() {
		System.out.println("Q19. Create an ArrayList and insert integers 1 through 10. Display the ArrayList. \r\n" + 
				" * Add all the even numbers up and display the result. Add all the odd numbers up and \r\n" + 
				" * display the result. Remove the prime numbers from the ArrayList and print out the \r\n" + 
				" * remaining ArrayList.");
		feelingListly();
	}
	/*
	 * This method creates an arraylist then inserts integers 1 through 10 and prints it
	 * This method then adds all even values, prints them, adds all odd values, prints them
	 * then removes all prime numbers and prints the remaining non-prime numbers
	 */
	public static void feelingListly() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1; i<=10; i++) {
			list.add(i);
		}
		System.out.println("The arraylist: " + list.toString());
		int even=0;
		for(Integer i: list) {
			if(i%2==0) {	even+=i;	}
		}
		System.out.println("The even numbers add up to :" + even);
		int odd=0;
		for(Integer i: list) {
			if(i%2!=0) {	odd+=i;	}
		}
		System.out.println("The odd numbers add up to :" + odd);
		for(int i=0; i<list.size(); i++) {
			int divs= 0;
			for(int j=1; j<=list.get(i); j++) {
				//System.out.println("j " + j);
				if (list.get(i)%j==0) {	divs++;	}
			}
			if (divs<=2) {	
				list.remove(i);
				i--;
			}
				
		}
		System.out.println("The list without prime numbers is :" + list.toString());
	}
}
