package com.revature.userfunctions;

import java.util.Scanner;

import com.revature.users.User;

public class UserFunctions {

	public static User register(Scanner getInput) {
		User newUser = new User();		

		System.out.print("Enter a user name: ");
		newUser.setUserName(getInput.nextLine());
		//TODO: check for user name availability
		
		System.out.print("Enter a password: ");
		newUser.setPassword(getInput.nextLine());
		
		//check password complexity
		
		System.out.print("Enter a first name: ");
		newUser.setFirstName(getInput.nextLine());
		
		System.out.print("Enter a last name: ");
		newUser.setLastName(getInput.nextLine());
		
		System.out.print("Enter a street address: ");
		newUser.setStreetAddress(getInput.nextLine());
		
		System.out.print("Enter a city: ");
		newUser.setCity(getInput.nextLine());
		
		System.out.print("Enter a two letter state abbreviation: ");
		newUser.setState(getInput.nextLine());
		
		System.out.print("Enter a phone number (123-456-7890): ");
		newUser.setPhoneNumber(getInput.nextLine());

		return newUser;
	}
	
	public static boolean validateUser(String password, User user) {
		if(password.equals(user.getPassword())) {
			return true;
		}
		
		System.out.println("Password does not match");
		return false;
	}
	
	public static void viewUserInformation(Scanner getInput) {

	}
	
}