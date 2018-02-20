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
		
		// Register the user.
		UserManager usrMng = UserManager.getInstance();
		usrMng.registerUser(usr);
		
		// Persist the changes.
		usrMng.save();
		
		// Set the next state
		setNextState(new EntryScreen());
	}

}
