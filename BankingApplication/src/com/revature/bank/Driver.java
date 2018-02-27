package com.revature.bank;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver implements Serializable {

	private static final long serialVersionUID = 1L;
	public static Scanner sc = new Scanner(System.in);
	public static boolean active = true;
	public static boolean newScreen = false;
	public static ArrayList<Customer> customs = new ArrayList<>();
	public static ArrayList<Serializable> users = new ArrayList<>();
	public static ArrayList<String> loginInfo = new ArrayList<>();
	public static CustomerServices custwork = new CustomerServices();
	public static LoginServices logwork = new LoginServices();
	public static AccountServices acctwork = new AccountServices();
	
	public static void main(String[] args) throws SQLException {
		
		do {
			logOn();
				
		}while(active);	
			
	}
	
	public static void createCustomer() throws SQLException {
		System.out.println("First name:");
		String fname = sc.next();
		System.out.println("Last name:");
		String lname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		Customer c = new Customer(fname, lname, username, password);
		System.out.println("Address in this format: Street#.StreetName");
		String address = sc.next();
		System.out.println("Phone #:");
		String phonenum = sc.next();
		//Customer c = new Customer(fname, lname, username, password);
		c.setAddress(address);
		c.setPhoneNumber(phonenum);
		System.out.println("Would you like to apply for an account?");
		String contents = sc.next();
		if(contents.toLowerCase().startsWith("y")) {
			makeApplication(c);
			//customs.add(c);
			//users.add(c);
			loginInfo.add(username + ":" + password + ":" + "customer");
		} else {
			System.out.println("Well why are you here then?!\n");
			logOn();
		}
	}
	
	public static void createEmployee() throws SQLException {
		System.out.println("First name:");
		String firstname = sc.next();
		System.out.println("Last name:");
		String lastname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		Employee e = new Employee(firstname, lastname, username, password); //does nothing, can get rid of
		users.add(e);
		logwork.addLogin(username, password, "EMPLOYEE"); //creates a new login for someone with employee access
	}
	
	public static void createAdmin() throws SQLException {
		System.out.println("First name:");
		String firstname = sc.next();
		System.out.println("Last name:");
		String lastname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		Admin a = new Admin(firstname, lastname, username, password); //does nothing, can get rid of
		users.add(a);
		logwork.addLogin(username, password, "ADMIN"); //creates a new login for someone with admin access
	}
	
	public static void logOn() throws SQLException {
		int cases = 0;
		
		System.out.println("Welcome to the bank of Gilead.");
		System.out.println("What would you like to do?");
		System.out.println("1. Customer Login");		
		System.out.println("2. Employee Login");
		System.out.println("3. New Customer");
		System.out.println("4. Log off");
		if(sc.hasNextInt()) {
			cases = sc.nextInt();
		}else {
			//show this
			String garbage = sc.nextLine();
			System.out.println("Please provide a number for input.");
			
		}
		do {
			switch(cases) {
			case 1:
				newScreen = true;
				logIn();
				break;
			case 2:
				newScreen = true;
				empLogIn();
				break;
			case 3: 
				newScreen = true;
				createCustomer();
				break;
			case 4:
				newScreen = true;
				active = false;
				
				break;
			default:
				System.out.println("What would you like to do?");
				System.out.println("1. Customer Login");		
				System.out.println("2. Employee Login");
				System.out.println("3. New Customer");
				System.out.println("4. Log off");
				cases = sc.nextInt();
				}
			} while(!newScreen);
	}
	
	public static void makeApplication(Customer c) throws SQLException {
		custwork.addCustomer(c);
		System.out.println("You have been added to the system.");
		System.out.println("An administrator will look over your account shortly.");
	}
	
	public static void logIn() throws SQLException {
		String username = "";
		String password = "";
		System.out.println("Username: ");
		username = sc.next();
		System.out.println("password: ");
		password = sc.next();
		Customer c = custwork.login(username, password);  //gets the customer with that login info
		if(c != null) {		//makes sure you have grabbed a customer to do things with
			doCustomerStuff(c);
			return;
		}
		System.out.println("That combination does not work.");
		System.out.println("Make sure you're trying to login to the right console.");
		System.out.println("Try again?");
		String contents = sc.next();
		if(contents.contains("y")) {		//recursion to stay in this window
			logIn();
		}else {
			return;
		}
	}
	
	public static void empLogIn() throws SQLException {
		String username = "";
		String password = "";
		System.out.println("Username: ");
		username = sc.next();
		System.out.println("password: ");
		password = sc.next();
		String now = logwork.getLogin(username, password);	//returns the type of emp logging in
		if(now != null) {
			if(now.toLowerCase().equals("admin")) {	//checks to see if login is for admin or employee
				doAdminStuff();
				return;
			}else {								//the only other option would be employee
				doEmployeeStuff();
				return;
			}
		}
		System.out.println("Try again?");
		String contents = sc.next();
		if(contents.contains("y")) {
			empLogIn();
		}else {
			return;
		}
	}
	
	
	
	public static void doCustomerStuff(Customer c) throws SQLException {
		int cases = 0;
		newScreen = false;
		System.out.println("Welcome, " + c.getFirstName());
		System.out.println("What would you like to do?");
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Transfer");
		System.out.println("4. Log off");
		Account a = null;
		if(sc.hasNextInt()) {
			cases = sc.nextInt();
		}else {
			//catches the input if the user puts in something other than a number
			String garbage = sc.nextLine();
			System.out.println("Please provide a number for input.");
			
		}
		do {
		switch(cases) {
			case 1:
				//gets us out of the loop.
				newScreen = true;
				System.out.println("Which Account?");
				custwork.showAccounts(c);
				
				if(sc.hasNextInt()) {
					int x = sc.nextInt();
					a = acctwork.getAccount(x);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}if(a != null) {
				System.out.println("How much?");
				if(sc.hasNextInt()) {
					int x = sc.nextInt();
					a.withdraw(x);
					acctwork.updateAccount(a);
					doCustomerStuff(c);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}
				}else {
					doCustomerStuff(c);
				}
				break;
			case 2: 
				newScreen = true;
				System.out.println("Which Account?");
				custwork.showAccounts(c);
				if(sc.hasNextInt()) {
					int x = sc.nextInt();
					a = acctwork.getAccount(x);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}
				if(a != null) {
				System.out.println("How much?");
				if(sc.hasNextInt()) {
					int x = sc.nextInt();
					a.deposit(x);
					acctwork.updateAccount(a);
					doCustomerStuff(c);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}}else {
					doCustomerStuff(c);
				}
				break;
			case 3:
				newScreen = true;
				System.out.println("What account # would you like to transfer to?");
				if(sc.hasNextInt()) {
					int x = sc.nextInt();
					Account b = acctwork.getAccount(x);
					System.out.println("From which account?");
					custwork.showAccounts(c);
					x = sc.nextInt();
					a = acctwork.getAccount(x);
					System.out.println("How much would you like to transfer?");
					x = sc.nextInt();
					a.transferFunds(x, b);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}
				doCustomerStuff(c);
				break;
			case 4:
				newScreen = true;
				logOn();
				break;
			default:
				System.out.println("What would you like to do?");
				System.out.println("1. Withdraw");
				System.out.println("2. Deposit");
				System.out.println("3. Transfer");
				System.out.println("4. Log off");
				cases = sc.nextInt();
			
		}
		}while(!newScreen);
	}
	
	public static void doAdminStuff() throws SQLException {
		int cases = 0;
		newScreen = false;
		System.out.println("----Admin console----");
		System.out.println("   Welcome Maerlyn.");
		System.out.println("What would you like to do?");
		System.out.println("1. Approve Accounts");
		System.out.println("2. View customer info");
		System.out.println("3. Update customer info");
		System.out.println("4. Create new user");
		System.out.println("5. Delete a user");
		System.out.println("6. Log off");
		if(sc.hasNextInt()) {
			cases = sc.nextInt();
		}else {
			//catches the input if the user puts in something other than a number
			String garbage = sc.nextLine();
			System.out.println("Please provide a number for input.");
			
		}
		do {
			switch(cases) {
				case 1:
					newScreen = true;
						approveApp();
						doAdminStuff();
					break;
				case 2: 
					newScreen = true;
					custwork.getAllCustomers();
					doAdminStuff();
					break;
				case 3: 
					newScreen = true;
					doAdminStuff();
					break;
				case 4: 
					newScreen = true;
					System.out.println("Would you like to create a new (1)Employee or (2)Admin?");
					int i = sc.nextInt();
					if(i == 1) {
						createEmployee();
					} else if(i == 2) {
						createAdmin();
					}else {
						System.out.println("Please input a valid choice.");
					}
					doAdminStuff();
					break;
				case 5: 
					newScreen = true;
					System.out.println("Type the Customer ID that you want to delete.");
					custwork.getAllCustomers();
					int id = sc.nextInt();
					custwork.deleteCustomer(id);
					System.out.println("Delete complete.");
					doAdminStuff();
					break;
				case 6:
					newScreen = true;
					logOn();
					break;
				default:
					System.out.println("----Admin console----");
					System.out.println("What would you like to do?");
					System.out.println("1. Approve Accounts");
					System.out.println("2. View customer info");
					System.out.println("3. Update customer info");
					System.out.println("4. Create new user");
					System.out.println("5. Delete a user");
					System.out.println("6. Log off");
					cases = sc.nextInt();
				
			}
			}while(!newScreen);
	}
	
	public static void doEmployeeStuff() throws SQLException {
		int cases = 0;
		newScreen = false;
		System.out.println("What would you like to do?");
		System.out.println("1. View Customer Accounts");
		System.out.println("2. Update Existing Accounts");
		System.out.println("3. Delete an account");
		System.out.println("4. Log off");
		if(sc.hasNextInt()) {
			cases = sc.nextInt();
		}else {
			//catches the input if the user puts in something other than a number
			String garbage = sc.nextLine();
			System.out.println("Please provide a number for input.");
			
		}
		do {
			switch(cases) {
			case 1:
				newScreen = true;
				custwork.getAllCustomers();
				doEmployeeStuff();
				break;
			case 2: 
				newScreen = true;
				doEmployeeStuff();
				break;
			case 3:
				newScreen = true;
				System.out.println("Type the Customer ID that you want to delete.");
				custwork.getAllCustomers();
				int id = sc.nextInt();
				custwork.deleteCustomer(id);
				System.out.println("Delete complete.");
				doEmployeeStuff();
				break;
			case 4:
				newScreen = true;
				logOn();
				break;
			default:
				System.out.println("What would you like to do?");
				System.out.println("1. View Customer Accounts");
				System.out.println("2. Update Existing Accounts");
				System.out.println("3. Delete an account");
				System.out.println("4. Log off");
				cases = sc.nextInt();
			
		}
		}while(!newScreen);
	}
	
	public static void approveApp() throws SQLException{
		System.out.println("Which application would you like to look at?");
		System.out.println("Choose the number of the account to verify."); 
		System.out.println(custwork.approveCustomer1());
		if(custwork.approveCustomer1().equals("There are no applicants pending approval.")) {
			
		}else {
			int i = sc.nextInt();
			custwork.approveCustomer(i);
		}

	}	
}

