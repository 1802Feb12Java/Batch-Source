package com.revature.JDBCBank;

import java.util.ArrayList;

public class User {	
	private static int userCount = 0;
	private int userID;
	private String username;
	private String password;
	private String fullName;
	private ArrayList<Account> accounts = new ArrayList<Account>();

	public void addAccount(Account accountToAdd) {
		accounts.add(accountToAdd);
		accountToAdd.getUsers().add(this);
	}
	public boolean removeAccount(Account accountToRemove) {
		if(accounts.indexOf(accountToRemove) == -1) {	//if the user doesn't have the account in its list
			System.err.println("This account does not belong to this user.");	//not on user's list of accounts
			return false;
		}
		int index = accounts.indexOf(accountToRemove);
		if(!accounts.get(index).getUsers().contains(this)) {	//if the account doesn't have this user in its list
			System.err.println("This user does not belong to this account.");	//not on account's list of users
			return false;
		}
		accounts.remove(index);
		int thisIndex = accountToRemove.getUsers().indexOf(this);
		accountToRemove.getUsers().remove(thisIndex);
		return true;
	}
	public boolean cancelAccount(int accountID) {
		Account a = null;
		for(int i=0; i<accounts.size(); i++) {
			if(accounts.get(i).getAccountID() == accountID) 
				a = accounts.get(i);
		}
		if(a == null) {
			System.err.println("You don't have access to account " + accountID);
			return false;
		}
		if(a.getBalance() != 0) {
			System.err.println("You can't cancel an account that's not empty.");
			return false;
		}
		
		System.out.println("Account " + accountID + " closed.");
		for(User c : a.getUsers()) {
			c.removeAccount(a);
		}
		return accounts.remove(a);
	}
	
	//past this point is mostly Alt+Shift+S, minor modification in toString and constructors
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFullName() {
		return fullName;
	}
	public int getUserID() {
		return userID;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public User() {
		super();
	}
	public User(int userID, String username, String password, String fullName) {
		userCount++;
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}
	public User(String username, String password, String fullName) {
		super();
		userCount++;
		this.userID = userCount;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		String str = "User = " + username + "#" + userID + "\nName = " + fullName + "\nAccount ID's: ";
		for(Account ac : accounts) {
			str += ac.getAccountID() + " ";
		}
		str += "\n";
		return str;
	}
}
