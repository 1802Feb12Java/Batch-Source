package com.revature.beans;

import java.util.ArrayList;

public class Employee {
	
	private int employeeID;
	
	public void checkID(int id) {
		while(id != employeeID) {
			System.out.println("Invalid ID. Please try again.");
		}
	}
	
	public Employee(int employeeID) {
		super();
		this.employeeID = employeeID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public void viewCustInfo(ArrayList<Customer> a) {
		for(Customer c : a) {
			System.out.println(c.toString());
		}
	}

	
}
