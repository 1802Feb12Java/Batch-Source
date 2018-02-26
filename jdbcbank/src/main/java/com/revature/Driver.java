package com.revature;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.BankAccount;
import com.revature.beans.User;
import com.revature.dao.bl.Accounts;
import com.revature.dao.bl.Users;
import com.revature.exceptions.InvalidMenuOptionException;
import com.revature.validation.Validate;

public class Driver {
	// no actual logging done in main, declared to load the logger property files
	private static final Logger logger = LogManager.getLogger(Driver.class);
	private static Scanner scan = new Scanner(System.in);
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		int check = 0;
		boolean bankSysOn = true;
		while (bankSysOn) {
			do {
				System.out.println("\n---------------------------------------------");
				System.out.println("\tWelcome to the Banking App");
				System.out.println("\t\t1. To Login");
				System.out.println("\t\t2. To Register");
				System.out.println("\t\t3. To Quit");
				System.out.println("---------------------------------------------\n");
				String input = scan.nextLine();
				// check input
				try {
					check = Validate.safeParse(input);
				} catch (InvalidMenuOptionException e) {
					e.printStackTrace();
				}

			} while (check < 1 || check > 3);
			// use input to give next menu
			switch (check) {
			case 1:
				loginMenu();
				break;
			case 2:
				registerUserMenu();
				break;
			case 3:
				logout();
			}
		}

	}

	public static void loginMenu() {
		int check = -1;
		String username = null, password = null;
		User user = null;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("\tEnter Username");
			username = scan.nextLine();
			System.out.println("\tEnter Password");
			password = scan.nextLine();
			System.out.println("---------------------------------------------");

			if ((user = Users.getUser(username, password)) != null)
				check = 0;
			else
				System.out.println("\n\tInvalid, please input valid credentials!\n");
		} while (check < 0);

		userMenu(user);
	}

	public static void registerUserMenu() {

		String username = null, password = null;
		System.out.println("---------------------------------------------");
		do {
			System.out.println("\tCreate a valid username, must be unique and more than 6 characters long");
			username = scan.nextLine();

		} while (!Validate.validUsername(username));

		System.out.println("\tCreate a password");
		password = scan.nextLine();

		System.out.println("\tInput First Name");
		String fname = scan.nextLine();

		System.out.println("\tInput Last Name");
		String lname = scan.nextLine();

		Date birthdate = null;

		do {
			System.out.println("\tInput Birthdate mm/dd/yyyy");

			try {
				java.util.Date date = formatter.parse(scan.nextLine());
				birthdate = new Date(date.getTime());
				break;
			} catch (ParseException e) {
				// e.printStackTrace();
				System.out.println("\n\tInvalid date, please retry\n");
			}
		} while (true);

		// super check
		System.out.println("\tInput user code, if you have one");
		boolean areSuper = Validate.validSuperCode(scan.nextLine());

		User user = new User(0, username, password, fname, lname, birthdate, areSuper, null);

		// add user
		if (Users.addUser(user)) {
			System.out.println("\n\tUser was successfully created");
			System.out.println("\t\tUsername: " + user.getUsername());
			System.out.println("\t\tFirst Name: " + user.getFirstName());
			System.out.println("\t\tLast Name: " + user.getLastName());
			System.out.println("\t\tBirthdate: " + formatter.format(user.getBirthdate()));
			if (areSuper)
				System.out.println("\t\tSuper User Status: " + user.isSuper());
		} else
			System.out.println("\n\tUser creation failed");
		System.out.println("\n\n");

	}

	public static void userMenu(User user) {
		System.out.println("\n\tWelcome " + user.getFirstName() + " " + user.getLastName() + "!");
		int check = 0;
		boolean condi = true;
		while (condi) {
			do {
				System.out.println("---------------------------------------------");
				System.out.println("\t1. View Accounts and Balances");
				System.out.println("\t2. Create Account");
				System.out.println("\t3. Delete Account");
				System.out.println("\t4. Deposit or Withdraw from Account");
				System.out.println("\t5. Logout");
				System.out.println("\t6. View Transactions");
				if (user.isSuper()) {
					System.out.println("\t7. Create User");
					System.out.println("\t8. Read User");
					System.out.println("\t9. Update User");
					System.out.println("\t10. Destroy User");
				}
				System.out.println("---------------------------------------------");
				try {
					check = Validate.safeParse(scan.nextLine());
				} catch (InvalidMenuOptionException e) {
					e.printStackTrace();
				}

			} while (user.isSuper() && (check < 1 || check > 10) || !user.isSuper() && (check < 1 || check > 6));

			switch (check) {
			case 1:
				viewBankAccounts(user);
				break;
			case 2:
				createBankAccount(user);
				break;
			case 3:
				deleteAccount();
				break;
			case 4:
				accountTransaction();
				break;
			case 5:
				logout();
			case 6:
				viewTransaction();
				break;
			case 7:
				createUser();
				break;
			case 8:
				readUser();
				break;
			case 9:
				updateUser();
				break;
			case 10:
				deleteUser();
				break;

			}
		}

	}

	public static void viewBankAccounts(User user) {

		ArrayList<BankAccount> accounts = Accounts.getAllBankAccounts(user.getUserId());
		if (accounts == null || accounts.size() == 0) {
			System.out.println("Sorry, there are no accounts tied to this user at this time");
			return;
		}
		for (BankAccount ba : accounts) {
			System.out.println("\t" + ba);
		}
		System.out.println("\n");
	}

	public static void createBankAccount(User user) {

		String balance = "";
		do {
			System.out.println("\tInput a Valid Initial Bank Account Balance");
			balance = scan.nextLine();
		} while (Validate.validAmount(balance));

		// create bank account POJO
		BankAccount bAccount = new BankAccount(0, new BigDecimal(balance), null);
		// store in DB
		Accounts.addBankAccount(bAccount, user.getUserId());

	}

	public static void deleteAccount() {
	}

	public static void accountTransaction() {
	}

	public static void viewTransaction() {
	}

	public static void createUser() {
	}

	public static void readUser() {
	}

	public static void updateUser() {
	}

	public static void deleteUser() {
	}

	public static void logout() {
		System.out.println("\nYou are now logged out, have a great day!\n");
		System.exit(0);

	}

}
