package com.revature.pt1;

import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	private static final String fileNameA = "Customer.txt";
	private static final String fileNameB = "Applier.txt";
	
	public static Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// Assuming all Employees share one ID which serves as their pass
		// They are good employees who wont share it to non employees
		int empass = 1010;		// employee password to get in
		Employee emp = new Employee(empass);
		
		// Assuming all Admins share one ID which serves as their pass
		// They are good admins who wont share it to non admins
		int Adpass = 2020;		// admin password
		Admin adm = new Admin(Adpass);
		
		// Creating Arraylists of Customers and Appliers to keep track of all their data
		ArrayList<Customers> cus = new ArrayList<Customers>();
		ArrayList<Applier> app = new ArrayList<Applier>();
		
		// Reading from the txt files and updating Customer lists
		try {
			ObjectInputStream isa = new ObjectInputStream(new FileInputStream(fileNameA));
			ObjectInputStream isb = new ObjectInputStream(new FileInputStream(fileNameB)); 

			ArrayList <Customers> readCus = (ArrayList<Customers>) isa.readObject();
			ArrayList<Applier> readApp = (ArrayList<Applier>) isb.readObject();
			
			cus = readCus;
			app = readApp;
			
			isa.close();
			isb.close();
			
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
						
		System.out.println("-----------------------------------------------------------");
		System.out.println("Welcome to the Bank of Gods");
		System.out.println("-----------------------------------------------------------");
		
		int rep=0;						// variable for menu switch case, has to start at 0 
		boolean running = true;			// conditional to keep the menu switch case within an infinite loop until program is quit 
		
		while(running) {
			
			switch(rep) {
			
			case 0: // Main menu
				System.out.println("Enter the right number to tell us who you are:");
				System.out.println("1. New Customer");
				System.out.println("2. Returning Customer");
				System.out.println("3. Employee");
				System.out.println("4. Admin");
				System.out.println("5. Quit");
				rep = sc.nextInt();	
				break;
				
			case 1: // New Customer
				String nus;		// New customer username
				String nps;		// New customer password
				String act;		// Account type: personal, Joint
				String joint;	// placeholder for name of joint partner
				double fund;	// New customer bank fund
				System.out.println("Welcome! Thank you for choosing to bank with the Gods");
				System.out.println("-----------------------------------------------------------");
				System.out.println("Please choose a username");
				nus = sc.next();
				sc.reset();
				System.out.println("Please choose a password");
				nps = sc.next();
				sc.reset();
				System.out.println("What kind of account are you opening: personal, joint");
				act = sc.next();
				sc.reset();
				if (act.equals("joint")) {
					System.out.println("Whats the username of your joint partner");
					joint = sc.next();
					sc.reset();
					System.out.println("How much will you be depositing to start with?");
					fund = sc.nextDouble();
					app.add(new Applier(nus,nps,act,joint,fund));
					System.out.println("Please enter 0 to logout so we can process your application:");
					System.out.println("-----------------------------------------------------------");
					rep = sc.nextInt();
				} else {
					System.out.println("How much will you be depositing to start with?");
					fund = sc.nextInt();
					app.add(new Applier(nus,nps,act,fund));
					System.out.println("Please enter 0 to logout so we can process your application:");
					rep = sc.nextInt();
					System.out.println("-----------------------------------------------------------");
				}
				break;
				
			case 2: // Returning Customer
				String cname;			// Customer Username 
				String cps;				// Customer Password
				String tran = null;		// transfer target user
				String choice = null;	// variable for switch case for customer options
				double dep;				// Existing Customer deposit amount
				double wit;				// Existing Customer withdrawal amount
				double tmoney = 0;		// money to be transfered
				System.out.println("Enter your username:");
				cname = sc.next();
				sc.reset();
				System.out.println("Your password:");
				cps = sc.next();
				sc.reset();
				if (cus.get(0).checkUser(cname, cps,cus)) {
				  	System.out.println("Welcome "+cname);
				  	System.out.println("Would you like to :");
				  	System.out.println("a. Deposit money");
				  	System.out.println("b. Withdraw money");
				  	System.out.println("c. Transfer money");
				  	choice = sc.next();
					sc.reset();
					switch (choice) {
					case "a":
						System.out.println("How much will you be depositing today?");
						dep = sc.nextDouble();
						cus.get(0).deposit(dep,cname,cus);
						break;
					case "b":
						System.out.println("How much will you be withdrawing today?");
						wit = sc.nextDouble();
						cus.get(0).withdraw(wit,cname,cus);
						break;
					case "c": // Transfer
						System.out.println("Who do you want to transfer the money to?");
						tran = sc.next();
						sc.reset();
						System.out.println("How much would you like to transfer?");
						tmoney = sc.nextDouble();
						cus.get(0).transfer(cname,tmoney,tran,cus);
						break;
					default:
						break;
					}
				} else {
					System.out.println("Wrong!!! We dont like frauds in our bank");
					System.out.println("-----------------------------------------------------------");
				}
				System.out.println("Thank you for your business");
				System.out.println("Please enter 0 to logout, 2 for some business or 5 to exit:");
				rep = sc.nextInt();
				System.out.println("-----------------------------------------------------------");
				break;
				
			case 3: // Employee
				String choice2 = null;
				String per1 = null;		// placeholder for applier being aproved
				String per2 = null; 	// placeholder for applier being denied
				int Eid;				// Employee Id
				System.out.println("Enter Employee id:");
				Eid = sc.nextInt();
				if (Eid == empass) {
					System.out.println("You really are a great employee!");
					System.out.println("-----------------------------------------------------------");
					System.out.println("What would you like to do?");
					System.out.println("a. Oversee all accounts");
					System.out.println("b. Approve account application");
					System.out.println("c. Deny account application");
					
						choice2 = sc.next();
						sc.reset();
					switch (choice2) {
					case "a":
						emp.oversee(cus,app);
						break;
					case "b":
						System.out.println("Enter username of person you want to approve");
						per1= sc.next();
						sc.reset();
						emp.approve(per1, app, cus);
						break;
					case "c":
						System.out.println("Enter username of person you want to deny");
						per2= sc.next();
						sc.reset();
						emp.deny(per2, app, cus);
						break;
					default:
						System.out.println("You chose wrong");
						break;
					}
				} else {
					System.out.println("You are not an employee here!!!! Try again");
					System.out.println("-----------------------------------------------------------");
				}
				System.out.println("Please enter 0 to logout, 3 to login again, or 5 to exit:");
				rep = sc.nextInt();
				System.out.println("-----------------------------------------------------------");
				break;
				
			case 4: // Admin
				String per3 = null;		//placeholder for user for account approval
				String per4 = null;		//placeholder for user for account denial
				String name1 = null;	//placeholder for user for withdrawal 
				String name2 = null;	//placeholder for user for deposit 
				String name3 = null;	//placeholder for user for transfer
				String name4 = null;	//placeholder for user for transfer target
				String name5 = null;	//placeholder for user for account cancellation
				double wit1 = 0;		//placeholder for withdrawal amount
				double wit2 = 0;		//placeholder for deposit amount
				double wit3 = 0;		//placeholder for transfer amount
				int Aid;				// Admin Id
						
				System.out.println("Enter Admin id");
				Aid = sc.nextInt();
				if (Aid == Adpass) {
					System.out.println("You are a great admin");
					System.out.println("-----------------------------------------------------------");
					System.out.println("What would you like to do?");
					System.out.println("a. Oversee all accounts");
					System.out.println("b. Approve account application");
					System.out.println("c. Deny account application");
					System.out.println("d. Withdraw from an account");
					System.out.println("e. Deposit in an account");
					System.out.println("f. Transfer from an account");
					System.out.println("g. Cancel account");
					choice = sc.next();
					sc.reset();
					switch (choice) {
					case "a":	// view all accounts
						adm.oversee(cus,app);
						break;
					case "b":	// approve accounts
						System.out.println("Enter username of person you want to approve");
						per3= sc.next();
						sc.reset();
						adm.approve(per3, app, cus);
						break;
					case "c":	// deny accounts
						System.out.println("Enter username of person you want to deny");
						per4= sc.next();
						sc.reset();
						adm.deny(per4, app, cus);
						break;
					case "d": 	// withdraw
						System.out.println("Whose account are you accessing today?");
						name1 = sc.next();
						sc.reset();
						System.out.println("How much will you be withdrawing?");
						wit1 = sc.nextDouble();
						adm.withdraw(wit1,name1,cus);
						break;
					case "e":	// deposit
						System.out.println("Whose account are you accessing today?");
						name2 = sc.next();
						sc.reset();
						System.out.println("How much will you be depositing?");
						wit2 = sc.nextDouble();
						adm.deposit(wit2,name2,cus);
						break;
					case "f":	// transfer
						System.out.println("Whose account are you accessing today?");
						name3 = sc.next();
						sc.reset();
						System.out.println("Who is the transfer target?");
						name4 = sc.next();
						System.out.println("What is the transfer amount");
						wit3 = sc.nextDouble();
						adm.transfer(name3, wit3, name4, cus);
						break;
					case "g":	// cancel account
						System.out.println("Whose account are you cancelling today?");
						name5 = sc.next();
						sc.reset();
						adm.cancel(name5, cus);
						break;					
					default:
						break;
					}
				} else {
					System.out.println("You are not an admin here. Try again");
					System.out.println("-----------------------------------------------------------");
					rep = 4;
				}
				System.out.println("Please enter 0 to logout, 4 to login again, or 5 to exit:");
				rep = sc.nextInt();
				break;
				
			case 5: // Exit
				System.out.println("Goodbye! Come by again");
				try {
					ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(fileNameA));
					ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream(fileNameB));
							
					os1.writeObject(cus);
					os2.writeObject(app);
					
					os1.close();
					os2.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
								
				running = false;			// breaks out of the infinite while loop
				break;
				
			default:
				System.out.println("How could you go wrong on such a simple instruction");
				System.out.println("-----------------------------------------------------------");
				break;
			}
		}
	}
}
