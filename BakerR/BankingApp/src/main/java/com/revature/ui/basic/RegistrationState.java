package com.revature.ui.basic;

import java.util.Scanner;

import com.revature.managers.PasswordUtils;
import com.revature.managers.UserManager;
import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;

public class RegistrationState extends DisplayState {

	@Override
	public void execute() {
		User usr = null;
		
		String fName, lName;
		char[] pw;
		Scanner scan = new Scanner(new UnclosableInputStream(System.in));
		
		// Choose account type
		System.out.println("Registration");
		Integer choice = null;
		do {
			System.out.print(
					"Choose account type [1: Customer; 2: Employee; 3: Admin]: ");
			String choiceStr = scan.nextLine();
			try {
				choice = Integer.parseInt(choiceStr);
				switch(choice) {
				case 1:
					usr = new Customer();
					break;
				case 2:
					usr = new Employee();
					break;
				case 3:
					usr = new Admin();
					break;
				default:
					System.out.println("Invalid input.");
					choice = null;
				}
			} catch(NumberFormatException ex) {
				System.out.println("Invalid input.");
			}
		} while(choice == null);
		
		
		// Prompt for more basic info
		System.out.print("First Name: ");
		fName = scan.nextLine();
		
		System.out.print("Last Name: ");
		lName = scan.nextLine();
		scan.close();
		
		
		pw = PasswordUtils.promptPassword();
		
		
		// Create user
		UserManager usrMng = UserManager.getInstance();
		usr.setId(usrMng.generateId());
		usr.setPwHash(
			PasswordUtils.hashPassword(
				PasswordUtils.convertCharsToBytes(pw)));
		usr.setFirstName(fName);
		usr.setLastName(lName);
		
		
		// Register the user
		if(usrMng.registerUser(usr)) {
			System.out.println("Registration Successful. User ID: " + usr.getId());
		} else {
			System.out.println("Registration Error.");
		}

		// Save
		UserManager.getInstance().save();
		
		// Set the next state
		setNextState(new EntryScreen());
	}

}
