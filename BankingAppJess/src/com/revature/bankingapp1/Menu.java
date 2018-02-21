package com.revature.bankingapp1;

import java.util.ArrayList;
import java.util.Scanner;

/*
	 * This class was inspired by Seth Maize. 
	 * add exit menu options
	 */
public class Menu {
	
//	public static int mainMenu() {
//		Scanner read = new Scanner(System.in);
//		System.out.println("Welcome to myFirstBank.");
//		System.out.println();
//		System.out.println("1. Log In.");
//		System.out.println("2. New User.");
//		System.out.println("3. Exit. ");
//		System.out.print("Your selection: ");
//		int userSelection = read.nextInt();
//		System.out.println();
//		return userSelection;
//		
//	}//end mainMenu method
	
	public static void mainMenu(ArrayList<String> userLog, ArrayList<Customer> customerLog, ArrayList<BankEmployee> employeeLog) {
		Scanner read = new Scanner(System.in);
		System.out.println("Welcome to myFirstBank.");
		System.out.println();
		System.out.println("1. Log In.");
		System.out.println("2. New User.");
		System.out.println("3. Exit. ");
		System.out.print("Your selection: ");
		int userSelection = read.nextInt();
		System.out.println();
	
		switch(userSelection) {
			case 1:
				returningUserMenu(userLog, customerLog, employeeLog);
				break;
			case 2:
				newUserMenu(userLog, customerLog, employeeLog);
				break;
			case 3:
				callExitMethod(userLog, customerLog, employeeLog);
				break;
			default:
				System.out.println();
				System.out.println("Invalid Input. Please enter either 1 or 2.");
				mainMenu(userLog, customerLog, employeeLog);
				break;
		}//end switch
	}//end chooseOption method
	
	public static void callExitMethod(ArrayList<String> userLog, ArrayList<Customer> customerLog, ArrayList<BankEmployee> employeeLog) {
		System.out.println("Saving any changes made...");
		FileKeeping.writeToEmployeeLogFile(employeeLog);
		System.out.println("...");
		FileKeeping.writeToCustomerLogFile(customerLog);
		System.out.println("...");
		FileKeeping.writeToUsernamePasswordFile(userLog);
		System.out.println("Goodbye.");
	}//end exit method
	
	public static void returningUserMenu(ArrayList<String> userLog, ArrayList<Customer> customerLog, ArrayList<BankEmployee> employeeLog) {
		FileKeeping.writeToCustomerLogFile(customerLog);
		FileKeeping.writeToUsernamePasswordFile(userLog);
		userLog = FileKeeping.readInUsernamePasswordLog();
		customerLog = FileKeeping.readInCustomerLog();
		
		System.out.println();
		Scanner read = new Scanner(System.in);
		System.out.println("Log In.");
		System.out.print("Username: ");
		String username = read.nextLine();
		System.out.print("Password: ");
		String password = read.nextLine();
		String result = FileKeeping.checkUsernamePasswordMatch(userLog, username, password);
		if(result.equals("Customer")) {
			customerMenu(userLog, customerLog, employeeLog, username);
		} else if(result.equals("Employee")) {
			employeeMenu(userLog, employeeLog, customerLog, username);
		} else if(result.equals("Admin")) {
			adminMenu(userLog, employeeLog, customerLog, username);
		}else {
			System.out.println(result);
			returningUserMenu(userLog, customerLog, employeeLog);
		}
		
	}//end returningUserMenu method
	
	public static void newUserMenu(ArrayList<String> userLog, ArrayList<Customer> customerLog, ArrayList<BankEmployee> employeeLog) {
		System.out.println();
			Scanner read = new Scanner(System.in);
			System.out.println("If you are a customer and would like to create an account with myFirstBank, select option 1.");
			System.out.println("1. Create a customer account.");
			System.out.println("2. Create an employee or admin account.");
			int selection = read.nextInt();
			read.nextLine();
			switch(selection) {
				case 1:
					createNewUser(userLog, customerLog, employeeLog);
					break;
				case 2:
					System.out.println("I'm sorry for the inconvinience, but this page is under maintenance.");
					System.out.println("New employees cannot be added to the system at this time.");
					mainMenu(userLog, customerLog, employeeLog);
//					System.out.println("You must be an Admin to create a new employee account.");
//					System.out.print("Please enter your username: ");maintenance 
//					String username = read.nextLine();
//					System.out.println("Please enter your password: ");
//					String password = read.nextLine();
//					String result = FileKeeping.checkUsernamePasswordMatch(userLog, username, password);
//					if(result.equalsIgnoreCase("Admin")) {
//						createNewEmployee();
//					} else {
//						System.out.println("I am sorry, but only admins can create new employees.");
//						mainMenu();
//					}
					break;
				default:
					System.out.println("Invalid Selection.");
					newUserMenu(userLog, customerLog, employeeLog);
					break;
			}//end switch
		
	}//end newUserMenu method
	
