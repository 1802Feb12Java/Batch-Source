package com.revature.banking.jdbc.view;

import java.io.Serializable;
import java.util.Scanner;

import com.revature.banking.jdbc.controller.Bank;
import com.revature.banking.jdbc.model.Admin;
import com.revature.banking.jdbc.model.Customer;
import com.revature.banking.jdbc.model.Employee;
import com.revature.banking.jdbc.model.User;
import com.revature.banking.view.PersistenceView;

public class UserView extends PersistenceView implements Serializable {
	private static final long serialVersionUID = -4602018659434205045L;
	protected Bank bank;
	public static void launch(Scanner scan) {
		Bank bank = Bank.loadBank();
		UserView banking = new UserView(bank);
		banking.welcome(scan);		
	}
	public UserView(Bank bank) {
		super();
		this.bank=bank;
	}
	public void welcome(Scanner scan) {
		System.out.println("Welcome to the Bank!! \nSelect one of the following to continue:\n"
				+ "1:\tLogin\n"
				+ "2:\tRegister\n"
				+ "3:\tExit");
		int choice = this.getNumber(scan);
		if(choice ==1) {
			this.login(scan);
		}else if (choice ==2) {
			new CustomerView(this.bank).registerCustomer(scan);
		}else if(choice == 3) {
			selectPersistence( scan);
		}else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.welcome(scan);
		}
		this.welcome(scan);
	}	
	
	public void login(Scanner scan) {
		boolean success = false;
		System.out.println("\nLOGIN\n");
		while(!success) {
			System.out.print("Username:\t");
			String username=scan.nextLine().toLowerCase();
			if (username.equals("exit"))
				return;
			if(!bank.usernameExists(username)) {
				System.out.println("The username was not found. Try again!!");
				return;
			}
			System.out.print("Password:\t");
			String password = scan.nextLine();
			if (password.equals("exit"))
				return;
			User user = bank.getUserByUsername(username);
			if (user.checkPassword(password)) {
				success=true;
				if(user.getRole().equals("Admin")) {
						new AdminView(bank).home((Admin) user, scan);
				}else if (user.getRole().equals("Employee")) {
						new EmployeeView(bank).home((Employee) user, scan);
				}else if (user.getRole().equals("Customer")) {
						new CustomerView(bank).home((Customer) user, scan);
				}
			}else
				System.out.println("Incorrect password!! Try again");
		}
		return;
	}	
	public void userProfile(User user, Scanner scan) {
		//	update username
		//	update password
		System.out.println("\nHello, " + user.getUsername() 
				+", what would you like to do?\n"
				+ "1:\tChange username\n"
				+ "2:\tChange password\n"
				+ "3:\tLogout / Back to main menu");
		int choice = getNumber(scan);
//				0;
//		try {
//			choice = Integer.parseInt(scan.nextLine().trim());
//		}catch(NumberFormatException e) {
//			System.out.println("Incorrect choice detected!!\nTry again...");
//			this.userProfile(user, scan);
//			return;
//		}	
		if(choice ==1) {
			String username = this.getUsername(scan);
			user.setUsername(username);
			this.bank.updateUsername(user);
		}else if (choice == 2){
			String password = this.getPassword(scan);
			user.setPassword(password);
			bank.updatePassword(user);
		}else if (choice !=3) {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.userProfile(user, scan);
		}
	}
	
	
	protected String getUsername(Scanner scan) {
		boolean success = false;
		String username="";
		System.out.println("Enter a username\n");
		while(!success) {
			System.out.print("Username:\t");
			username = scan.nextLine().trim().replaceAll("\\s", "").toLowerCase();
			System.out.println("bank:\t" + bank.toString());
			if(bank.usernameExists(username))
				System.out.println("that username is taken. Try again...");
			else
				success= true;
		}
		return username;
	}
	protected String getPassword(Scanner scan) {
		String password = "";
		boolean success=false;
		System.out.println("Enter a password");
		while(!success) {
			System.out.print("Password:\t");
			password =scan.nextLine();
			System.out.println("Confirm password");
			System.out.print("Confirm password:\t");
			String password2 =scan.nextLine();
			if (password.equals(password2))
				success=true;
			else
				System.out.println("Passwords do not match. Try again...");
			
		}
		return password;
	}
	protected String[] getFullName(Scanner scan) {
		String firstName = "";
		String lastName = "";
		boolean success=false;
		while(!success) {
			System.out.println("Enter your first name");
			System.out.print("First name:\t");
			firstName =scan.nextLine().trim();
			System.out.println("Enter your last name");
			System.out.print("Last name:\t");
			lastName =scan.nextLine().trim();
			System.out.println("Is correct: " + firstName + " " + lastName);
			success = yesOrNo(scan);
		}
		return new String[] {firstName, lastName};
	}
	protected String getEmail(Scanner scan) {
		String email="";
		boolean success=false;
		while(!success) {
			System.out.println("Enter your email");
			System.out.print("Email:\t");
			email =scan.nextLine().trim();
			System.out.print("Is this email correct:\t" + email);
			success=yesOrNo(scan);
		}
		return email;
	}
	protected boolean yesOrNo(Scanner scan) {
		boolean success =false;
		boolean confirm = false;
		while(!confirm) {
			System.out.print("\nyes or no:\t");
			String stringConfirm = scan.nextLine().trim().toLowerCase();
			if(stringConfirm.equals("yes")) {
				confirm=true;
				success=true;
			}
			else if(stringConfirm.equals("no")) {
				System.out.println("Try again!!");
				confirm=true;
			}
			else
				System.out.println("Enter \"yes\" or \"no\"");
		}
		return success;
	}
	
	
}
