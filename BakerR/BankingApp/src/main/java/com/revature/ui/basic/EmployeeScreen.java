package com.revature.ui.basic;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.revature.managers.AccountManager;
import com.revature.managers.SessionManager;
import com.revature.managers.UserManager;
import com.revature.model.Account;
import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;

public class EmployeeScreen extends DisplayState {

	@Override
	public void execute() {
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		String cmd;
		String[] cmdSplit;
		
		do {
			System.out.println(">>> ");
			cmd = userIn.nextLine();
			cmdSplit = cmd.split("\\s+");
			
			if(cmdSplit.length == 0) {
				continue;
			}
			if(cmdSplit[0].equals("deposit")) {
				deposit(cmdSplit);
			} else if(cmdSplit[0].equals("withdraw")) {
				withdraw(cmdSplit);
			} else if(cmdSplit[0].equals("transfer")) {
				transfer(cmdSplit);
			} else if(cmdSplit[0].equals("lsacct")) {
				lsacct(cmdSplit);
			} else if(cmdSplit[0].equals("logout")) {
				logout(cmdSplit);
				break;
			} else if(cmdSplit[0].equals("exit")) {
				exit(cmdSplit);
				break;
			} else {
				System.out.println("Unknown command.");
				continue;
			}
		} while(true);
		
		userIn.close();
	}

	
	protected void deposit(String[] args) {
		AccountManager acctMng = AccountManager.getInstance();
		
		try {
			Long acctId = Long.parseLong(args[1]);
			double amt = Double.parseDouble(args[2]);
			
			if(amt < 0.0) {
				System.out.println("Deposit cannot be negative.");
				return;
			}
			
			acctMng.deposit(acctId, amt);
		} catch(NumberFormatException ex) {
			System.out.println("Invalid Arguments.");
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid invocation of this command.");
		}
	}
	
	protected void withdraw(String[] args) {
		AccountManager acctMng = AccountManager.getInstance();
		
		try {
			Long acctId = Long.parseLong(args[1]);
			double amt = Double.parseDouble(args[2]);
			
			if(amt < 0.0 || acctMng.getAccount(acctId).getBalance() < amt) {
				System.out.println("Withdrawal cannot be negative or over your balance.");
				return;
			}
			
			acctMng.withdraw(acctId, amt);
		} catch(NumberFormatException ex) {
			System.out.println("Invalid Arguments.");
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid invocation of this command.");
		}
	}
	
	protected void transfer(String[] args) {
		AccountManager acctMng = AccountManager.getInstance();
		
		try {
			Long fromAcctId = Long.parseLong(args[1]);
			Long toAcctId = Long.parseLong(args[2]);
			double amt = Double.parseDouble(args[3]);
			
			if(acctMng.getAccount(toAcctId) == null) {
				System.out.println("The destination account does not exist.");
				return;
			} else if(amt < 0.0 || acctMng.getAccount(fromAcctId).getBalance() < amt) {
				System.out.println("Withdrawal cannot be negative or over your balance.");
				return;
			}
			
			acctMng.transfer(toAcctId, fromAcctId, amt);
		} catch(NumberFormatException ex) {
			System.out.println("Invalid Arguments.");
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid invocation of this command.");
		}
	}
	
	protected void lsacct(String[] args) {
		List<Account> acctList = AccountManager.getInstance().getAccounts();
		acctList.forEach((Account a) -> {
			System.out.println("[" + a.getId() + ": " + a.getBalance() + "]");
			System.out.print("    ");
			a.getOwnerIds().forEach((Long id) -> {
				UserManager usrMng = UserManager.getInstance();
				User u = usrMng.getUser(id);
				System.out.print("[" + u.getId() + "]: " + 
				u.getLastName() + ", " + u.getFirstName() + ";");
			});
			System.out.println();
		});
	}
	
	protected void lsusr(String[] args) {
		List<User> usrList = UserManager.getInstance().getUsers();
		usrList.forEach((User u) -> {
			if(u instanceof Customer) {
				System.out.println("[Customer:" + u.getId() + "] " + u.getLastName() + ", " + u.getFirstName());
				Set<Long> acctIds = ((Customer)u).getAccountIds();
				acctIds.forEach((Long id) -> {
					Account a = AccountManager.getInstance().getAccount(id);
					System.out.println("    [" + a.getId() + "]: " + a.getBalance());
				}); 
			} else if(u instanceof Admin) {
				System.out.println("[Admin:" + u.getId() + "] " + u.getLastName() + ", " + u.getFirstName());
			} else if(u instanceof Employee) {
				System.out.println("[Employee:" + u.getId() + "] " + u.getLastName() + ", " + u.getFirstName());
			}
		});
	}
	
	protected void logout(String[] args) {
		UserManager.getInstance().save();
		AccountManager.getInstance().save();
		SessionManager.getInstance().logout();
		setNextState(new EntryScreen());
	}
	
	protected void exit(String[] args) {
		UserManager.getInstance().save();
		AccountManager.getInstance().save();
		SessionManager.getInstance().logout();
		setNextState(null);
	}
}
