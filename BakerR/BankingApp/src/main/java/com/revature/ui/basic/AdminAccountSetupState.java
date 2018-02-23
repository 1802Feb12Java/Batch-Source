package com.revature.ui.basic;

import java.util.Scanner;

import com.revature.managers.PasswordUtils;
import com.revature.managers.UserManager;
import com.revature.model.Admin;
import com.revature.model.User;

public class AdminAccountSetupState extends DisplayState {

	@Override
	public void execute() {
		User usr = new Admin();
		
		String fName, lName;
		char[] pw;
		Scanner scan = new Scanner(new UnclosableInputStream(System.in));
		
		// Prompt for info
		System.out.println("Initial System Setup: Administrator Account Setup");
		System.out.print("First Name: ");
		fName = scan.nextLine();
		
		System.out.print("Last Name: ");
		lName = scan.nextLine();
		scan.close();
		
		
		pw = PasswordUtils.promptPassword();
		
		// Create user.
		usr.setId(UserManager.getInstance().generateId());
		usr.setPwHash(PasswordUtils.hashPassword(PasswordUtils.convertCharsToBytes(pw)));
		usr.setFirstName(fName);
		usr.setLastName(lName);
		
		// Register the user
		UserManager usrMng = UserManager.getInstance();
		if(usrMng.registerUser(usr)) {
			System.out.println("Registration Successful. User ID: " + usr.getId());

			// Set the next state
			setNextState(new EntryScreen());
		} else {
			System.out.println("Registration Error.");
			
			// Set the next state
			setNextState(new AdminAccountSetupState());
		}
		
		// Persist the changes.
		usrMng.save();
	}

}
