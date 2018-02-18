package com.revature;

import java.util.ArrayList;
//import java.util.Comparator;
import java.util.Collections;

//imports for Q7 custom implemented comparators
import com.revature.employee.Employee;
import com.revature.employee.Sortbyage;
import com.revature.employee.Sortbyname;
import com.revature.employee.Sortbydept;

//The driver class that will run all the question/classes
//MOST methods are static so that I can be lazy and not call the constructor
public class Driver {
	//question bank
	private static ArrayList<String> questions = new ArrayList<String>(20);
	
	//prints a line of '=' to help separate each question
	private static void printSep() {
		System.out.println("================================================");
	}
	
	//initalizes the quetion bank
	private static void loadQuetions() {
		questions.add("Q1. Perform Bubble Sort on a given Array: ");
		questions.add("Q2. Write a program to display the first 25 Fibonacci \n    numbers beginning at 0.: ");
		questions.add("Q3. Reverse a string without using a temporary variable.\n    Do NOT use reverse() in the StringBuffer or the \n    StringBuilder APIs.");
		questions.add("Q4. Write a program to compute N factorial.");
		questions.add("Q5. Print substring given a string and an index, from 0 \n    to index-1(inclusive).");
		questions.add("Q6: Write a program to determine if an integer is even \n    without using the modulus operator (%).");
		questions.add("Q7: Sort two employees based on their name, department, \n    and age using the Comparator interface.");
		questions.add("Q8. Write a program that stores the following strings \n    in an ArrayList and saves all the palindromes in \n    another ArrayList.");
		questions.add("Q9. Create an ArrayList which stores numbers from 1 to 100 \n    and prints out all the prime numbers to the console.");
		questions.add("Q10. Find the minimum of two numbers using ternary operators.");
		questions.add("Q11. Write a program that would access two float-variables \n     from a class that exists in another package. \n     Note, you will need to create two packages to \n     demonstrate the solution.");
		questions.add("Q12. Write a program to store numbers from 1 to 100 in an \n     array. Print out all the even numbers from the array. \n     Use the enhanced FOR loop for printing out the numbers.");
		questions.add("Q13. Display the triangle on the console as follows using any \n     type of loop.  Do NOT use a simple group of print \n     statements to accomplish this.\n     0\n     1 0\n     1 0 1\n     0 1 0 1");
		questions.add("Q14. Write a program that demonstrates the switch case. \n     Implement the following functionalities in the cases:java\n     Case 1: Find the square root of a number using the Math class method.\n     Case 2: Display today’s date.\n     Case 3: Split the following string and store it in a string array.\n             \"I am learning Core Java\"");
		questions.add("Q15. Write a program that defines an interface having the \n     following methods: addition, subtraction, multiplication, \n     and division.  Create a class that implements this \n     interface and provides appropriate functionality to carry \n     out the required operations. Hard code two operands in a \n     test class having a main method that calls the implementing \n     class.");
		questions.add("Q16. Write a program to display the number of characters for a \n     string input. The string should be entered as a command \n     line argument using (String [ ] args).");
		questions.add("Q17. Write a program that calculates the simple interest on the \n     principal, rate of interest and number of years provided \n     by the user. Enter principal, rate and time through the console \n     using the Scanner class.\n     Interest = Principal* Rate* Time");
		questions.add("Q18. Write a program having a concrete ;subclass that inherits three \n     abstract methods from a superclass.  Provide the following three \n     implementations in the subclass corresponding to the abstract \n     methods in the superclass:\n     1. Check for uppercase characters in a string, and return ‘true’ \n        or ‘false’ depending if any are found.\n     2. Convert all of the lower case characters to uppercase in the \n        input string, and return the result.\n     3. Convert the input string to integer and add 10, output the \n        result to the console.\nCreate an appropriate class having a main method to test the above setup");
		questions.add("Q19. Create an ArrayList and insert integers 1 through 10. Display \n     the ArrayList. Add all the even numbers up and display the \n     result. Add all the odd numbers up and display the result. \n     Remove the prime numbers from the ArrayList and print out the\n     remaining ArrayList.");
		questions.add("Q20. Create a notepad file called Data.txt and enter the following:\n     Mickey:Mouse:35:Arizona\n     Hulk:Hogan:50:Virginia\n     Roger:Rabbit:22:California\n     Wonder:Woman:18:Montana\n\n     Write a program that would read from the file and print it out \n     to the screen in the following format:\n     Name: Mickey Mouse\n     Age: 35 years\n     State: Arizona State");
	}
	
	private static void q1() {
		System.out.println(questions.get(0));
		int[] arr = {1,0,5,6,3,2,3,7,9,8,4}; //len = 11;
		BubbleSort.bubbleSort(arr);
	}
	
