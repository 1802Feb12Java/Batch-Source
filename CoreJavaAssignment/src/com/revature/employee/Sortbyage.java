package com.revature.employee;

import java.util.Comparator;

//defines the sortbyage compare method
public class Sortbyage implements Comparator<Employee> {
	
	public int compare(Employee a, Employee b) {
		return a.age - b.age;
	}
	
}