package com.revature.beans;

import java.util.ArrayList;

public class Administrator {

	private String master;

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public Administrator(String master) {
		super();
		this.master = master;
	}

	public void viewCustInfo(ArrayList<Customer> a) {
		for(Customer c : a) {
			System.out.println(c.toString());
		}
	}
	
	
}
