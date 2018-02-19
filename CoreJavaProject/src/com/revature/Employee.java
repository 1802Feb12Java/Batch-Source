package com.revature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Employee {
	
	public static void example() {
		//create employees, and employees ArrayList
		Employee emp1 = new Employee("Zylander", "Sales", 30);
		Employee emp2 = new Employee("Gunther", "Managment", 45);
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		//add employees to arraylist
		employees.add(emp1);
		employees.add(emp2);
		System.out.println("PreSort: " );
		
		for(Employee e: employees) {
			System.out.println(e.toString());
		}
		
		//sort the collection by name
		Collections.sort(employees, new SortEmployee());
		
		System.out.println("After Sort: " );
		
		for(Employee e: employees) {
			System.out.println(e.toString());
		}
		
		
		//compare(emp1, emp2);
		
		
	}

	String name;
	String department;
	int age;
	
	public Employee(){
		name = "";
		department = "";
		int age = 0;
	}
	
	public Employee(String name, String department, int age) {
		this.name = name;
		this.department = department;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
	
}

