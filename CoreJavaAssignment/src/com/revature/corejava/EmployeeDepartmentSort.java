package com.revature.corejava;

import java.util.Comparator;

public class EmployeeDepartmentSort implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.department.compareTo(e2.department);
	}

}
