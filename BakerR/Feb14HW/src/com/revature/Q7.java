package com.revature;

import java.util.Arrays;



public class Q7 implements Runnable {
	private final Employee[] EMPLOYEE_ARY;
	
	public Q7(Employee[] employees) {
		EMPLOYEE_ARY = employees;
	}
	
	@Override
	public void run() {
		System.out.println("Question 7: Sorting using a Comparator");
		
		System.out.println("Before: ");
		for(Employee e : EMPLOYEE_ARY) {
			System.out.print("    ");
			System.out.println(e.toString());
		}
		System.out.println();
		
		Arrays.sort(EMPLOYEE_ARY, (Employee a, Employee b) -> {
			// Compare by name, department, & age
			if(!a.getName().equals(b.getName())) {
				return a.getName().compareTo(b.getName());
			} else if(a.getDepartment().equals(b.getDepartment())) {
				return a.getDepartment().compareTo(b.getDepartment());
			} else if(a.getAge() > b.getAge()) {
				return 1;
			} else if(a.getAge() < b.getAge()) {
				return -1;
			} else {
				return 0;
			}
		});
		
		
		System.out.println("After: ");
		for(Employee e : EMPLOYEE_ARY) {
			System.out.print("    ");
			System.out.println(e.toString());
		}
		
	}

}
