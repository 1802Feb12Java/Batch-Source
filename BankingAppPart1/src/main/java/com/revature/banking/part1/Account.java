package com.revature.banking.part1;

import java.io.Serializable;

public class Account implements Serializable{
	
	private static final long serialVersionUID = 99L;
	
	private final int AccountNumber;
	private double balance;
	private String status;
	private Customer primaryCustomer, secondaryCustomer;
	private boolean open = true;
	


	public Account(int accountNumber, Customer primary, String status) {
		this(accountNumber, primary, null, status);
	}
	
	public Account(int accountNumber, Customer primaryCustomer, Customer secondaryCustomer, String status) {
		super();
		AccountNumber = accountNumber;
		this.primaryCustomer = primaryCustomer;
		this.secondaryCustomer = secondaryCustomer;
		this.status=status;
	}
	public boolean isOpen() {
		return open;
	}
	public int getAccountNumber() {
		return AccountNumber;
	}
	public double getBalance() {
		return balance;
	}

	public void withdraw(double amount) {
		this.balance -= amount;
	}
	public void deposit(double amount) {
		this.balance += amount;
	}
	public Customer getPrimaryCustomer() {
		return primaryCustomer;
	}

	public Customer getSecondaryCustomer() {
		return secondaryCustomer;
	}

	public String getStatus() {
		return status;
	}

	public void cancel(Admin admin) {
		this.status = "canceled by " + admin.getID();
	}
	public void close(Customer customer) {
		this.status = "closed by " + customer.getName();
	}
	@Override
	public String toString() {
		if (secondaryCustomer != null)
			return "Account \n[\tAccountNumber=" + AccountNumber +", \n\tbalance=" + balance + ", \n\tstatus=" + status +
				", \n\tprimary Customer=" + primaryCustomer.getID()
				+ ", \n\tsecondary Customer=" + secondaryCustomer.getID() +  "\n]";
		else
			return "Account \n[\tAccountNumber=" + AccountNumber +", \n\tbalance=" + balance + ", \n\tstatus=" + status +
					", \n\tprimary Customer=" + primaryCustomer.getID()
					+   "\n]";
	}
	
}
