package com.revature.userfunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.users.*;

public class UserFunctions {

	public static Customer register(Scanner getInput) {
		Customer newCustomer = new Customer();		

		System.out.print("Enter a user name: ");
		newCustomer.setUserName(getInput.nextLine());
		//check for user name availability
		
		System.out.print("Enter a password: ");
		newCustomer.setPassword(getInput.nextLine());
		
		//check password complexity
		
		System.out.print("Enter your first name: ");
		newCustomer.setFirstName(getInput.nextLine());
		
		System.out.print("Enter your last name: ");
		newCustomer.setLastName(getInput.nextLine());
		
		System.out.print("Enter your street address: ");
		newCustomer.setStreetAddress(getInput.nextLine());
		
		System.out.print("Enter your city: ");
		newCustomer.setCity(getInput.nextLine());
		
		System.out.print("Enter your two letter state abbreviation: ");
		newCustomer.setState(getInput.nextLine());
		
		System.out.print("Enter your phone number (1234567890): ");
		newCustomer.setPhoneNumber(getInput.nextLine());

		return newCustomer;

	}
	
	public static boolean validateCustomer(String password, Customer customer) {
		if(password.equals(customer.getPassword())) {
			System.out.println("password match");
			return true;
		}
		
		System.out.println("Password does not match");
		return false;
	}
	
	public static boolean validateEmployee(String password, Employee employee) {
		if(password.equals(employee.getPassword())) {
			return true;
		}
		
		System.out.println("Password does not match");
		return false;
	}
	
	public static boolean validateAdmin(String password, Admin admin) {
		if(password.equals(admin.getPassword())) {
			return true;
		}
		System.out.println("Password does not match");
		return false;
	}
	
	public static void viewCustomerInformation(HashMap<String, Customer> map, Scanner getInput) {
		System.out.println("Enter a customer user name to view: ");
		String userName = getInput.nextLine();
		
		if(map.containsKey(userName)) {
			Customer customer = map.get(userName);
			System.out.println(customer.toString());
		}
		
		else {
			System.out.println("User not found");
			System.out.println();
		}
	}
	
	public static String viewPendingAccounts(ArrayList<Customer> pending, Scanner getInput) {
		int option = 0;
		
		if(pending.isEmpty()) {
			System.out.println("There are no pending accounts at this time");
			return null;
		}
		
		else {
			for(Customer current : pending) {
				do {
					System.out.println("Pending request for: " + current.getFirstName() + " " + current.getLastName());
					System.out.println("  1. Approve");
					System.out.println("  2. Deny");
					System.out.println("Select option: ");
						
					try {
							option = getInput.nextInt();
						}catch(Exception e) {
							System.out.println("Please enter an appropriate selection");
						}
							
					getInput.nextLine();
						
					if(option == 1) {
						return current.getUserName();
					}
						
					if(option == 2) {
						pending.remove(current);
						return null;
					}
				}while(option != 1 || option != 2);
			}
		}//end else
		return null;
	}
}
