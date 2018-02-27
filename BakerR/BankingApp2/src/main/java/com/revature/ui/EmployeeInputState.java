package com.revature.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.db.BankAccountDAO;
import com.revature.db.BankAccountDAOImpl;
import com.revature.db.UserDAO;
import com.revature.db.UserDAOImpl;
import com.revature.model.Admin;
import com.revature.model.BankAccount;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.model.UserType;
import com.revature.ui.command.Command;
import com.revature.ui.exception.IllegalCommandParameterException;

public class EmployeeInputState extends SessionState {
	
	public EmployeeInputState(Employee usr) {
		super(usr);
		
		// Commands
		
		// lsacct
		Command lsacctCommand = new Command("lsacct", "lsacct [account id]", 
				"If [account id] isn't specified, it displays all account numbers.\n"
			  + "If [account id] is specified, it displays detailed info about the account.") {

			private Integer acctId;
			
			{
				acctId = null;
			}
			
			@Override
			public void run() {
				try {
					BankAccountDAO dao = new BankAccountDAOImpl();
					
					if(acctId == null) { // display all accounts.
						List<BankAccount> acctList = dao.getAllAccounts();
						acctList.forEach((BankAccount acct) -> {
							displayStream.println(acct.getAccountId() + ": " + acct.getBalance());
						});
					} else { // Display detailed info
						UserDAO uDao = new UserDAOImpl();
						BankAccount acct = dao.getAccount(acctId);
						displayStream.println("Account #: " + acct.getAccountId());
						User usr = uDao.getUserById(acct.getOwner());
						displayStream.println("Owner: " + usr.getLastName() + ", " + usr.getFirstName());
						displayStream.println("Balance: " + acct.getBalance());
						displayStream.println("Active: " + acct.isActive());
					}
				} catch (ClassNotFoundException | SQLException e) {
				}
				
				reset();
			}

			@Override
			public List<String> getParams() {
				if(acctId == null) {
					return Collections.emptyList();
				} else {
					return Collections.singletonList(acctId.toString());
				}
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				if(args.length > 0) {
					try {
						acctId = new Integer(args[0]);
					} catch(NumberFormatException ex) {
						throw new IllegalCommandParameterException(this, "[account id] must be a valid integer.");
					}
				}
				
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				if(args == null) {
					throw new IllegalArgumentException("args cannot be null");
				}
				if((end-begin) == 0) return;
				if(begin < 0 || begin >= args.length || end <= 0 || end > args.length) {
					throw new IllegalArgumentException("begin and/or end are out of bounds.");
				}
				if(begin >= end) {
					throw new IllegalArgumentException("begin cannot be larger than end.");
				}
				
				if(args.length > 0) {
					try {
						acctId = new Integer(args[begin]);
					} catch(NumberFormatException ex) {
						throw new IllegalCommandParameterException(this, "[account id] must be a valid integer.");
					}
				}
			}

			@Override
			public void reset() {
				acctId = null;
			}
			
		};
		
		registerCommand(lsacctCommand);
		
		// lsusr
		Command lsusrCommand = new Command("lsusr", "lsusr [user id]", 
				"If [user id] isn't specified, it displays all users.\n"
			  + "If [user id] is specified, it displays detailed info about the user.") {

			private Integer usrId;
			
			{
				usrId = null;
			}
			
			@Override
			public void run() {
				try {
					UserDAO uDao = new UserDAOImpl();
					if(usrId == null) { // display all users.
						List<User> userList = uDao.getAllUsers();
						userList.forEach((User usr) -> {
							displayStream.println("[" + usr.getUserId() + "]: " + usr.getUserName());
						});
					} else { // Display detailed info
						User usr = uDao.getUserById(usrId);
						
						if(usr == null) {
							displayStream.println("User not found.");
						} else {
							UserType uType = null;
							
							if(usr instanceof Customer) {
								uType = UserType.CUSTOMER;
							} else if(usr instanceof Admin) {
								uType = UserType.ADMIN;
							} else if(usr instanceof Employee) {
								uType = UserType.EMPLOYEE;
							}
							
							displayStream.println("[" + UserType.toString(uType) + "] " + usr.getUserId() + ": " + usr.getUserName());
							displayStream.println("    Name: " + usr.getLastName() + ", " + usr.getFirstName());
							
							if(uType == UserType.CUSTOMER) {
								List<BankAccount> acctList = new ArrayList<>(((Customer)usr).getAccounts().values());
								acctList.forEach((BankAccount acct) -> {
									displayStream.println("    [" + acct.getAccountId() + "]: " + acct.getBalance());
								});
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
				}
				
				reset();
			}

			@Override
			public List<String> getParams() {
				if(usrId == null) {
					return Collections.emptyList();
				} else {
					return Collections.singletonList(usrId.toString());
				}
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				if(args.length > 0) {
					try {
						usrId = new Integer(args[0]);
					} catch(NumberFormatException ex) {
						throw new IllegalCommandParameterException(this, "[account id] must be a valid integer.");
					}
				}
				
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				if(args == null) {
					throw new IllegalArgumentException("args cannot be null");
				}
				if((end - begin) == 0) return;
				if(begin < 0 || begin >= args.length || end <= 0 || end > args.length) {
					throw new IllegalArgumentException("begin and/or end are out of bounds.");
				}
				if(begin >= end) {
					throw new IllegalArgumentException("begin cannot be larger than end.");
				}
				
				if(args.length > 0) {
					try {
						usrId = new Integer(args[begin]);
					} catch(NumberFormatException ex) {
						throw new IllegalCommandParameterException(this, "[account id] must be a valid integer.");
					}
				}
			}

			@Override
			public void reset() {
				usrId = null;
			}
			
		};
		
		registerCommand(lsusrCommand);
		
		
		// deposit
		
		
		
		
		
		// withdraw
		
		// transfer
		
		// acceptacct
		
		// denyacct
		
		
	}
}
