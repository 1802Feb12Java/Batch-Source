package com.revature.driver;
 
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.beans.BankAccount;
import com.revature.beans.CheckingsAccount;
import com.revature.beans.Customers;
import com.revature.beans.SavingsAccount;
/**
 * 
 * @author johne
 *
 */
public class CustomerDriver {

	public static void main(String[] args) {
		/*
		 * public Customers(String username, String password, Account account, double custBalance)
		 * cust1 and cust2 is the first customer being tested and added to the customer list
		 */
		CheckingsAccount checkings = new CheckingsAccount();
		SavingsAccount savings = new SavingsAccount();
		checkings.setBalance(1000);
		savings.setBalance(100);
		Customers newUser = new Customers("newUser", "password", checkings, savings);
		
		CheckingsAccount checkings2 = new CheckingsAccount();
		SavingsAccount savings2 = new SavingsAccount();
		checkings2.setBalance(2000);
		savings2.setBalance(4000);
		Customers newUser2 = new Customers("newUser2", "password1", checkings2, savings2);
		
		ArrayList<Customers> customersList = new ArrayList<Customers>();
		customersList.add(newUser);
		customersList.add(newUser2);
		
		CheckingsAccount checkings3 = new CheckingsAccount();
		SavingsAccount savings3 = new SavingsAccount();
		
		/*
		 * give user to select an option. 
		 */
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to \"Your Dirty Money\" Bank App!");
		System.out.print("\n(1) For Sign Up \n(2) For Login \n(3) Exit"
				+ "\nChose 1, 2, or 3 from above: ");
		int option = input.nextInt();
		
