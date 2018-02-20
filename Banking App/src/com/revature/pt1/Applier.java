package com.revature.pt1;

import java.io.Serializable;

public class Applier implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private String username, password;	// Login info
	private String accounttype;			// personal, joint
	private String joint;
	private double balance; 			
	
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
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getJoint() {
		return joint;
	}
	public void setJoint(String joint) {
		this.joint = joint;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Applier(String username, String password, String accounttype, String joint, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.accounttype = accounttype;
		this.joint = joint;
		this.balance = balance;
	}
	public Applier(String username, String password, String accounttype, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.accounttype = accounttype;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Applier [username=" + username + ", password=" + password + ", accounttype=" + accounttype + ", joint="
				+ joint + ", balance=" + balance + "]";
	}

}
