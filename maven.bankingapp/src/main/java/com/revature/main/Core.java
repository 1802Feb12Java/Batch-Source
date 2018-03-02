package com.revature.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.beans.UserBankAccount;
import com.revature.services.AccountServices;
import com.revature.services.UserBankAccountServices;
import com.revature.services.UserServices;

public class Core {
	
	// Instantiate objects and set variables
	Scanner sc = new Scanner(System.in);
	UserServices us = new UserServices();
	UserBankAccountServices ubas = new UserBankAccountServices();
	AccountServices as = new AccountServices();
	Account account = null;
	UserBankAccount uba = null;
	Menu menu = new Menu();
	User user = null;
	
	int choice = 0;
	String username = "";
	String password = "";
	boolean mainMenu = false;
	boolean validSelection = false;
	double depositAmount = 0;
	double withdrawAmount = 0;
	boolean depositing = false;
	boolean withdrawing = false;
	double initialDeposit = 0;
	
	public void adminLogin() throws FileNotFoundException, IOException, SQLException {
		
		// Get super user login info from database.properties
		Properties prop = new Properties();
		prop.load(new FileReader("src/main/resources/database.properties"));
		String s_username = prop.getProperty("s_usr");
		String s_password = prop.getProperty("s_psw");
		
		// Get username input
		System.out.println("******************************************************************");
		System.out.println("Enter your username");
		System.out.println("******************************************************************");
		String usernameInput = sc.next();
		
		// Get password input
		System.out.println();
		System.out.println("******************************************************************");
		System.out.println("Enter your password");
		System.out.println("******************************************************************");
		String passwordInput = sc.next();
		
		// Verification
		if(usernameInput.equals(s_username) && passwordInput.equals(s_password)) {
			
			List<User> users = null;
			
			boolean logout = false;
			while(!logout) {
				
				System.out.println("******************************************************************");
				System.out.println("Welcome admin!");
				System.out.println("******************************************************************");
				System.out.println("Please choose an action:");
				System.out.println("1. View All Users");
				System.out.println("2. View Pending Applications");
				System.out.println("3. Create New User");
				System.out.println("4. Delete User");
				System.out.println("5. Logout");
				choice = sc.nextInt();
				
				
				switch(choice) {
					case 1:
						
						boolean userAccountScreen = true;
						while(userAccountScreen) {
							System.out.println("******************************************************************");
							System.out.println("User list:");
							System.out.println("******************************************************************");
							
							users = us.retrieveAllUsers();
							for(User user : users) {
								System.out.println("User Id ----- " + user.getId());
								System.out.println("First Name -- " + user.getFirstName());
								System.out.println("Last Name --- " + user.getLastName());
								System.out.println("Email ------- " + user.getEmail());
								System.out.println("Username ---- " + user.getUsername());
								System.out.println("Password ---- " + user.getPassword());
								System.out.println("******************************************************************");
							}
							System.out.println("Please choose an action:");
							System.out.println("1. Edit User");
							System.out.println("2. Main Menu");
							choice = sc.nextInt();
							
							switch(choice) {
								case 1:
									System.out.println("******************************************************************");
									System.out.println("Please enter the id of the user you would like to edit:");
									System.out.println("******************************************************************");
									sc.nextLine();
									choice = sc.nextInt();
									
									User userEdit = null;
									boolean userFound = false;
									while(!userFound) {
										userEdit = us.getUser(choice);
										if(userEdit == null) {
											System.out.println("******************************************************************");
											System.out.println("Error! User not found. Please try again.");
											System.out.println("******************************************************************");
											choice = sc.nextInt();
										} else {
											userFound = true;
										}
									}
									
									boolean editingUser=true;
									while(editingUser) {
										System.out.println("******************************************************************");
										System.out.println(" -  User Id ----- " + userEdit.getId());
										System.out.println("(1) First Name -- " + userEdit.getFirstName());
										System.out.println("(2) Last Name --- " + userEdit.getLastName());
										System.out.println("(3) Email ------- " + userEdit.getEmail());
										System.out.println("(4) Username ---- " + userEdit.getUsername());
										System.out.println("(5) Password ---- " + userEdit.getPassword());
										System.out.println("(6) EXIT");
										System.out.println("******************************************************************");
										System.out.println("Choose a field to edit:");
										System.out.println("******************************************************************");
										choice = sc.nextInt();
										
										String value = "";
										switch(choice) {
											case 1:
												System.out.println("Enter new first name:");
												value = sc.next();
												userEdit.setFirstName(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 2:
												System.out.println("Enter new last name:");
												value = sc.next();
												userEdit.setLastName(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 3:
												System.out.println("Enter new email:");
												value = sc.next();
												userEdit.setEmail(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
																	
											case 4:
												System.out.println("Enter new username:");
												value = sc.next();
												userEdit.setUsername(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 5:
												System.out.println("Enter new password:");
												value = sc.next();
												userEdit.setPassword(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 6:
												editingUser=false;
												break;
										}
										
									}
									
								case 2:
									userAccountScreen=false;
									break;
								
								default:
									System.out.println("Error! Invalid input.");
									break;
							
							}
							
						}
						break;
						
					case 2:
						System.out.println("******************************************************************");
						System.out.println("Pending applications:");
						System.out.println("******************************************************************");
						
						
						boolean hasPending = as.pendingAccount();
						if(!hasPending) {
							System.out.println("There are currently no application pending approval.");
							System.out.println("1. Main Menu");
							choice = sc.nextInt();
							
							boolean validSelection=false;
							while(!validSelection) {
						
								switch(choice) {
									case 1:
										validSelection=true;
										break;
										
									default:
										System.out.println("Invalid selection. Please try again.");
										choice = sc.nextInt();
										break;
								}
								
							}
						} else {
							
							User user = null;
							List<Account> accounts = as.retrieveAllAccounts();
							
							for(Account account_0 : accounts) {
								if(account_0.getIs_approved() == 0) {
									
									
									List<UserBankAccount> ubaAll = ubas.retrieveAllRecords();
									for(UserBankAccount newUba : ubaAll) {
										if(newUba.getAccountId() == account_0.getId()) {
											
											user = us.getUser(newUba.getUserId());
											
											System.out.println("Account Id ------- " + account_0.getId());
											System.out.println("Initial Deposit -- " + account_0.getBalance());
											System.out.println("Name ------------- " + user.getFirstName() + " " + user.getLastName());
											System.out.println("Email ------------ " + user.getEmail());
											System.out.println("Username --------- " + user.getUsername());
											System.out.println("******************************************************************");
										}
										
									}
								}
							}
							
							
							System.out.println("Please choose an action: ");
							boolean approvalScreen = true;
							
							while(approvalScreen) {
								
								System.out.println("1. Approve Account");
								System.out.println("2. Deny Account");
								System.out.println("3. Main Menu");
								choice = sc.nextInt();
								
								switch(choice) {
									case 1:
										System.out.println("******************************************************************");
										System.out.println("Enter the id of the account you would like to approve:");
										System.out.println("******************************************************************");
										choice = sc.nextInt();
										
										boolean approving = true;
										while(approving) {
											account = as.getAccount(choice);
											if(account == null) {
												System.out.println("******************************************************************");
												System.out.println("Error! That account doesn't exist. Please try again.");
												System.out.println("******************************************************************");
												choice = sc.nextInt();
											} else {
												account.setIs_approved(1);
												as.updateAccount(account);
												approving=false;
												System.out.println("******************************************************************");
												System.out.println("Account has been approved successfully!");
												System.out.println("******************************************************************");;
											}
										}
										
										break;
								
									case 3:
										approvalScreen=false;
										break;
										
									default:
										System.out.println("Invalid selection. Please try again.");
										break;
								}

							}
							
						}
						break;

					case 3:
						System.out.println("******************************************************************");
						System.out.println("Enter customer's first name:");
						System.out.println("******************************************************************");
						String firstName = sc.next();
						
						System.out.println("******************************************************************");
						System.out.println("Enter customer's your last name:");
						System.out.println("******************************************************************");
						String lastName = sc.next();
						
						System.out.println("******************************************************************");
						System.out.println("Enter customer's email:");
						System.out.println("******************************************************************");
						String email = sc.next();
						
						System.out.println("******************************************************************");
						System.out.println("Enter customer's username:");
						System.out.println("******************************************************************");
						String username = sc.next();
						
						boolean usernameOk = false;
						while(!usernameOk) {
							user = us.getUserByUsername(username);
							if(user == null) {
								usernameOk = true;
							} else {
								System.out.println("******************************************************************");
								System.out.println("Error! That username is already take. Please choose a different one.");
								System.out.println("******************************************************************");
								username = sc.next();
							}
						}
						
						System.out.println("******************************************************************");
						System.out.println("Enter customer's password:");
						System.out.println("******************************************************************");
						String password = sc.next();
						
						boolean passwordOk = false;
						while(!passwordOk) {
							if (password.length() < 6) {
								System.out.println("******************************************************************");
								System.out.println("Error! Please choose a password that has at least six characters.");
								System.out.println("******************************************************************");
								password = sc.next();
							} else {
								passwordOk = true;
							}
						}
						
						this.us.insertNewUser(3, firstName, lastName, email, username, password);
						
						System.out.println();
						System.out.println("******************************************************");
						System.out.println("New customer account created successfully!");
						System.out.println("******************************************************");
						break;
						
					case 4:
						boolean deleteUserScreen = true;
						while(deleteUserScreen) {
							System.out.println("******************************************************************");
							System.out.println("User list:");
							System.out.println("******************************************************************");
							
							users = us.retrieveAllUsers();
							for(User user : users) {
								System.out.println("User Id ----- " + user.getId());
								System.out.println("First Name -- " + user.getFirstName());
								System.out.println("Last Name --- " + user.getLastName());
								System.out.println("Email ------- " + user.getEmail());
								System.out.println("Username ---- " + user.getUsername());
								System.out.println("Password ---- " + user.getPassword());
								System.out.println("******************************************************************");
							}
							System.out.println("Enter the id of the user you would like to delete or type 1 and hit enter to exit:");
							choice = sc.nextInt();
							
							if(choice == 1) {
								deleteUserScreen=false;
							} else {
								boolean userNotFound=true;
								while(userNotFound) {
									user = us.getUser(choice);
									if(user == null) {
										System.out.println("Error! We could not find a user with that id. Please try again.");
										choice = sc.nextInt();
									} else {
										userNotFound = false;
									}
								}
								
								UserBankAccount record = null;
								record = ubas.getRecordByCustId(user.getId());
								if(record == null) {
									us.deleteUser(user.getId());
									System.out.println();
									System.out.println("******************************************************************");
									System.out.println("Customer " + user.getFirstName() + " " + user.getLastName() + " deleted successfully!");
									System.out.println("******************************************************************");
									System.out.println();
									deleteUserScreen = false;
									
								} else {
									System.out.println("******************************************************************");
									System.out.println("Error! You cannot delete a user that has an account. Please enter a different id.");
									System.out.println("******************************************************************");
									
								}
							}
						}
						break;
						
					case 5:
						logout=true;
						break;
						
					default:
						System.out.println("Error! Please invalid choice");
						break;
				}
				
			}
			
		}
	}
	
	public void register() {
		System.out.println("******************************************************************");
		System.out.println("Enter your first name:");
		System.out.println("******************************************************************");
		String firstName = sc.next();
		
		System.out.println("******************************************************************");
		System.out.println("Enter your last name:");
		System.out.println("******************************************************************");
		String lastName = sc.next();
		
		System.out.println("******************************************************************");
		System.out.println("Enter your email:");
		System.out.println("******************************************************************");
		String email = sc.next();
		
		System.out.println("******************************************************************");
		System.out.println("Enter your desired username:");
		System.out.println("******************************************************************");
		String username = sc.next();
		
		boolean usernameOk = false;
		while(!usernameOk) {
			user = us.getUserByUsername(username);
			if(user == null) {
				usernameOk = true;
			} else {
				System.out.println("******************************************************************");
				System.out.println("Error! That username is already take. Please choose a different one.");
				System.out.println("******************************************************************");
				username = sc.next();
			}
		}
		
		
		System.out.println("******************************************************************");
		System.out.println("Enter your password:");
		System.out.println("******************************************************************");
		String password = sc.next();
		
		boolean passwordOk = false;
		while(!passwordOk) {
			if (password.length() < 6) {
				System.out.println("******************************************************************");
				System.out.println("Error! Please choose a password that has at least six characters.");
				System.out.println("******************************************************************");
				password = sc.next();
			} else {
				passwordOk = true;
			}
		}
		
		// Insert new user
		this.us.insertNewUser(3, firstName, lastName, email, username, password);
		
		System.out.println();
		System.out.println("******************************************************");
		System.out.println("Your account has been created! You may now log in.");
		System.out.println("******************************************************");
	}
	
	public void userAccountFlow() throws SQLException {
		
		User user = null;
		UserBankAccount uba = null;
		
		boolean validLogin = false;
		while(!validLogin) {
			
			System.out.println("******************************************************************");
			System.out.println("Please enter your username:");
			System.out.println("******************************************************************");
			username = sc.next();
			System.out.println("******************************************************************");
			System.out.println("Please enter your password:");
			System.out.println("******************************************************************");
			password = sc.next();
			
			user = us.getUserByUsername(username);
			if(user == null) {
				System.out.println();
				System.out.println("******************************************************************");
				System.out.println("Error! The username or password is incorrect. Please try again.");
				System.out.println("******************************************************************");
			} else {
				if(user.getPassword().equals(password)) {
					validLogin = true;
				} else {
					System.out.println();
					System.out.println("******************************************************************");
					System.out.println("Error! The username or password is incorrect. Please try again.");
					System.out.println("******************************************************************");
				}
			}
		}
		
		boolean logout = false;
		while(!logout) {
			
			boolean hasApplied = false;
			uba = ubas.getRecordByCustId(user.getId());
			if(uba == null) {
				
				System.out.println();
				System.out.println("******************************************************************");
				System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
				System.out.println("******************************************************************");
				System.out.println("Please choose an action:");
				System.out.println("1. Apply for single account");
				System.out.println("2. Apply for joint account");
				System.out.println("3. View personal information");
				System.out.println("4. Logout");
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						System.out.println("******************************************************************");
						System.out.println("How much would you like to deposit?");
						System.out.println("******************************************************************");
						initialDeposit = sc.nextDouble();
						
						boolean depositOk = false;
						while(!depositOk) {
							if(initialDeposit < 50) {
								System.out.println("******************************************************************");
								System.out.println("Please deposit a minimum of $50");
								System.out.println("******************************************************************");
								initialDeposit = sc.nextDouble();
							} else {
								depositOk = true;
							}
						}
						
						as.insertNewAccount(0, initialDeposit);
						List<Account> accounts = as.retrieveAllAccounts();
						account = accounts.get(0);
						ubas.insertRecord(user.getId(), account.getId());
						break;
						
						
					case 2:
						User userTwo = null;
						mainMenu = false;
						while(!mainMenu) {
							
							System.out.println("******************************************************************");
							System.out.println("Please enter the username of the customer you will have a joing account with:");
							System.out.println("******************************************************************");
							username = sc.next();
							
							boolean userNotFound = true;
							while(userNotFound) {
								userTwo = us.getUserByUsername(username);
								if(userTwo == null) {
									System.out.println("******************************************************************");
									System.out.println("Error! We could not find a customer with that username. Please try again.");
									System.out.println("******************************************************************");
									username = sc.next();
								} else {
									userNotFound = false;
								}
							}
							
							uba = ubas.getRecordByCustId(userTwo.getId());
							if(uba == null) {
								System.out.println("******************************************************************");
								System.out.println("How much would you like to deposit?");
								System.out.println("******************************************************************");
								initialDeposit = sc.nextDouble();
								
								boolean depositOkTwo = false;
								while(!depositOkTwo) {
									if(initialDeposit < 50) {
										System.out.println("******************************************************************");
										System.out.println("Please deposit a minimum of $50");
										System.out.println("******************************************************************");
										initialDeposit = sc.nextDouble();
									} else {
										depositOkTwo = true;
									}
								}
								
								as.insertNewAccount(0, initialDeposit);
								List<Account> accountsTwo = as.retrieveAllAccounts();
								Account accountThree = accountsTwo.get(0);
								ubas.insertRecord(user.getId(), accountThree.getId());
								ubas.insertRecord(userTwo.getId(), accountThree.getId());
								
								mainMenu = true;
								
							} else {
								System.out.println("******************************************************************");
								System.out.println("Error! This user already has an account.");
								System.out.println("******************************************************************");
								System.out.println("Actions:");
								System.out.println("1. Enter Another Username");
								System.out.println("2. Main Menu");
								choice = sc.nextInt();
								
								if(choice == 2) {
									mainMenu=true;
								}
							}
							
						}
						

						break;
						
					case 3:
						mainMenu = false;
						while(!mainMenu) {
							System.out.println("******************************************************************");
							System.out.println("Name ------ " + user.getFirstName() + " " + user.getLastName());
							System.out.println("Email ----- " + user.getEmail());
							System.out.println("Username -- " + user.getUsername());
							System.out.println("Password -- " + user.getPassword());
							System.out.println("******************************************************************");
							
							System.out.println("Actions:");
							System.out.println("1. Main Menu");
							System.out.println("******************************************************************");
							choice = sc.nextInt();
							
							if(choice == 1) {
								mainMenu = true;
							} else {
								System.out.println();
								System.out.println("******************************************************************");
								System.out.println("Error! Invalid option.");
							}
						}
						break;
						
					case 4:
						logout = true;
						break;
						
					default:
						System.out.println("Error! Invalid option.");
						break;
				}
				
				
			} else {
				
				account = as.getAccount(uba.getAccountId());
				
				if(account.getIs_approved() == 0) {
					System.out.println();
					System.out.println("******************************************************************");
					System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
					System.out.println("Your account is penind approval!");
					System.out.println("******************************************************************");
					System.out.println("What would you like to do?");
					System.out.println("1. View Personal Information");
					System.out.println("2. Logout");
					choice = sc.nextInt();
					
					switch(choice) {
						case 1:
							mainMenu = false;
							while(!mainMenu) {
								System.out.println("******************************************************************");
								System.out.println("Name ------ " + user.getFirstName() + " " + user.getLastName());
								System.out.println("Email ----- " + user.getEmail());
								System.out.println("Username -- " + user.getUsername());
								System.out.println("Password -- " + user.getPassword());
								System.out.println("******************************************************************");
								
								System.out.println("Actions:");
								System.out.println("1. Main Menu");
								System.out.println("******************************************************************");
								choice = sc.nextInt();
								
								if(choice == 1) {
									mainMenu = true;
								} else {
									System.out.println();
									System.out.println("******************************************************************");
									System.out.println("Error! Invalid option.");
								}
							}
							break;
							
						case 2:
							logout = true;
							break;
							
						default:
							System.out.println("******************************************************************");
							System.out.println("Error! Invalid selection");
							break;
					}
					
				} else {
					
					logout = false;
					while(!logout) {
					
						System.out.println();
						System.out.println("******************************************************************");
						System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
						System.out.println("******************************************************************");
						System.out.println("What would you like to do?");
						System.out.println("1. Make A Deposit");
						System.out.println("2. Make A Withdrawl");
						System.out.println("3. View Account Info");
						System.out.println("4. View Personal Info");
						System.out.println("5. Logout");
						choice = sc.nextInt();
						
						switch(choice) {
						
							case 1:
								boolean depositing = true;
								while(depositing) {
									System.out.println("******************************************************************");
									System.out.println("How much would you like to deposit?");
									System.out.println("******************************************************************");
									depositAmount = sc.nextDouble();
									
									boolean depositAmountOk = false;
									while(!depositAmountOk) {
										if(depositAmount <= 0) {
											System.out.println("******************************************************************");
											System.out.println("Error! Please enter an amount greater than $0");
											System.out.println("******************************************************************");
											depositAmount = sc.nextDouble();
										} else {
											double newBalance = account.getBalance() + depositAmount;
											account.setBalance(newBalance);
											as.updateAccount(account);
											depositAmountOk = true;
										}
									}
									System.out.println("******************************************************************");
									System.out.println("Deposit made successfully! Your new balance is $" + account.getBalance());
									System.out.println("******************************************************************");
									System.out.println("Actions:");
									System.out.println("1. Make Another Deposit");
									System.out.println("2. Main Menu");
									choice = sc.nextInt();
									
									validSelection = false;
									while(!validSelection) {
										switch(choice) {
											case 1:
												validSelection=true;
												break;
												
											case 2:
												validSelection=true;
												depositing=false;
												break;
												
											default:
												System.out.println("Invalid selection. Please try again.");
												choice = sc.nextInt();
												break;
										}
									}
									
								}
								break;
								
							case 2:
								withdrawing = true;
								while(withdrawing) {
									System.out.println("******************************************************************");
									System.out.println("Available balance: $" + account.getBalance());
									System.out.println("How much would you like to withdraw from your account?");
									System.out.println("******************************************************************");
									withdrawAmount = sc.nextDouble();
									
									boolean withdrawAmountOk = false;
									while(!withdrawAmountOk) {
										if(withdrawAmount <= 0) {
											System.out.println("******************************************************************");
											System.out.println("Error! Please enter an amount greater than $0");
											System.out.println("******************************************************************");
											withdrawAmount = sc.nextDouble();
										} else if(withdrawAmount > account.getBalance()) {
											System.out.println("******************************************************************");
											System.out.println("Error! Insufficient funds. Please enter an amount less than or equal to $" + account.getBalance());
											System.out.println("******************************************************************");
											withdrawAmount = sc.nextDouble();
										} else {
											double newBalance = account.getBalance() - withdrawAmount;
											account.setBalance(newBalance);
											as.updateAccount(account);
											withdrawAmountOk = true;
										}
									}
									System.out.println("******************************************************************");
									System.out.println("Money withdrawn successfully! Your new balance is $" + account.getBalance());
									System.out.println("******************************************************************");
									System.out.println("Actions:");
									System.out.println("1. Make another widthdrawl");
									System.out.println("2. Main menu");
									choice = sc.nextInt();
									
									validSelection = false;
									while(!validSelection) {
										switch(choice) {
											case 1:
												validSelection=true;
												break;
												
											case 2:
												validSelection=true;
												withdrawing=false;
												break;
												
											default:
												System.out.println("Invalid selection. Please try again.");
												choice = sc.nextInt();
												break;
										}
									}
									
								}
								break;
							
						
							case 3:
								mainMenu = false;
								while(!mainMenu) {
									
									// Get first account
									uba = ubas.getRecordByCustId(user.getId());
									account = as.getAccount(uba.getAccountId());
									
									// Check if second customer on account
									UserBankAccount uba2 = ubas.getRecordExcludingCustId(user.getId(), account.getId());
									
									// Check if second customer exists
									if(uba2 == null) {
										System.out.println("******************************************************************");
										System.out.println("Account Id ------- " + account.getId());
										System.out.println("Account Type ----- Single");
										System.out.println("Account Balance -- $" + account.getBalance());
										System.out.println("******************************************************************");
									} else {
										User user2 = us.getUser(uba2.getUserId());
										
										System.out.println("******************************************************************");
										System.out.println("Account Id ------- " + account.getId());
										System.out.println("Account Type ----- Joint");
										System.out.println("Customer Two ----- " + user2.getFirstName() + " " + user2.getLastName());
										System.out.println("Account Balance -- $" + account.getBalance());
										System.out.println("******************************************************************");
									}
									
									System.out.println("Actions:");
									System.out.println("1. Main Menu");
									choice = sc.nextInt();
									
									validSelection = false;
									while(!validSelection) {
										if(choice == 1) {
											validSelection = true;
											mainMenu = true;
										} else {
											System.out.println("Invalid selection. Please try again.");
											choice = sc.nextInt();
										}
									}
									
											
								}
								break;
						
							case 4:
								mainMenu = false;
								while(!mainMenu) {
									System.out.println("******************************************************************");
									System.out.println("Name ------ " + user.getFirstName() + " " + user.getLastName());
									System.out.println("Email ----- " + user.getEmail());
									System.out.println("Username -- " + user.getUsername());
									System.out.println("Password -- " + user.getPassword());
									System.out.println("******************************************************************");
									
									System.out.println("Actions:");
									System.out.println("1. Main Menu");
									System.out.println("******************************************************************");
									choice = sc.nextInt();
									
									if(choice == 1) {
										mainMenu = true;
									} else {
										System.out.println();
										System.out.println("******************************************************************");
										System.out.println("Error! Invalid option.");
									}
								}
								break;
						
							case 5:
								logout=true;
								break;
								
							default:
								System.out.println("Error! Invalid selection. Please try again.");
								break;	
						}
						
						
						
					}
					
					
				}
				
				
			}
		}
		
		
	}
	
}
