package com.revature;

import java.io.Serializable;
import javafx.util.Pair;


/*
 * Super class to be extend from
 * All methods in the project can interact with all users
 * SubClasses:	
 * 		Client
 * 		Employee
 * 			Admin
 * Contains personal information:
 * 		Pair<String,String> Name<Last,First>
 * 			(Key = Last name, Value = First)
 * 		String Address
 * 		Phone Number
*/
public abstract class BankPersonnel implements Serializable{

	private static final long serialVersionUID = -9165449174413835361L;
	Pair<String, String> name;
	String address;
	String phone;
	String username;
	String password;
	
	public BankPersonnel() {
		this.name = new Pair<String, String>("Last","First");
		this.address = "123 Default Drive, City, State, Zip";
		this.phone = "(000) 000-0000";
	}
	
	public BankPersonnel(Pair<String,String> name, String addr, String phone, String username, String password) {
		this.name = name;
		this.address = addr;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
	//Getters
	public Pair<String,String> getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	//Setters
	public void setName(String first, String last) {
		this.name = new Pair<String,String> (last, first);
	}

	public void setAddress(String addr) {
		this.address = addr;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Name: " + name.getValue() + ", " + name.getKey() + "\nAddress: " + address + "\nPhone: " + phone + "\n";
	}
}
