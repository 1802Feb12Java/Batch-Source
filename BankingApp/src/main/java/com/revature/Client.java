package com.revature;

import javafx.util.Pair;
import java.util.ArrayList;

/* 
 * Client Class
 * Members:
 * 		inherit from Bank Personnel
 * 		Account account
 * Methods:
 * 		Withdraw
 * 		Deposit
 * 		Transfer
*/
public final class Client extends BankPersonnel{
	
	private static final long serialVersionUID = -2373966132209379408L;
	//List of accounts owned by Client
	private ArrayList<Account> accountList;
	
	//Default Constructor 
	public Client() {
		super();
		//default list of size 1
		this.accountList = new ArrayList<Account>(1);
	}
	
	//Constructor w/o Accounts
	public Client(Pair<String,String> name, String addr, String phone, String username, String password) {
		super(name,addr,phone,username,password);
	}

	
	//Constructor w/ a list of Accounts
	public Client(Pair<String,String> name, String addr, String phone, String username, String password, ArrayList<Account> accList) {
		super(name,addr,phone,username,password);
		this.accountList = new ArrayList<Account>(accList);
	}
	
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
	//Setter for AccountList
	public void setAccount(ArrayList<Account> accList) {
		this.accountList = accList;
	}

	//override toString method for readablity
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer(super.toString());
		buff.append("Accounts:\n");
		if(this.accountList!=null) {
			for(Account a: this.accountList) {
				buff.append("\t" + a.getAccountNumber() + 
							": $" + a.getBalance() + "\n");
			}
		}
		return buff.toString();
	}
	
	/*
	 * Withdraw from Client's account 
	 * return true when withdrawn successfully
	 * return false otherwise
	*/
	public boolean withdraw(double amount, String accNum, Client client) {
		//look for client's account
		ArrayList<Account> clientAccounts = client.getAccountList();
		for(Account a: clientAccounts) {
			if(a.getAccountNumber().equals(accNum)) {
				//withdraw amount
				return a.withdraw(amount);
			}
		}
		return false;//account num mismatch
	}
	
	/*
	 * Deposit to Client's account
	 * return true when deposit successful
	 * return false otherwise
	*/
	public boolean deposit(double amount, String accNum, Client client) {
		//look for client's account
		ArrayList<Account> clientAccounts = client.getAccountList();
		for(Account a: clientAccounts) {
			if(a.getAccountNumber().equals(accNum)) {
				//deposit amount
				return a.deposit(amount);
			}
		}
		return false;
	}
	
	public boolean transfer(double amount, String fromAcc, Client c, String toAcc) {
		if(fromAcc == toAcc) {
			return false; //cannot transfer to same accuont
		}
		
		Account fromA = null;
		Account toA = null;
		for(Account a: c.getAccountList()) {
			if(a.getAccountNumber().equals(fromAcc)) {
				fromA = a;
			}
		}
		for(Account a: c.getAccountList()) {
			if(a.getAccountNumber().equals(toAcc)) {
				toA = a;
			}
		}
		if(fromA != null && toA != null) {
			c.withdraw(amount, fromA.getAccountNumber(), c);
			c.deposit(amount, toA.getAccountNumber(), c);
			return true;
		}
		return false;
	}

	/*
	 * Transfer to another Client's accounts
	 * return true when successful
	 * return false otherwise
	*/
	public boolean transfer(double amount, String fromAcc, Client fromClient, String toAcc, Client toClient) {
		if(fromAcc == toAcc) {
			return false; //cannot transfer to same accuont
		}
		
		for(Account a: fromClient.getAccountList()) {
			if(a.getAccountNumber().equals(fromAcc)) {
				for(Account b: toClient.getAccountList()) {
					if(b.getAccountNumber().equals(toAcc)) {
						if(a.withdraw(amount)) {
							//return true after modify acc balance
							return b.deposit(amount);
						}
						return false;//withdraw from fromAcc failed
					}
				}
				return false;//toAcc does not exist
			}
		}
		return false; //fromAcc does not exist
	}

	
	/* TODO openAccount()
	 * Apply for a new Account
	 */
	
	/* TODO openJoint()
	 * Apply for a Joint Account
	 * return true when successful
	 * return false otherwise
	*/
}
