package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BankApp1Driver {

	private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	private static ArrayList<Account> allAccounts = new ArrayList<Account>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Hi, welcome to Trevor's banking app!");
		System.out.println("Please indicate whether you are a customer, employee, or admin by typing C, E, or A, respectively, then pressing ENTER.");
		String userType = scan.nextLine();
		String[] validUserTypes = {"C","c","E","e","A","a"};
		while(!(Arrays.stream(validUserTypes).parallel().anyMatch(userType::equals))){	
			//while they didn't put in c, e, or a (or their capital versions)
			System.out.println("Invalid input. Please put either C, E, or A.");
			userType = scan.nextLine();
		}
		
		allCustomers.add(new Customer("joe", "@@", "Jim George"));
		allAccounts.add(new Account());
		allAccounts.get(0).deposit(500);
		allAccounts.add(new Account());
		allAccounts.get(1).deposit(250);
		allAccounts.add(new Account());
		allCustomers.get(0).addAccount(allAccounts.get(0));
		allCustomers.get(0).addAccount(allAccounts.get(1));
		allAccounts.get(0).setApproved(true);
		allAccounts.get(1).setApproved(true);

		String endChoice = "";
		switch(userType) {
			case "c":	//chain them into each other without a break to do the same for both
			case "C":
				Customer c = loginCustomer(scan);
				do{
					endChoice = doCustomerStuff(scan, c);
				} while(!endChoice.equals("quit"));
				System.out.println();	//formatting
				break;
				
			case "e":
			case "E":
				do{
					endChoice = doEmployeeStuff(scan);
				} while(!endChoice.equals("quit"));
				break;
				
			case "a":
			case "A":
				do{
					endChoice = doAdminStuff(scan);
				} while(!endChoice.equals("quit"));
				break;
		}
		System.out.println("Thanks for using Trevor's banking app!");
		
		ArrayList<Serializable> serList = new ArrayList<Serializable>();
		serList.add(allAccounts);
		serList.add(allCustomers);
		File file = new File("out.txt");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(serList);
			if(oos != null)
				oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Check out \"out.txt\" for some garbled ArrayLists! :)");
		
