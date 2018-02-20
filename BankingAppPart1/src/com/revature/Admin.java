package com.revature;

import java.util.ArrayList;

public class Admin extends Employee {	//have all the same access to view as employees, but with additional modification access
	private ArrayList<Account> allAccounts = super.getAllAccounts();
	
	public boolean transferMoney(int accountIDa, int accountIDb, int amount) {
		Account a = null;
		Account b = null;
		for(int i=0; i<allAccounts.size(); i++) {
			if(allAccounts.get(i).getAccountID() == accountIDa)
				a = allAccounts.get(i);
			if(allAccounts.get(i).getAccountID() == accountIDb)
				b = allAccounts.get(i);
		}
		if(a == null || b == null) {
			System.out.println("One or both of these accounts don't exist yet");
			return false;
		}
		
		return a.transfer(b, amount);
	}
	public boolean editAccount(String editType, int accountID, int amount) {
		//handles withdrawing, depositing, and canceling based on editType being "W", "D", or "C" respectively
		Account a = null;
		for(int i=0; i<allAccounts.size(); i++) {
			if(allAccounts.get(i).getAccountID() == accountID) 
				a = allAccounts.get(i);
		}
		if(a == null) {
			System.out.println("This account doesn't exist yet.");
			return false;
		}
		
		switch(editType) {
			case "W":
				return a.withdraw(amount);
			case "D":
				return a.deposit(amount);
			case "C":
				System.out.println("Account " + accountID + " closed.");
				for(Customer c : a.getCustomers()) {
					c.removeAccount(a);
				}
				return allAccounts.remove(a);
		}
		
		return false;
	}

	//past this point is mostly Alt+Shift+S, minor modification in toString and constructors
	public Admin() {
		super();
	}
	public Admin(ArrayList<Account> allAccounts, ArrayList<Customer> allCustomers) {
		super(allAccounts, allCustomers);
		this.allAccounts = allAccounts;
	}
}
