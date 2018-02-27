package com.revature.banking.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.revature.banking.jdbc.controller.Bank;
import com.revature.banking.jdbc.model.Account;
import com.revature.banking.jdbc.model.Admin;
import com.revature.banking.jdbc.model.Employee;

public class AdminView extends EmployeeView {
	private static final long serialVersionUID = -5570153600973499854L;
	public AdminView(Bank bank) {
		super(bank);
	}
	
	public void home(Admin admin, Scanner scan) {
		//	Approving/denying applications
		//	withdrawing, depositing, transferring from all accounts
		//	canceling accounts
		//	logout/back to main menu
		System.out.println("\nHello " + admin.toString() + "\nSelect one of the following options\n"
				+ "1:\tView all applications\n"
				+ "2:\tView all accounts\n"
				+ "3:\tView all customer\n"
				+ "4:\tView all employee\n"
				+ "5:\tView all admins\n"
				+ "6:\tWithdraw, Transfer, or deposit money\n"
				+ "7:\tCancel account\n"
				+ "8:\tChange my login information\n"
				+ "9:\tRegister new employee\n"
				+ "10:\tRegister new admin\n"
				+ "11:\tLogout / Go back to the main menu");
		int choice = this.getNumber(scan);
		if(choice ==-1) 
			return;
		if(choice ==1) 
			this.viewApplicationsEmployee(admin, scan);
		else if (choice == 2)
			this.viewAccountInfo(admin, scan);
		else if (choice == 3)
			this.viewCustomerInfo(admin, scan);
		else if (choice == 4)
			this.viewEmployeeInfo(admin, scan);
		else if (choice == 5)
			this.viewAdminInfo(admin, scan);
		else if (choice == 6)
			this.modAccountBalance(admin, scan);
		else if (choice ==7) 
			this.cancelAccount(admin, scan);
		else if (choice == 8) 
			this.userProfile(admin, scan);
		else if (choice == 9) 
			this.registerEmployee(admin, scan);
		else if (choice == 10) 
			this.registerAdmin(admin, scan);
		else if (choice == 11) 
			this.welcome(scan);
		else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.home(admin, scan);
		}
		this.home(admin, scan);
	}
	public Admin registerAdmin(Admin admin, Scanner scan) {
		System.out.println("Enter the following infomation to create your login credentials");
		String username=getUsername(scan);
		if(username.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String password = this.getPassword(scan);
		if(password.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String email = this.getEmail(scan);
		if(email.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		Admin newAdmin = bank.registerAdmin(username, password, email);
		System.out.println("Thank you, " + admin.getUsername() + ". The registration is Complete.\n"
				+ "Provide the following credentials to the Admin for system access.\n"
				+ "Username:\t"  + newAdmin.getUsername() + "\nPassword\t" + password 
				+ "\nEmployee ID:\t" + newAdmin.getUserId());
		return newAdmin;
	}
	public Employee registerEmployee(Admin admin, Scanner scan) {
		System.out.println("Please enter the following infomation to create your login credentials");
		String username=this.getUsername(scan);
		if(username.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String password = this.getPassword(scan);
		if(password.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		String email = this.getEmail(scan);
		if(email.equals("exit")) {
			this.welcome(scan);
			System.exit(0);
		}
		Employee employee =  bank.registerEmployee(username, password, email);
		System.out.println("Thank you, " + admin.getUsername() + ". The registration is Complete.\n"
				+ "Provide the following credentials to the employee for system access.\n"
				+ "Username:\t"  + employee.getUsername() + "\nPassword\t" + password 
				+ "\nEmployee ID:\t" + employee.getUserId());
		return employee;
	}
	public void cancelAccount(Admin admin, Scanner scan) {
		List<Account> accounts = bank.getAllAccounts();
		if(accounts.size()==0) {
			System.out.println("There are no accounts at this time");
			return;
		}
		for (int i=0; i< accounts.size(); i++) {
			System.out.println(i +"\t"+accounts.get(i) + "\t" + accounts.get(i).getStatus());
		}
		boolean success = false;
		int choice =0;
		while (!success) {
			System.out.println("Please select an Account\n");
			System.out.print("Enter:\t");
			choice =this.getNumber(scan);
			if(choice ==-1) 
				return;
			else if((choice <= 0) || choice > accounts.size()+1 ) {
				System.out.println("Incorrect choice detected!!\nTry again...");
			}else
				success=true;
		}
		Account account = accounts.get(choice);
		if(account.getStatus().equals("Open")) {
			account.cancel(admin);
			System.out.println();
		}else {
			System.out.println("ERROR: The account's previous status was \"" +account.getStatus()+"\"");
		}
		System.out.println("The account's new status is \"" +account.getStatus()+"\"");
	}
	public void modAccountBalance(Admin admin, Scanner scan) {
		//bank.getAllAccounts
		//options to withdraw, transfer, deposit
		//bank."selectedTransaction"(customer selected account, other account)
		List<Account> accounts = bank.getAllOpenAccounts();
		if(accounts.size()!=0) {
			for (int i=0; i< accounts.size(); i++) {
				if(!accounts.get(i).getStatus().equals("Open")) {
					accounts.remove(i);
					continue;
				}
				System.out.println(accounts.get(i).toString());
			}
			System.out.println("\nWhat would you like to do?\n1:\tWithdraw\n2:\tDeposit\n"
					+ "3:\tTransfer\n4:\tExit\nEnter your choice:\t");
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
				
				System.out.println("Select an account number\n");
				success = false;
				int choice2 =0;
				while(!success) {
					choice2 = this.getNumber(scan);
					if(choice2 ==-1) 
						return;
					else if(choice2>0 && choice2 <=accounts.size()) {
						success=true;
					}else
						System.out.println("Incorrect choice detected!!\nTry again...");
				}
				Account account1 = accounts.get(choice2-1);
				if (choice == 3){
					if(!(accounts.get(choice2-1).getBalance()>0)) {
						System.out.println("Account balance must be greater than $0");
						return;
					}
					//Transefer between accounts
					if(accounts.size()>1) {
						System.out.println("Select the account number\n");
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
						System.out.println("The new balance is for " + account1.getAccountId() + " is now\t$"+ account1.getBalance());
						System.out.println("The new balance is for " + account2.getAccountId() + " is now\t$"+ account2.getBalance());
						
					}else
						System.out.println("There are no other accounts to transer to");
				}else {
					if (choice ==1) {
						if(!(accounts.get(choice2-1).getBalance()>0)) {
							System.out.println("Account balance must be greater than $0");
							return;
						}
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
						bank.withdraw(account1, amount);
						System.out.println("$"+amount+" has been withdrawn from the account: "+account1.getAccountId());
						System.out.println("The new balance is for " + account1.getAccountId() + " is now\t"+ account1.getBalance());
					}else if (choice == 2) {
						//deposit
						System.out.println("Enter an amount\n");
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
						System.out.println("The new balance is now\t"+ account1.getBalance());
					}
				}
			}
		}
		else
			System.out.println("There are no accounts at this time.");
	}
	public void viewEmployeeInfo(Admin admin, Scanner scan) {
		List<Employee> employees = bank.getAllEmployees();
		if (employees.size()==0) {
			System.out.println("There are no customers at this time");
		}
		for (Employee employee: employees) {
			System.out.println(employee.toString());
		}
	}
	public void viewAdminInfo(Admin admin, Scanner scan) {
		List<Employee> employees = bank.getAllEmployees();
		if (employees.size()==0) {
			System.out.println("There are no customers at this time");
		}
		for (Employee employee: employees) {
			System.out.println(employee.toString());
		}
	}
}
