/*  This is a class file that has methods for registering, opening an account, and creating a joint account, 
 *  and withdrawing, depositing, and transferring funds.
 * 
 *  @author Dominic Nguyen
 */

package com.revature.project1;

//import java.util.ArrayList;
import java.util.Scanner;

public class BankingApp {

	// constructor
	public BankingApp(){
		
	}
	
	public static Scanner scan = new Scanner(System.in);
	
	public int bankingMenu() {
		int selection = 0;
		
		while(selection < 1 || selection > 3) {
			System.out.println("WELCOME TO THE BANK OF AMERICA");
			System.out.println("-----------------------------------------------");
			System.out.println("Please make a selection from the options below:");
			System.out.println("1. Register");
			System.out.println("2. Open an Account");
			System.out.println("3. Exit");
		
			selection = scan.nextInt();
			scan.nextLine();
			
			if(selection < 1 || selection > 3) {
				System.out.println("Invalid input. Please try again.");
			}
		}
		
		return selection;
	}
	
	public String[] register(String[] accountInfo, int i) {
		//String[] accountInfo = new String[2];
		
		System.out.println("Username: ");
		accountInfo[0] = scan.nextLine();
		//array.add(accountInfo[0]);
		
		System.out.println("Password: ");
		accountInfo[1] = scan.nextLine();
		//array.add(accountInfo[1]);
		
		
		return accountInfo;
	}
	
	public String[] openAccount(String[] accountInfo, int i) {
		//String[] accountInfo = new String[3];
		String str = "";
		
		System.out.println("Create a username: ");
		accountInfo[i] = scan.nextLine();
		//array.add(accountInfo[0]);
		
		System.out.println("Create a password: ");
		accountInfo[i+1] = scan.nextLine();
		//array.add(accountInfo[1]);
		
		System.out.println("Are you a customer or employee?");
		System.out.println("Type c for customer or e for employee");
		str = scan.nextLine();
		
		if(str.equals("c")) {
			//array.add("customer");
			accountInfo[i+2] = "customer";
		}
		else if(str.equals("e")) {
			//array.add("employee");
			accountInfo[i+2] = "employee";
		}
		
		
		return accountInfo;
	}
	
	public String[] jointAccount(String[] accountInfo, int i) {
		String answer = "";
		String str = "";
		
		System.out.println("Would you like to apply for a joint account (yes or no)?");
		answer = scan.nextLine();
		
		if(answer.equals("yes")) {
			System.out.println("Enter a username:");
			accountInfo[i] = scan.nextLine();
			//array.add(accountInfo[0]);
			
			System.out.println("Enter a password");
			accountInfo[i+1] = scan.nextLine();
			//array.add(accountInfo[1]);
			
			System.out.println("Are you a customer or employee?");
			System.out.println("Type c for customer or e for employee");
			str = scan.nextLine();
			
			if(str.equals("c")) {
				//array.add("customer");
				accountInfo[i+2] = "customer";
			}
			else if(str.equals("e")) {
				//array.add("employee");
				accountInfo[i+2] = "employee";
			}
		}
		return accountInfo;
	}
	
	public int bankOptions() {
		int selection = 0;
		
		while(selection < 1 || selection > 4) {
			System.out.println("BANK OPTIONS:");
			System.out.println("-------------------------");
			System.out.println("Please make a selection from the options below:");
			System.out.println("1. Withdraw");
			System.out.println("2. Deposit");
			System.out.println("3. Transfer");
			System.out.println("4. Exit");
			selection = scan.nextInt();
			
			if(selection < 1 || selection > 4) {
				System.out.println("Invalid input. Please try again");
			}
		}
		
		return selection;
	}
	
	public double withdraw(double totalAmount) {
		double amount = 0;
		//double oldValue = 0;
		//double newValue = 0;
		
		System.out.println("The current balance for your account is " + totalAmount);
		System.out.println("Enter the amount to withdraw from your account:");
		
		amount = scan.nextInt();
		totalAmount = totalAmount - amount;
		
		System.out.println("Your new balance is " + totalAmount);
		
		return totalAmount;
	}
	
	public double deposit(double totalAmount) {
		double amount = 0;
		
		System.out.println("The current balance for your account is " + totalAmount);
		System.out.println("Enter the amount to deposit to your account:");
		
		amount = scan.nextInt();
		totalAmount = totalAmount + amount;
		
		System.out.println("Your new balance is " + totalAmount);
		
		return totalAmount;
	}
	
	public double transfer(double totalAmount) {
		double amount = 0;
		
		System.out.println("The current balance for your account is " + totalAmount);
		System.out.println("Enter the amount to transfer:");
		
		amount = scan.nextInt();
		totalAmount = totalAmount - amount;
		
		System.out.println("Your new balance is " + totalAmount);
		
		return totalAmount;
	}
}
