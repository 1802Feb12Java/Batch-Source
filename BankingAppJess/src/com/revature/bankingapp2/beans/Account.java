package com.revature.bankingapp2.beans;

import java.io.Serializable;
import java.util.ArrayList;

import com.revature.bankingapp2.Validation;



public class Account implements Serializable{
	private static final long serialVersionUID = -868268109759706191L;
	private final long accountNumber;
	private ArrayList<CustomerBean> customerList;
	private String accountType;//checking, savings
	private boolean jointAccount; //true - joint, false - not
	private String accountStatus;//active, closed
	private double balance;
	
	public Account(ArrayList<CustomerBean> customerList, String accountType) {
		this.customerList = customerList;
		this.accountType = accountType;
		this.accountStatus = "Active";
		this.accountNumber = Validation.randomIDGenerator();
	}

	public ArrayList<CustomerBean> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<CustomerBean> customerList) {
		this.customerList = customerList;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public boolean isJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(boolean jointAccount) {
		this.jointAccount = jointAccount;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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

	public long getAccountNumber() {
		return accountNumber;
	}
	
	
	
	
}//end class
