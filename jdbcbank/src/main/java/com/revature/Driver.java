package com.revature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.BankAccount;
import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.bl.Accounting;
import com.revature.bl.Accounts;
import com.revature.bl.Transactions;
import com.revature.bl.Users;
import com.revature.validation.Validate;

public class Driver {
	// no actual logging done in main, declared to load the logger property files
	private static final Logger logger = LogManager.getLogger(Driver.class);
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		int check = 0;
		boolean bankSysOn = true;
		while (bankSysOn) {
			do {
				logger.info("\n---------------------------------------------");
				logger.info("\tWelcome to the Banking App Part II");
				logger.info("\t\t1. To Login");
				logger.info("\t\t2. To Register");
				logger.info("\t\t3. To Quit");
				logger.info("---------------------------------------------\n");
				String input = scan.nextLine().trim();
				// check input
				check = Validate.safeParse(input);

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
			logger.info("---------------------------------------------");
			logger.info("\tEnter Username");
			username = scan.nextLine().trim();
			logger.info("\tEnter Password");
			password = scan.nextLine().trim();
			logger.info("---------------------------------------------");

			if ((user = Users.getUser(username, password)) != null)
				check = 0;
			else
				logger.info("\n\tInvalid, please input valid credentials!\n");
		} while (check < 0);

		userMenu(user);
	}

	public static void registerUserMenu() {

		String username = null, password = null;
		logger.info("---------------------------------------------");
		do {
			logger.info("\tCreate a valid username, must be unique and more than 6 characters long");
			username = scan.nextLine().trim();

		} while (!Validate.validUsername(username));

		logger.info("\tCreate a password");
		password = scan.nextLine().trim();

		logger.info("\tInput First Name");
		String fname = scan.nextLine().trim();

		logger.info("\tInput Last Name");
		String lname = scan.nextLine().trim();

		Date birthdate = null;

		do {
			logger.info("\tInput Birthdate mm/dd/yyyy");
			if ((birthdate = Validate.validDate(scan.nextLine())) != null)
				break;
			logger.info("\n\tInvalid date, please retry\n");
		} while (true);

		// super check
		logger.info("\tInput user code, if you have one");
		boolean areSuper = Validate.validSuperCode(scan.nextLine());

		User user = new User(0, username, password, fname, lname, birthdate, areSuper, null);

		// add user
		if (Users.addUser(user)) {
			logger.info("\n\tUser was successfully created");
			logger.info("\t\tUsername: " + user.getUsername());
			logger.info("\t\tFirst Name: " + user.getFirstName());
			logger.info("\t\tLast Name: " + user.getLastName());
			logger.info("\t\tBirthdate: " + Validate.formatter.format(user.getBirthdate()));
			if (areSuper)
				logger.info("\t\tSuper User Status: " + user.isSuper());
		} else
			logger.info("\n\tUser creation failed");
		logger.info("\n\n");

	}

	public static void userMenu(User user) {
		logger.info("\n\tWelcome " + user.getFirstName() + " " + user.getLastName() + "!");
		int check = 0;
		boolean condi = true;
		while (condi) {
			do {
				logger.info("---------------------------------------------");
				logger.info("\t1. View Bank Accounts and Balances");
				logger.info("\t2. Create Bank Account");
				logger.info("\t3. Delete Bank Account");
				logger.info("\t4. Logout");
				logger.info("\t5. View Transactions");
				if (user.isSuper()) {
					logger.info("\t6. Create User");
					logger.info("\t7. View Users");
				}
				logger.info("---------------------------------------------");
				check = Validate.safeParse(scan.nextLine().trim());

			} while (user.isSuper() && (check < 1 || check > 7) || !user.isSuper() && (check < 1 || check > 5));

			switch (check) {
			case 1:
				viewBankAccounts(user.getUserId());
				break;
			case 2:
				createBankAccount(user.getUserId());
				break;
			case 3:
				deleteAccount(user.getUserId());
				break;
			case 4:
				logout();
			case 5:
				viewTransactions(user.getUserId());
				break;
			case 6:
				createUser();
				break;
			case 7:
				readUsers();
				break;

			}
		}

	}