	public static void createNewUser(ArrayList<String> userLog, ArrayList<Customer> customerLog, ArrayList<BankEmployee> employeeLog) {
		System.out.println();
		Scanner read = new Scanner(System.in);
		System.out.print("Please enter your desired username: ");
		String username = read.nextLine();
		System.out.print("Please enter a password: ");
		String password = read.nextLine();
		System.out.print("Please enter your first name: ");
		String firstName = read.nextLine();
		System.out.print("Please enter your last name: ");
		String lastName = read.nextLine();
		new Customer(customerLog, userLog, username, password, firstName, lastName);
		System.out.println("Your account has been created.");
		System.out.println();
		
		returningUserMenu(userLog, customerLog, employeeLog);
	}//createNewUser
	
	
	public static void customerMenu(ArrayList<String> userLog, ArrayList<Customer> customerLog, ArrayList<BankEmployee> employeeLog, String username) {
		System.out.println();
		System.out.println("Welcome Back!");
		Scanner read = new Scanner(System.in);
		boolean b = true;
		while(b) {
			System.out.println("What would you like to do?");
			System.out.println("1. Apply to open a new account.");
			System.out.println("2. View your existing account details.");
			System.out.println("3. View your customer details.");
			System.out.println("4. Make a deposit to an existing account.");
			System.out.println("5. Make a withdrawal from an existing account.");
			System.out.println("6. Make a transfer from one of your existing accounts to another account.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			switch(selection) {
				case 1: 
					createApplication(customerLog, username);
					break;
				case 2:
					for(Customer c : customerLog) {
						if(c.getUsername().equals(username)) {
							c.viewAccountDetails();
							break;
						}
					}
					break;
				case 3:
					for(int i = 0; i < customerLog.size(); i++) {
						if(customerLog.get(i).getUsername().equals(username)) {
							customerLog.get(i).viewCustomerDetails();
							break;
						}
					}
					break;
					
				case 4: 
					System.out.print("What is the account number of the account you wish to deposit to? ");
					int accountNum = read.nextInt();
					System.out.print("How much would you like to deposit? ");
					float amount = read.nextFloat();
					for(Customer c : customerLog) {
						if(c.getUsername().equals(username)) {
							c.deposit(customerLog, accountNum, amount);
							break;
						}
					}
					break;
				case 5:
					System.out.print("What is the account number of the account you would like to withdraw from? ");
					int accountNum2 = read.nextInt();
					System.out.print("How much would you like to withdraw? ");
					float amount2 = read.nextFloat();
					for(Customer c : customerLog) {
						if(c.getUsername().equals(username)) {
							c.withdraw(accountNum2, amount2);
							break;
						}
					}
					break;
				case 6: 
					System.out.print("What is the account number of the account you would like to transfer from? ");
					int accountNum3 = read.nextInt();
					System.out.print("What is the account number of the account you wish to transfer to? ");
					int accountNum4 = read.nextInt();
					System.out.print("How much would you like to transfer? ");
					float amount3 = read.nextFloat();
					for(Customer c : customerLog) {
						if(c.getUsername().equals(username)) {
							c.transfer(customerLog, accountNum3, accountNum4, amount3);
							break;
						}
					}
					break;
				default:
					System.out.println("Invalid Selection.");
					break;
					
			}//end switch
			System.out.println();
			System.out.print("Would you like to return to the Customer Menu? ");
			String reply = read.next();
			if(reply.startsWith("y")) {
				b = true;
			} else {
				b = false;
			}
			
		}//end while
		
		mainMenu(userLog, customerLog, employeeLog);
		
	}//end customerMenu method
	
	public static void createApplication(ArrayList<Customer> customerLog, String username) {
		System.out.println();
		Scanner read = new Scanner(System.in);
		
		String accountType = "";
		boolean b = true;
		
		while(b) {
			System.out.println("What kind of account would you like to create?");
			System.out.println("1. Checking.");
			System.out.println("2. Savings.");
			System.out.print("Please enter your selection: ");
			int selection = read.nextInt();
			switch(selection) {
				case 1:
					accountType = "Checking";
					b = false;
					break;
				case 2:
					accountType = "Savings";
					b = false;
					break;
				default:
					System.out.println("Invalid Input.");
					break;
			}//end switch
		}//end while
		ArrayList<Customer> appCustList = new ArrayList<Customer>();
		
		for(Customer c : customerLog) {
			if(c.getUsername().equals(username)) {
				appCustList.add(c);
				break;
			}
		}
		boolean addMore = true;
		while (addMore) {
			System.out.println("Would you like to add another person on this account?");
			String answer = read.next();
			if(answer.startsWith("y")) {
				System.out.print("What is the username of the person you would like to add? ");
				String username2 = read.nextLine();
				for(Customer c : customerLog) {
					if(c.getUsername().equals(username2)) {
						appCustList.add(c);
						break;
					}
				}
				read.nextLine();
			} else {
				addMore = false;
				//break;
			}
			
		}//end while
		
		for(int i = 0; i < customerLog.size(); i++) {
			for(int j = 0; j < appCustList.size(); j++) {
				if(customerLog.get(i).getUsername().equals(appCustList.get(j).getUsername())) {
					customerLog.get(i).createApplication(appCustList, accountType);
				}
			}
		}
		FileKeeping.writeToCustomerLogFile(customerLog);
		customerLog = FileKeeping.readInCustomerLog();
	}//end createApplication method
	
