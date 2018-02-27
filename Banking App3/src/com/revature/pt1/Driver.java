package com.revature.pt1;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.CustomerService;

import oracle.jdbc.logging.annotations.Log;

import com.revature.dao.ApplicantService;

public class Driver {
	private static LoggingService ls = LoggingService.getInstance();
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		CustomerService Cdao = new CustomerService();	
		ApplicantService Adao = new ApplicantService();

		// Assuming all Admins share one ID which serves as their pass
		// They are good admins who wont share it to non admins
		int Adpass = 2020;		// admin password
								
		System.out.println("-----------------------------------------------------------");
		System.out.println("Welcome to the New Age Bank");
		System.out.println("-----------------------------------------------------------");
		
		int rep=0;						// variable for menu switch case, has to start at 0 
		boolean running = true;			// conditional to keep the menu switch case within an infinite loop until program is quit 
		
		while(running) {
			
			switch(rep) {
			
			case 0: // Main menu
				System.out.println("Enter the right number to tell us who you are:");
				System.out.println("1. New Customer");
				System.out.println("2. Returning Customer");
				System.out.println("3. Admin");
				System.out.println("4. Quit");
				rep = sc.nextInt();	
				break;
				
			case 1: // New Customer
				String nus;		// New customer username
				String nps;		// New customer password
				double fund;	// New customer bank fund
				System.out.println("Welcome! Thank you for choosing the New Age Bank");
				System.out.println("-----------------------------------------------------------");
				System.out.println("Please choose a username");
				nus = sc.next();
				sc.reset();
				System.out.println("Please choose a password");
				nps = sc.next();
				sc.reset();
				System.out.println("How much will you be depositing to start with?");
				fund = sc.nextInt();
				Adao.insertNewApp(nus, nps, fund);
				//ls.loggingMethod();
				System.out.println("Please enter 0 to return to main menu so we can process your application:");
				rep = sc.nextInt();
				System.out.println("-----------------------------------------------------------");
				break;
				
			case 2: // Returning Customer
				String cname;			// Customer Username 
				String cps;				// Customer Password
				String tran = null;		// transfer target user
				String choice = null;	// variable for switch case for customer options
				double dep;				// Existing Customer deposit amount
				double wit;				// Existing Customer withdrawal amount
				double tmoney = 0;		// money to be transfered
				boolean CusRun = true; 	
				System.out.println("Enter your username:");
				cname = sc.next();
				sc.reset();
				System.out.println("Your password:");
				cps = sc.next();
				sc.reset();
				if (Cdao.checkUser(cname, cps)) {	
					choice = "x";
					while (CusRun) {
				  	switch (choice) {
					case "x": 
						System.out.println("Welcome "+cname);
					  	System.out.println("Would you like to :");
					  	System.out.println("a. Deposit money");
					  	System.out.println("b. Withdraw money");
					  	System.out.println("c. Transfer money");
					  	System.out.println("d. View account info");
					  	System.out.println("e. Logout");
					choice = sc.next();
					sc.reset();
					break;
					case "a":
						System.out.println("How much will you be depositing today?");
						dep = sc.nextDouble();
						Cdao.depositCus(cname, dep);
						choice = "x";
						break;
					case "b":
						System.out.println("How much will you be withdrawing today?");
						wit = sc.nextDouble();
						Cdao.withdrawCus(cname, wit);
						choice = "x";
						break;
					case "c": // Transfer
						System.out.println("Who do you want to transfer the money to?");
						tran = sc.next();
						sc.reset();
						System.out.println("How much would you like to transfer?");
						tmoney = sc.nextDouble();
						if (Cdao.checktran(tran)) {
						Cdao.transferCus(cname,tran,tmoney);
						} else {
							System.out.println(tran +" does not exist");
						}
						choice = "x";
						break;
					case "d":
						Cdao.infoCus(cname);
						choice = "x";
						break;
					case "e":
						CusRun = false;
						break;
					default:
						System.out.println("Invalid choice. Choose from listed option");
						choice = "x";
						break;
					}}
				} else {
					System.out.println("Wrong login. Please Try again");
					System.out.println("-----------------------------------------------------------");
				}
				System.out.println("Thank you for your business");
				System.out.println("Please enter 0 to return to main menu, 2 for some business or 4 to exit:");
				rep = sc.nextInt();
				System.out.println("-----------------------------------------------------------");
				break;
				
			case 3: // Admin
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
				boolean Adrun = true;	
				System.out.println("Enter Admin id");
				Aid = sc.nextInt();
				if (Aid == Adpass) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("Admin access authorized!");
					System.out.println("-----------------------------------------------------------");
					
					choice = "x";
					
					while (Adrun) {
						
					switch (choice) {
					case "x":
						System.out.println("What would you like to do?");
						System.out.println("a. Oversee all accounts");
						System.out.println("b. Approve account application");
						System.out.println("c. Deny account application");
						System.out.println("d. Withdraw from an account");
						System.out.println("e. Deposit in an account");
						System.out.println("f. Transfer from an account");
						System.out.println("g. Cancel account");
						System.out.println("h. Logout");
						choice = sc.next();
						sc.reset();
						break;
					case "a":	// view all accounts
						List<Customers> customerList = Cdao.retrieveAllCus();
						for(Customers accounts : customerList){
							System.out.println(accounts);
						}
						List<Applier> applicantList = Adao.retrieveAllApp();
						for(Applier applications : applicantList) {
							System.out.println(applications);
						}
						choice = "x";
						break;
					case "b":	// approve accounts
						System.out.println("Enter username of person you want to approve");
						per3= sc.next();
						sc.reset();
						Cdao.moveFromApp(per3);
						choice = "x";
						break;
					case "c":	// deny accounts
						System.out.println("Enter username of person you want to deny");
						per4= sc.next();
						sc.reset();
						Adao.deleteAppByName(per4);
						choice = "x";
						break;
					case "d": 	// withdraw
						System.out.println("Whose account are you accessing today?");
						name1 = sc.next();
						sc.reset();
						System.out.println("How much will you be withdrawing?");
						wit1 = sc.nextDouble();
						Cdao.withdrawCus(name1, wit1);
						choice = "x";
						break;
					case "e":	// deposit
						System.out.println("Whose account are you accessing today?");
						name2 = sc.next();
						sc.reset();
						System.out.println("How much will you be depositing?");
						wit2 = sc.nextDouble();
						Cdao.depositCus(name2, wit2);
						choice = "x";
						break;
					case "f":	// transfer
						System.out.println("Whose account are you accessing today?");
						name3 = sc.next();
						sc.reset();
						System.out.println("Who is the transfer target?");
						name4 = sc.next();
						System.out.println("What is the transfer amount");
						wit3 = sc.nextDouble();
						if (Cdao.checktran(name4)) {
						Cdao.transferCus(name3, name4, wit3);
						} else {
							System.out.println(name4+ " does not exist");
						}
						choice = "x";
						break;
					case "g":	// cancel account
						System.out.println("Whose account are you cancelling today?");
						name5 = sc.next();
						sc.reset();
						Cdao.closeCus(name5);
						choice = "x";
						break;	
					case "h":
						Adrun = false;
						break;
					default: 
						System.out.println("Invalid choice. Choose from the listed option");
						choice = "x";
						break;
					}
					}
				} else {
					System.out.println("You entered wrong credentials. Try again");
					System.out.println("-----------------------------------------------------------");
					rep = 3;
				}
				System.out.println("Please enter 0 to return to main menu, 3 to login again, or 4 to exit:");
				rep = sc.nextInt();
				break;
				
			case 4: // Exit
				System.out.println("Goodbye! Come by again");
				System.out.println("-----------------------------------------------------------");							
				running = false;			// breaks out of the infinite while loop
				break;
				
			default:
				System.out.println("You chose an invalid option. Please try again");
				System.out.println("-----------------------------------------------------------");
				break;
			}
		}
	}
}
