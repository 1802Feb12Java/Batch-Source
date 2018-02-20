package userPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class User {

	public static void runThis(String user, String pwd) {
		
		Scanner sc = new Scanner(System.in);
		LoginDataNode temp = LoginDataOps.traverse(user, pwd);			// looks for account with that user name and password
		
		if(((user != null) || (pwd != null)) && temp == null) {
			
			System.out.println("Your account was not found.");
		}
		
		System.out.println();
		
		File f = new File("src\\\\userPackage\\\\requests.txt");
		FileOutputStream outputStream = null;
		
		try {
			
			outputStream = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		while(true) {		// continuing menu options for customer
			
			System.out.println("What would you like to do today?");
			System.out.println("Please choose one of the following options: ");
			System.out.println("\tEnter 1 for applying to open an account."
					+ "\n\tEnter 2 for checking your balance."
					+ "\n\tEnter 3 for deposits to your account."
					+ "\n\tEnter 4 for withdrawals from your account."
					+ "\n\tEnter 5 for transfers between accounts."
					+ "\n\tEnter 6 to cancel your account."
					+ "\n\tEnter 7 to exit the banking transactions.");
	
			int in = sc.nextInt();
			System.out.println(in);
			
			switch(in) {
			
			case 1:			// fill out an application
					
				System.out.println("Enter your name:");
				String name = sc.next();
				
			    try {
				
			    	outputStream.write((name+"\n").getBytes());
			    	
			    	System.out.println("Enter your birth date: ");
			    	name = sc.next();
			    	outputStream.write((name+"\n").getBytes());
			    	
			    	System.out.println("Enter your age: ");
			    	name = sc.next();
			    	outputStream.write((name+"\n").getBytes());
			    	
			    	System.out.println("Enter your phone number: ");
			    	name = sc.next();
			    	outputStream.write((name+"\n").getBytes());
			    
			    	System.out.println("Your request will be answered by an Employee once they log in.");
			    	outputStream.write("\n".getBytes());
			    	System.out.println("\n\n\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				break;
				
			case 2:		// check a customer's balance
				
				if(temp == null) {
					
					System.out.println("You did not specify account details");
					break;
				}
				
				System.out.println("Your balance is currently: "+temp.Balance);
				System.out.println();
				break;
				
			case 3:		// deposit an amount
				
				if(temp == null) {
					
					System.out.println("You did not specify account details");
					break;
				}
				
				System.out.println("How much money would you like to deposit?");
				double deposits = sc.nextDouble();
				temp.Balance = temp.Balance + deposits;				// file users doesn't change amount ******************************
				
				System.out.println("You have deposited an additional "+deposits+" dollars successfully.");
				System.out.println("Your balance is now: "+temp.Balance+"\n");
				break;
				
			case 4:		// withdraw an amount
				
				if(temp == null) {
					
					System.out.println("You did not specify account details");
					break;
				}
				
				System.out.println("How much money would you like to withdraw?");
				double withdraw = sc.nextDouble();
				temp.Balance = temp.Balance - withdraw;							// file users doesn't change amount ******************************
				
				System.out.println("You have withdrawn "+withdraw+" dollars successfully.");
				System.out.println("Your balance is now: "+temp.Balance+"\n");
				break;
				
			case 5:		// transfer an amount to another account
				
				if(temp == null) {
					
					System.out.println("You are not specifying account details");
					break;
				}
				
				System.out.println("What name is the account under?");
				String names = sc.next();
				LoginDataNode ptr = LoginDataOps.check(names);
				
				if(ptr == null) {
					
					System.out.println("The account was not found. Try again.");
					break;
				}
				
				System.out.println("How much money would you like to transfer?");
				double transfer = sc.nextDouble();
				temp.Balance = temp.Balance - transfer;								// file users doesn't change amount ******************************
				ptr.Balance = ptr.Balance + transfer;
				
				System.out.println("Your transfer was done successfully.");
				System.out.println(transfer+" dollars was transfered from your account into "+names+"'s account.");
				System.out.println("Your balance is now: "+temp.Balance+"\n");
				break;
				
			case 6:
				
				if(temp == null) {
					
					System.out.println("You did not specify account details");
					break;
				}
				
				LoginDataNode iter = LoginDataOps.front;
				
				if(iter ==  null) {
					
					System.out.println("No account here.");
				} else if(iter == temp) {
					
					LoginDataOps.front = null;
				}
				
				while(iter.next != temp) {
					
					iter = iter.next;
				}
				
				if(iter.next == temp) {
					
					iter.next = temp.next;
				}
				
				System.out.println("Your account has been deleted.");
				break;
				
			case 7:		// quit the menu
				
				System.out.println("Ok, thank you for your business.");
				return;
				
			default:		// for all other inputs
				
				System.out.println("That's not a valid input. Please try again.");
				break;
			}
			
		}
	}
}
