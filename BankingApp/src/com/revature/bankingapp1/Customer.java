package com.revature.bankingapp1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Customer implements Serializable, User{
	
	private static final long serialVersionUID = 6047116377920406502L;
	private String username;
	private String password;
	private static final String accessType = "Customer";
	private String firstName;
	private String lastName;
	public ArrayList<Account> accountList; //will hold accounts associated with this customer
	public ArrayList<Application> applicationList; //will hold applications associated with this customer
	
	
	
	public Customer(ArrayList<String> userLog, String username, String password, String firstName, String lastName) {
		this.username = validateUsername(userLog, username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountList = new ArrayList<Account>();
		this.applicationList = new ArrayList<Application>();
		FileKeeping.addToUsernamePasswordLog(userLog, this);
	}//end constructor
	
	public Customer(ArrayList<Customer> customerLog, ArrayList<String> userLog, String username, String password, String firstName, String lastName) {
		this.username = validateUsername(userLog, username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountList = new ArrayList<Account>();
		this.applicationList = new ArrayList<Application>();
		FileKeeping.addToUsernamePasswordLog(userLog, this);
		FileKeeping.addToCustomerLog(customerLog, this);
	}//end constructor

	public void viewAccountDetails() {
		System.out.println(accountListToString(this.accountList));
		
	}//end viewAccountDetails method

	public void viewCustomerDetails() {
		System.out.println("Customer Information:");
		System.out.println();
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Number of Accounts: " + accountList.size());
		System.out.println("Number of Pending Applications: " + this.applicationList.size());
		System.out.println();
		
	}//end viewCustomerDetails method
	
	public void createApplication(ArrayList<Customer> customerList, String applicationType) {
		this.applicationList.add(new Application(customerList, applicationType));
	}//end createApplication method

	public void withdraw(long accountNumber, double amount) {
		boolean b = false;
		for(Account a: accountList) {
			if(a.getAccountNumber() == accountNumber) {
				b = true;
				if(a.getBalance() >= amount) {
					double newBalance = a.getBalance() - amount;
					a.setBalance(newBalance);
					System.out.println("The amount of $" + amount + " has been renoved from the account " + accountNumber + ".");
					System.out.println("Account " + accountNumber + " has a new balance of $" + a.getBalance() + ".");
					System.out.println();
				} else {
					System.out.println("Insufficient Funds. Transaction Cancelled.");
					System.out.println();
				}
			}
		}
		//if the first if conditional never evaluates to true, then the account number
		//does not match any in this customer's account. No changes will be made and the
		//boolean variable b will stay false. In this case, the user should be made aware
		//that no changes have been made.
		if(!b) {
			System.out.println("Account Number " + accountNumber + " was not found in your account list." 
					+ " Transaction Canceled.");
			System.out.println();
		}
	}//end withdraw method
	
	public void deposit(ArrayList<Customer> customerLog, long accountNumber, double amount) {
		boolean b = false;
		for(Customer c : customerLog) {
			for(Account a : c.accountList) {
				if(a.getAccountNumber() == accountNumber) {
					b = true;
					double newBalance = a.getBalance() + amount;
					a.setBalance(newBalance);
					System.out.println("The amount of $" + amount + " has been added to the account " + accountNumber + ".");
					System.out.println("Account " + accountNumber + " has a new balance of $" + a.getBalance() + ".");
					System.out.println();
				}
			}
		}
		if(!b) {
			System.out.println("Account Number " + accountNumber + " was not found. Transaction Canceled.");
			System.out.println();
		}
	}//end deposit method
	
	public void transfer(ArrayList<Customer> customerLog, long fromAccountNumber, long toAccountNumber, double amount) {
		//first perform withdraw from the 'from' account
		//then perform deposit to the 'to' account
		withdraw(fromAccountNumber, amount);
		deposit(customerLog, toAccountNumber, amount);
		
	}//end transfer method
	
	public String getUsername() {
		return username;
	}

	public String validateUsername(ArrayList<String> userLog, String username) {
		String username2 = "";
		boolean b = FileKeeping.checkForUsername(userLog, username);
		if(!b) { 
			return username;
		} else {
			while(b) {
				System.out.println("This username is already taken.");
				Scanner read = new Scanner(System.in);
				System.out.print("Enter another username you wish to use: ");
				username2 = read.nextLine();
				b = FileKeeping.checkForUsername(userLog, username2);
			}
			return username2;
		}
		
		
	}//end validateUsername

	public String getPassword() {
		//should change this to be more restrictive
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getAccessType() {
		return accessType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
	
	public void addToAccountList(Account newAccount) {
		this.accountList.add(newAccount);
	}

	public ArrayList<Application> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(ArrayList<Application> applicationList) {
		this.applicationList = applicationList;
	}
	
	public void addToApplicationList(Application newApplication) {
		this.applicationList.add(newApplication);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String accountListToString(ArrayList<Account> accountList) {
		String toReturn = "";
		for(Account a : accountList) {
			toReturn = toReturn.concat(a.toString() + "\n");
		}
		return toReturn;
	}
	

	
}//end class
