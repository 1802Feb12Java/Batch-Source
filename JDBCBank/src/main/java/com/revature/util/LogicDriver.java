package com.revature.util;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.services.AccountServices;
import com.revature.services.UserServices;

/*
 * The Logic Driver class 
 * All prepared statements are declared/initialized before each method
 * Methods included in this class:
 *		validate - validate a username/password combination with the DB
 *		checkUser - check if a username already exists in the DB
 *		register - add a new client to the DB 
*/
public class LogicDriver {
	
	//Log4J object in Driver
	public final Logger logger = Logger.getLogger(LogicDriver.class);
	
	/*
	 * Validating a user/password, set User u to current user
	 * using retrieve by login: username & pw
	*/
	public User validate(String user, String password, User u) {
		try {
			UserServices us = new UserServices();
			u = us.retrieveRecordByLogin(user, password);
		}
		catch (SQLException e) {
			logger.error(e.toString());
		}
		return u;
	}
	
	/*
	 * Check if username exist already
	 */
	public boolean checkUser(String username) {
		boolean exists = true;
		try {
			UserServices us = new UserServices();
			exists = us.retrieveRecordByUsername(username);
		}
		catch (SQLException e) {
			logger.error(e.toString());
		}
		return exists;
	}
	
	/*
	 * Registering a new user, inserting the info into DB
	*/
	public boolean register(User u,String username, String password) {
		try {
			UserServices us = new UserServices();
			us.insertNewRecord(u, username, password);
		}
		catch (SQLException e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	
	/*
	 * Prints out all the accounts with its balances of a user
	*/
	public void viewAccounts(User u) {
		try {
			AccountServices as = new AccountServices();
			ArrayList<Account> accountList = 
					new ArrayList<Account>(as.retrieveAllRows(u));
			
			for(Account a: accountList) {
				System.out.println(a.toString());
				System.out.println("---------------------------");
			}
		
		}
		catch (SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public void createAccount(Account a) {
		try {
			AccountServices as = new AccountServices();
			as.insertNewRecord(a);
			logger.info("Account created successful");
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}

	public void withdraw(String aNum, double amount) {
		
		try {
			AccountServices as = new AccountServices();
			//lookup
			Account a = as.retrieveRecordByAccountNumber(aNum);
			//update
			if(a.withdraw(amount)) {
				as.updateRecord(a);
			}
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public void deposit(String aNum, double amount) {
		
		try {
			AccountServices as = new AccountServices();
			//lookup
			Account a = as.retrieveRecordByAccountNumber(aNum);
			//update
			if(a.deposit(amount)) {
				logger.debug("updating");
				as.updateRecord(a);
			}
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}

	public void transfer(String fromAcc, String toAcc, double amount) {
		withdraw(fromAcc,amount);
		deposit(toAcc,amount);
	}

	public void deleteEmpty(String aNum) {
		//check if empty
		try {
			AccountServices as = new AccountServices();
			Account a = as.retrieveRecordByAccountNumber(aNum);
			if(a.getBalance()==0) {
				as.deleteRecord(a.getAccountID());
				logger.info("Deleting account...");
			}
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public void deleteAcc(String aNum) {
		try {
			AccountServices as = new AccountServices();
			Account a = as.retrieveRecordByAccountNumber(aNum);
			as.deleteRecord(a.getAccountID());
			logger.info("Deleting account...");
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public User userLookup(int uID) {
		User u = null;
		try {
			UserServices us = new UserServices();
			u = us.retrieveRecordByID(uID);
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
		return u;
	}
	
	public void ViewAllUser() {
		try {
			UserServices us = new UserServices();
			ArrayList<User> userList = us.retrieveAllRows();
			for(User curU: userList) {
				System.out.println(curU.toString());
				System.out.println("---------------------------");
			}
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}

	public void viewAllAcc() {
		try {
			UserServices us = new UserServices();
			ArrayList<User> usrList = us.retrieveAllRows();
			for(User curU: usrList) {
				viewAccounts(curU);
			}
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public void deleteUser(int userID) {
		try {
			UserServices us = new UserServices();
			User toDelete = userLookup(userID);
			us.deleteRecord(toDelete);
		}
		catch(SQLException e) {
			logger.error(e.toString());
		}
	}

}