	public static void viewBankAccounts(int userId) {

		ArrayList<BankAccount> accounts = Accounts.getAllBankAccounts(userId);
		if (accounts == null || accounts.size() == 0) {
			logger.info("Sorry, there are no bank accounts tied to this user at this time");
			return;
		}

		// READ ONLY

		int choice = -1, accountSize = accounts.size();
		// Account Access Menu Loop
		while (true) {
			// User Input Loop
			do {
				logger.info("\n\tPlease enter the index of a bank account you would like to access");
				for (int i = 0; i < accountSize; i++) {
					logger.info("\t[" + (i + 1) + "] " + accounts.get(i));
				}
				logger.info("\t[0] To Go Back");
				// get user menu choice
				choice = Validate.safeParse(scan.nextLine().trim());
				// quit menu condi
				if (choice == 0)
					return;

			} while (choice < 0 || choice > accountSize);

			// Access account with index
			doBankAccountTransaction(accounts.get(choice - 1), userId);
		}
	}

	public static void createBankAccount(int userId) {

		String balance = "";
		do {
			logger.info("\tInput a Valid Initial Bank Account Balance");
			balance = scan.nextLine().trim();
		} while (Validate.validAmount(balance));

		// create bank account POJO
		BankAccount bAccount = new BankAccount(0, new BigDecimal(balance), null);

		// store in DB
		if (Accounts.addBankAccount(bAccount, userId)) {
			logger.info("\nBank Account was successfully created");
			logger.info("\tBank Account ID: " + bAccount.getAccountId());
			logger.info("\tStarting Balance: " + bAccount.getBalance());
		} else {
			logger.info("\tBank Account was not created");
		}
		logger.info("\n");

	}

	public static void deleteAccount(int userId) {
		int choice = -1, numAccounts = 0;

		while (true) {
			// check if there are any accounts left
			ArrayList<BankAccount> accounts = Accounts.getAllBankAccounts(userId);
			if (accounts == null || accounts.size() == 0) {
				logger.info("Sorry, there are no bank accounts tied to this user at this time");
				return;
			}
			numAccounts = accounts.size();

			// loop through existing accounts and display options
			do {
				logger.info("\n\tPlease enter the index number of account you would like to delete\n");
				logger.info("\n\tBank Account must have 0 balance to be deletable");
				for (int i = 0; i < accounts.size(); i++) {
					logger.info("\t[" + (i + 1) + "] to Delete " + accounts.get(i));
				}
				logger.info("\t[0] to quit");

				// get user menu choice
				choice = Validate.safeParse(scan.nextLine().trim());
				// quit menu condi
				if (choice == 0)
					return;

			} while (choice < 0 || choice > numAccounts);

			BankAccount accountPicked = accounts.get(choice - 1);

			// Check if balance is 0, before attempting delete
			if (accountPicked.getBalance().compareTo(BigDecimal.ZERO) > 0) {
				logger.info("\n\tBank Account's balance has to be 0 to remove");
				continue;
			}

			if (Accounts.deleteBankAccount(accountPicked.getAccountId(), userId)) {
				logger.info("\n\tDeleted Bank Account");
			} else {
				logger.info("\n\tUnable to delete Bank Account");
			}
		}

	}

	public static void doBankAccountTransaction(BankAccount ba, int userId) {
		int choice = -1;
		// Bank Account Option Menu Loop
		while (true) {
			// User Input Validation Loop
			do {
				logger.info("---------------------------------------------");
				logger.info("\n\t1. To Deposit");
				logger.info("\n\t2. To Withdraw");
				logger.info("\n\t3. To Transfer");
				logger.info("\n\t4. To Go Back");
				logger.info("---------------------------------------------");
				// get user menu choice
				choice = Validate.safeParse(scan.nextLine().trim());
				// quit this menu condition
				if (choice == 4)
					return;

			} while (choice < 0 || choice > 4);

			switch (choice) {
			case 1:
				depositMenu(ba, userId);
				break;
			case 2:
				withdrawMenu(ba, userId);
				break;
			case 3:
				transferMenu(ba, userId);
				break;
			}
		}
	}

	public static void viewTransactions(int userId) {

		// get all account ids
		ArrayList<BankAccount> accountList = Accounts.getAllBankAccounts(userId);
		if (accountList == null || accountList.size() == 0) {
			logger.info("Sorry, this user does not have any bank accounts currently");
			return;
		}
		// get all transactions associated with those
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();

		for (BankAccount ba : accountList) {
			transactions.addAll((ArrayList<Transaction>) Transactions.getTransactions(ba.getAccountId()));
		}
		if (transactions.size() == 0) {
			logger.info("Sorry, there are no transactions tied to this user's accounts at this time");
			return;
		}

		for (Transaction t : transactions)
			logger.info("\t" + t);

		logger.info("\tEnter Any Key To Go Back");
		scan.nextLine();

	}

