package com.revature;

import java.util.Comparator;
import java.util.Scanner;

public class SortEmp7 implements Comparator<EmployeeFor7>{

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String name = null, depart = null;
		int age = 0;
		
		System.out.println("Details of first Employee: ");			// enter first employee details
		System.out.println("What is his name, first and last? ");
		name = sc.nextLine();
		
		System.out.println("What is his department? ");
		depart = sc.nextLine();
		
		System.out.println("What is his age? ");
		age = sc.nextInt();
		
		EmployeeFor7 emp1 = new EmployeeFor7(name,depart,age);	// create emp1 employee
		
		System.out.println("Details of second Employee: ");			// enter second employee details
		System.out.println("What is his name, first and last? ");
		name = sc.next();
		
		System.out.println("What is his department? ");
		depart = sc.next();
		
		System.out.println("What is his age? ");
		age = sc.nextInt();
		
		EmployeeFor7 emp2 = new EmployeeFor7(name,depart,age);	// create emp2 employee
		
		SortEmp7 temp = new SortEmp7();
		temp.compare(emp1, emp2);
	}

	@Override
	public int compare(EmployeeFor7 emp1, EmployeeFor7 emp2) {	// compares each field of emp1 and emp2
		
		if(emp1.name.compareToIgnoreCase(emp2.name) < 0) {	// emp1 before 2 if name comes later in alphabet
			
			System.out.println("In terms of name, Employee 1 comes before Employee 2");
		}else if(emp1.name == emp2.name){
			
			System.out.println("Employee 1 and 2 have the same name");
		}else {
			
			System.out.println("In terms of name, Employee 2 comes before Employee 1");
		}
		
		if(emp1.department.compareToIgnoreCase(emp2.department) < 0) {	// emp1 before 2 if department comes later in alphabet
			
			System.out.println("In terms of department, Employee 1 comes before Employee 2");
		}else if(emp1.department == emp2.department){
		
			System.out.println("Employee 1 and 2 are in the same department");
		}else {
			
			System.out.println("In terms of department, Employee 2 comes before Employee 1");
		}
		
		if(emp1.age < emp2.age) {	// emp1 before 2 if age comes later sequencially
			
			System.out.println("In terms of age, Employee 1 comes before Employee 2");
		}else if(emp1.age == emp2.age){
		
			System.out.println("Employee 1 and 2 are the same age");
		}else {

			System.out.println("In terms of age, Employee 2 comes before Employee 1");
		}
		
		return 0;	// I am using this return value as a boolean, 1 for true and 0 for false
	}
}
