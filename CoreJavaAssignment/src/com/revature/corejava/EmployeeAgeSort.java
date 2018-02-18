package com.revature.corejava;

import java.util.Comparator;

public class EmployeeAgeSort implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		if (e1.age > e2.age) {
			return 1;
		}
		if (e1.age < e2.age) {
			return -1;
		}
		else 
			return 0;
	}

}