	public static void employeeMenu(ArrayList<String> userLog, ArrayList<BankEmployee> employeeLog, ArrayList<Customer> customerLog, String empUsername) {
		System.out.println();
		System.out.println();
		
		Scanner read = new Scanner(System.in);
		boolean doMore = true;
		while(doMore) {
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("1. View a customer's information.");
			System.out.println("2. View a customer's pending applications.");
			System.out.println("3. Change the status of a pending application.");
			System.out.println("4. View an account's details.");
			System.out.print("Your Selection: ");
			int selection = read.nextInt();
			read.nextLine();
			switch(selection) {
				case 1: 
					System.out.print("Please enter the username of the customer who's information you would like to view: ");
					String username = read.nextLine();
					for(Customer c : customerLog) {
						if(c.getUsername().equals(username)) {
							c.viewCustomerDetails();
						}
					}
					break;
				case 2:
					System.out.print("Please enter the username of the customer who's pending applications you would like to view: ");
					String username2 = read.nextLine();
					for(BankEmployee e : employeeLog) {
						if(e.getUsername().equals(empUsername)) {
							e.viewPendingApplications(customerLog, username2);
							break;
						}
					}
					break;
				case 3:
					System.out.print("Please enter the username of the customer who's application you would like to edit: ");
					String username3 = read.nextLine();
					for(Customer c : customerLog) {
						if(c.getUsername().equals(username3)) {
							for(int i = 0; i < c.applicationList.size(); i++) {
								c.applicationList.get(i).toString();
							}
							break;
						}
					}
					System.out.print("Please enter the application type of the application you would like to edit: ");
					String appType = read.nextLine();
					System.out.println("Would you like to approve or deny this application?");
					System.out.println("1. Approve.");
					System.out.println("2. Deny.");
					System.out.print("Your selection: ");
					int selection2 = read.nextInt();
					String newStatus="";
					if (selection2 == 1) {
						newStatus = "Approved";
					} else if (selection2 == 2) {
						newStatus = "Denied";
					} else {
						System.out.println("Invalid Selection.");
						newStatus = "Pending";
					}
					for(BankEmployee e : employeeLog) {
						if(e.getUsername().equals(empUsername)) {
							e.changeApplicationStatus(customerLog, username3, appType, newStatus);
							break;
						}
					}
					FileKeeping.writeToCustomerLogFile(customerLog);
					customerLog = FileKeeping.readInCustomerLog();
					break;
				case 4:
					System.out.print("Please enter the account number of the account you would like to view: ");
					int accountNum = read.nextInt();
					for(BankEmployee e : employeeLog) {
						if(e.getUsername().equals(empUsername)) {
							e.viewAccountDetails(customerLog, accountNum);
							break;
						}
					}
					break;
				default:
					System.out.println("Invalid Input.");
			
			}//end switch
			System.out.print("Would you like to return to the Employee Menu? ");
			String reply = read.next();
			if(reply.startsWith("y")) {
				doMore = true;
			} else {
				doMore = false;
			}
		}//end while
		mainMenu(userLog, customerLog, employeeLog);
		
	}//end employeeMenu method
	
	public static void adminMenu(ArrayList<String> userLog, ArrayList<BankEmployee> employeeLog, ArrayList<Customer> customerLog, String adminUsername) {
		Scanner read = new Scanner(System.in);
		System.out.println("Would you like to close an account?");
		System.out.println("1. Yes.");
		System.out.println("2. No.");
		System.out.print("Your selection: ");
		int selection = read.nextInt();
		read.nextLine();
		
		switch(selection) {
			case 1:
				System.out.println();
				System.out.print("Please enter the account number for the account you would like to close: ");
				int accountNum = read.nextInt();
				BankAdmin.changeAccountStatus(customerLog, accountNum);
				break;
			case 2:
				System.out.println();
				System.out.println("Would you like to handle Customer Accounts or Customer Applications?");
				System.out.println("1. Customer Accounts.");
				System.out.println("2. Customer Applications.");
				System.out.print("Your selection: ");
				int selection2 = read.nextInt();
				switch(selection2) {
					case 1: 
						System.out.print("What is the username of the customer whos account you would like access to?: ");
						String custUsername = read.nextLine();
						customerMenu(userLog, customerLog, employeeLog, custUsername);
						break;
					case 2:
						employeeMenu(userLog, employeeLog, customerLog, adminUsername);
						break;
					default:
						System.out.println("Invalid Selection.");
						break;
				}
				break;
			default:
				System.out.println("Invalid Selection.");
				adminMenu(userLog, employeeLog, customerLog, adminUsername);
				break;
				
		}//end switch
		
		
	}//end adminMenu method
	
}//end class
