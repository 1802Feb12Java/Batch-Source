package com.revature;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int customerCount = 0;
	private int customerID;
	private String username;
	private String password;
	private String fullName;
	private ArrayList<Account> accounts = new ArrayList<Account>();

	public void addAccount(Account accountToAdd) {
		accounts.add(accountToAdd);
		accountToAdd.getCustomers().add(this);
	}
	public boolean removeAccount(Account accountToRemove) {
		if(accounts.indexOf(accountToRemove) == -1) {	//if the customer doesn't have the account in its list
			System.out.println("This account does not belong to this customer.");	//not on customer's list of accounts
			return false;
		}
		int index = accounts.indexOf(accountToRemove);
		if(!accounts.get(index).getCustomers().contains(this)) {	//if the account doesn't have this customer in its list
			System.out.println("This customer does not belong to this account.");	//not on account's list of customers
			return false;
		}
		accounts.remove(index);
		int thisIndex = accountToRemove.getCustomers().indexOf(this);
		accountToRemove.getCustomers().remove(thisIndex);
		return true;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFullName() {
		return fullName;
	}
	public int getCustomerID() {
		return customerID;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public Customer() {
		super();
	}
	public Customer(String username, String password, String fullName) {
		super();
		customerCount++;
		this.customerID = customerCount;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		//System.out.println("Customer " + username+"#"+customerID + " created!");	//TODO: Dear god remove, this is disgusting in test cases
	}
	@Override
	public String toString() {
		String str = "User = " + username + "#" + customerID + "\nName = " + fullName + "\nAccount ID's: ";
		for(Account ac : accounts) {
			str += ac.getAccountID() + " ";
		}
		return str;
	}
}
