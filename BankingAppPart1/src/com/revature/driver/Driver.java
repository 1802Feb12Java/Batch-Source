package com.revature.driver;

import java.util.Scanner;

import com.revature.userfunctions.UserFunctions;
import com.revature.users.*;

public class Driver {
	public static void main(String[] args) {
		boolean userConnected = true;
		boolean loggingIn = true;
		boolean loggedIn = false;
		String userFilename = "sysusers.dat";
		String accountFilename = "accounts.dat";
		String userName = null;
		String password = null;

		int option = 0;
		int loginAttempts = 0;
		Scanner getInput = null;
		Customer user = null;

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
					user = UserFunctions.validate(userName, password);
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
