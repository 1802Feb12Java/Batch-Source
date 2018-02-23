package com.revature.ui.basic;

import java.util.Scanner;

import com.revature.managers.PasswordUtils;
import com.revature.managers.SessionManager;
import com.revature.managers.UserManager;
import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;

public class LoginState extends DisplayState {

	@Override
	public void execute() {
		Long id = null;
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		User usr = null;
		do { // Loop over if over 5 failed attempts.
			do { // User ID input
				System.out.print("User ID: ");
				String idStr = userIn.nextLine();
				
				if(idStr.equals("exit")) {
					userIn.close();
					setNextState(null);
					return;
				}
				
				try {
					id = Long.parseLong(idStr);
					usr = UserManager.getInstance().getUser(id);
					if(usr == null) {
						System.out.println("Error: ID not found.");
					}
				} catch(NumberFormatException ex) {
					System.out.println("Error: Invalid input.");
				}
			} while(usr == null);
			
			// Password Attempts (up to 5)
			boolean pwSuccess = false;
			for(int i = 0; i < 5 && !pwSuccess; ++i) {
				char[] pw = PasswordUtils.promptPassword();
				
				if(PasswordUtils.validatePassword(
						PasswordUtils.convertCharsToBytes(pw), usr.getPwHash())) {
					pwSuccess = true;
				} else {
					System.out.println("Incorrect Password.");
				}
			}
			
			if(pwSuccess) {
				break;
			}
		} while(true);
		
		userIn.close();
		
		// Login
		if(usr instanceof Customer) {
			setNextState(new CustomerScreen());
			SessionManager.getInstance().login(usr);
		} else if(usr instanceof Admin) {
			setNextState(new AdminScreen());
			SessionManager.getInstance().login(usr);
		} else if(usr instanceof Employee) {
			setNextState(new EmployeeScreen());
			SessionManager.getInstance().login(usr);
		} else {
			System.out.println("Unknown User Type. Returning to Entry Screen.");
			setNextState(new EntryScreen());
		}
	}

}