	private static void q2() {
		System.out.println(questions.get(1));
		FibNum.printToK(25);
	}
	
	private static void q3() {
		System.out.println(questions.get(2));
		StringReverse.rev("Dark Souls");
	}
	
	private static void q4() {
		System.out.println(questions.get(3));
		int n = 5;
		System.out.println(n + "! = " + NFactorial.nFact(n));
	}
	
	private static void q5() {
		System.out.println(questions.get(4));
		String s = "Tacocat!";
		System.out.println("s: " + s);
		System.out.print("subStr(s,3): ");
		SubStr.subStr(s, 3);
	}
	
	private static void q6() {
		System.out.println(questions.get(5));
		int n = 21;
		System.out.println("int n: " + n);
		System.out.print("n is ");
		IsEven.isEven(n);
	}
	
	private static void q7() {
		System.out.println(questions.get(6));
		ArrayList<Employee> emArr = new ArrayList<Employee>(2);
		emArr.add(new Employee("Matt","Instructor",26));
		emArr.add(new Employee("Kevin", "Trainee", 23));
		System.out.println("Og list: " + emArr);
		Collections.sort(emArr, new Sortbyname());
		System.out.println("Sort By Name: " + emArr);
		
		Collections.sort(emArr, new Sortbydept());
		System.out.println("Sort By Dept: " + emArr);
		
		Collections.sort(emArr, new Sortbyage());
		System.out.println("Sort By Ages: " + emArr);
	}
	
	private static void q8() {
		System.out.println(questions.get(7));
		PalindromesArray.readList();
		PalindromesArray.sepPalin();
	}
	
	private static void q9() {
		System.out.println(questions.get(8));
		Primes.printPrimes();
	}
	
	private static void q10() {
		System.out.println(questions.get(9));
		System.out.println(TernaryMin.ternaryMin(20, 1));
	}
	
	private static void q11() {
		System.out.println(questions.get(10));
		AccessFloatFromPackage.printFloats();
	}
	
	private static void q12() {
		System.out.println(questions.get(11));
		PrintEven.print();
	}
	
	private static void q13() {
		System.out.println(questions.get(12));
		
	}
	
	private static void q14() {
		System.out.println(questions.get(13));
		Cases.test(1);
		Cases.test(2);
		Cases.test(3);
	}
	
	private static void q15() {
		System.out.println(questions.get(14));
		
		double a = 10;
		double b = 2;
		MathClass mC = new MathClass();
		System.out.println("Addition: " + mC.addition(a,b));
		System.out.println("Subtraction: " +mC.subtraction(a, b));
		System.out.println("Multiplication: " + mC.multiplication(a, b));
		System.out.println("Division: " + mC.division(a, b));
		
	}
	
	private static void q16(String s) {
		System.out.println(questions.get(15));
		StringSize.print(s);
	}
	
	private static void q17() {
		System.out.println(questions.get(16));
		Interest.input();
	}
	
	private static void q18() {
		System.out.println(questions.get(17));
		CustomExtended cE = new CustomExtended();
		System.out.println(cE.checkUpper("dellllo"));
		System.out.println(cE.toUpper("no these are lower cases"));
		cE.intPlus10("11");
		
	}
	
	private static void q19() {
		System.out.println(questions.get(18));
		ArrayManipulation.initArr();
		System.out.print("Array Initalized: ");
		ArrayManipulation.print();
		System.out.println("Odd Sum: " + ArrayManipulation.evenSum());
		System.out.println("Odd Sum: " + ArrayManipulation.oddSum());
		ArrayManipulation.removePrime();
		System.out.print("Removed Primes: ");
		ArrayManipulation.print();
		
	}
	
	private static void q20() {
		System.out.println(questions.get(19));
		ReadFile.readFromData();
		ReadFile.printInFormat();
	}
	
	
	//runs all classes
	//*Q16 requires commandline args
	//	by default commented out q16 so it will compile w/o commandline args
	//*Q17 requires user input
	//	by default commented out q16 so it will run all methods w/o stopping to ask for input
	//**UNCOMMENT THEM TO TEST THE CLASSES**
	public static void main(String[] args) {
		loadQuetions();
		q1();
		printSep();
		q2();
		printSep();
		q3();
		printSep();
		q4();
		printSep();
		q5();
		printSep();
		q6();
		printSep();
		q7();
		printSep();
		q8();
		printSep();
		q9();
		printSep();
		q10();
		printSep();
		q11();
		printSep();
		q12();
		printSep();
		q13();
		printSep();
		q14();
		printSep();
		q15();
		printSep();
//		q16(args[0]);
		printSep();
//		q17();
		printSep();
		q18();
		printSep();
		q19();
		printSep();
		q20();
		printSep();
	}

}