		if(option == 1) {	//sign up for new account
				System.out.print("\nCreate a username: ");
				String username = input.next();
				System.out.print("Create a password: ");
				String password = input.next();
				
				Customers newCustomer = new Customers(username, password, checkings3, savings3);
				customersList.add(newCustomer);
				
				System.out.println("Congratulations, "+ newCustomer.getUsername() +"! You created an account! You now have a Checkings and Savings account\n");
				
				System.out.println("(1) Select Checkings\n(2) Select Savings\n(3) Log out");
				System.out.print("What would you like to do next (chose 1, 2, or 3 from above): ");
				int optionA = input.nextInt();
				
				if (optionA == 1) { //checkings account options
					 	
						//deposit
							System.out.print("how much would you like to deposit: ");
							double depositAmount = input.nextDouble();
							newCustomer.getCheckings().deposit(depositAmount);
							
						//withdraw
							System.out.print("how much would you like to withdraw: ");
							double withdrawAmount = input.nextDouble();
							newCustomer.getCheckings().withdraw(withdrawAmount);
							
						//transfer to savings
							System.out.print("How much would you like to transfer to your savings: ");
							double transferAmount = input.nextDouble();
							newCustomer.getCheckings().transfer(transferAmount, newCustomer.getSavings());
							
						//transfer to someone's account. still a work in progress
							System.out.print("How much would you like to tranfer someone's Checkings: ");
							double transferToCheckingsAmount = input.nextDouble();
							System.out.print("How much would you like to tranfer someone's Savings: ");
							double transferToSavingsAmount = input.nextDouble();
							
							for(Customers c : customersList) {
								System.out.println(c.getUsername());
							}
							System.out.print("Select user to transfer funds to: ");
							String otherUserAccount = input.next();
							
							for(Customers c : customersList) {
								//.equals is used for comparing objects
								if(c.getUsername().equals(otherUserAccount)) {
									newCustomer.getCheckings().transfer(transferToCheckingsAmount, c.getCheckings());
									newCustomer.getCheckings().transfer(transferToSavingsAmount, c.getSavings());
								}
							}
				}	//end of checkings account options
				else if (optionA == 2) { 	//savings account options
					
						//deposit
							System.out.print("how much would you like to deposit: ");
							double depositAmount = input.nextDouble();
							newCustomer.getSavings().deposit(depositAmount);
							//break;
						//deposit
							System.out.print("how much would you like to withdraw: ");
							double withdrawAmount = input.nextDouble();
							newCustomer.getSavings().withdraw(withdrawAmount);
							//break;
						//transfer to savings
							System.out.print("How much would you like to transfer to your checkings: ");
							double transferAmount = input.nextDouble();
							newCustomer.getSavings().transfer(transferAmount, newCustomer.getCheckings());
							//break;
						//transfer to someone's account. still a work in progress
							System.out.print("How much would you like to tranfer someone's Checkings: ");
							double transferToCheckingsAmount = input.nextDouble();
							System.out.print("How much would you like to tranfer someone's Savings: ");
							double transferToSavingsAmount = input.nextDouble();
							
							for(Customers c : customersList) {
								System.out.println(c.getUsername());
							}
							System.out.print("Select user to transfer funds to: ");
							String otherUserAccount = input.next();
							
							for(Customers c : customersList) {
								//.equal is for comparing object
								if(c.getUsername().equals(otherUserAccount)) {
									newCustomer.getCheckings().transfer(transferToCheckingsAmount, c.getCheckings());
									newCustomer.getCheckings().transfer(transferToSavingsAmount, c.getSavings());
								}
							}
				}	//end of savings account option
				else { 
					System.out.println("Exited ");
				}//end of select checkings or savings account
				
		} else if (option == 2) {	//logging in
			System.out.print("Enter username: ");
			String username = input.next();
			System.out.print("Enter password: ");
			String password = input.next();
			
			for(Customers c : customersList) {
				if(c.getUsername().equals(username) && c.getPassword().equals(password)) {
					System.out.println("Welcome, " + c.getUsername() + "!");
					
					System.out.println("(1) Select Checkings\n(2) Select Savings\n(3) Log out");
					System.out.print("What would you like to do next (chose 1, 2, or 3 from above): ");
					int optionA = input.nextInt();
					
					switch(optionA) {
						case 1 : 	//checkings option
							//deposit
							System.out.print("how much would you like to deposit: ");
							double depositAmount = input.nextDouble();
							c.getCheckings().deposit(depositAmount);
							//break;
						//deposit
							System.out.print("how much would you like to withdraw: ");
							double withdrawAmount = input.nextDouble();
							c.getCheckings().withdraw(withdrawAmount);
							//break;
						//transfer to savings
							System.out.print("How much would you like to transfer to your savings: ");
							double transferAmount = input.nextDouble();
							c.getCheckings().transfer(transferAmount, c.getSavings());
							//break;
						//transfer to someone's account
							System.out.print("How much would you like to tranfer someone's Checkings: ");
							double transferToCheckingsAmount = input.nextDouble();
							System.out.print("How much would you like to tranfer someone's Savings: ");
							double transferToSavingsAmount = input.nextDouble();
							
							for(Customers c1 : customersList) {
								System.out.println(c1.getUsername());
							}
							System.out.print("Select user to transfer funds to: ");
							String otherUserAccount = input.next();
							
							for(Customers c2 : customersList) {
								//.equals is used for comparing objects
								if(c2.getUsername().equals(otherUserAccount)) {
									c.getCheckings().transfer(transferToCheckingsAmount, c2.getCheckings());
									c.getCheckings().transfer(transferToSavingsAmount, c2.getSavings());
								}
							}
							break;
						case 2 :	//savings option
							//deposit
							System.out.print("how much would you like to deposit: ");
							double depositAmount2 = input.nextDouble();
							c.getSavings().deposit(depositAmount2);
							//break;
						//deposit
							System.out.print("how much would you like to withdraw: ");
							double withdrawAmount2 = input.nextDouble();
							c.getSavings().withdraw(withdrawAmount2);
							//break;
						//transfer to checkings
							System.out.print("How much would you like to transfer to your Checkings: ");
							double transferAmount2 = input.nextDouble();
							c.getSavings().transfer(transferAmount2, c.getCheckings());
							//break;
						//transfer to someone's account. 
							System.out.print("How much would you like to tranfer someone's Checkings: ");
							double transferToCheckingsAmount2 = input.nextDouble();
							System.out.print("How much would you like to tranfer someone's Savings: ");
							double transferToSavingsAmount2 = input.nextDouble();
							
							for(Customers c1 : customersList) {
								System.out.println(c1.getUsername());
							}
							System.out.print("Select user to transfer funds to: ");
							String otherUserAccount2 = input.next();
							
							for(Customers c2 : customersList) {
								//.equal from Object class is for comparing object
								if(c2.getUsername().equals(otherUserAccount2)) {
									c.getSavings().transfer(transferToCheckingsAmount2, c2.getCheckings());
									c.getSavings().transfer(transferToSavingsAmount2, c2.getSavings());
								}
							}
							break;
						case 3 :
							System.out.println("GoodBye");
							break;
					} //end of main menu
				}	//end of logging in 
				
			}	//end of enhanced for loop for logging in
		}//	end of logging in
		else {	//log out
			System.out.println("GoodBye");
		}//end of sign up or sign in
		
		System.out.println();
		for(Customers c : customersList) {
			System.out.println(c + "\n");
		}
	}	//end of main
}	//end of class
