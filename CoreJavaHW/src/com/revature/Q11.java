package com.revature;

import com.revature.otherpackage.Q11b;	//have to import the other package to get access to the class

public class Q11 {
	public static void run() {
		Q11b q11b = new Q11b();		//create an instance of the class to use them
		System.out.println("Getting default float values:\tf1 = " + q11b.getF1() + "\tf2 = " + q11b.getF2());	//"getting" the variables
		System.out.print("Changing f1 to 25.4: ");
		q11b.setF1(25.4f);	//demonstrating setting the first variable
		System.out.println("\t\tf1 = " + q11b.getF1() + "\tf2 = " + q11b.getF2());
		System.out.print("Changing f1 to 1.8: ");
		q11b.setF2(1.8f);	//demonstrating setting the second variable
		System.out.println("\t\tf1 = " + q11b.getF1() + "\tf2 = " + q11b.getF2());
	}
}
