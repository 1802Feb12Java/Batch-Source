package com.revature.beans;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private String name;
	private String address;
	private int SSN;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
		SSN = sSN;
	}
	public Employee(String name, String address, int sSN) {
		super();
		this.name = name;
		this.address = address;
		SSN = sSN;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", address=" + address + ", SSN=" + SSN + "]";
	}

}
