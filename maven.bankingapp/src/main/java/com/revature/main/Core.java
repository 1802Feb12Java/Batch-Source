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
	Menu menu = new Menu();
	
	int choice = 0;
	String username = "";
	String password = "";
	
	public void adminLogin() throws FileNotFoundException, IOException, SQLException {
		
		// Get super user login info from database.properties
		Properties prop = new Properties();
		prop.load(new FileReader("src/main/resources/database.properties"));
		String s_username = prop.getProperty("s_usr");
		String s_password = prop.getProperty("s_psw");
		
		// Get username input
		System.out.println();
		System.out.println("Enter your username");
		String usernameInput = sc.next();
		
		// Get password input
		System.out.println();
		System.out.println("Enter your password");
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
				System.out.println("1. View User Accounts");
				System.out.println("2. View Pending Applications");
				System.out.println("3. Logout");
				choice = sc.nextInt();
				
				
				switch(choice) {
					case 1:
						
						boolean userAccountScreen = true;
						while(userAccountScreen) {
							System.out.println("******************************************************************");
							System.out.println("User account list:");
							System.out.println("******************************************************************");
							
							users = us.retrieveAllUsers();
							for(User user : users) {
								System.out.println("User Id ----- " + user.getId());
								System.out.println("First Name -- " + user.getFirstName());
								System.out.println("Last Name --- " + user.getLastName());
								System.out.println("Email ------- " + user.getEmail());
								System.out.println("Phone ------- " + user.getPhone());
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
										System.out.println("(4) Phone ------- " + userEdit.getPhone());
										System.out.println("(5) Username ---- " + userEdit.getUsername());
										System.out.println("(6) Password ---- " + userEdit.getPassword());
										System.out.println("(7) EXIT");
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
												System.out.println("Enter new phone number:");
												value = sc.next();
												userEdit.setPhone(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 5:
												System.out.println("Enter new username:");
												value = sc.next();
												userEdit.setUsername(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 6:
												System.out.println("Enter new password:");
												value = sc.next();
												userEdit.setPassword(value);
												us.updateUser(userEdit);
												System.out.println("******************************************************************");
												System.out.println("User updated successfully!");
												break;
												
											case 7:
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
											System.out.println("Phone ------------ " + user.getPhone());
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
		System.out.println("Enter your first name:");
		String firstName = sc.next();
		
		System.out.println("Enter your last name:");
		String lastName = sc.next();
		
		System.out.println("Enter your email:");
		String email = sc.next();
		
		System.out.println("Enter your phone number:");
		String phone = sc.next();
		
		System.out.println("Enter your desired username:");
		String username = sc.next();
		
		System.out.println("Enter your password:");
		String password = sc.next();
		
		boolean passwordOk = false;
		while(!passwordOk) {
			if (password.length() < 6) {
				System.out.println("Error! Please choose a password that has at least six characters.");
				password = sc.next();
			} else {
				passwordOk = true;
			}
		}
		
		this.us.insertNewUser(3, firstName, lastName, email, phone, username, password);
	}
	
	public void userAccountFlow() throws SQLException {
		
		User user = null;
		UserBankAccount uba = null;
		
		boolean validLogin = false;
		while(!validLogin) {
			
			System.out.println("Please enter your username:");
			username = sc.next();
			
			System.out.println("Please enter your password:");
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
						double initialDeposit = sc.nextDouble();
						
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
						Account account = accounts.get(0);
						ubas.insertRecord(user.getId(), account.getId());
						break;
						
					case 3:
						menu.customerPersonalInfo();
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
							boolean mainMenu = false;
							while(!mainMenu) {
								System.out.println("******************************************************************");
								System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
								System.out.println("Email: " + user.getEmail());
								System.out.println("Phone: " + user.getPhone());
								System.out.println("Username: " + user.getUsername());
								System.out.println("Password: " + user.getPassword());
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
					
					System.out.println();
					System.out.println("******************************************************************");
					System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
					System.out.println("******************************************************************");
					System.out.println("What would you like to do?");
					System.out.println("1. Make A Deposit");
					System.out.println("2. Make A Withdrawl");

					choice = sc.nextInt();
				}
				
				
			}
		}
		
		
	}
	
	public boolean userHasAccount(int id) {
		
		
		
		
		
		return false;
	}
	
	
	
	
	
	
	
	
}
