package com.revature.employee;

import java.util.Comparator;

//defines the sortbydept compare method
public class Sortbydept implements Comparator<Employee> {
	
	public int compare(Employee a, Employee b) {
		return a.department.compareTo(b.department);
	}
	
}