package com.revature.driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.accounts.Account;
import com.revature.menus.Menus;
import com.revature.services.AccountServices;
import com.revature.services.CustomerServices;
import com.revature.userfunctions.*;
import com.revature.users.User;

public class Driver {
	public static void main(String[] args) {
		//loop controllers
		boolean userConnected = false;  
		boolean loggingIn = true;
		boolean validated = false;
		boolean runningBackend = true;
		boolean systemRunning = true;
		
		//active session objects
		User user = null;
		User locatedUser = null;
		String locateUserName = null;
		Account account = null;
		ArrayList<Account> accountList = null;
		CustomerServices cs = new CustomerServices();
		AccountServices as = new AccountServices();
		
		//login and validation variables
		int option = 0;
		String userName = null;
		String password = null;
		Scanner getInput = null;

		//open the scanner to be used for all input
		getInput = new Scanner(System.in);

		//start the system
		while(systemRunning) {
			do {
				Menus.displayStartMenu();
			
				try {
				option = getInput.nextInt();
				}catch(Exception e) {
					System.out.println("Please enter an appropriate selection");
				}
				
				getInput.nextLine();
				
				if (option == 1) {
					//exit the start menu and launch the front end
					option = 0;
					runningBackend = false;
					userConnected = true;
					loggingIn = true;
					System.out.println();
				}//end option 1
						
				if (option == 2) {	
					option = 0;
					Menus.displayBackendAccountCreationMenu();	
					System.out.print("Please select an option: ");
					try {
						option = getInput.nextInt();
					}catch(Exception e) {
						System.out.println("Please enter an appropriate selection");
					}
					
					getInput.nextLine();
						
					switch(option) {
					case 1:
						//create a new user account and set it to administrator
						user = UserFunctions.register(getInput);
						user.setUserType("Administrator");
						//add the administrator to the database
						try {
							cs.addUser(user);
						} catch (SQLException e2) {
							System.out.println("Unable to add user.");
							e2.printStackTrace();
						}
						option = 0;
						break;
								
					case 2:
						//create a new customer account and set it to customer
						user = UserFunctions.register(getInput);
						user.setUserType("Customers");
						try {
							cs.addUser(user);
						} catch (SQLException e1) {
							System.out.println("Unable to add user.");
							e1.printStackTrace();
						}

						option = 0;
						break;
								
					case 3:
						option = 0;
						userConnected = false;
						runningBackend = false;
						break;
								
					default:
						option = 0;
						System.out.println("Please enter an approriate selection.");
						break;
					}//end backend administration switch
				}//end option 2
			}while(runningBackend);
			
			while(userConnected) {			
				//initiate login		
				while(loggingIn) {
					//determine if new or returning user
					Menus.displayLogInMenu();
					
					try {
						option = getInput.nextInt();
					}catch(Exception e) {
						System.out.println("Please enter an appropriate selection");
					}
					
					getInput.nextLine();
					
					switch(option) {
					
					//begin login procedure
					case 1:
						option = 0;
						
						//validate user
						System.out.print("Please enter your user name: ");
						userName = getInput.nextLine();
						try {
							user = cs.getUser(userName);
						} catch (SQLException e) {
							System.out.println("Something went wrong when fetching user.");
							e.printStackTrace();
						}
									
						//exit the switch if the customer name is not found
						if(user == null) {
							break;
						}

						System.out.print("Please enter your password: ");
						password = getInput.nextLine();
						
						//validate login
						validated = UserFunctions.validateUser(password, user);
						if(validated) {
							loggingIn = false;
						}
							
						else {
							System.out.println();
							user = null;
						}
					    
						break;

					
					case 2:
						//register new customer
						user = UserFunctions.register(getInput);
						user.setUserType("Customer");
						try {
							cs.addUser(user);
						} catch (SQLException e1) {
							System.out.println("An error occurred while trying to add the user.");
							e1.printStackTrace();
						}
						
						validated = true;
						loggingIn = false;
						option = 0;
						break; 
						
					case 3:
						//logout
						option = 0;
						loggingIn = false;
						userConnected = false;
						validated = false;
						break;
						
					default:
						break;
					}//end login/register switch
				}//end logging in while loop
				
				while(validated) {
					switch(user.getUserType()) {
					case "Customer":
						Menus.displayCustomerMenu(user.getFirstName(), user.getLastName());
						System.out.print("Please make a selection: ");
						
						try {
							option = getInput.nextInt();
							}catch(Exception e) {
								System.out.println("Please enter an appropriate selection");
							}
							
						getInput.nextLine();

						switch(option) {
						case 1:
							//Apply for an account
							Menus.displayAccountCreationMenu();
							System.out.println("Select the type of account you would like to apply for: ");

							try {
								option = getInput.nextInt();
								}catch(Exception e) {
									System.out.println("Please enter an appropriate selection");
								}
							
							getInput.nextLine();
							
							switch(option) {
							case 1:
								//create the checking account
								account = Account.createAccount(user, "Checking");
								
								//add the account to the database
								try {
									as.addAccount(account);
								} catch (SQLException e1) {
									System.out.println("Something went wrong trying to add the account.");
									e1.printStackTrace();
								}
								account = null;
								break;
								
							case 2:
								//create the savings account
								account = Account.createAccount(user, "Savings");
								
								//add the account to the database
								try {
									as.addAccount(account);
								} catch (SQLException e1) {
									System.out.println("Something went wrong trying to add the account.");
									e1.printStackTrace();
								}
								account = null;
								break;
							}//end account type switch
							
							option = 0;
							break;
							
						case 2:
							//LIST ACCOUNTS
							//List customer's accounts
							if(!user.isAccountHolder()) {
								System.out.print("You currently have no open accounts");
								System.out.println();
							}
							
							else {
								//TODO:list accounts
							}
							
							option = 0;
							break;
							
						case 3:
							//VIEW ACCOUNT
							//List customer's accounts
							if(!user.isAccountHolder()) {
								System.out.print("You currently have no open accounts");
								System.out.println();
							}
							
							else {
								//TODO: List accoutn
								System.out.print("Select an account to view: ");
								
								try {
									option = getInput.nextInt();
									}catch(Exception e) {
										System.out.println("Please enter an appropriate selection");
										break;
									}
								
								System.out.println();
								System.out.println();
								getInput.nextLine();
								
							}
													
							option = 0;
							break;
							
						case 4:
							//WITHDRAW
							if(!user.isAccountHolder()) {
								System.out.print("You currently have no open accounts");
								System.out.println();
							}
							
							else {
								//TODO: List customer's accounts
								System.out.print("Select an account to withdraw from: ");
								
								try {
									option = getInput.nextInt();
									}catch(Exception e) {
										System.out.println("Please enter an appropriate selection");
										break;
									}
								System.out.print("Enter an amount to withdraw: ");
								System.out.println();
								System.out.println();
								getInput.nextLine();
							}
												
							option = 0;
							break;
							
						case 5:
							//DEPOSIT
							//List customer's accounts
							if(!user.isAccountHolder()) {
								System.out.print("You currently have no open accounts");
								System.out.println();
							}
							
							else {
								//TODO: List accounts
								System.out.print("Select an account to deposit to: ");
								
								try {
									option = getInput.nextInt();
									}catch(Exception e) {
										System.out.println("Please enter an appropriate selection");
										break;
									}
								getInput.nextLine();
								System.out.print("Enter an amount to deposit: ");
								System.out.println();
								System.out.println();
								getInput.nextLine();
							}
							
							option = 0;
							break;
							
						case 6:
							//TRANSFER
							//List customer's accounts
							if(!user.isAccountHolder()) {
								System.out.print("You currently have no open accounts");
								System.out.println();
							}
							
							else {
								//TODO:  Transfer
							}
							
							option = 0;
							break;
							
						case 7:
							//EXIT
							option = 0;
							validated = false;
							
							break;
							
					    default:
					    	System.out.println();
					    	break;
						}
						break;
						
					case "Administrator":
						//ADMIN
						Menus.displayAdminMenu(user.getFirstName(), user.getLastName());
						System.out.print("Please make a selection: ");
						
						try {
							option = getInput.nextInt();
							}catch(Exception e) {
								System.out.println("Please enter an appropriate selection");
							}
							
						getInput.nextLine();
						
						switch(option) {
						case 1:
							//View customer information
							System.out.print("Enter a user name to view: ");
							
							locateUserName = getInput.nextLine();
							
							try {
								locatedUser = cs.getUser(locateUserName);
							} catch (SQLException e) {
								System.out.println("Something went wrong when trying to locate the user.");
								e.printStackTrace();
							}
							
							if(locatedUser != null) {
								System.out.println();
								System.out.print(locatedUser.toString());
								System.out.println();
							}
							
							else {
								System.out.println("User not found");
							}
							
							option = 0;
							break;

						case 2:
							//TODO: view customer accounts
							option = 0;
							break;

						case 3:
							//View pending account applications
							try {
								accountList = as.getPendingAccounts();
							} catch (SQLException e) {
								System.out.println("Something went wrong reading the account list.");
								e.printStackTrace();
							}

							//if accounts exist, allow administrator to approve or deny them
							if(accountList != null) {
								System.out.println("The following accounts are currently pending: ");
								Account.listAccounts(accountList);
								
								System.out.print("Select an account for processing: ");
								option = getInput.nextInt();
								getInput.nextLine();
							}
							
							option = 0;
							break;

						case 4:
							//TODO: modify accounts
							option = 0;
							break;

						case 5:
							//exit
							option = 0;
							validated = false;
							break;
						}
						break;
					}//end switch
				option = 0;	
				}//end banking system loop
			System.out.println();
			break;
			}//end user connected while loop
		}//system running loop
	}//end main
}//end Driver class