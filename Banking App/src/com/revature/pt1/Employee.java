package com.revature.pt1;

import java.util.ArrayList;

public class Employee {
	
	private int EmpId;

	public int getEmpId() {
		return EmpId;
	}

	public void setEmpId(int empId) {
		EmpId = empId;
	}

	public Employee(int empId) {
		super();
		EmpId = empId;
	}

	@Override
	public String toString() {
		return "Employee [EmpId=" + EmpId + "]";
	}
	
	// see all accounts
	public void oversee(ArrayList<Customers> allA, ArrayList<Applier> allB ) {
		for (int x = 0; x < allA.size(); x++) {
			System.out.println(allA.get(x));
		}
		for (int x = 0; x < allB.size(); x++) {
			System.out.println(allB.get(x));
		}
	}
	
	// approve applicant
	public void approve(String name,ArrayList<Applier> all, ArrayList<Customers> cus) {
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				cus.add(new Customers(all.get(x).getUsername(),all.get(x).getPassword(),all.get(x).getAccounttype(),all.get(x).getBalance()));
				all.remove(all.get(x));
				System.out.println("You approved "+name+" and now they are a customer");
			}	
		}
	}
	
	// deny applicant
	public void deny(String name,ArrayList<Applier> all,ArrayList<Customers> cus) {
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				all.remove(all.get(x));
				System.out.println("You denied "+name+" and now they are no longer an applicant");
			}			
		}
	}
	
}
