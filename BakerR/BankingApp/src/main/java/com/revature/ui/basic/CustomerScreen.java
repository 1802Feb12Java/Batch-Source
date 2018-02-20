package com.revature.ui.basic;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import com.revature.managers.AccountManager;
import com.revature.managers.Linker;
import com.revature.managers.SessionManager;
import com.revature.managers.UserManager;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.User;

public class CustomerScreen extends DisplayState {

	@Override
	public void execute() {
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		String cmd;
		String[] cmdSplit;
		
		AccountManager acctMng = AccountManager.getInstance();
		UserManager usrMng = UserManager.getInstance();
		Customer currentUser = (Customer)(SessionManager.getInstance().getCurrentUser());
		
		do {
			System.out.println(">>> ");
			cmd = userIn.nextLine();
			cmdSplit = cmd.split("\\s+");
			
			if(cmdSplit.length == 0) {
				continue;
			}
			
			
			
			if(cmdSplit[0].equals("apply")) {
				
				if(cmdSplit.length == 1) {
					acctMng.requestNewAccount(
							(Customer)(SessionManager.getInstance().getCurrentUser()));
				} else {
					ArrayList<Customer> customerList = new ArrayList<>();
					customerList.add((Customer)(SessionManager.getInstance().getCurrentUser()));
					
					for(int i = 1; i < cmdSplit.length; ++i) {
						try {
							Long usrId = Long.parseLong(cmdSplit[i]);
							User usr = usrMng.getUser(usrId);
							if(usr instanceof Customer)
								customerList.add((Customer)usr);
						} catch(NumberFormatException ex) {}
					}
					
					Customer[] customerAry = customerList.toArray(new Customer[0]);
					acctMng.requestNewAccount(customerAry);
				}
			} else if(cmdSplit[0].equals("deposit")) {
				try {
					Long acctId = Long.parseLong(cmdSplit[1]);
					double amt = Double.parseDouble(cmdSplit[2]);
					
					if(amt < 0.0) {
						System.out.println("Deposit cannot be negative.");
						continue;
					} else if(!currentUser.getAccountIds().contains(acctId)) {
						System.out.println("This account is not yours."); 
						continue;
					}
					
					acctMng.deposit(acctId, amt);
				} catch(NumberFormatException ex) {
					System.out.println("Invalid Arguments.");
				} catch(ArrayIndexOutOfBoundsException ex) {
					System.out.println("Invalid invocation of this command.");
				}
			} else if(cmdSplit[0].equals("withdraw")) {
				try {
					Long acctId = Long.parseLong(cmdSplit[1]);
					double amt = Double.parseDouble(cmdSplit[2]);
					
					if(!currentUser.getAccountIds().contains(acctId)) {
						System.out.println("This account is not yours.");
						continue;
					} else if(amt < 0.0 || acctMng.getAccount(acctId).getBalance() < amt) {
						System.out.println("Withdrawal cannot be negative or over your balance.");
						continue;
					}
					
					acctMng.withdraw(acctId, amt);
				} catch(NumberFormatException ex) {
					System.out.println("Invalid Arguments.");
				} catch(ArrayIndexOutOfBoundsException ex) {
					System.out.println("Invalid invocation of this command.");
				}
			} else if(cmdSplit[0].equals("transfer")) {
				try {
					Long fromAcctId = Long.parseLong(cmdSplit[1]);
					Long toAcctId = Long.parseLong(cmdSplit[2]);
					double amt = Double.parseDouble(cmdSplit[3]);
					
					if(!currentUser.getAccountIds().contains(fromAcctId)) {
						System.out.println("This account is not yours.");
						continue;
					} else if(acctMng.getAccount(toAcctId) == null) {
						System.out.println("The destination account does not exist.");
						continue;
					} else if(amt < 0.0 || acctMng.getAccount(fromAcctId).getBalance() < amt) {
						System.out.println("Withdrawal cannot be negative or over your balance.");
						continue;
					}
					
					acctMng.transfer(toAcctId, fromAcctId, amt);
				} catch(NumberFormatException ex) {
					System.out.println("Invalid Arguments.");
				} catch(ArrayIndexOutOfBoundsException ex) {
					System.out.println("Invalid invocation of this command.");
				}
			} else if(cmdSplit[0].equals("lsacct")) {
				Set<Long> acctList = currentUser.getAccountIds();
				acctList.forEach((Long id) -> {
					Account a = acctMng.getAccount(id);
					System.out.println("Account [" + a.getId() + "]: " + a.getBalance());
				});
			} else if(cmdSplit[0].equals("logout")) {
				usrMng.save();
				acctMng.save();
				SessionManager.getInstance().logout();
				setNextState(new EntryScreen());
				break;
			} else if(cmdSplit[0].equals("exit")) {
				usrMng.save();
				acctMng.save();
				SessionManager.getInstance().logout();
				setNextState(null);
				break;
			} else if(cmdSplit[0].equals("closeacct")) {
				try {
					Long acctId = Long.parseLong(cmdSplit[1]);
					if(!currentUser.getAccountIds().contains(acctId)) {
						System.out.println("This account is not yours.");
						continue;
					}
					
					Account a = acctMng.removeAccount(acctId);
					Set<Long> aId = a.getOwnerIds();
					aId.forEach((Long id) -> {
						Customer c = (Customer)(usrMng.getUser(id));
						Linker.unlinkAccountFromCustomer(c, a);
					});
				} catch(NumberFormatException ex) {
					System.out.println("Invalid Arguments.");
				} catch(ArrayIndexOutOfBoundsException ex) {
					System.out.println("Invalid invocation of this command.");
				}
				continue;
			} else {
				System.out.println("Unknown command.");
				continue;
			}
			
		} while(true);
		
		userIn.close();
	}
}
