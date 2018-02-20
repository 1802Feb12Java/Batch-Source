package com.revature.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver implements Serializable {

	private static final long serialVersionUID = 1L;
	public static Scanner sc = new Scanner(System.in);
	private static final String cust = "Customers.txt";
	public static boolean active = true;
	public static boolean newScreen = false;
	public static ArrayList<Customer> customs = new ArrayList<>();
	public static ArrayList<User> users = new ArrayList<>();
	private static final String apps = "Applications";
	public static ArrayList<String> loginInfo = new ArrayList<>();
	
	public static void main(String[] args) throws ClassNotFoundException {
		do {
			logOn();
				
		}while(active);	
			
	}
	
	public static void createCustomer() throws ClassNotFoundException {
		System.out.println("First name:");
		String firstname = sc.next();
		System.out.println("Last name:");
		String lastname = sc.next();
		System.out.println("Username:");
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		Customer c = new Customer(firstname, lastname, username, password);
		System.out.println("Would you like to apply for an account?");
		String contents = sc.next();
		if(contents.toLowerCase().startsWith("y")) {
			makeApplication(c);
			customs.add(c);
			users.add(c);
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
	
	public static void logOn() throws ClassNotFoundException {
		int cases = 0;
		
		System.out.println("Welcome to Yo Mama's Bank.");
		System.out.println("What would you like to do?");
		System.out.println("1. Login");
		System.out.println("2. New Customer");
		System.out.println("3. New Employee");
		System.out.println("4. New Admin");
		System.out.println("5. Log off");
		cases = sc.nextInt();
		do {
			switch(cases) {
			case 1:
				newScreen = true;
				logIn();
				break;
			case 2: 
				newScreen = true;
				createCustomer();
				break;
			case 3:
				newScreen = true;
				createEmployee();
				break;
			case 4:
				newScreen = true;
				createAdmin();
				break;
			case 5:
				newScreen = true;
				active = false;
				
				break;
			default:
				System.out.println("What would you like to do?");
				System.out.println("1. Login");
				System.out.println("2. New Customer");
				System.out.println("3. New Employee");
				System.out.println("4. New Admin");
				System.out.println("5. Log off");
				cases = sc.nextInt();
				}
			} while(!newScreen);
	}
	
	public static void makeApplication(Customer c) {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		File file = new File(apps);
		try {
			fs = new FileOutputStream(file);
			os = new ObjectOutputStream(fs);
			os.writeObject(c);
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
	
	public static void logIn() throws ClassNotFoundException {
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
				logIn(tipe);
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
	
	public static void logIn(String s) throws ClassNotFoundException {
		if(s.trim().equals("customer")) {
			doCustomerStuff();
		}else if(s.trim().equals("admin")) {
			doAdminStuff();
		}else {
			doEmployeeStuff();
		}
	}
	
	public static void doCustomerStuff() throws ClassNotFoundException {
		int cases = 0;
		newScreen = false;
		//c = customs.get();
		System.out.println("What would you like to do?");
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Transfer");
		System.out.println("4. Log off");
		cases = sc.nextInt();
		do {
		switch(cases) {
			case 1:
				newScreen = true;
				System.out.println("How much?");
				int x = sc.nextInt();
				//c.withdraw(x);
				break;
			case 2: 
				newScreen = true;
				System.out.println("How much?");
				int y = sc.nextInt();
				//c.deposit(y);
				break;
			case 3:
				newScreen = true;
				
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
		}while(newScreen);
	}
	
	public static void doAdminStuff() throws ClassNotFoundException {
		int cases = 0;
		newScreen = false;
		System.out.println("What would you like to do?");
		System.out.println("1. Approve Accounts");
		System.out.println("2. Customer info");
		System.out.println("3. Log off");
		cases = sc.nextInt();
		do {
			switch(cases) {
				case 1:
					newScreen = true;
						approveApp();
						doAdminStuff();
					break;
				case 2: 
					newScreen = true;
					
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
	
	public static void doEmployeeStuff() {
		int cases = 0;
		newScreen = false;
		System.out.println("What would you like to do?");
		System.out.println("1. Login");
		System.out.println("2. New Customer");
		System.out.println("3. New Employee");
		System.out.println("4. New Admin");
		System.out.println("5. Log off");
	}
	
	public static void approveApp(){
		FileInputStream fs = null;
		ObjectInputStream os = null;
		File file = new File(apps);
		try {
			fs = new FileInputStream(file);
			os = new ObjectInputStream(fs);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Customer c;
		try {
			c = (Customer) os.readObject();
			c.getAcct();
			//System.out.println(c.firstName + " " + c.hasAcct);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
}