//		ArrayList<Serializable> newSerList = null;
//		try {
//			FileInputStream fis = new FileInputStream(file);
//			ObjectInputStream oos = new ObjectInputStream(fis);
//			newSerList = (ArrayList<Serializable>) oos.readObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		allAccounts = (ArrayList<Account>) newSerList.get(0);
//		allCustomers = (ArrayList<Customer>) newSerList.get(1);
//		
//		System.out.println("Account 1 - " + allAccounts.get(0));
//		System.out.println("Customer 1 - " + allCustomers.get(0));
		
		scan.close();
	}
	
	public static Customer loginCustomer(Scanner scan) {
		Customer user = null;
		String[] currentUsernames = new String[allCustomers.size()];
		for(int i=0; i<allCustomers.size(); i++) {
			currentUsernames[i] = allCustomers.get(i).getUsername();
		}
		System.out.print("Enter L to login or N to make a new customer account: ");
		String customerChoice = scan.nextLine();
		switch(customerChoice) {
			case "n":
			case "N":
				System.out.print("Please enter your desired username: ");
				String username = scan.nextLine();
				while(Arrays.stream(currentUsernames).parallel().anyMatch(username::equals)) {
					System.out.print("Username already taken. Sorry, please enter another.\n\nNew username: ");
					username = scan.nextLine();
				}
				System.out.print("Please enter your desired password: ");
				String password = scan.nextLine();
				System.out.print("Please enter your full name: ");
				String name = scan.nextLine();
				user = new Customer(username, password, name);
				allCustomers.add(user);
				System.out.println("Customer account created! Welcome!\n");
				break;
			case "l":
			case "L":
				System.out.print("Please enter your username: ");
				username = scan.nextLine();
				while(!Arrays.stream(currentUsernames).parallel().anyMatch(username::equals)) {
					System.out.print("Username doesn't match one on record. Please try again: ");
					username = scan.nextLine();
				}
				for(Customer c : allCustomers) {
					if(c.getUsername().equals(username))
						user = c;
				}
				System.out.print("Please enter your password: ");
				int failCounter = 0;
				password = scan.nextLine();
				while(!user.getPassword().equals(password) && failCounter < 3) {
					System.out.print("Incorrect password for username \"" + username + "\", please try again.\nPassword: ");
					password = scan.nextLine();
					if(!user.getPassword().equals(password))
						failCounter++;
				}
				if(failCounter == 3) {
					System.out.println("Too many failed passwords. Exiting.");
					System.exit(0);
					break;
				}
				System.out.println("Welcome back " + user.getFullName() + "!\n");
				break;
		}
		return user;
	}
	
	public static String doCustomerStuff(Scanner scan, Customer user) {
		//outside of switch for new/login
		System.out.println("Your account list:\n");
		for(Account a : user.getAccounts()) {
			System.out.println(a);
		}
		String optionsList = "\tA: Apply for an account\n"
				+ "\tT: Transfer between two accounts\n"
				+ "\tW: Withdraw from account\n"
				+ "\tD: Deposit to account\n";
		String[] validCustomerOptions = {"A","a","T","t","W","w","D","d","quit"};
		System.out.println("What would you like to do?\n"+optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String customerChoice = scan.nextLine();
		while(!(Arrays.stream(validCustomerOptions).parallel().anyMatch(customerChoice::equals))) {	
			//while they didn't put in a valid option string
			System.out.println("\nInvalid input. Try again.\n" + optionsList);
			System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
			customerChoice = scan.nextLine();
		}
		switch(customerChoice) {
			case "quit":
				return "quit";
			case "A":
			case "a":
				Account newAccount = new Account();
				allAccounts.add(newAccount);
				user.addAccount(newAccount);
				System.out.println("Account applied for. New Account's ID = " + newAccount.getAccountID());
				break;
			case "T":
			case "t":
				System.out.print("Please enter the ID of the account to withdraw from: ");
				int withdrawAccountID = scan.nextInt();
				System.out.print("Please enter the ID of the account to deposit into: ");
				int depositAccountID = scan.nextInt();
				
				Account withdrawAccount = null;
				Account depositAccount = null;
				for(Account a : allAccounts) {
					if(a.getAccountID() == withdrawAccountID) {
						if(!user.getAccounts().contains(a)) {
							System.out.println("You are not an owner of account " + withdrawAccountID);
							return "";
						}
						else {
							withdrawAccount = a;
						}
					}
					if(a.getAccountID() == depositAccountID) {
						if(!user.getAccounts().contains(a)) {
							System.out.println("You are not an owner of account " + depositAccountID);
							return "";
						}
						else {
							depositAccount = a;
						}
					}
				}
				System.out.print("Please input the amount to transfer: ");
				int amount = scan.nextInt();
				withdrawAccount.transfer(depositAccount, amount);
				break;
			case "W":
			case "w":
			case "D":
			case "d":
				if(customerChoice.equals("W") || customerChoice.equals("w"))
					System.out.print("Please enter the ID of the account to withdraw from: ");
				if(customerChoice.equals("D") || customerChoice.equals("d"))
					System.out.print("Please enter the ID of the account to deposit to: ");
				int accountID = scan.nextInt();
				
				Account account = null;
				for(Account a : allAccounts) {
					if(a.getAccountID() == accountID) {
						if(!user.getAccounts().contains(a)) {
							System.out.println("You are not an owner of account " + accountID);
							return "";
						}
						else {
							account = a;
						}
					}
				}
				if(customerChoice.equals("W") || customerChoice.equals("w")) {
					System.out.print("Please input the amount to withdraw: ");
					amount = scan.nextInt();
					account.withdraw(amount);
				}
				if(customerChoice.equals("D") || customerChoice.equals("d")) {
					System.out.print("Please input the amount to deposit: ");
					amount = scan.nextInt();
					account.deposit(amount);
				}
				break;
		}
		//end of customerChoice switch
		System.out.println("\nIf you would like to quit, type \"quit\", otherwise press enter.");
		if(!(customerChoice.equals("a") || customerChoice.equals("A"))){
			scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		}
		String endChoice = scan.nextLine();
		return endChoice;
	}
	
	public static String doEmployeeStuff(Scanner scan) {
		Employee emp = new Employee(allAccounts, allCustomers);
		System.out.println("\nHi employee.");
		String optionsList = "Possible choices - \n"
				+ "\tRA: Read account information\n"
				+ "\tRC: Read customer information\n"
				+ "\tR: Review (approve/deny) account applications\n";
		System.out.println(optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String empChoice = scan.nextLine();
		String[] validOptions = {"RA","Ra","rA","ra","RC","Rc","rC","rc","R","r", "quit"};
		while(!(Arrays.stream(validOptions).parallel().anyMatch(empChoice::equals))) {	
			//while they didn't put in a valid option string
			System.out.println("\nInvalid input. Try again.\n" + optionsList);
			System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
			empChoice = scan.nextLine();
		}
		System.out.println();	//formatting
		switch(empChoice) {
			case "quit":
				return "quit";
			case "RA":
			case "Ra":
			case "rA":
			case "ra":
				System.out.print("Available account ID's: ");
				for(Account acc : allAccounts) {
					System.out.println(acc.getAccountID() + " ");
				}
				System.out.print("Please input the account ID that you wish to read: ");
				int accountID = scan.nextInt();
				System.out.println("\nAttempting to read account " + accountID + ":");
				System.out.print(emp.readAccount(accountID));
				break;
			case "RC":
			case "Rc":
			case "rC":
			case "rc":
				System.out.println("Available customer ID's: ");
				for(Customer crustomer : allCustomers) {
					System.out.println(crustomer.getCustomerID());
				}
				
				System.out.print("Please enter the customer's username: ");
				String username = scan.nextLine();
				System.out.print("Please enter the customer's ID: ");
				int customerID = scan.nextInt();
				System.out.println("\nAttempting to read customer " + customerID + ":");
				System.out.println(emp.readCustomer(username, customerID));
				break;
			case "R":
			case "r":
				emp.reviewAccountApplications();
				break;
		}
		System.out.println("\nIf you would like to quit, type \"quit\", otherwise press enter.");
		scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		String endChoice = scan.nextLine();
		return endChoice;
	}
	
	public static String doAdminStuff(Scanner scan) {
		Admin admin = new Admin(allAccounts, allCustomers);
		System.out.println("\nHi admin.");
		String optionsList = "Possible choices - \n"
				+ "\tRA: Read account information\n"
				+ "\tRC: Read customer information\n"
				+ "\tR: Review (approve/deny) account applications\n"
				+ "\tT: Transfer between two accounts\n"
				+ "\tW: Withdraw from account\n"
				+ "\tD: Deposit to account\n"
				+ "\tC: Cancel account";
		System.out.println(optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String adminChoice = scan.nextLine();
		String[] validAdminOptions = {"RA","Ra","rA","ra","RC","Rc","rC","rc","R","r","T","t","W","w","D","d","C","c","quit"};
		while(!(Arrays.stream(validAdminOptions).parallel().anyMatch(adminChoice::equals))) {	
			//while they didn't put in a valid option string
			System.out.println("\nInvalid input. Try again.\n" + optionsList);
			System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
			adminChoice = scan.nextLine();
		}
		System.out.println();	//formatting
		switch(adminChoice) {
			case "quit":
				return "quit";
			case "RA":
			case "Ra":
			case "rA":
			case "ra":
				System.out.print("Available account ID's: ");
				for(Account acc : allAccounts) {
					System.out.println(acc.getAccountID() + " ");
				}
				System.out.print("Please input the account ID that you wish to read: ");
				int accountID = scan.nextInt();
				System.out.println("\nAttempting to read account " + accountID + ":");
				System.out.print(admin.readAccount(accountID));
				break;
			case "RC":
			case "Rc":
			case "rC":
			case "rc":
				System.out.println("Available customer ID's: ");
				for(Customer crustomer : allCustomers) {
					System.out.println(crustomer.getCustomerID());
				}
				
				System.out.print("Please enter the customer's username: ");
				String username = scan.nextLine();
				System.out.print("Please enter the customer's ID: ");
				int customerID = scan.nextInt();
				System.out.println("\nAttempting to read customer " + customerID + ":");
				System.out.println(admin.readCustomer(username, customerID));
				break;
			case "R":
			case "r":
				admin.reviewAccountApplications();
				break;
			case "T":
			case "t":
				System.out.print("Please enter the ID of the account to withdraw from: ");
				int withdrawAccountID = scan.nextInt();
				System.out.print("Please enter the ID of the account to deposit into: ");
				int depositAccountID = scan.nextInt();
				System.out.print("Please input the amount to transfer: ");
				int amount = scan.nextInt();
				admin.transferMoney(withdrawAccountID, depositAccountID, amount);
				break;
			case "W":
			case "w":
			case "D":
			case "d":
				System.out.print("Please enter the account ID: ");
				accountID = scan.nextInt();
				System.out.print("Please enter the amount to withdraw: ");
				amount = scan.nextInt();
				admin.editAccount(adminChoice.toUpperCase(), accountID, amount);
				break;
			case "C":
			case "c":
				System.out.print("Please enter the account ID: ");
				accountID = scan.nextInt();
				admin.editAccount("C", accountID, 0);
				break;
		}

		System.out.println("\nIf you would like to quit, type \"quit\", otherwise press enter.");
		scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		String endChoice = scan.nextLine();
		return endChoice;
	}
}
