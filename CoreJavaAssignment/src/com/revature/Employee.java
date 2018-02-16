package com.revature;

import java.util.Comparator;

public class Employee implements Comparator<Employee>{

	public static void main(String[] args) {
		
		Employee firstEmp = new Employee("Jessica Colson", "Software Engineering", 21);
		Employee secondEmp = new Employee("Nick Strocchia", "Systems Administration", 23);
		
		//sortByName(firstEmp, secondEmp);
		
		
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
		int result = employeeOne.name.compareToIgnoreCase(employeeTwo.name);
		return result;
	}//end compare method
	
	public void sortByName(Employee employeeOne, Employee employeeTwo) {
		int result = compare(employeeOne, employeeTwo);
		if(result < 0) {
			System.out.println(employeeOne.toString());
			System.out.println(employeeTwo.toString());
		} else {
			System.out.println(employeeTwo.toString());
			System.out.println(employeeOne.toString());
		}
	}//end sortByName method
	 
	@Override
	public String toString() {
		return "Employee name: " + name + ", department: " + department + ", age: " + age;
	}

	 
	
}//end class
