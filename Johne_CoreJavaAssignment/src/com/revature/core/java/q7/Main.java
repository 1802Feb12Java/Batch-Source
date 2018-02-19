package com.revature.core.java.q7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Employees michael = new Employees("Michael", "Regional Manager", 53);
		Employees dwight = new Employees("Dwight", "Assistant to the Regional Manager", 40);
		
		ArrayList<Employees> employeesList = new ArrayList<Employees>();
		employeesList.add(michael);
		employeesList.add(dwight);
		
		/**
		 * sort in ascending order using the sort() method from the Collections class
		 */
		System.out.println("Sort by Name");
		Collections.sort(employeesList, new EmployeesSortName());
		for(Employees i : employeesList) {
			System.out.println(i);
		}

		System.out.println("Sorted by Department");
		Collections.sort(employeesList, new EmployeesSortDept());
		for(Employees e : employeesList) {
			System.out.println(e);
		}
		
		System.out.println("Sort by Age");
		Collections.sort(employeesList, new EmployeesSortAge());
		for(Employees e : employeesList) {
			System.out.println(e);
		}
	}	//end of main
}