	public static void createUser() {
		logger.info("\n\tInput Username, Password, firstname, lastname, birthdate (mm/dd/yyy)");
		String info = scan.nextLine();
		logger.info("\tAre they a super user? true/false");
		String superUser = scan.nextLine();

		String[] userInfo = info.split(",");

		User user = new User(0, userInfo[0], userInfo[1], userInfo[2], userInfo[3], Validate.validDate(userInfo[4]),
				Validate.validBoolean(superUser), null);
		if (Users.addUser(user))
			logger.info("\n\tAdded User " + user + " to the Database");
		else
			logger.info("\n\tFailed to Add user " + user + " to the Database");

	}

	public static void readUsers() {

		ArrayList<User> users = (ArrayList<User>) Users.getAllUsers();
		if (users == null || users.size() == 0) {
			logger.info("Sorry, there are no users registered at this time");
			return;
		}
		int choice = 0, userSize = users.size();
		// User Access Menu Loop
		while (true) {
			// Admin Input Loop
			do {
				logger.info("\n\tPlease enter the index of a user account you would like to access");
				for (int i = 0; i < userSize; i++) {
					logger.info("\t[" + (i + 1) + "] " + users.get(i));
				}
				logger.info("\t[0] To Go Back");
				// get user menu choice
				choice = Validate.safeParse(scan.nextLine().trim());
				// quit menu condi
				if (choice == 0)
					return;

			} while (choice < 0 || choice > userSize);

			// Access user with index
			editUser(users.get(choice - 1));
		}
	}

	private static void depositMenu(BankAccount ba, int userId) {
		String amount = "";
		do {
			logger.info("\tInput a Valid Deposit Amount");
			amount = scan.nextLine().trim();
		} while (Validate.validAmount(amount));

		if (Accounting.createDeposit(new BigDecimal(amount), ba, userId)) {
			logger.info("\n\tDeposited " + amount);
		} else {
			logger.info("\n\tAmount was not deposited");
		}

	}

	private static void withdrawMenu(BankAccount ba, int userId) {
		String amount = "";
		do {
			logger.info("\tInput a Valid Withdraw Amount");
			amount = scan.nextLine().trim();
		} while (Validate.validAmount(amount));

		if (Accounting.createWithdraw(new BigDecimal(amount), ba, userId)) {
			logger.info("\n\tWithdrew " + amount);
		} else {
			logger.info("\n\tAmount was not withdrawn");
		}
	}

	private static void transferMenu(BankAccount ba, int userId) {

	}

	private static void editUser(User user) {
		int choice = -1;
		do {
			logger.info("---------------------------------------------");
			logger.info("\n\t1. To Update User Info");
			logger.info("\n\t2. To Delete This User");
			logger.info("\n\t3. To Go Back");
			logger.info("---------------------------------------------");
			choice = Validate.safeParse(scan.nextLine());
			if (choice == 3)
				return;

		} while (choice < 0 || choice > 3);
		if (choice == 2) {
			deleteUser(user.getUserId());
		} else
			updateUser(user);

	}

	private static void deleteUser(int userId) {
		if (Users.deleteUser(userId))
			logger.info("\n\tUser with id " + userId + " was deleted");
		else
			logger.info("\n\t User was not deleted");

	}

	private static void updateUser(User user) {
		Method methodFound = null;

		while (true) {
			logger.info("\n\tEnter user attribute you want to edit");
			String fieldName = scan.nextLine();

			// find a match
			for (Method m : user.getClass().getMethods()) {
				// match input choice to a method name in the Person Class
				if (m.getName().toLowerCase().contains(fieldName.toLowerCase()) && m.getName().startsWith("set")) {
					methodFound = m;
				}
			}
			logger.info("\t" + methodFound.getName());
			logger.info("\tIs this the field you want to edit?");

			logger.info("\tEnter value you would like to change it to");
			String fieldValue = scan.nextLine();
			try {
				methodFound.invoke(user, fieldValue);
				// update user in database as well
				Users.updateUser(user);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error(e.getMessage());
			}
			logger.info("\tEnter 0 to exit, or anything else to continue editing this user");
			if (scan.nextLine().equals("0"))
				return;
		}

	}

	public static void logout() {
		logger.info("\nYou are now logged out, have a great day!\n");
		System.exit(0);

	}

}
