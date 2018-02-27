package com.revature.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.db.BankAccountDAO;
import com.revature.db.BankAccountDAOImpl;
import com.revature.db.UserDAO;
import com.revature.db.UserDAOImpl;
import com.revature.model.BankAccount;
import com.revature.model.Customer;
import com.revature.ui.command.Command;
import com.revature.ui.exception.IllegalCommandParameterException;

public class CustomerInputState extends SessionState {
	
	public CustomerInputState(Customer usr) {
		super(usr);
		
		// Register commands
		// mkacct
		Command mkacctCommand = new Command("mkacct", "mkacct", "Create an account.") {

			@Override
			public void run() {
				BankAccount acct;
				try {
					BankAccountDAO acctDao = new BankAccountDAOImpl();
					acct = acctDao.createAccount((Customer)getUser());
					((Customer)getUser()).getAccounts().put(acct.getAccountId(), acct);
				} catch (ClassNotFoundException | SQLException e) {
				}
			}

			@Override
			public List<String> getParams() {
				// TODO Auto-generated method stub
				return Collections.emptyList();
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				
			}

			@Override
			public void reset() {
			}
			
		};
		
		registerCommand(mkacctCommand);
		
		// rmacct
		Command rmacctCommand = new Command("rmacct", "rmacct <account id>", "Deletes the account if empty.") {
			private Integer targetAcctId;
			
			@Override
			public void run() {
				
				
			}

			@Override
			public List<String> getParams() {
				return Collections.singletonList(Integer.toString(targetAcctId));
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				if(args == null || args.length == 0) {
					throw new IllegalArgumentException("args must not be null and have at least 1 item.");
				}
				
				try {
					targetAcctId = Integer.parseInt(args[0]);
				} catch(NumberFormatException ex) {
					throw new IllegalCommandParameterException(this, "<account id> must be a valid integer.");
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
				
				try {
					targetAcctId = Integer.parseInt(args[begin]);
				} catch(NumberFormatException ex) {
					throw new IllegalCommandParameterException(this, "<account id> must be a valid integer.");
				}
			}

			@Override
			public void reset() {
				targetAcctId = null;
			}
			
		};
		
		registerCommand(rmacctCommand);
		
		// deposit
		Command depositCommand = new Command("deposit", "deposit <account id> <amount>", "Deposits <amount> into <account id>.") {
			private Integer acctId;
			private Double depVal;
			
			@Override
			public void run() {
				try {
					// Update user.
					UserDAO uDao = new UserDAOImpl();
					uDao.getInfo(getUser());
					BankAccount acct;
					
					if(!((Customer)getUser()).getAccounts().containsKey(acctId)) {
						// Account doesn't belong to the user.
						return;
					}
					
					acct = ((Customer)getUser()).getAccounts().get(acctId);
					
					BankAccountDAO dao = new BankAccountDAOImpl();
					dao.deposit(acct, depVal);
					double bal = dao.getBalance(acct);
					acct.setBalance(bal);
					
				} catch (ClassNotFoundException | SQLException e) {
				}
				
				reset();
			}

			@Override
			public List<String> getParams() {
				List<String> strParams = new ArrayList<>();
				strParams.add(acctId.toString());
				strParams.add(depVal.toString());
				
				return Collections.unmodifiableList(strParams);
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				if(args == null || args.length < 2) {
					throw new IllegalArgumentException("args must not be null and must have at least 2 arguments.");
				}
				
				try {
					acctId = new Integer(args[0]);
					depVal = new Double(args[1]);
				} catch(NumberFormatException ex) {
					throw new IllegalCommandParameterException(this, "Invalid parameters.");
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
				
				if((end - begin) < 2) {
					throw new IllegalArgumentException("At least 2 args must be specified.");
				}
				
				try {
					acctId = new Integer(args[begin]);
					depVal = new Double(args[begin+1]);
				} catch(NumberFormatException ex) {
					throw new IllegalCommandParameterException(this, "Invalid parameters.");
				}
			}

			@Override
			public void reset() {
				depVal = null;
				acctId = null;
			}
			
		};
		
		registerCommand(depositCommand);
		
		// withdraw
		Command withdrawCommand = new Command("withdraw", "withdraw <account id> <amount>", "Withdraws <amount> from <account id>.") {
			private Integer acctId;
			private Double withVal;
			
			@Override
			public void run() {
				try {
					// Update user.
					UserDAO uDao = new UserDAOImpl();
					uDao.getInfo(getUser());
					BankAccount acct;
					
					if(!((Customer)getUser()).getAccounts().containsKey(acctId)) {
						// Account doesn't belong to the user.
						return;
					}
					
					acct = ((Customer)getUser()).getAccounts().get(acctId);
					
					BankAccountDAO dao = new BankAccountDAOImpl();
					dao.withdraw(acct, withVal);
					double bal = dao.getBalance(acct);
					acct.setBalance(bal);
					
				} catch (ClassNotFoundException | SQLException e) {
				}
				
				reset();
			}

			@Override
			public List<String> getParams() {
				List<String> strParams = new ArrayList<>();
				strParams.add(acctId.toString());
				strParams.add(withVal.toString());
				
				return Collections.unmodifiableList(strParams);
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				if(args == null || args.length < 2) {
					throw new IllegalArgumentException("args must not be null and must have at least 2 arguments.");
				}
				
				try {
					acctId = new Integer(args[0]);
					withVal = new Double(args[1]);
				} catch(NumberFormatException ex) {
					throw new IllegalCommandParameterException(this, "Invalid parameters.");
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
				
				if((end - begin) < 2) {
					throw new IllegalArgumentException("At least 2 args must be specified.");
				}
				
				try {
					acctId = new Integer(args[begin]);
					withVal = new Double(args[begin+1]);
				} catch(NumberFormatException ex) {
					throw new IllegalCommandParameterException(this, "Invalid parameters.");
				}
			}

			@Override
			public void reset() {
				withVal = null;
				acctId = null;
			}
			
		};
		
		registerCommand(withdrawCommand);
		
		// transfer
		
		
		
		
	}

}
