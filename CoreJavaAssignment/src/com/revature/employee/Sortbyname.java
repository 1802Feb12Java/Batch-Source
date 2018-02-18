package com.revature.employee;

import java.util.Comparator;

//defines the sortbyname compare method
public class Sortbyname implements Comparator<Employee> {
	
	public int compare(Employee a, Employee b) {
		return a.name.compareTo(b.name);
	}
	
}