package com.revature.corejava;

import java.util.ArrayList;
import java.util.Arrays;

public class Question7 {
	
	public static void compareEmployees() {
		Employee employee1 = new Employee("Zahara", 24, "IT");
		Employee employee2 = new Employee("Sarah", 97, "Geriatrics");
		ArrayList<Employee> employeeArrayList = new ArrayList<Employee>();
		
		EmployeeNameSort nameSort = new EmployeeNameSort();
		EmployeeAgeSort ageSort = new EmployeeAgeSort();
		EmployeeDepartmentSort departmentSort = new EmployeeDepartmentSort();
		
		employeeArrayList.add(employee1);
		employeeArrayList.add(employee2);
		
		System.out.println("Created 2 Employee objects");
		System.out.println(employee1.toString());
		System.out.println(employee2.toString());

		System.out.println("Sorting by name:");
		employeeArrayList.sort(nameSort);
		System.out.println(Arrays.toString(employeeArrayList.toArray()));
		System.out.println("Sorting by age:");
		employeeArrayList.sort(ageSort);
		System.out.println(Arrays.toString(employeeArrayList.toArray()));
		System.out.println("Sorting by department:");
		employeeArrayList.sort(departmentSort);
		System.out.println(Arrays.toString(employeeArrayList.toArray()));
	}
	
}
