package com.revature.bankingapp1;
/*
 * Need to make sure that account numbers are unique
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Account implements Serializable{
	
	private static final long serialVersionUID = -868268109759706191L;
	private final long accountNumber;
	private ArrayList<Customer> customerList;
	private String accountType;//checking, savings, joing checking, joint savings
	private String accountStatus;//active, closed
	private double balance;
	
	public Account(ArrayList<Customer> customerList, String accountType) {
		this.customerList = customerList;
		this.accountType = accountType;
		this.accountStatus = "Open";
		this.accountNumber = randomIDGenerator();
	}

	public Account(ArrayList<Customer> customerList, String accountType, double startingBalance) {
		this.customerList = customerList;
		this.accountType = accountType;
		this.balance = startingBalance;
		this.accountStatus = "Open";
		this.accountNumber = randomIDGenerator();
		
	}
	
	public static Integer randomIDGenerator() {
		
		Random rand = new Random();
		int n = rand.nextInt(10000);
		n = n + 10000000;
		ArrayList<Integer> accountNumLog = FileKeeping.readInAccountNumberLog();
		for(Integer i : accountNumLog) {
			if(n == i) {
				n = randomIDGenerator();
			}
		}
		accountNumLog.add(n);
		FileKeeping.writeToAccountNumberFile(accountNumLog);
		return n;
	}//end randomIDGenerator method

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double amount) {
		this.balance = amount;
	}

	public String customerListToString(ArrayList<Customer> customerList) {
		String toReturn = "";
		for(Customer c : customerList) {
			toReturn = toReturn.concat(c.getFirstName() + " " + c.getLastName());
			toReturn = toReturn.concat("\n");
		}
		return toReturn;
	}//end customerListToString method
	
	@Override
	public String toString() {
		return "Account Number: " + accountNumber + "\nAccount Type: " + accountType + "\nAuthorized Users: " 
				+ customerListToString(customerList)  + "\nAccount Status: " + accountStatus + "\nBalance: $" + balance ;
	}
	
	
	
	
}//end class
