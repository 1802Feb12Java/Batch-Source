package com.revature.bankingappnew;

import java.sql.SQLException;
import java.util.Scanner;


public class Validation {
	
	public static String validateUsername(String username) throws SQLException {
		//returns an appropriate username
		String username2 = "";
		boolean b = com.revature.bankingappnew.daoimplementations.UserLogImp.usernameIsUnique(username);
		if(b) { 
			return username;
		} else {
			while(!b) {
				System.out.println("This username is already taken.");
				Scanner read = new Scanner(System.in);
				System.out.print("Enter another username: ");
				username2 = read.nextLine();
				b = com.revature.bankingappnew.daoimplementations.UserLogImp.usernameIsUnique(username2);
			}
			return username2;
		}
	}//end validateUsername
		
	public static boolean checkUsernamePasswordMatch(String username, String password) throws SQLException {
		if(com.revature.bankingappnew.daoimplementations.UserLogImp.usernameIsUnique(username)) {
			return false;
		}
		User u = com.revature.bankingappnew.daoimplementations.UserLogImp.getUserObject(username);
		//change this to get user object
		if(u.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}//end checkUsernamePasswordMatch method
	
	
	
}//end class
