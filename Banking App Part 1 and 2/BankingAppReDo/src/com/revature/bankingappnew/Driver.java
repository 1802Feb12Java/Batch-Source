package com.revature.bankingappnew;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Driver {
	/*
	 * 
	 */
	
	private static Scanner read = new Scanner(System.in);
	private static Logger log = Logger.getLogger(Driver.class.getName());
	
	public static void main(String[] args) throws SQLException {
				
		
		/*
		 * Commented out code is to create first employee and first admin and to test
		 * that the userID's are being generated properly.
		 */
//		new Admin("nlb1", "iWillNeverLeaveYou", "Norma Louise", "Bates");
//		new Admin("admin", "admin", "Norma Louise", "Bates");
//		new Employee("MommasBoy", "mother?", "Norman", "Bates");
		
		//welcome message
		System.out.println("___________________________________________________________________________________");
		System.out.println("		   Welcome to White Pine Bay's City Bank.");
		System.out.println("___________________________________________________________________________________");
		log.info("\n\n ------BEGIN NEW RUN OF APPLICATION------ \n\n");
		
		boolean exit = false;
		while(!exit) {
			//main menu - log in, new customer, exit
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("				Main Menu"); 
			System.out.println("1. Log In.");
			System.out.println("2. New Customer.");
			System.out.println("3. Exit.");
			System.out.print("		Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1: //log in
					String username = logInPage();
					User u = com.revature.bankingappnew.daoimplementations.UserLogImp.getUserObject(username);
					String userType = com.revature.bankingappnew.daoimplementations.UserLogImp.getUserObject(username).getUserType();
					System.out.println("___________________________________________________________________________________");
					System.out.println("		Welcome Back " + u.getFirstName() + " " + u.getLastName() + "!");
					System.out.println("___________________________________________________________________________________");
					if(userType.equalsIgnoreCase("Customer")) {
						customerMenu1(username);
						log.info("\n The customer " + username + " has logged in.");
					} else if (userType.equalsIgnoreCase("Employee")) {
						employeeMenu(username);
						log.info("\n The employee " + username + " has logged in.");
					} else if (userType.equalsIgnoreCase("Admin")) {
						adminMenu1(username);
						log.info("\n The admin " + username + " has logged in.");
					} else {
						log.debug("Debug Message: A user was logged in, but their userType was invalid.");
						break;
					}
					break;
					
				case 2: //new customer
					newCustRegistration();
					break;
					
				case 3: //exit
					exit = true;
					break;
					
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			
			}//end switch
		}//end while
		
		
		
		//thank you prompt
		System.out.println();
		System.out.println("___________________________________________________________________________________");
		
		System.out.println("			   Thank you. Have a great day!"); 
		System.out.println("___________________________________________________________________________________");
		read.close();
	}//end main
	
	public static void newCustRegistration() throws SQLException {
		read.nextLine();
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Please enter the following information to create your customer profile.");
		System.out.print("Desired Username:	");
		String desiredUsername = read.nextLine();
		String pass = "filler", secondPass = "notFiller";
		while(!pass.equals(secondPass)) {
			System.out.print("Password:	");
			pass = read.nextLine();
			System.out.print("Confirm Password:	");
			secondPass = read.nextLine();
			if(!pass.equals(secondPass)) {
				System.out.println("*********Error: Those passwords do not match.*********");
				log.info("User Error: Tried to register an account, but entered two passwords that did not match.");
			}
		}//end password while loop
		System.out.print("First Name:	");
		String fName = read.nextLine();
		System.out.print("Last Name:	");
		String lName = read.nextLine();
		
		new Customer(desiredUsername, pass, fName, lName);
	}//end newCustRegistration method

	public static String logInPage() throws SQLException {
		String username = "";
		while(username.equals("")) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			read.nextLine();
			System.out.print("Username:	");
			username = read.nextLine();
			System.out.print("Password:	");
			String pass = read.nextLine();
			if(Validation.checkUsernamePasswordMatch(username, pass)) {
				//continue to return statement
			} else {
				System.out.println("*********Error: Username or Password is incorrect.*********");
				System.out.println("Press Enter to Try Again.");
				log.info("User Error: User tried to log in, but the username and password entered did not match.");
				username = "";
			}
		}
		
		System.out.println();
		return username;
	
	}//end logInPage method
	
	public static void customerMenu1(String username) throws SQLException{
		boolean exit = false;
		Customer c = com.revature.bankingappnew.daoimplementations.UserLogImp.getCustomerObject(username);
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. Apply to open a new account.");
			System.out.println("2. View account information.");
			System.out.println("3. Manage existing accounts.");
			System.out.println("4. Log Out.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					System.out.println();
					read.nextLine();
					System.out.println("What kind of account would you like to apply for?");
					System.out.println("1. Checking.");
					System.out.println("2. Savings.");
					System.out.println("3. Joint Checking.");
					System.out.println("4. Joint Savings.");
					System.out.print("Please enter the number of your selection: ");
					int type = read.nextInt();
					String appType;
					if(type == 1 || type == 2) {
						if(type == 1) {
							appType = "Checking";
						} else {
							appType = "Savings";
						}
						c.createApplication(appType);
					} else if (type == 3 || type == 4) {
						if(type == 3) {
							appType = "Checking";
						} else {
							appType = "Savings";
						}
						System.out.println();
						read.nextLine();
						System.out.print("Please enter the username of the other applicant:	");
						String user2 = read.nextLine();
						c.createJointApplication(appType, user2);
					} else {
						System.out.println("*********Error: Invalid Selection.*********");
						log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					}
					break;
				case 2:
					//view account info menu
					customerMenu2(username);
					break;
				case 3:
					//manage account menu
					customerMenu3(username);
					break;
				case 4:
					log.info("\n The customer " + username + " has logged out.");
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end switch
			
			System.out.println();
		}//end while
		
	}//end customerMenu1 method
	
	public static void customerMenu2(String username) throws SQLException{
		boolean exit = false;
		Customer c = com.revature.bankingappnew.daoimplementations.UserLogImp.getCustomerObject(username);
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. View your profile details");
			System.out.println("2. View your existing account details.");
			System.out.println("3. View an account's transaction history.");
			System.out.println("4. View your transaction history.");
			System.out.println("5. Return to the Customer Menu.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			System.out.println();
			switch(selection) {
				case 1:
					//view your profile details
					c.viewMyInfo();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 2:
					//view your existing account details
					c.viewAccountDetails();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 3:
					//view an account's transaction history
					System.out.print("Please enter the account number: ");
					int accountNum = read.nextInt();
					Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accountNum);
					c.viewAccountTransactionHistory(a);
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 4:
					//view your transaction history
					c.viewMyTransactionHistory();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 5:
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end switch
		}//end while
	}//end customerMenu2 method
	
	public static void customerMenu3(String username) throws SQLException{
		boolean exit = false;
		Customer c = com.revature.bankingappnew.daoimplementations.UserLogImp.getCustomerObject(username);
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. Make a deposit to an existing account.");
			System.out.println("2. Make a withdrawal from an existing account.");
			System.out.println("3. Make a transfer from one of your existing accounts to another account.");
			System.out.println("4. Return to the Customer Menu.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					read.nextLine();
					System.out.println("Please enter the account number of the account you wish to deposit to.");
					System.out.print("Account Number:	");
					int accntID = read.nextInt();
					Account a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID);
					System.out.print("Please enter the ammount you wish to deposit: $");
					double amount = read.nextDouble();
					c.deposit(a, amount);
					break;
				case 2:
					read.nextLine();
					System.out.println("Please enter the account number of the account you wish to withdraw from.");
					System.out.print("Account Number:	");
					accntID = read.nextInt();
					a = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID);
					System.out.print("Please enter the ammount you wish to withdraw: $");
					amount = read.nextDouble();
					c.withdraw(a, amount);
					break;
				case 3:
					read.nextLine();
					System.out.println("Please enter the account number of the account you wish to withdraw from.");
					System.out.print("Account Number:	");
					accntID = read.nextInt();
					Account a1 = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID);
					System.out.println("Please enter the account number of the account you wish to deposit to.");
					System.out.print("Account Number:	");
					int accntID2 = read.nextInt();
					Account a2 = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID2);
					System.out.print("Please enter the ammount you wish to transfer: $");
					amount = read.nextDouble();
					c.transfer(a1, a2, amount);
					break;
				case 4:
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end switch
		}//end while
		System.out.println();
	}//end customerMenu2 method
		
	public static void employeeMenu(String username) throws SQLException {
		boolean exit = false;
		Employee e = com.revature.bankingappnew.daoimplementations.UserLogImp.getEmployeeObject(username);
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. View customer details.");
			System.out.println("2. View all pending applications.");
			System.out.println("3. View a customer's pending applications.");
			System.out.println("4. View a customer's account details.");
			System.out.println("5. View all account details.");
			System.out.println("6. Approve or deny a pending application.");
			System.out.println("7. Log Out.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					System.out.println();
					//view customer details
					read.nextLine();
					System.out.print("Please enter the customer's username:	");
					String custUsername = read.nextLine();
					e.viewCustomerInfo(custUsername);
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 2:
					read.nextLine();
					//view all pending applications
					e.viewAllPendingApplications();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 3:
					//view a customer's pending applications
					System.out.println();
					read.nextLine();
					System.out.print("Please enter the customer's username:	");
					custUsername = read.nextLine();
					e.viewUsersPendingApplications(custUsername);
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 4:
					//view a customers account details
					System.out.println();
					read.nextLine();
					System.out.print("Please enter the customer's username:	");
					custUsername = read.nextLine();
					e.viewAccountDetails(custUsername);
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 5:
					//view all account details
					e.viewAllAccountDetails();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 6:
					//approve or deny an application
					read.nextLine();
					System.out.print("Please enter the application number: ");
					int appNumber = read.nextInt();
					System.out.println("Would you like to approve or deny this application?");
					System.out.println("1. Approve.");
					System.out.println("2. Deny.");
					System.out.print("Please enter the number of your selection: ");
					int decision = read.nextInt();
					if(decision == 1) {
						e.changeApplicationStatus(appNumber, "Approved");
					} else if (decision == 2) {
						e.changeApplicationStatus(appNumber, "Denied");
					} else {
						System.out.println("*********Error: Invalid Selection.*********");
						log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					}
					
					break;
				case 7:
					log.info("\n The employee " + username + " has logged out.");
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end switch
		}//end while loop
	}//end employeeMenu method
	
	public static void adminMenu1(String username) throws SQLException{
		boolean exit = false;
		Admin a = com.revature.bankingappnew.daoimplementations.UserLogImp.getAdminObject(username);
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		Which menu would you like to go to?						  ");
			System.out.println("1. User Profiles Menu.");
			System.out.println("2. Customer Applications Menu.");
			System.out.println("3. Customer Accounts Menu.");
			System.out.println("4. Log Out.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					//user profiles menu
					adminMenu2(a);
					break;
				case 2:
					//customer applications menu
					adminMenu3(a);
					break;
				case 3: 
					//customer accounts menu
					adminMenu4(a);
					break;
				case 4:
					//main menu
					log.info("\n The Admin " + username + " has logged out.");
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
				
			}//end switch
			if(!exit) {
				System.out.println("Press enter to continue.");
				read.nextLine();
			}
		}//end while
	}//end adminMenu1 method
	
	public static void adminMenu2(Admin a) throws SQLException{
		//user profiles menu
		boolean exit = false;
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. Create a new employee or administrator profile.");
			System.out.println("2. View user details.");
			System.out.println("3. View all user profile details.");
			System.out.println("4. Return to Admin Menu.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					//create new employee or admin
					read.nextLine();
					System.out.println("Please enter the following information to create the new profile.");
					System.out.print("Desired Username:	");
					String desiredUsername = read.nextLine();
					String pass = "filler", secondPass = "notFiller";
					while(!pass.equals(secondPass)) {
						System.out.print("Password:	");
						pass = read.nextLine();
						System.out.print("Confirm Password:	");
						secondPass = read.nextLine();
						if(!pass.equals(secondPass)) {
							System.out.println("*********Error: Those passwords do not match.*********");
							log.info("User Error: User tried to register an account, but the two passwords entered did not match.");
						}
					}//end password while loop
					System.out.print("First Name:	");
					String fName = read.nextLine();
					System.out.print("Last Name:	");
					String lName = read.nextLine();
					System.out.println("Will this user be an employee or an administrator?");
					System.out.println("1. Employee.");
					System.out.println("2. Administrator.");
					System.out.print("Please enter the number of your selection: ");
					int decision = read.nextInt();
					String type;
					if(decision == 1) {
						type = "Employee";
					} else if (decision == 2) {
						type = "Admin";
					} else {
						type = "User";
					}
					new Employee(desiredUsername, pass, fName, lName, type);
					break;
				case 2:
					//view customer details
					read.nextLine();
					System.out.print("Please enter the user's username:	");
					String custUsername = read.nextLine();
					a.viewCustomerInfo(custUsername);
					break;
				case 3:
					//view all profile details
					read.nextLine();
					a.viewCustomerInfo("all");
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 4:
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end selection
		}//end while
	}//end adminMenu2
	
	public static void adminMenu3(Admin a) throws SQLException{
		//customer applications menu
		boolean exit = false;
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. View all pending applications.");
			System.out.println("2. View a customer's pending applications.");
			System.out.println("3. Approve or deny a pending application.");
			System.out.println("4. Return to Admin Menu.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					read.nextLine();
					//view all pending applications
					a.viewAllPendingApplications();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 2:
					//view a customer's pending applications
					read.nextLine();
					System.out.print("Please enter the customer's username:	");
					String custUsername = read.nextLine();
					a.viewUsersPendingApplications(custUsername);
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 3:
					//approve or deny an application
					read.nextLine();
					System.out.print("Please enter the application number: ");
					int appNumber = read.nextInt();
					System.out.println("Would you like to approve or deny this application?");
					System.out.println("1. Approve.");
					System.out.println("2. Deny.");
					System.out.print("Please enter the number of your selection: ");
					int decision = read.nextInt();
					if(decision == 1) {
						a.changeApplicationStatus(appNumber, "Approved");
					} else if (decision == 2) {
						a.changeApplicationStatus(appNumber, "Denied");
					} else {
						System.out.println("*********Error: Invalid Selection.*********");
						log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					}
					break;
				case 4:
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end selection
		}//end while
	}//end adminMenu3
	
	public static void adminMenu4(Admin a) throws SQLException{
		//customer accounts menu
		boolean exit = false;
		while(!exit) {
			System.out.println();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("		What would you like to do?						  ");
			System.out.println("1. View a customer's account details.");
			System.out.println("2. View all account details.");
			System.out.println("3. View transaction history.");
			System.out.println("4. Change an account's status.");
			System.out.println("5. Deposit to an account.");
			System.out.println("6. Withdraw from an account.");
			System.out.println("7. Transfer money between two accounts.");
			System.out.println("8. Return to Admin Menu.");
			System.out.print("Please enter the number of your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					//view a customers account details
					read.nextLine();
					System.out.print("Please enter the customer's username:	");
					String custUsername = read.nextLine();
					a.viewAccountDetails(custUsername);
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 2:
					read.nextLine();
					//view all account details
					a.viewAllAccountDetails();
					System.out.println("Press enter to continue.");
					read.nextLine();
					break;
				case 3:
					read.nextLine();
					//view transaction history
					System.out.println("Which transactions would you like to view?");
					System.out.println("1. All account transactions.");
					System.out.println("2. Transactions by username.");
					System.out.println("3. Transactions by account number.");
					System.out.print("Please enter the number of your selection: ");
					int decision = read.nextInt();
					switch(decision) {
						case 1:
							a.viewUserTransactionHistory("all");
							System.out.println("Press enter to continue.");
							read.nextLine();
							break;
						case 2:
							System.out.print("Please enter the username: ");
							String userName = read.nextLine();
							a.viewUserTransactionHistory(userName);
							System.out.println("Press enter to continue.");
							read.nextLine();
							break;
						case 3:
							System.out.print("Please enter the account number: ");
							int accountNum = read.nextInt();
							a.viewAccountTransactionHistory(accountNum);
							System.out.println("Press enter to continue.");
							read.nextLine();
							break;
						default:
							System.out.println("*********Error: Invalid Selection.*********");
							log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
							break;
					}//end switch
					break;
				case 4:
					//change an accounts status
					read.nextLine();
					System.out.print("Please enter the account number: ");
					int acctNum = read.nextInt();
					Account account = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(acctNum);
					
					System.out.println("Would you like to mark this account as open or closed?");
					System.out.println("1. Open.");
					System.out.println("2. Close.");
					System.out.print("Please enter the number of your selection: ");
					decision = read.nextInt();
					if(decision == 1) {
						a.changeAccountStatus(account, "Open");
					} else if (decision == 2) {
						a.changeAccountStatus(account, "Closed");
					} else {
						System.out.println("*********Error: Invalid Selection.*********");
						log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					}
					break;
				case 5:
					//deposit to an account
					read.nextLine();
					System.out.println("Please enter the account number of the account you wish to deposit to.");
					System.out.print("Account Number:	");
					int accntID = read.nextInt();
					account = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID);
					System.out.print("Please enter the ammount you wish to deposit: $");
					double amount = read.nextDouble();
					a.deposit(account, amount);
					break;
				case 6:
					//withdraw from an account
					read.nextLine();
					System.out.println("Please enter the account number of the account you wish to withdraw from.");
					System.out.print("Account Number:	");
					accntID = read.nextInt();
					account = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID);
					
					System.out.print("Please enter the ammount you wish to withdraw: $");
					amount = read.nextDouble();
					a.withdraw(account, amount);
					break;
				case 7:
					//transfer between accounts
					read.nextLine();
					System.out.println("Please enter the account number of the account you wish to withdraw from.");
					System.out.print("Account Number:	");
					accntID = read.nextInt();
					account = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID);
					System.out.println("Please enter the account number of the account you wish to deposit to.");
					System.out.print("Account Number:	");
					int accntID2 = read.nextInt();
					Account account2 = com.revature.bankingappnew.daoimplementations.AccountLogImp.getAccountObject(accntID2);
					
					System.out.print("Please enter the ammount you wish to transfer: $");
					amount = read.nextDouble();
					a.transfer(account, account2, amount);
					break;
				case 8:
					exit = true;
					break;
				default:
					System.out.println("*********Error: Invalid Selection.*********");
					log.info("User Error: Made an invalid selection when prompted to choose from a list of options.");
					break;
			}//end selection
		}//end while
	}//end adminMenu4
	
	
}//end class
