package com.revature.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver implements Serializable {

	private static final long serialVersionUID = 1L;
	public static Scanner sc = new Scanner(System.in);
	private static final String cust = "Customers.txt";
	public static boolean active = true;
	public static boolean newScreen = false;
	public static ArrayList<Customer> customs = new ArrayList<>();
	public static ArrayList<Serializable> users = new ArrayList<>();
	private static final String apps = "Applications";
	public static ArrayList<String> loginInfo = new ArrayList<>();
	private static final String userFile = "Users.txt";
	private static final String loglog = "LoginInfo.txt";
	private static CustomerServices custwork;
	
	public static void main(String[] args) throws SQLException {
		//Customer c = new Customer("frank", "tank", "frank", "tank");
		//c.getAcct();
		//customs.add(c);
		//users.add(c);
		readLogIn();
		do {
			logOn();
			printCToFile(customs, cust);
			printToFile(users, userFile);
			printTo(loginInfo, loglog);
				
		}while(active);	
			
	}
	
	public static void createCustomer() throws SQLException {
		System.out.println("First name:");
		String firstname = sc.next();
		System.out.println("Last name:");
		String lastname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		System.out.println("Address:");
		String address = sc.next();
		System.out.println("Phone #:");
		String phonenum = sc.next();
		Customer c = new Customer(firstname, lastname, username, password);
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
	
	public static void createEmployee() {
		System.out.println("First name:");
		String firstname = sc.next();
		System.out.println("Last name:");
		String lastname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		Employee e = new Employee(firstname, lastname, username, password);
		users.add(e);
		loginInfo.add(username + ":" + password + ":" + "employee");
	}
	
	public static void createAdmin() {
		System.out.println("First name:");
		String firstname = sc.next();
		System.out.println("Last name:");
		String lastname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		Admin a = new Admin(firstname, lastname, username, password);
		users.add(a);
		loginInfo.add(username + ":" + password + ":" + "admin");
	}
	
	public static void logOn() throws SQLException {
		int cases = 0;
		
		System.out.println("Welcome to Yo Mama's Bank.");
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
				
		System.out.println("");
		for(int i = 0; i < loginInfo.size(); i++) {
			if(loginInfo.get(i).startsWith(username + ":" + password)) {
				String[] arr = loginInfo.get(i).split(":");
				String tipe = arr[2];
				String user = arr[0];
				logIn(tipe, user);
				return;
			}
		}
		System.out.println("That combination does not work.");
		System.out.println("Try again?");
		String contents = sc.next();
		if(contents.contains("y")) {
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
				
		System.out.println("");
		for(int i = 0; i < loginInfo.size(); i++) {
			if(loginInfo.get(i).startsWith(username + ":" + password)) {
				String[] arr = loginInfo.get(i).split(":");
				String tipe = arr[2];
				String user = arr[0];
				empLogIn(tipe, user);
				return;
			}
		}
		System.out.println("That combination does not work.");
		System.out.println("Try again?");
		String contents = sc.next();
		if(contents.contains("y")) {
			logIn();
		}else {
			return;
		}
	}
	
	public static void logIn(String s, String u) throws SQLException {
		if(s.trim().equals("customer")) {
			Customer c;
			for(int i = 0; i < customs.size(); i++) {
				c = customs.get(i);
				if(u.equals(c.getUsername())) {
					doCustomerStuff(c);
				}
			}
		}else {
			System.out.println("You are not a customer, please login through the appropriate portal.");
		}
	}
	
	public static void empLogIn(String s, String u) throws SQLException {
		if(s.trim().equals("admin")) {
			doAdminStuff();
		}else if(s.trim().equals("employee")){						//if they made it this far and they aren't a customer or admin, they must be 
			doEmployeeStuff();		//an employee.
		}else {
			System.out.println("You are not an employee, please login through the appropriate portal.");

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
				System.out.println("How much?");
				if(sc.hasNextInt()) {
					int x = sc.nextInt();
					//c.withdraw(x);
					doCustomerStuff(c);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}
				break;
			case 2: 
				newScreen = true;
				System.out.println("How much?");
				if(sc.hasNextInt()) {
					int y = sc.nextInt();
					//c.deposit(y);
					doCustomerStuff(c);
				}else {
					//catches the input if the user puts in something other than a number
					String garbage = sc.nextLine();
					System.out.println("Please provide a number for input.");
					
				}
				break;
			case 3:
				newScreen = true;
				if(sc.hasNextInt()) {
					cases = sc.nextInt();
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
					String i = sc.nextLine();
					if(i == "1") {
						createEmployee();
					} else if(i == "2") {
						createAdmin();
					}else {
						System.out.println("Please input a valid choice.");
					}
					doAdminStuff();
					break;
				case 5: 
					newScreen = true;
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
		System.out.println("4. ");
		System.out.println("5. Log off");
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
					
					doEmployeeStuff();
				break;
			case 2: 
				newScreen = true;
				doEmployeeStuff();
				break;
			case 3:
				newScreen = true;
				logOn();
				break;
			default:
				System.out.println("What would you like to do?");
				System.out.println("1. Approve Account");
				System.out.println("2. Customer info");
				System.out.println("3. Log off");
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
//		Customer c;
//		for(int i = 0; i < customs.size(); i++) {
//			c = customs.get(i);
//			if(c.hasAcct == false) {
//				System.out.println(c.getFirstName() + " " + c.getLastName());
//			}
//		}
//		String ip = sc.nextLine();
		
	}
	
	public static void printToFile(ArrayList<Serializable> t, String s) {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		File file = new File(s);
		try {
			fs = new FileOutputStream(file);
			os = new ObjectOutputStream(fs);
			for(int i = 0; i < t.size(); i++) {
			os.writeObject(t.get(i));
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printCToFile(ArrayList<Customer> t, String s) {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		File file = new File(s);
		try {
			fs = new FileOutputStream(file);
			os = new ObjectOutputStream(fs);
			for(int i = 0; i < t.size(); i++) {
			os.writeObject(t.get(i));
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printTo(ArrayList<String> t, String s) {
		FileOutputStream fs = null;
		OutputStream os = null;
		File file = new File(s);
		try {
			os = new FileOutputStream(file);
			for(int i = 0; i < t.size(); i++) {
			os.write(t.get(i).getBytes());
			os.write("\n".getBytes());
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void readLogIn() {
		FileInputStream fs = null;
		File file = new File(loglog);
		String s = "";
		try {
			fs = new FileInputStream(file);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		int b = 0;
		try {
			while((b = fs.read()) != -1) {
				char c = (char) b;
				s += c;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(fs != null) {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String[] arr = s.split("\n");
		for(int i = 0; i < arr.length; i++) {
			loginInfo.add(arr[i]);
		}
	}
	
}

