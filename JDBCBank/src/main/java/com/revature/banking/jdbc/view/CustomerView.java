package com.revature.banking.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.revature.banking.jdbc.controller.Bank;
import com.revature.banking.jdbc.model.Account;
import com.revature.banking.jdbc.model.Application;
import com.revature.banking.jdbc.model.Customer;

public class CustomerView extends UserView {
	private static final long serialVersionUID = -3558653106504986476L;
	public CustomerView(Bank bank) {
		super(bank);
	}
	public void viewCustomerInfo(Customer customer, Scanner scan) {
		System.out.println(customer.toString());
	}
	public void viewAccountInfo(Customer customer, Scanner scan) {
		for (Account account: bank.getAccountsByCustomerId(customer.getUserId()))
				System.out.println(account.toString());
	}
	public void home(Customer customer, Scanner scan){
		//	Apply for accounts including joint accounts
		//	Check on applications
		//	withdrawing, depositing, transferring from their accounts
		System.out.println("\nHello "
				+",\n" + customer.toString() +
				"\nSelect one of the following options\n"
				+"1:\tApply for a new account\n"
				+"2:\tView all applications\n"
				+"3:\tView Customer Information\n"
				+"4:\tView Account Infomration\n"
				+"5:\tWithdraw, Transfer, or deposit money\n"
				+"6:\tChange login information\n"
				+"7:\tLogout / Go back to the main menu\nEnter your choice:\t");
		int choice = this.getNumber(scan);		
		if(choice ==1) {
			this.customerApply(customer, scan);
		}else if (choice == 2){
			this.viewApplicationsCustomer(customer, scan);
		}else if (choice ==3) {
			this.viewCustomerInfo(customer, scan);
		}else if (choice ==4) {
			this.viewAccountInfo(customer, scan);
		}else if (choice ==5) {
			this.modAccountBalance(customer, scan);
		}else if (choice == 6) {
			this.userProfile(customer, scan);
		}else if (choice == 7) {
			this.welcome(scan);
		}else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.home(customer, scan);
		}
		this.home(customer, scan);
	}
	public void viewApplicationsCustomer(Customer customer, Scanner scan) {
		List<Application> customerApplications = bank.getApplicationsByCustomerId(customer.getUserId());
		if(customerApplications.size() ==0) 
			System.out.println("There are no applications at this time");
		else
			for(Application application: customerApplications) 
				System.out.println(application.getApplicationId()+ "\t"+ application.getStatus());		
	}
	public Customer registerCustomer(Scanner scan) {
		System.out.println("Thank you for your interest in banking with us."
				+ "Enter the following infomation to create your login credentials");
		String username = this.getUsername(scan);
		if(username.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String password = this.getPassword(scan);
		if(password.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String[] name = this.getFullName(scan);
		if(name[0].equals("exit") || name[1].equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String email = this.getEmail(scan);
		if(email.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		Customer customer = bank.registerCustomer(username, password, email, name[0], name[1]);
		System.out.println("Thank you, " + customer.getFirstName() + " " + customer.getLastName() + ". The registration is Complete.\n"
				+ "Use the following username for system access.\n"
				+ "Username:\t"  + customer.getUsername() + "\nPassword\t" + password 
				+ "\nCustomerID:\t" + customer.getUserId());
		return customer;
	}
	public void customerApply(Customer customer, Scanner scan) {
//		Application app = new Application(primaryCustomerID, secondaryCustomerID);
//		DataManager.saveApplication(app);
//		bank.addNumApplications();
		boolean confirm = false;
		while (!confirm) {
			System.out.println("Are you opening a joint account?");
			String choice2 ="";
			System.out.print("yes or no:\t");
			choice2 = scan.nextLine().trim();
			if (choice2.equals("yes")) {
				confirm = true;
				boolean confirm2 = false;
				System.out.println("Do you have the secondary customer's bank ID?");
				while (!confirm2) {
					System.out.print("yes or no:\t");
					choice2 = scan.nextLine().trim().toLowerCase();
					int secondaryCustomerID =0;
					if (choice2.equals("yes")) {
						confirm2 = true;
						boolean confirm3 = false;
						while(!confirm3) {
							System.out.println("Enter the secondary customer's bank ID?\nEnter -1 to exit to main menu.");
							boolean getSecondaryCustomerID = false;
							while(!getSecondaryCustomerID) {
								System.out.print("Secondary Customer's bank ID\t");
								secondaryCustomerID = this.getNumber(scan);
								if(secondaryCustomerID ==-1) 
									return;
								else if (bank.customerIdExists(secondaryCustomerID))
										getSecondaryCustomerID = true;
								else
									System.out.println("Incorrect choice detected!!\nTry again...");
								
							}	
							if(this.bank.customerIdExists(secondaryCustomerID)) {
								Customer secondaryCustomer = bank.getCustomerById(secondaryCustomerID);
								this.bank.customerApply(customer, secondaryCustomer);
								System.out.println("Secondary Customer: "+ secondaryCustomer.toString() +"\n");
								System.out.println("Application completed. An employee will check on your application and"
										+ " get back to you shortly. Thank you\n");
								confirm3=true;
							}else if(secondaryCustomerID ==-1){
								this.home(customer, scan);
							}else {
								System.out.println("Incorrect choice detected!!\nTry again...");
							}
						}
					}else if (choice2.equals("no")) {
						confirm2 = true;
						Customer secondary = this.registerCustomer(scan);
						this.bank.customerApply(customer, secondary);
						System.out.println("Application completed. An employee will check on your application and"
								+ " get back to you shortly. Thank you\n");
					}else if (choice2.equals("exit")) {
						confirm=true;
						this.home(customer, scan);
					}else
						System.out.println("Incorrect choice detected!!\nTry again...");
				}
			}else if (choice2.equals("no")) {
				confirm=true;
				Customer secondaryCustomer = null;
				this.bank.customerApply(customer, secondaryCustomer);
				System.out.println("Application completed. An employee will check on your application and"
						+ " get back to you shortly. Thank you\n");
			}else 
				System.out.println("Incorrect choice detected!!\nTry again...");
		}
	}
	public void modAccountBalance(Customer customer, Scanner scan) {
		//view customer accounts
		//options to withdraw, transfer, deposit
		//Get amount from transaction
		//bank."selectedTransaction"(customer selected account, other account, amount)
		List<Account> accounts = bank.getAccountsByCustomerId(customer.getUserId());
		if(accounts.size()!=0) {
			for (int i=0; i< accounts.size(); i++) {
				if(accounts.get(i).getStatus().equals("Open"))
					System.out.println(accounts.get(i).toString());
				else
					accounts.remove(i);
			}
			System.out.println("\nWhat would you like to do?\n"
					+ "1:\tWithdraw\n"
					+ "2:\tDeposit\n"
					+ "3:\tTransfer\n"
					+ "4:\tExit\nEnter your choice:\t");
			boolean success = false;
			int choice =0;
			while(!success) {
				choice = this.getNumber(scan);
				if(choice ==-1) 
					return;
				else if(choice>0 || choice <5) {
					success=true;
				}else
					System.out.println("Incorrect choice detected!!\nTry again...");
			}
			if(choice ==4) {
				return;
			}else {
				for (int i=0; i< accounts.size(); i++) {
					if(accounts.get(i).getStatus().equals("Open"))
						System.out.println("\t"+(i+1)+ ":\t"+accounts.get(i).toString());
				}
				System.out.println("Select an account\n");
				success = false;
				int choice2 =0;
				while(!success) {
					choice2 = this.getNumber(scan);
					if(choice ==-1) 
						return;
					else if(choice2>0 && choice2 <=accounts.size()) {
						success=true;
					}else
						System.out.println("Incorrect choice detected!!\nTry again...");
				}
				Account account1 = accounts.get(choice2-1);
				if (choice == 3){
					//Transefer between accounts
					if(accounts.size()>1) {
						System.out.println("Select the other account");
						success = false;
						int choice3 =0;
						while(!success) {
							choice3 = this.getNumber(scan);
							if(choice ==-1) 
								return;
							else if(choice3>0 && choice3 <=accounts.size() && choice3 != choice2) {
								success=true;
							}else
								System.out.println("Incorrect choice detected!!\nTry again...");
						}
						Account account2 = accounts.get(choice3-1);
						System.out.println("Enter an amount\n");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>=0 && amount <=account1.getBalance()) {
								success=true;
							}else
								System.out.println("ERROR:\tamount cannot be greater than balance!!\n"
										+ "ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						this.bank.transfer(account1, account2, amount);
						System.out.println("$"+amount+" has been transferred to the account: "+account2.getAccountId());
						System.out.println("The new balance is for " + account1.getAccountId() + " is now\t"+ account1.getBalance());
						System.out.println("The new balance is for " + account2.getAccountId() + " is now\t"+ account2.getBalance());
					}else
						System.out.println("There are no other accounts to transer to");
				}else {
					if (choice ==1) {
						//withdraw
						System.out.println("Enter an amount");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>=0 && amount <=account1.getBalance()) {
								success=true;
							}else
								System.out.println("ERROR:\tAmount cannot be greater than balance!!\n"
										+ "ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						bank.withdraw(account1, amount);
						System.out.println("$"+amount+" has been withdrawn from the account: "+account1.getAccountId());
						System.out.println("The new balance is for " + account1.getAccountId() + " is now\t"+ account1.getBalance());
					}else if (choice == 2) {
						//deposit
						System.out.println("Enter an amount");
						success = false;
						double amount =0;
						while(!success) {
							amount = this.getAmount(scan);
							if(amount ==-1) 
								return;
							else if(amount>=0) {
								success=true;
							}else
								System.out.println("ERROR:\tAmount must be a positive number.!!\nTry again...");
						}
						bank.deposit(account1, amount);
						System.out.println("$"+amount+" has been deposited to the account: "+account1.getAccountId());
						System.out.println("The new balance is now\t$"+ account1.getBalance());
						
					}
				}
			}
		}else {
			if(bank.getNumAccounts()!=0) {
				System.out.println("You can only make a deposit at this time. What would you like to do?\n"
						+ "1:\tDeposit\n2:Exit\n");
				boolean success =false;
				int choice = 0;
				while(!success) {
					choice = this.getNumber(scan);
					if(choice ==-1) 
						return;
					else if(choice<1 && choice>2) {
						System.out.println("Incorrect choice detected.!!\nTry again...");
					}else
						success=true;
				}
				if(choice==2)
					return;
				else {
					System.out.println("Enter an account number\nEnter -1 to exit");
					success =false;
					while(!success) {
						choice = this.getNumber(scan);
						if(choice==-1) {
							return;
						}else if(!bank.accountIdExists(choice)) {
							System.out.println("That account number does not exist");
						}else {
							success=true;
						}
					}
					Account account = bank.getAccountByAccountId(choice);
					System.out.println("Enter an amount");
					success = false;
					double amount =0;
					while(!success) {
						amount = this.getAmount(scan);
						if(amount ==-1) 
							return;
						else if(amount>0) {
							success=true;
						}else
							System.out.println("ERROR:\tAmount must be a positive number.!!\nTry again...");
					}
					bank.deposit(account, amount);
					System.out.println("$"+amount+" has been deposited to the account: "+account.getAccountId());
					System.out.println("The new balance is now\t$"+ account.getBalance());
				}
			}else {
				System.out.println("There are no accounts in the bank at this time.\n");
			}
		}
	}
}
