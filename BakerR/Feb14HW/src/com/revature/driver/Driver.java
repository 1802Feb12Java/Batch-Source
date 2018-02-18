package com.revature.driver;

import com.revature.Employee;
import com.revature.Q1;
import com.revature.Q10;
import com.revature.Q11;
import com.revature.Q12;
import com.revature.Q13;
import com.revature.Q14;
import com.revature.Q15;
import com.revature.Q16;
import com.revature.Q17;
import com.revature.Q2;
import com.revature.Q3;
import com.revature.Q4;
import com.revature.Q5;
import com.revature.Q6;
import com.revature.Q7;
import com.revature.Q8;
import com.revature.Q9;

public class Driver {
	private static final Runnable[] qtnPrinter = {
			new Q1(new int[]{1,0,5,6,3,2,3,7,9,8,4}), // Array to sort
			new Q2(0, 25), // Fibonacci sequence range: [0 25)
			new Q3("My Test String"), // String to reverse
			new Q4(5), // Factorial (N!)
			new Q5("My sample String", 10), // Substring [0,10) of given string
			new Q6(8_651_684), // Detect if number is even
			new Q7(new Employee[]{
				new Employee("Clone z", "Financial", 33),
				new Employee("Clone Z", "HR", 33)
			}), // Sort employees by name, department, & age
			new Q8("karan", "madam", "tom", "civic", "radar", 
					"jimmy", "kayak", "john", "refer", "billy", 
					"did"), // Find palindromes
			new Q9(1, 100), // Find primes within the bounds
			new Q10<Integer>(1_883, 9_001), // Find minimum of two values
			new Q11(900.23f, 82.93f), // Modify 2 floats in a different package
			new Q12(1, 100), // Print even numbers within a range [a, b]
			new Q13(4), // Print triangle with given number of rows
			new Q14(), // Switch-case
			new Q15(), // Math Operations & Interfaces
			new Q16("This is the input argument string"), // Input argument String Length
			new Q17(), // User-Input Simple Interest Calculation
	};
	
	public static void main(String[] args) {
		for(Runnable r : qtnPrinter) {
			r.run();
			System.out.println("--------------------------------------------------------------------------------");
		}
	
	}

}
