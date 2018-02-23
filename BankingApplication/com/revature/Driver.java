package com.revature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Consumer;

import com.revature.banking.BankAccount;
import com.revature.banking.BankAccounts;
import com.revature.banking.Request;
import com.revature.banking.Requests;
import com.revature.banking.people.Admin;
import com.revature.banking.people.Customer;
import com.revature.banking.people.Employee;
import com.revature.banking.people.People;
import com.revature.banking.people.Person;
import com.revature.banking.people.User;
import com.revature.banking.people.Users;
import com.revature.database.DataStore;
import com.revature.validation.Validate;

public class Driver {
	private static Scanner scan = new Scanner(System.in);
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		boolean keepBanking = true;
		while (keepBanking) {

			boolean optionPicked = false;
			Person p = null;
			do {
				// Ask User Log in or Register
				drawLoginCow();
				System.out.println("+============================+");
				System.out.println("|          MAIN MENU         |");
				System.out.println("+============================+");
				System.out.println("| Options:                   |");
				System.out.println("|    0. to quit              |");
				System.out.println("|    1. to login             |");
				System.out.println("|    2. to create an account |");
				System.out.println("+============================+");
				String choice = scan.nextLine();
				switch (choice) {
				case "0":
					quit();
				case "1":
					if ((p = promptCredentials()) != null)
						optionPicked = true;
					break;
				case "2":
					registerUser();
					break;
				default:
					System.out.println("Invalid option selected, please re-try");
				}
			} while (!optionPicked);

			System.out.println("************************************");
			System.out.println("\n\tWelcome " + p.getName() + "!\n\n");
			System.out.println("************************************");

			// give different menu depending on what kind of user logged in
			if (p instanceof Customer) {
				openCustomerAccount((Customer) p);
			} else if (p instanceof Employee) {
				boolean condi = true;
				while (condi) {
					// Employees can view customer info: account info, balances, personal info
					// Employees can approve/deny open applications for accounts
					System.out.println(
							"\n~~EMPLOYEE MENU~~\n0 to logout\n1 to view customer info\n2 to approve/deny account applications");
					String choice = scan.nextLine();
					switch (choice) {
					case "0":
						condi = false;
						break;
					case "1":
						viewCustomerInfoMenu();
						break;
					case "2":
						requestMenu();
						break;
					default:
						System.out.println("Invalid option selected, please re-try");
					}
				}

			} else if (p instanceof Admin) {
				// Bank admins should be open to view and edit accounts:
				// approve/deny accounts, widthdraw, deposit, transfer from all accounts, cancel
				// accounts
				boolean condi = true;
				while (condi) {
					System.out.println(
							"\n~ADMIN MENU~\n1 to open customer accounts\n2 to approve/deny account applications\n"
									+ "3 to withdraw, deposit, or transfer from an account\n4 to cancel an account\n"
									+ "5 to print lists\n0 to logout");
					String choice = scan.nextLine();
					switch (choice) {
					case "0":
						condi = false;
						break;
					case "1":
						System.out.println("Please enter an account name you'd like to view/edit");
						String accountName = scan.nextLine();
						Person account = People.getPersonByName(accountName);
						if (account == null) {
							System.out.println("Sorry that account doesn't exist");
							break;
						}
						accountMenu(account);
						break;
					case "2":
						requestMenu();
						break;
					case "3":
						System.out.println("Please enter an owner's account name of the account you'd like to view");
						String customerName = scan.nextLine();
						Customer customer = (Customer) People.getPersonByName(customerName);
						openCustomerAccount(customer);
						break;
					case "4":
						cancelAccount();
						break;
					case "5":
						printList();
						break;
					default:
						System.out.println("Invalid option selected, please re-try");
					}
				}
			}
		}

	}

	public static Person promptCredentials() {
		User u = null;
		boolean validCredentials = false;
		do {
			System.out.println("Please enter a valid username");
			String username = scan.nextLine();
			System.out.println("Please enter a valid password");
			String password = scan.nextLine();

			// check if valid
			if ((u = Users.checkCredentials(username, password)) != null) {
				// get corresponding user obj
				return Users.getPerson(u);
			} else {
				System.out.println("Invalid credentials, hit enter to retry\nor enter 0 to go back to main menu");
				String option = scan.nextLine();
				if (option.equals("0"))
					return null;
			}
		} while (!validCredentials);
		return null;
	}

	public static boolean registerUser() {

		// Create Credentials First
		String username = "";
		do {
			System.out.println("\nCreate a username, must be more than 5 characters long");
			username = scan.nextLine();
			if (Validate.validUsername(username))
				break;
			System.out.println("Invalid username, try a differnt one");
		} while (true);

		String password = "";
		do {
			System.out.println("\nCreate a password, must be more than 5 characters long and contain a number");
			password = scan.nextLine();
			if (Validate.validPassword(password))
				break;
			System.out.println("Invalid password, please try again");
		} while (true);
		// Then account information
		System.out.println("\nEnter Full Name");
		String name = scan.nextLine();

		System.out.println("\nEnter Address");
		String address = scan.nextLine();
		LocalDate birthdate = null;

		do {
			System.out.println("Birthdate mm/dd/yyyy");

			try {
				Date date = formatter.parse(scan.nextLine());
				birthdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				break;
			} catch (ParseException e) {
				// e.printStackTrace();
				System.out.println("invalid, retry");
			}
		} while (true);
		System.out.println("Enter user code (if applicable, otherwise just hit enter to skip)");
		String userType = scan.nextLine();

		// using received input create the correct Person object
		Person p;
		switch (userType) {
		case "admin":
			p = new Admin(name, address, birthdate);
			break;
		case "employee":
			p = new Employee(name, address, birthdate);
			break;
		default:
			p = new Customer(name, address, birthdate);
			requestAccount((Customer) p);
			System.out.println("Please wait 1-5 days for your account to be approved");

		}

		// create credentials
		User user = new User(username, password, p.getPersonId());
		Users.addUser(user);

		// add the new Person to the datastore
		People.addPersonToFile(p);

		return true;
	}

	public static void requestMenu() {
		// approve/deny requests
		// fetch all requests and output one by one
		ArrayList<Request> reqs = DataStore.readRequestsFromFile();
		if (reqs == null || reqs.isEmpty()) {
			System.out.println("There are no open requests at this time");
			return;
		}
		for (Request r : reqs) {
			System.out.println(r.toString());
			System.out.println("1 to approve\n2 to deny\n3 to skip\n0 to go back");
			String select = scan.nextLine();
			switch (select) {
			case "0":
				return;
			case "1":
				r.approveRequest();
				break;
			case "2":
				r.denyRequest();
				break;
			case "3":
				System.out.println("Skipped");
				break;
			default:
				System.out.println("No valid option selected, skipping to next");
			}
		}
	}

	public static void viewCustomerInfoMenu() {
		// View customer info
		System.out.println("Enter Customer Name");
		String customerName = scan.nextLine();
		System.out.println("1 to view account info\n2 to view balances\n3 personal info");
		String option = scan.nextLine();
		Person person = People.getPersonByName(customerName);
		switch (option) {
		case "1":
			System.out.println(Users.getUser(person));
			break;
		case "2":
			if (!(((Customer) person).getBankAccountId() == null))
				System.out.println(BankAccounts.findAccount(((Customer) person).getBankAccountId()));
			break;
		case "3":
			System.out.println(person);
			break;

		default:
			System.out.println("Invalid option selected");
		}
	}

	public static void openCustomerAccount(Customer c) {
		BankAccount customerAccount = BankAccounts.findAccount(c.getBankAccountId());
		// check if their account has been approved
		if (customerAccount == null) {
			System.out.println("Sorry, your account has not been approved yet, please come back later");
			quit();
		}
		// customer can withdraw, deposit, and transfer
		while (true) {
			System.out.println(
					"\n~CUSTOMER MENU~\nPress 1 to withdraw\n2 to deposit\n3 to transfer\n4 to check balance\n0 to logout");
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				System.out.println("Please enter the amount you would like to withdraw");
				double withdrawAmount = Double.parseDouble(scan.nextLine());

				if (!customerAccount.withdraw(withdrawAmount)) {
					System.out.println("Sorry we were unable to withdraw that amount, please try again");
				}
				BankAccounts.updateAccountFromFile(customerAccount);
				break;
			case "2":
				System.out.println("Please enter the amount you would like to deposit");
				double depositAmount = Double.parseDouble(scan.nextLine());
				if (!customerAccount.deposit(depositAmount)) {
					System.out.println("Sorry we were unable to deposit that amount, please try again");
				}
				BankAccounts.updateAccountFromFile(customerAccount);
				break;

			case "3":
				System.out.println("Please enter the amount you would like to transfer");
				double transferAmount = Double.parseDouble(scan.nextLine());
				System.out.println("and the account name to transfer to");
				String accountName = scan.nextLine();

				if (!customerAccount.transfer(transferAmount, accountName)) {
					System.out
							.println("Sorry we were unable to transfer that amount to that account, please try again");
				}
				BankAccounts.updateAccountFromFile(customerAccount);
				break;
			case "4":
				System.out.println(customerAccount.getBalance());
				break;
			case "0":
				// save banking changes
				BankAccounts.updateAccountFromFile(customerAccount);
				return;
			default:
				System.out.println("Invalid option selected, please re-try");
			}
		}

	}

	public static boolean accountMenu(Person account) {
		while (true) {
			User user = Users.getUser(account);
			System.out.println("Viewing: " + account);
			System.out.println(user);
			System.out.println(
					"\nACCOUNT MENU\n1 to edit account info\n2 to edit credentials info\n0 to go back to menu");
			String choice = scan.nextLine();
			switch (choice) {
			case "0":
				return true;
			case "1":
				System.out.println("Enter field name you want to edit");
				String fieldName = scan.nextLine();
				System.out.println("Value to change it to");
				String fieldValue = scan.nextLine();
				for (Method m : account.getClass().getMethods()) {
					// match input choice to a method name in the Person Class
					if (m.getName().toLowerCase().contains(fieldName.toLowerCase()) && m.getName().startsWith("set")) {
						try {
							m.invoke(account, fieldValue);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				break;
			case "2":
				System.out.println("Enter field name you want to edit");
				String fieldName2 = scan.nextLine();
				System.out.println("Value to change it to");
				String fieldValue2 = scan.nextLine();
				for (Method m : user.getClass().getMethods()) {
					// match input choice to a method name in the Person Class
					if (m.getName().toLowerCase().contains(fieldName2.toLowerCase()) && m.getName().startsWith("set")) {
						try {
							m.invoke(user, fieldValue2);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				break;
			default:
				System.out.println("No valid option selected, please retry");
			}
		}
	}

	public static void requestAccount(Customer customer) {

		while (true) {
			System.out.println("1 for new account\n2 for joint account with an existing account");
			String option = scan.nextLine();
			switch (option) {
			case "1":
				Request req = new Request(customer.getPersonId(), customer, null);
				Requests.addRequestToFile(req);
				System.out.println("Thank you, your application has been submitted");
				return;
			case "2":
				System.out.println("Please input the Joint Account #");
				String accountNumber = scan.nextLine();

				// find matching bank account
				if (BankAccounts.findAccount(accountNumber) != null) {
					// submit new requests to file
					Request jointReq = new Request(customer.getPersonId(), customer, accountNumber);
					Requests.addRequestToFile(jointReq);
					System.out.println("Thank you, your joint application has been submitted");
					return;
				} else
					System.out.println("Sorry that account doesn't seem to exist, please try again");
				break;
			default:
				System.out.println("No valid option selected, please retry");
			}
		}

	}

	public static boolean cancelAccount() {
		System.out.println("Input the accountId of the account you'd like to cancel");
		String account = scan.nextLine();

		// Find their personal account, credentials to remove
		if (!People.removePersonFromFile(account)) {
			System.out.println("Unable to find and remove a person with that id");
		}
		if (!Users.removeUserFromFile(account)) {
			System.out.println("Unable to find and remove a user with that id");
		}

		return false;
	}

	private static Consumer<ArrayList<?>> printList = (ArrayList<?> list) -> {
		for (Object o : list)
			System.out.println(o);
	};

	public static void printList() {
		while (true) {
			System.out.println("1 to print users\n2 to print people\n3 to print bank accounts\n0 to quit");
			String selection = scan.nextLine();
			switch (selection) {
			case "0":
				return;
			case "1":
				printList.accept(DataStore.readUsersFromFile());
				break;
			case "2":
				printList.accept(DataStore.readPeopleFromFile());
				break;
			case "3":
				printList.accept(DataStore.readBankAccountsFromFile());
				break;
			default:
				System.out.println("No valid option selected, try again");
			}
		}
	}

	public static void quit() {
		System.out.println("\nThank you for banking with this bank today! ^__^\n");
		drawLogoutCow();
		System.exit(0);
	}

	public static void drawLoginCow() {
		StringBuilder p1 = new StringBuilder("    /\\_/\\           ");
		StringBuilder p2 = new StringBuilder("    (0  0)\\_______   ");
		StringBuilder p3 = new StringBuilder("     (o_o)\\       )\\\\");
		StringBuilder p4 = new StringBuilder("          ||------||\\\\");
		StringBuilder p5 = new StringBuilder("          ||      ||  ");
		System.out.println(reverseAscii(p1.reverse()));
		System.out.println(reverseAscii(p2.reverse()));
		System.out.println(reverseAscii(p3.reverse()));
		System.out.println(reverseAscii(p4.reverse()));
		System.out.println(reverseAscii(p5.reverse()));
	}

	public static String reverseAscii(StringBuilder b) {
		for (int i = 0; i < b.length(); i++) {
			char c = b.charAt(i);
			switch (c) {
			case '\\':
				b.replace(i, i + 1, "/");
				break;
			case '/':
				b.replace(i, i + 1, "\\");
				break;
			case '(':
				b.replace(i, i + 1, ")");
				break;
			case ')':
				b.replace(i, i + 1, "(");
				break;
			}
		}
		return b.toString();
	}

	public static void drawLogoutCow() {
		System.out.println("    /\\_/\\");
		System.out.println("    (0  0)\\_______");
		System.out.println("     (o_o)\\       )\\\\");
		System.out.println("          ||------||\\\\");
		System.out.println("          ||      ||");
		System.out.println("^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^");
	}

}
