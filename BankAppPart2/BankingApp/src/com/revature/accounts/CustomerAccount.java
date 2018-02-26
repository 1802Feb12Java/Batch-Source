package com.revature.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.dao.CustomerAccountDAOImp;
import com.revature.utility.ConnectionFactory;

public class CustomerAccount extends Account{
	
	//CustomerAccounts identifier, and PrivilegeLevel
	private static final byte PRIVILEGE_LEVEL = 0;
	
	private int customerID;
	private int age;
	private double totalBalance;
	private double checkingBalance;
	private double savingsBalance;
	private int accountNumber;
	private boolean active;

	
	public CustomerAccount() {
		
	}
	
	//this is the constructor to use when customer creates an account
	public CustomerAccount(String userName, String password, int age) {
		super.setUsername("Username");
		super.setPassword("Passwords");
		customerID = 1000;
		this.age = age;
		this.active = false;
		accountNumber = 1;
		totalBalance = 0;
		savingsBalance = 0;
		checkingBalance = 0;
	}
	
	public CustomerAccount(String firstName, String lastName, int age, boolean active) {
		super.setFirstName(firstName);
		super.setLastName(lastName);
		customerID = 1000;
		this.age = age;
		this.active = active;
		this.active = false;
		accountNumber = 1;
		totalBalance = 0;
		savingsBalance = 0;
		checkingBalance = 0;
	}

	@Override
	public String toString() {
		return "CustomerAccount [UserName =" + super.getUsername() +  " Account Number =" + accountNumber + " Name =" + super.getFirstName() 
				+ " " + super.getLastName() + " age=" + age + ", totalBalance=" 
				+ totalBalance + ", checkingBalance=" + checkingBalance
				+ ", savingsBalance=" + savingsBalance + ", accountNumber=" + accountNumber + ", active=" + active
				+ "]";
	}

	public int getAge() {
		return age;
	}

	public boolean setAge(int age) {
		
		if(age < 18) {
			System.out.println("Sorry, you must be eighteen or older to register!");
			return false;
		}
		else {
			this.age = age;
			return true;
		}
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public static byte getPrivilegeLevel() {
		return PRIVILEGE_LEVEL;
	}

	@Override
	public byte getPriorityLevel() {
		// TODO Auto-generated method stub
		return PRIVILEGE_LEVEL;
	}
	
	public double getTotalBalance() {
		return totalBalance;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}
	
	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		CustomerAccountDAOImp customerDAO = new CustomerAccountDAOImp();
		customerDAO.updateCustomer(this);
	}

	//functional functions
	
	//deposit money into checking
	public void depositChecking(double deposit) {
		this.checkingBalance += deposit;
		this.totalBalance += deposit;
		
		CustomerAccountDAOImp customerDAO = new CustomerAccountDAOImp();
		customerDAO.updateCustomer(this);
	}
	
	//withdraw money from checking
	public boolean withdrawChecking(double withdraw) {
		if(withdraw > checkingBalance) {
			return false;
		}
		else {
			this.checkingBalance -= withdraw;
			this.totalBalance -= withdraw;
			
			CustomerAccountDAOImp customerDAO = new CustomerAccountDAOImp();
			customerDAO.updateCustomer(this);
			
			return true;
		}
	}
	
	//deposit money into savings
	public void depositSavings(double deposit) {
		this.savingsBalance += deposit;
		this.totalBalance += deposit;
		
		CustomerAccountDAOImp customerDAO = new CustomerAccountDAOImp();
		customerDAO.updateCustomer(this);
	}
	
	//withdraw money from savings
	public boolean withdrawSavings(double withdraw) {
		if(withdraw > savingsBalance) {
			return false;
		}
		else {
			this.savingsBalance -= withdraw;
			this.totalBalance -= withdraw;
			
			CustomerAccountDAOImp customerDAO = new CustomerAccountDAOImp();
			customerDAO.updateCustomer(this);
			
			return true;
		}
	}

	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
}
