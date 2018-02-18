package com.revature.work;

public class Q13 {
	public void triangle (int fin) {
		// using switch case to print out the pattern
		switch (fin) {
		case 1: 
			System.out.println("0");
			fin = 2;
			// no breaks needed for first 3 cases because end of the case, it gets passed to the next case
		case 2: 
			System.out.println("1 0");
			fin = 3;
		case 3: 
			System.out.println("1 0 1");
		case 4:
			System.out.println("0 1 0 1");
			break;
		default:
			System.out.println("You should not see me!");
			break;
			}	
		}
}
