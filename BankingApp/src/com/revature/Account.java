package com.revature;

public class Account {
	
	private int id;
	private int is_approved;
	private int is_joint;
	private int custIdOne;
	private int custIdTwo;
	private double accountBalance;
	
	public Account(int id, int is_approved, int is_joint, int custIdOne, int custIdTwo, double accountBalance) {
		this.id = id;
		this.is_approved = is_approved;
		this.is_joint = is_joint;
		this.custIdOne = custIdOne;
		this.custIdTwo = custIdTwo;
		this.accountBalance = accountBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIs_approved() {
		return is_approved;
	}

	public void setIs_approved(int is_approved) {
		this.is_approved = is_approved;
	}
	
	public int getIs_joint() {
		return is_joint;
	}

	public void setIs_joint(int is_joint) {
		this.is_joint = is_joint;
	}

	public int getCustIdOne() {
		return custIdOne;
	}

	public void setCustIdOne(int custIdOne) {
		this.custIdOne = custIdOne;
	}

	public int getCustIdTwo() {
		return custIdTwo;
	}

	public void setCustIdTwo(int custIdTwo) {
		this.custIdTwo = custIdTwo;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", is_approved=" + is_approved + ", is_joint=" + is_joint + ", custIdOne="
				+ custIdOne + ", custIdTwo=" + custIdTwo + ", accountBalance=" + accountBalance + "]";
	}
	
}
