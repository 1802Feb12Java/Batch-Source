package com.revature.corejavaassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Q7 {
/*
 * Q7. Sort two employees based on their name, department, and age using the Comparator interface.
 */
	/*
	 * This method creates 2 employee objects and sorts them using the List.sort method
	 * that uses the sortEmployee class which implements the Comparator interface
	 */
	public static void answer() {
		System.out.println("Q7.\tSort two employees based on their name, department, and age using the Comparator interface.\n");
		Employee a = new Employee("Jack Bower", "Die Slow", 55);
		System.out.println("Employee 1: " + a);
		Employee b = new Employee("Nacho Libre", "Donde Esta", 32);
		System.out.println("Employee 2: " + b);
		List<Employee> list = new ArrayList<Employee>();
		list.add(a);
		list.add(b);
		list.sort(new sortEmployee());
		System.out.println(list.toString() + "\n");
		sortEmployee s= new sortEmployee();
		int c=s.compare(a, b);
		System.out.println(c);
	}
	
}
class Employee{
	String name, department;
	int age;
	public Employee() {		
		this("Tim Henk", "movie store", 77);
	}
	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}
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
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
}
class sortEmployee implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		int nameCompare = o1.getName().compareTo(o2.getName());
		if (o1.getName().compareTo(o2.getName())==0) {
			int deptCompare = o1.getDepartment().compareTo(o2.getDepartment());
			if (deptCompare==0) {
				return o1.getAge() - o2.getAge();
			}else {
				return deptCompare;
			}
		}else {
			return nameCompare;
		}
	}
	
}