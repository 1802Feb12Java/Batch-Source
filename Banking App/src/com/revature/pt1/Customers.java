package com.revature.pt1;

import java.io.Serializable;
import java.util.ArrayList;

public class Customers implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username, password;	// Login info
	private String accounttype;			// personal, joint
	private String joint;				// username of other joint user
	private double balance;				// account balance
	
	public String getJoint() {
		return joint;
	}
	public Customers(String username, String password, String accounttype, String joint, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.accounttype = accounttype;
		this.joint = joint;
		this.balance = balance;
	}
	public Customers(String username, String password, String accounttype, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.accounttype = accounttype;
		this.balance = balance;
	}
	public void setJoint(String joint) {
		this.joint = joint;
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
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Customers [username=" + username + ", password=" + password + ", accounttype=" + accounttype
				+ ", joint=" + joint + ", balance=" + balance + "]";
	}
	
	// checks if username and matching password exists
	public boolean checkUser(String name, String password, ArrayList<Customers> all) {
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name) && all.get(x).getPassword().equals(password)) {
				return true;
			} 
		}
		return false;
	}

	public void deposit(double amount, String name, ArrayList<Customers> all) {
		double bal = 0;		// user bank balance
		int index = 0;		// index of where the customer is
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				index = x;
			} 
		}
		if (amount > 0 ) {
		bal = all.get(index).getBalance();
		all.get(index).setBalance(bal += amount);
		System.out.println(all.get(index).getUsername()+" deposited $"+amount+" and now has total of $"+bal);
		} else {
			System.out.println("You cant deposit negative. Try withdrawing");
		}
	}
	
	public void withdraw(double amount, String name, ArrayList<Customers> all) {
		double bal = 0;		// user bank balance 
		int index = 0;		// index of where the customer is
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				index = x;
			} 
		}
		if (all.get(index).getBalance() >= amount) {
		bal = all.get(index).getBalance();
		all.get(index).setBalance(bal -= amount);
		System.out.println(all.get(index).getUsername()+" withrew $"+amount+" and now has total of $"+bal);
		} else {
			System.out.println("You cant withdraw more than you have. Try depositing first");
		}
	}
	
	public void transfer(String user, double amount, String target, ArrayList<Customers> all) {
		double bal = 0;		// bank balance of user
		double bal1 = 0;	// bank balance of target
		int index = 0;		// index of where the customer is
		int index1 = 0;		// index of where the target account is
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(user)) {
				index = x;
			} 
		}
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(target)) {
				index1 = x;
			} 
		}
		if (all.get(index).getBalance() > amount) {
			bal = all.get(index).getBalance();
			bal1 = all.get(index1).getBalance();
			all.get(index).setBalance(bal-=amount);
			all.get(index1).setBalance(bal1+= amount);
			System.out.println(user+" transfered $"+amount+ " to "+target+" and now has "+all.get(index).getBalance());
		} else {
			System.out.println("You are too broke to help your friend");
		}
	}
	

}
