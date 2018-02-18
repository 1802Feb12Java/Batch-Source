package main;

import com.revature.InterfaceImplementation;

public class RunInterface15 {

	public static void main(String[] args) {
	
		InterfaceImplementation a = new InterfaceImplementation();	// have to call by object because implementations forbid methods from being static
		
		System.out.println("Example 1");
		System.out.println("Adding 6 and 9 makes " + a.add(6, 9));
		System.out.println("Subtracting 6 and 9 makes " + a.subtract(6, 9));
		System.out.println("Multiplying 6 and 9 makes " + a.multiply(6, 9));
		System.out.println("Dividing 9 and 3 makes " + a.divide(9, 3));
		
		System.out.println();
		
		System.out.println("Example 2");
		System.out.println("Adding 10 and 34 makes " + a.add(10,34));
		System.out.println("Subtracting 15 and 24 makes " + a.subtract(15, 24));
		System.out.println("Multiplying 30 and 10 makes " + a.multiply(30,10));
		System.out.println("Dividing 80 and 4 makes " + a.divide(80, 4));
	}
}
