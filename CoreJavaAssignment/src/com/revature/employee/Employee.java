package com.revature.employee;

//import java.util.Comparator;

//Q7 Sort two employees based on their name, department, and age using the Comparator interface.

public class Employee {
	
	String name;
	String department;
	int age;
	
	public Employee() {
		name = "";
		department = "";
		age = 0;
	}
	
	public Employee(String n, String d, int a) {
		this.name = n;
		this.department = d;
		this.age = a;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
	
}

