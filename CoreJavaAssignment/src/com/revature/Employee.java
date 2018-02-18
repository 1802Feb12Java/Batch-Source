package com.revature;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Employee implements Comparator<Employee>{
	/*
	 * The purpose of this class is to define Employee objects.
	 * There is a sorting method that allows for two Employee
	 * objects to be sorted in an Employee ArrayList.
	 */

	public static void main(String[] args) {
		
		runEmployee();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main

	 private String name;
	 private String department;
	 private int age;
	 
	 	 
	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}//end constructor
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public int compare(Employee employeeOne, Employee employeeTwo) {
		//comparing names alphabetically, ignoring case to avoid user input error from
		//messing with the output
		//if names are the same, then departments are compared alphabetically
		//if departments are the same, then ages are compared
		int result1 = employeeOne.name.compareToIgnoreCase(employeeTwo.name);
		int result2 = employeeOne.department.compareToIgnoreCase(employeeTwo.department);
		Boolean result3 = employeeOne.age <= employeeTwo.age;
		if(result1 != 0) {
			return result1;
		} else if (result2 != 0) {
			return result2;
		} else if (result3) {
			return -1;
		} else {
			return 1;
		}
		
	}//end compare method
	
	public ArrayList<Employee> sortEmployees(Employee employeeTwo) {
		ArrayList<Employee> sortedList = new ArrayList<Employee>();
		int result = compare(this, employeeTwo);
		if(result < 0) {
			sortedList.add(this);
			sortedList.add(employeeTwo);
		} else {
			sortedList.add(employeeTwo);
			sortedList.add(this);
		}
		return sortedList;
	}//end sortByName method
	 
	@Override
	public String toString() {
		return "Employee name: " + name + "\nDepartment: " + department + "\nAge: " + age;
	}

	public static void runEmployee() {
		Scanner read = new Scanner(System.in);
		System.out.print("Please enter the name of the first employee: ");
		String name1 = read.nextLine();
		System.out.print("Please enter the department of the first employee: ");
		String dept1 = read.nextLine();
		System.out.print("Please enter the age of the first employee: ");
		int age1 = read.nextInt();
		read.nextLine();
		System.out.println();
		
		System.out.print("Please enter the name of the second employee: ");
		String name2 = read.nextLine();
		System.out.print("Please enter the department of the second employee: ");
		String dept2 = read.nextLine();
		System.out.print("Please enter the age of the second employee: ");
		int age2 = read.nextInt();
		System.out.println();
		System.out.println();
		
		Employee firstEmp = new Employee(name1, dept1, age1);
		Employee secondEmp = new Employee(name2, dept2, age2);
		
		
		ArrayList<Employee> sortedList = firstEmp.sortEmployees(secondEmp);
		//the for loop is to print out the employee objects using their toString methods
		for(Employee e : sortedList) {
			System.out.println(e.toString());
			System.out.println();
		}//end for loop
		
	}//end runEmployee method
	 
	
}//end class
