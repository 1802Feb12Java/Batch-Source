package com.revature.driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.userfunctions.UserFunctions;
import com.revature.users.*;

public class Driver {
	public static void main(String[] args) {
		//loop controllers
		boolean userConnected = true;
		boolean loggingIn = true;
		boolean loggedIn = false;
		
		//filenames
		String customerFilename = "customers.dat";
		String employeeFilename = "employees.dat";
		String adminFilename = "admins.dat";
		String accountFilename = "accounts.dat";
		
		//storage maps
		HashMap <String, Customer> customers = new HashMap<String, Customer>();
		HashMap <String, Employee> employees = new HashMap<String, Employee>();
		HashMap <String, Admin> admins = new HashMap<String, Admin>();
		HashMap <String, Account> accounts = new HashMap<String, Account>();

		//Output stream objects
		FileOutputStream customerFileStreamOut = null;
		FileOutputStream employeeFileStreamOut = null;
		FileOutputStream adminFileStreamOut = null;
		FileOutputStream accountFileStreamOut = null;
		
		ObjectOutputStream writeCustomers = null;
		ObjectOutputStream writeEmployees = null;
		ObjectOutputStream writeAdmins = null;
		ObjectOutputStream writeAccounts = null;
		
		//Input stream objects
		FileInputStream customerFileStreamIn = null;
		FileInputStream employeeFileStreamIn = null;
		FileInputStream adminFileStreamIn = null;
		FileInputStream accountFileStreamIn = null;
		
		ObjectInputStream readCustomers = null;
		ObjectInputStream readEmployees = null;
		ObjectInputStream readAdmins = null;
		ObjectInputStream readAccounts = null;
		
		//active session objects
		Customer user = null;
		Employee employee = null;
		Admin admin = null;
		Account account = null;
		
		//login and validation variables
		int option = 0;
		int loginAttempts = 0;
		String userName = null;
		Scanner getInput = null;


		//open system user file: sysusers.dat and read the data into the collection
		//open account file: accounts.dat and read it into the collection
		
		while(userConnected) {			
			//initiate login		
			while(loggingIn) {
				//determine if new or returning user
				System.out.println("1.  Login existing user");
				System.out.println("2.  Register new user");
				System.out.print("Please select an option: ");

				getInput = new Scanner(System.in);
				
				try {
					option = getInput.nextInt();
				}catch(Exception e) {
					System.out.println("Please enter an appropriate selection");
				}
				
				switch(option) {
				
				case 1:
					//validate user
					System.out.print("Please enter your user name: ");
					loggedIn = UserFunctions.validate(userName, password);
					if (loggedIn) {
						loggingIn = false;
					}
					//if user fails validation, and still has login attempts available, increment loginAttempts
					if (!loggedIn && loginAttempts < 5) {
						loginAttempts++;
						System.out.println("Error:  Incorrect user name or password, please try again.");
						System.out.println("(" + (5 - loginAttempts) + " remaining)");
					}
					
					//if user fails validation and exceeds login attempts, exit the login loop
					if (!loggedIn && loginAttempts >= 5) {
						System.out.println("Login attempts exceeded.  Please try again later.");
						loggingIn = false;
					}
					
					break;

				case 2:
					user = (Customer) UserFunctions.register(getInput);
					System.out.println();
					break; 
					
				default:
					break;
				}//end switch			
			}//end logging in while loop
			
			while(loggedIn) {
			//access the banking system	
			}//end banking system loop
			
		}//end user connected while loop
	}//end main
}//end Driver class
