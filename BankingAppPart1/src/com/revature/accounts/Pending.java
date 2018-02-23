package com.revature.accounts;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.users.Customer;

public class Pending {
	public static Customer view(ArrayList<Customer> pending, Scanner getInput) {
		int option = 0;
		
		if(pending.isEmpty()) {
			System.out.println("There are no pending accounts at this time");
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
						return current;
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
