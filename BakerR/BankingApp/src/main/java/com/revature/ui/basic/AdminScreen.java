package com.revature.ui.basic;

import java.util.List;
import java.util.Scanner;

import com.revature.managers.AccountManager;
import com.revature.managers.UserManager;
import com.revature.model.Account;
import com.revature.model.User;

public class AdminScreen extends EmployeeScreen {
	@Override
	public void execute() {
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		String cmd;
		String[] cmdSplit;
		
		do {
			System.out.print(">>> ");
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
			} else if(cmdSplit[0].equals("lsusr")) {
				lsusr(cmdSplit);
			} else if(cmdSplit[0].equals("logout")) {
				logout(cmdSplit);
				break;
			} else if(cmdSplit[0].equals("exit")) {
				exit(cmdSplit);
				break;
			} else if(cmdSplit[0].equals("cancel")) {
				cancelRequest(cmdSplit);
			} else if(cmdSplit[0].equals("accept")) {
				acceptRequest(cmdSplit);
			} else if(cmdSplit[0].equals("deny")) {
				denyRequest(cmdSplit);
			} else if(cmdSplit[0].equals("stat")) {
				stat(cmdSplit);
			} else if(cmdSplit[0].equals("lscmd")) {
				lscmd(cmdSplit);
			} else {
				System.out.println("Unknown command.");
				continue;
			}
		} while(true);
		
		userIn.close();
	}
	
	protected void lsacct(String[] args) {
		System.out.println("Active Accounts:");
		super.lsacct(args);
		
		List<Account> acctList = AccountManager.getInstance().getRequests();
		if(!acctList.isEmpty())
			System.out.println("\nPending Accounts:");
		acctList.forEach((Account a) -> {
			System.out.println("[Account ID: " + a.getId() + "]");
			System.out.print("    ");
			a.getOwnerIds().forEach((Long id) -> {
				UserManager usrMng = UserManager.getInstance();
				User u = usrMng.getUser(id);
				System.out.print("[" + u.getId() + "]: " + 
				u.getLastName() + ", " + u.getFirstName() + "; ");
			});
			System.out.println();
		});
	}
	
	protected void cancelRequest(String[] args) {
		try {
			// Parse
			Long id = Long.parseLong(args[1]);
			// Remove account from request list.
			AccountManager.getInstance().removeAccountRequest(id);
		} catch(NumberFormatException ex) {
			System.out.println("Invalid arguments");
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid invocation of this command.");
		}
	}
	
	protected void acceptRequest(String[] args) {
		try {
			// Parse
			Long id = Long.parseLong(args[1]);
			
			AccountManager.getInstance().acceptNewAccount(id);
		} catch(NumberFormatException ex) {
			System.out.println("Invalid arguments");
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid invocation of this command.");
		}
	}
	
	protected void denyRequest(String[] args) {
		cancelRequest(args);
	}
	
	public void lscmd(String[] args) {
		System.out.println("deposit, withdraw, transfer, lsacct, lsusr, accept, deny, cancel, logout, exit");
	}
}
