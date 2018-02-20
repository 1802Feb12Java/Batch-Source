package com.revature;

import java.io.Serializable;

public class Customers implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerUsername;
	private String customerPassword;
	private String phone;
	private String address;
	private String name;
	
	private double balance;
	public boolean deposit(double amount)
	{
		if(amount <= 0)
		{
			System.out.println("Invalid Amount!");
			return false;
		}
		else
		{
			balance = this.getBalance() + amount;
			System.out.println("Deposit Success");
			System.out.println("New Balance: " + this.getBalance());
			return true;

		}
	}
	public boolean withdraw(double amount)
	{
		if(amount >= this.getBalance())
		{
			System.out.println("You don't have enough money in your account!");
			return false;
		}
		else
		{
			balance = this.getBalance() - amount;
			System.out.println("Withdraw Success");
			System.out.println("New Balance: " + this.getBalance());
			return true;
		}
	}
	public Customers(String customerUsername, String customerPassword, String phone, String address, String name,
			double balance) {
		super();
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.phone = phone;
		this.address = address;
		this.name = name;
		this.balance = balance;
	}
	public Customers(String customerUsername, String customerPassword, String phone, String address, String name) {
		super();
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.phone = phone;
		this.address = address;
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean transfer(double amount)
	{
		if(amount < 0 || amount > getBalance())
		{
			System.out.println("Invalid Amount!");
			return false;
			
		}
		else
		{
			this.balance = this.getBalance() - amount;
			System.out.println("Transfer Success");
			System.out.println("New Balance: " + this.getBalance());
			return true;
		}
	}
	public Customers(String customerUsername, String customerPassword, double balance) {
		super();
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.balance = balance;
	}
	public Customers()
	{
		this.customerPassword = "";
		this.customerUsername = "";
		this.balance = 0.0;
	}
	@Override
	public String toString() {
		return "Customers [customerUsername=" + customerUsername + ", customerPassword=" + customerPassword + ", phone="
				+ phone + ", address=" + address + ", name=" + name + ", balance=" + balance + "]";
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Customers(String customerUsername)
	{
		this(customerUsername, "");
	}
	public Customers(String customerUsername, String customerPassword) {		
	
		this(customerUsername, customerPassword, 0.0);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
