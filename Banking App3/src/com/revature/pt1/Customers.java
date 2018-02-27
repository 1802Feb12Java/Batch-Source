package com.revature.pt1;

public class Customers{

	private Integer id;					// Customer ID
	private String username, password;	// Login info
	private double balance;				// account balance
	
	public Customers(Integer id, String username, String password, double balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Customers [id=" + id + ", username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}

}
