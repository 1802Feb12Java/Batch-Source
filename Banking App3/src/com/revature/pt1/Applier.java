package com.revature.pt1;

public class Applier{

	private Integer id;
	private String username, password;	// Login info
	private double balance; 			
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
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
	
	public Applier(Integer id, String username, String password, double balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Applier [id=" + id + ", username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}
	
}
