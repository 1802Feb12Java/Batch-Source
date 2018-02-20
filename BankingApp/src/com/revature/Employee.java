package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;

import com.revature.beans.Customer;

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
	
	public void approveAccount() {
		
	}
	
	public void denyAccount() {
		
	}

	
}
