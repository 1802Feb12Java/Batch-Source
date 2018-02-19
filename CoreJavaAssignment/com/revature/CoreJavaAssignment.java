package com.revature;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class CoreJavaAssignment {

	public static void main(String[] args)
	{
			Scanner scan = new Scanner(System.in); //create a scanner object to be used throughout our class

			BubbleSort bs = new BubbleSort();
			System.out.println(bs.bubbleSort(new int[] {1,0,5,6,3,2,3,7,9,8,4}));
			
			System.out.println();
			
			Fibonacci fibo = new Fibonacci();
			System.out.println(fibo.calculateFibonacci(25));
			
			System.out.println();
			
			ReverseString rs = new ReverseString();
			System.out.println(rs.stringReverse("RevatureSpeltBackwards"));
			
			System.out.println();
			
			Factorial F = new Factorial();
			int number;
			do {
			    System.out.println("Input an integer to get a factorial of, >1: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    number = scan.nextInt();
			} while (number <= 0);
			
			System.out.println(F.factorial(number));
			scan.nextLine(); //clear the scanner object
	// StringIndex		
			String searchString = "";
			do {
			    System.out.println("Input a string that you'll print out a section of afterwards (at least 4 characters)");
			    searchString = scan.next();
			} while (searchString.length() < 4);		
			scan.nextLine();
			
			int stringIndex = 0;
			do {
			    System.out.println("Input an integer for a string index, >0, < length: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    stringIndex = scan.nextInt();
			} while (stringIndex < 1 || stringIndex > searchString.length() - 2);
			scan.nextLine();
			
			StringIndex si = new StringIndex();
			System.out.println(si.subString(searchString, stringIndex));
			
	//// Even check
			int num = 0;
			do {
			    System.out.println("Input an integer to check if it's even: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    num = scan.nextInt();
			} while (num <= 0);			
			scan.nextLine();
			
			IsEven ie = new IsEven();
			System.out.println(ie.evenCheck(num));
	//
	
	//comparator interface check////////////////////////////////
			
			Employee emp1 = new Employee("John", "Astroturfing", 27);
			Employee emp2 = new Employee("Broseph", "Computers", 23);
			Employee emp3 = new Employee("Jubaliah", "Pencil Pushing", 31);
			
			ArrayList<Employee> list = new ArrayList<Employee>();
			
			list.add(emp1);
			list.add(emp2);
			list.add(emp3);
			
			// non-sorted
			System.out.println(" Unsorted List ");
			for(Employee emp: list)
				System.out.println(emp);
			System.out.println();
			
			// name sort
			Collections.sort(list, new EmployeeNameCompare());
			
			System.out.println(" Sorted By Name");
			for(Employee emp: list)
				System.out.println(emp);
			System.out.println();
			
			// department sort
			Collections.sort(list, new EmployeeDepartmentCompare());
			System.out.println(" Sorted by Department");
			for(Employee emp: list)
				System.out.println(emp);
			System.out.println();
			
			
			// age sort
			Collections.sort(list, new EmployeeAgeCompare());
			System.out.println(" Sorted by Age");
			for(Employee emp: list)
				System.out.println(emp);			
	////////////////////////////////////////////////////////////
	
			ArrayList<String> list2 = new ArrayList<String>() //defined with anonymous inner class
			{
				private static final long serialVersionUID = 1L;

			{
			    add("karan");
			    add("madam");
			    add("tom");
			    add("civic");
			    add("radar");
			    add("jimmy");
			    add("kayak");
			    add("john");
			    add("refer");
			    add("billy");
			    add("did");
			}};
			
			Palindrome P = new Palindrome();
			P.checkArrayList(list2);
			P.printArrayL();

			System.out.println();
			
			PrimeNumbers pn = new PrimeNumbers();
			pn.printPrimeNumbers();
			
			System.out.println();
			
//ternary operator
			int intA;
			do {
			    System.out.println("Input integer A to compare: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    intA = scan.nextInt();
			} while (intA <= 0);
	
			scan.nextLine();
	
			int intB;
			do {
			    System.out.println("Input integer B to compare: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    intB = scan.nextInt();
			} while (intB <= 0 || intA == intB);
			
			scan.nextLine();
			
			Ternary ternary = new Ternary();
			System.out.println(ternary.compareValues(intA, intB) + " is the smaller value");
			
			@SuppressWarnings("unused")
			AccessFloat fl = new AccessFloat();
			
			@SuppressWarnings("unused")
			EvenArray ea = new EvenArray();
			
			@SuppressWarnings("unused")
			Triangle01 t01 = new Triangle01();
			
			DemonSwitch ds = new DemonSwitch();
			ds.doSomething(1);
			ds.doSomething(2);
			ds.doSomething(3);
			
			final double doubleOne = 20.00;
			final double doubleTwo = 10.00;
			@SuppressWarnings("unused")
			MathClass mc = new MathClass(doubleOne, doubleTwo);
			
			String lengthString = "";
			do {
			    System.out.println("Input a string to view the length of it:");
			    lengthString = scan.next();
			} while (lengthString.length() <= 0);
			
			
			String[] ary = lengthString.split("");
			@SuppressWarnings("unused")
			StringLength sl = new StringLength(ary);
			
			//SimpleInterest
			int principle = 0;
			do {
			    System.out.println("Input a principle value: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    principle = scan.nextInt();
			} while (principle <= 0);
			
			scan.nextLine();
			
			int rate = 0;
			do {
			    System.out.println("Input a rate value: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    rate = scan.nextInt();
			} while (rate <= 0);
			
			scan.nextLine();
			
			int years = 0;
			do {
			    System.out.println("Input a number of years value: ");
			    while (!scan.hasNextInt()) {
			        System.out.println("That's not a number!");
			        scan.next(); // this is important!
			    }
			    years = scan.nextInt();
			} while (years <= 0);
			
			scan.nextLine();
			
			SimpleInterest SI = new SimpleInterest(principle,rate,years);
			System.out.println("Interest: " + SI.getInterest());
			
			InheritClass ic = new InheritClass();
			System.out.println("skfkfkddfAA is uppercase: " + ic.checkUppercase("skfkfkddfAA"));
			System.out.println("abcdef is uppercase: " + ic.checkUppercase("abcdef"));
			System.out.println("Converting hoNEyBadger to all uppercase: " + ic.convertUppercase("hoNEyBadger"));
			System.out.println("Converting 125 as a String to integer and adding 10: " + ic.parseInt("125"));
			
			System.out.println();
			
			ArrayListExample ale = new ArrayListExample();
			System.out.println("Even values of arraylist added: " + ale.displayEven());
			System.out.println("Odd values of arraylist added: " + ale.displayOdd());
			ale.removePrimeNums(); //remove prime numbers and print
			
			@SuppressWarnings("unused")
			DataRetrieve dr = new DataRetrieve();
			
			scan.close();
}

}
