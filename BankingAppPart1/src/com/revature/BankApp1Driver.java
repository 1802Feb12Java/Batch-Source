package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BankApp1Driver {

	private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	private static ArrayList<Account> allAccounts = new ArrayList<Account>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Hi, welcome to Trevor's banking app!\n");
		System.out.print("Please indicate whether you are a customer, employee, or admin by typing C, E, or A, respectively, then pressing ENTER: ");
		String userType = scan.nextLine();
		String[] validUserTypes = {"C","c","E","e","A","a"};
		while(!(Arrays.stream(validUserTypes).parallel().anyMatch(userType::equals))){	
			//while they didn't put in c, e, or a (or their capital versions)
			System.out.println("Invalid input. Please put either C, E, or A.");
			userType = scan.nextLine();
		}
		
		allCustomers.add(new Customer("joe", "@@", "Jim George"));	//making some initial stuff for the start of a bank
		allCustomers.add(new Customer("jim", "@13", "Joe Johnson"));
		allAccounts.add(new Account());
		allAccounts.get(0).setBalance(500);
		allAccounts.add(new Account());
		allAccounts.get(1).setBalance(250);
		allAccounts.add(new Account());
		allAccounts.get(2).setBalance(200);
		allAccounts.add(new Account());
		allAccounts.get(3).setBalance(80);	//purposefully not approved
		allCustomers.get(0).addAccount(allAccounts.get(0));
		allCustomers.get(0).addAccount(allAccounts.get(1));
		allCustomers.get(1).addAccount(allAccounts.get(2));	//given to second person
		allCustomers.get(1).addAccount(allAccounts.get(3));
		allAccounts.get(0).setApproved(true);
		allAccounts.get(1).setApproved(true);
		allAccounts.get(2).setApproved(true);
		
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
		System.out.println("\nThanks for using Trevor's banking app!");
		
		File fileAcc = new File("AccountsList.txt");
		File fileCust = new File("CustomersList.txt");
		try {
			FileOutputStream fosAcc = new FileOutputStream(fileAcc);
			FileOutputStream fosCust = new FileOutputStream(fileCust);
			ObjectOutputStream oos = new ObjectOutputStream(fosAcc);
			ObjectOutputStream oos2 = new ObjectOutputStream(fosCust);
			oos.writeObject(allAccounts);
			oos2.writeObject(allCustomers);
			if(oos != null)
				oos.close();
			if(oos2 != null)
				oos2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Check out \"AccountsList.txt\" and \"CustomersList.txt\" for some garbled ArrayLists! :)");
		
		
		
//		ArrayList<Account> allAccCopy = null;
//		ArrayList<Customer> allCustCopy = null;
//		try {
//			FileInputStream fisAcc = new FileInputStream(fileAcc);
//			FileInputStream fisCust = new FileInputStream(fileCust);
//			ObjectInputStream ois = new ObjectInputStream(fisAcc);
//			ObjectInputStream ois2 = new ObjectInputStream(fisCust);
//			allAccCopy = (ArrayList<Account>) ois.readObject();
//			allCustCopy = (ArrayList<Customer>) ois2.readObject();
//			if(ois != null)
//				ois.close();
//			if(ois2 != null)
//				ois2.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("\nAccount 3 - \n" + allAccCopy.get(2));
//		System.out.println("Customer 2 - \n" + allCustCopy.get(1));
		
		
		
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
		System.out.print("What would you like to do?\n"+optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String customerChoice = scan.nextLine();
		while(!(Arrays.stream(validCustomerOptions).parallel().anyMatch(customerChoice::equals))) {	
			//while they didn't put in a valid option string
			System.out.print("\nInvalid input. Try again.\n" + optionsList);
			System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
			customerChoice = scan.nextLine();
		}
		switch(customerChoice) {
			case "quit":
				return "quit";
			case "A":
			case "a":
				System.out.print("Would you like to add anyone else to this account? Please type Y or N: ");
				String addAcc = scan.nextLine();
				while(!(addAcc.equals("y") || addAcc.equals("n") || addAcc.equals("Y") || addAcc.equals("N"))) {	
					//while they didn't put in c, e, or a (or their capital versions)
					System.out.println("Invalid input. Please put either Y or N.");
					addAcc = scan.nextLine();
				}
				Customer otherCustomer = null;
				if(addAcc.equals("y") || addAcc.equals("Y")) {
					System.out.println("Please enter the username of the person you would like to own this account with: ");
					String secondUser = scan.nextLine();
					String[] currentUsernames = new String[allCustomers.size()];
					for(int i=0; i<allCustomers.size(); i++) {
						currentUsernames[i] = allCustomers.get(i).getUsername();
					}
					while(!Arrays.stream(currentUsernames).parallel().anyMatch(secondUser::equals)) {
						System.out.print("Username not found. Please try again.\n\nNew username: ");
						secondUser = scan.nextLine();
					}
					for(int i=0; i<allCustomers.size(); i++) {
						if(currentUsernames[i].equals(secondUser))
							otherCustomer = allCustomers.get(i);
					}
				}
				Account newAccount = new Account();
				allAccounts.add(newAccount);
				user.addAccount(newAccount);
				if(otherCustomer != null) {
					otherCustomer.addAccount(newAccount);
				}
				System.out.println("Account applied for. New Account's ID = " + newAccount.getAccountID());
				break;
			case "T":
			case "t":
				System.out.print("Please enter the ID of the account to withdraw from: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nEnter the ID of the account to withdraw from: ");
			        scan.next(); 
			    }
			    int withdrawAccountID = scan.nextInt();
				System.out.print("Please enter the ID of the account to deposit into: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nEnter the ID of the account to deposit into: ");
			        scan.next(); 
			    }
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
						depositAccount = a;
					}
				}
				System.out.print("Please input the amount to transfer: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the amount to transfer: ");
			        scan.next(); 
			    }
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

			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the account ID: ");
			        scan.next(); 
			    }
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
				    while (!scan.hasNextInt()) {
						System.out.print("Please enter a number.\nInput the amount to withdraw: ");
				        scan.next(); 
				    }
					amount = scan.nextInt();
					account.withdraw(amount);
				}
				if(customerChoice.equals("D") || customerChoice.equals("d")) {
					System.out.print("Please input the amount to deposit: ");
				    while (!scan.hasNextInt()) {
						System.out.print("Please enter a number.\nInput the amount to deposit: ");
				        scan.next(); 
				    }
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
		System.out.print(optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String empChoice = scan.nextLine();
		String[] validOptions = {"RA","Ra","rA","ra","RC","Rc","rC","rc","R","r", "quit"};
		while(!(Arrays.stream(validOptions).parallel().anyMatch(empChoice::equals))) {	
			//while they didn't put in a valid option string
			System.out.print("\nInvalid input. Try again.\n" + optionsList);
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
					System.out.print(acc.getAccountID() + " ");
				}
				System.out.println();
				System.out.print("Please input the account ID that you wish to read: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the ID of the account to read: ");
			        scan.next(); 
			    }
				int accountID = scan.nextInt();
				System.out.println("\nAttempting to read account " + accountID + ":");
				System.out.print(emp.readAccount(accountID));
				break;
			case "RC":
			case "Rc":
			case "rC":
			case "rc":
				System.out.print("Available customer ID's: ");
				for(Customer crustomer : allCustomers) {
					System.out.print(crustomer.getCustomerID() + " ");
				}
				System.out.print("\nPlease enter the customer's ID: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the customer ID: ");
			        scan.next(); 
			    }
				int customerID = scan.nextInt();
				System.out.println("\nAttempting to read customer " + customerID + ":");
				System.out.println(emp.readCustomer(customerID));
				break;
			case "R":
			case "r":
				emp.reviewAccountApplications();
				break;
		}
		System.out.println("\nIf you would like to quit, type \"quit\", otherwise press enter.");
		if(!(empChoice.equals("R")||empChoice.equals("r"))){
			scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		}
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
					System.out.print(acc.getAccountID() + " ");
				}
				System.out.println();
				System.out.print("Please input the account ID that you wish to read: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the account ID: ");
			        scan.next(); 
			    }
				int accountID = scan.nextInt();
				System.out.println("\nAttempting to read account " + accountID + ":");
				System.out.print(admin.readAccount(accountID));
				break;
			case "RC":
			case "Rc":
			case "rC":
			case "rc":
				System.out.print("Available customer ID's: ");
				for(Customer crustomer : allCustomers) {
					System.out.print(crustomer.getCustomerID() + " ");
				}
				System.out.print("\nPlease enter the customer's ID: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the customer ID: ");
			        scan.next(); 
			    }
				int customerID = scan.nextInt();
				System.out.println("\nAttempting to read customer " + customerID + ":");
				System.out.println(admin.readCustomer(customerID));
				break;
			case "R":
			case "r":
				admin.reviewAccountApplications();
				break;
			case "T":
			case "t":
				System.out.print("Please enter the ID of the account to withdraw from: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nEnter the ID of the account to withdraw from: ");
			        scan.next(); 
			    }
			    int withdrawAccountID = scan.nextInt();
				System.out.print("Please enter the ID of the account to deposit into: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nEnter the ID of the account to deposit into: ");
			        scan.next(); 
			    }
				int depositAccountID = scan.nextInt();
				System.out.print("Please input the amount to transfer: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the amount to transfer: ");
			        scan.next(); 
			    }
				int amount = scan.nextInt();
				admin.transferMoney(withdrawAccountID, depositAccountID, amount);
				break;
			case "W":
			case "w":
			case "D":
			case "d":
				System.out.print("Please enter the account ID: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the account ID: ");
			        scan.next(); 
			    }
				accountID = scan.nextInt();
				if(adminChoice.equals("W") || adminChoice.equals("w")) {
					System.out.print("Please enter the amount to withdraw: ");
				}
				if(adminChoice.equals("D") || adminChoice.equals("d")) {
					System.out.print("Please enter the amount to deposit: ");
				}
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the amount: ");
			        scan.next(); 
			    }
				amount = scan.nextInt();
				admin.editAccount(adminChoice.toUpperCase(), accountID, amount);
				break;
			case "C":
			case "c":
				System.out.print("Please enter the account ID: ");
			    while (!scan.hasNextInt()) {
					System.out.print("Please enter a number.\nInput the account ID: ");
			        scan.next(); 
			    }
				accountID = scan.nextInt();
				admin.editAccount("C", accountID, 0);
				break;
		}

		System.out.print("\nIf you would like to quit, type \"quit\", otherwise press enter. ");
		if(!(adminChoice.equals("R")||adminChoice.equals("r"))){
			scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		}
		String endChoice = scan.nextLine();
		return endChoice;
	}
}
