package com.revature.JDBCBank;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.DAO.AccountDAO;
import com.revature.DAO.AccountDAOClass;
import com.revature.DAO.TransactionDAO;
import com.revature.DAO.TransactionDAOClass;
import com.revature.JDBCBank.Account;
import com.revature.JDBCBank.User;

public class SuperUser extends User {	//have all the same access to view as normal users, but with additional modification access
	private ArrayList<Account> allAccounts;
	private ArrayList<User> allUsers;
	private String superUserUsername;
	private String superUserPassword;
	private TransactionDAO transDao;
	private AccountDAO accountDao;
	private Logger log;
	private int superUserID = -2;
	
	public String readAccount(int accountID) {
		boolean foundAccount = false;
		int index = 0;
		for(int i=0; i<allAccounts.size(); i++) {
			if(allAccounts.get(i).getAccountID() == accountID) {
				foundAccount = true;
				index = i;
			}
		}
		if(!foundAccount) {
			return "Account ID doesn't exist currently.";
		} 
		Account a = allAccounts.get(index);
		return a.toString();
	}
	public String readUser(int userID) {
		User c = null;		
		boolean foundUser = false;
		for(int i=0; i<allUsers.size(); i++) {
			if(allUsers.get(i).getUserID() == userID) {
				c = allUsers.get(i);
				foundUser = true;
			}
		}
		if(!foundUser) {
			return "ID doesn't exist currently.";
		} 
		return c.toString();
	}
	public void reviewAccountApplications() throws SQLException {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);	//have to leave it open here because it'll mess up the driver if I close it here
		String choice = "";
		for(Account a : allAccounts) {
			if(!a.isApproved()) {
				System.out.print(a.toString() + "\nWould you like to approve this account? Please type Y or N: ");
				choice = s.nextLine().toUpperCase();
				while(!(choice.equals("Y") || choice.equals("N"))) {	
					System.err.println("Invalid input. Please put either Y or N.");
					System.out.print("Y or N: ");
					choice = s.nextLine().toUpperCase();
				}
				if(choice.equals("Y")) {
					a.setApproved(true);
					System.out.println("Account " + a.getAccountID() + " approved!");

					accountDao.approveAccount(a.getAccountID());
					String transactionDescription = "Super user approved account " + a.getAccountID();
					transDao.insertTransaction(superUserID, transactionDescription);
					log.info(transactionDescription);
				}
			}
		}
		System.out.println("\nNo more unapproved accounts.");
	}
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
			System.err.println("One or both of these accounts don't exist yet");
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
			System.err.println("This account doesn't exist yet.");
			return false;
		}
		
		switch(editType) {
			case "W":
				return a.withdraw(amount);
			case "D":
				return a.deposit(amount);
			case "C":
				System.out.println("Account " + accountID + " closed.");
				for(User c : a.getUsers()) {
					c.removeAccount(a);
				}
				return allAccounts.remove(a);
		}
		
		return false;
	}
	public boolean deleteUser(int userID) {
		for(User u2 : allUsers) {
			if(u2.getUserID() == userID) {
				allUsers.remove(u2);
				return true;
			}
		}
		System.err.println("User doesn't exist.");
		return false;
	}
	
	//past this point is mostly Alt+Shift+S, minor modification in toString and constructors
	public String getSuperUserUsername() {
		return superUserUsername;
	}
	public String getSuperUserPassword() {
		return superUserPassword;
	}
	public int getSuperUserID() {
		return superUserID;
	}
	public SuperUser() {
		super();
	}
	public SuperUser(ArrayList<Account> allAccounts, ArrayList<User> allUsers, Connection conn, Logger log) {
		super();
		this.allAccounts = allAccounts;
		this.allUsers = allUsers;
		transDao = new TransactionDAOClass(conn);
		accountDao = new AccountDAOClass(conn);
		this.log = log;
		
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream(new File("database.properties"));
			props.load(in);
			in.close();
			
			superUserUsername = props.getProperty("adminuser");
			superUserPassword = props.getProperty("adminpass");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
}
