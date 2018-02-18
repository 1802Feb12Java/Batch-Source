package com.revature.assignments;

import java.util.ArrayList;

public class AssignmentSeven {

	public static ArrayList<Employee> generateEmployeeList() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee("Bob Smith", "Computer Science", 45));
		employeeList.add(new Employee("Jane Doe", "Computer Science", 55));
		employeeList.add(new Employee("Abe Lincoln", "Computer Science", 29));
		employeeList.add(new Employee("Wayne Johnson", "Natural Sciences", 34));
		employeeList.add(new Employee("Bob Smith", "Astronomy", 45));
		return employeeList;
	}

}
