package com.revature.work;

	// filler class Employee with just the parameters listed
public class Q7Employee {
	String name, department;
	int age;
	
	public Q7Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Q7Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
}
