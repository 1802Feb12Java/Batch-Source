package com.revature.beans;

import javafx.util.Pair;

public class User{
	
	int userID;
	int accessLevel;
	Pair<String, String> name;
	String address;
	String phone;
	
	//Default Constructor
	public User() {
		this.userID = 0;
		this.accessLevel=0;
		this.name = new Pair<String, String> ("","");
		this.address = "";
		this.phone = "";
	}
	
	//Copy Constructor
	public User(User u) {
		this.userID = u.getUserID();
		this.accessLevel = u.getAccessLevel();
		this.name = u.getName();
		this.address = u.getAddress();
		this.phone = u.getPhone();
	}
	
	//all param Constructor
	public User(int u_id, int lvl, String f_name, String l_name, String addr, String phone) {
		this.userID = u_id;
		this.accessLevel = lvl;
		this.name = new Pair<String,String> (f_name,l_name);
		this.address = addr;
		this.phone = phone;
	}

	//Getters & Setters
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	public Pair<String, String> getName() {
		return name;
	}
	public void setName(Pair<String, String> name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//To String override
	@Override
	public String toString() {
		return "User: "+ userID + "\nName: " + name.getKey() + " " + name.getValue() + "\nAddress: " + address + "\nPhone: " + phone;
	}

	
}
