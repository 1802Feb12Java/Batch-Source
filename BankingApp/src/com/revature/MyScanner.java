package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

public class MyScanner {
	
	public MyScanner() {
		
		// Instantiate new Scanner object
		Scanner sc = new Scanner(System.in);
		
		// Create new InteractWithFile object
		InteractWithFile fi = new InteractWithFile();
		
		// Take in user input for principal and store as int
		System.out.println("Please choose from the following options:");
		System.out.println("1. Login");
		System.out.println("2. Register");
		
		// Get input
		int roleInput = sc.nextInt();
		
		// Set booleans
		boolean goToNextScreen = false;
		boolean createAccount = false;
		
		while(!goToNextScreen) {
			
			switch(roleInput) {
				case 1:
					goToNextScreen = true;
					break;
					
				case 2:
					goToNextScreen = true;
					createAccount = true;
					break;
					
				default:
					System.out.println("Please choose from the following options:");
					System.out.println("1. Login");
					System.out.println("2. Register");
					roleInput = sc.nextInt();
					break;
			}
		
		}
	
		if(createAccount) {
			
			// Get customer's first name
			System.out.println("Please enter your first name:");
			String firstName = sc.next();
			
			// Get customer's last name
			System.out.println("Please enter your last name:");
			String lastName = sc.next();
			
			// Get customer's username
			System.out.println("Please choose your username:");
			String username = sc.next();
			
			ArrayList<String> usernames = fi.readUsernames();
			
			boolean sameUsername = true;
			while(sameUsername) {
				
				sameUsername = false;
				
				for(String existingUsername : usernames) {
					if(username.equals(existingUsername)) {
						sameUsername = true;
					}
				}
				if(sameUsername) {
					System.out.println("Sorry, that username is taken. Please choose another:");
					username = sc.next();
				}
			}
			
			// Get customer's password
			System.out.println("Please choose your password:");
			String password = sc.next();
			
			boolean passTooShort = true;
			while(passTooShort) {
				if(password.length() < 6) {
					System.out.println("Error! Your password must be at least 6 characters long.");
					password = sc.next();
				} else {
					passTooShort = false;
				}
			}
			
			
			// Populate customer id
			int id = fi.getLastUserId();
			id++;

			// Create new customer
			Customer cust = new Customer(id, firstName, lastName, username, password);
			
			// Create string to store in users.txt
			String custStr = cust.getCustomerId() + ":" + cust.getFirstName() + ":" + cust.getLastName() + ":" + cust.getUsername() + ":" + cust.getPassword();
			
			// Write to users.txt
			fi.addNewCustomer(custStr);
			
			// Re-instantiate class
			new MyScanner();
			
		} else {
			
			// Get login username
			System.out.println("Please enter your username:");
			String username = sc.next();
			
			// Check if username exists
			ArrayList<String> usernames = fi.readUsernames();
			boolean usernameExists = false;
			
			while(!usernameExists) {
				for(String uname : usernames) {
					if(username.equals(uname)) {
						usernameExists = true;
					}
				}
				if(!usernameExists) {
					System.out.println("Error! That username does not exists. Please try again.");
					username = sc.next();
				}
			}
			
			
			if(username.equals("jboud1217")) {
				
				// Get password
				System.out.println("Please enter your password:");
				String password = sc.next();
				
				ArrayList<String> admin = fi.getUser("jboud1217");
				
				if(!password.equals(admin.get(4))) {
					boolean wrongPassword = true;
					while(wrongPassword) {
						System.out.println("Error! Wrong password. Please try again.");
						password = sc.next();
						if(password.equals("bobcat")) {
							wrongPassword = false;
						}
					}
				}
				
				boolean loggedIn = true;
				while(loggedIn) {
					
					// Login successful
					System.out.println("Hello Admin! Please choose an action:");
					System.out.println("1. View Accounts");
					System.out.println("2. Cancel Account");
					System.out.println("3. Transfer");
					System.out.println("4. Pending Applications");
					System.out.println("5. Logout");
					int choice = sc.nextInt();
					
					ArrayList<String> pendingApplications = new ArrayList<String>();
					
					switch(choice) {
						case 1:
							ArrayList<String> customers = new ArrayList<String>();
							customers = fi.getCustomers();
							
							for(String customer : customers) {
								String[] split = customer.split(":");
								
								int id = Integer.parseInt(split[0]);
								ArrayList<String> account = fi.getCustAccount(id);
								
								double balance = Double.parseDouble(account.get(4));
								
								if(account.get(3).equals("0")) {
									System.out.println("-----------------------------------------");
									System.out.println("Account type: Single");
									System.out.println("Account member: " + split[1] + " " + split[2]);
									System.out.println("Accoutn balance: " + balance);
									System.out.println("-----------------------------------------");
								} else {
									System.out.println("-----------------------------------------");
									System.out.println("Account type: Joint");
									System.out.println("Account member: " + split[1] + " " + split[2]);
									System.out.println("Account balance: " + balance);
									System.out.println("-----------------------------------------");
								}
								
								
								
							}
							
							System.out.println("1. Main Menu");
							choice = sc.nextInt();
							
							break;
					
						case 3:
							System.out.println("Enter the username of the account you would like to transfer money from.");
							username = sc.next();
							
							ArrayList<String> user = fi.getUser(username);
							int id = Integer.parseInt(user.get(0));
							
							ArrayList<String> account = fi.getCustAccount(id);
							double balance = Double.parseDouble(account.get(4));
							
							System.out.println("The current balane in the account is $" + balance + ". How much would you like to transfer?");
							double transferAmount = sc.nextDouble();
							
							boolean transfering = true;
							while(transfering) {
								if(transferAmount <= balance) {
									
									System.out.println("Enter the username of the account you would like to transfer the money to.");
									String usernameTwo = sc.next();
									
									ArrayList<String> userTwo = fi.getUser(usernameTwo);
									int idTwo = Integer.parseInt(userTwo.get(0));
									
									ArrayList<String> accountTwo = fi.getCustAccount(idTwo);
									double balanceTwo = Double.parseDouble(accountTwo.get(4));
									
									
									double newAmount = balance - transferAmount;
									int accountId = Integer.parseInt(account.get(0));
									fi.updateAccountBalance(accountId, newAmount);
									
									double newAmountTwo = balanceTwo + transferAmount;
									int accountIdTwo = Integer.parseInt(accountTwo.get(0));
									fi.updateAccountBalance(accountIdTwo, newAmountTwo);
									
									
									System.out.println("Transfer was successful!");
									System.out.println("1. Main Menu");
									choice = sc.nextInt();
									
									
									
								} else {
									System.out.println("Error! Please enter an amount equal to or less than $" + balance);
									transferAmount = sc.nextDouble();
								}
							}
							
							break;
					
						case 4:
							pendingApplications = fi.getPendingApplications();
							
							if(pendingApplications.isEmpty()) {
								System.out.println("There are currently no applications pending approval.");
								System.out.println("1. Main Menu");
								choice = sc.nextInt();
							} else {
								for(String application : pendingApplications) {
									
									String[] split = application.split(":");
									int appId = Integer.parseInt(split[0]);
									int custIdOne = Integer.parseInt(split[2]);
									int custIdTwo = Integer.parseInt(split[3]);
									
									if(custIdOne != 0 && custIdTwo != 0) {
										
										System.out.println("-----------------------------------------");
										System.out.println("Account Type: Joint");
										
										ArrayList<String> custOne = fi.getUserById(custIdOne);
										ArrayList<String> custTwo = fi.getUserById(custIdTwo);
										
										System.out.println("Customer One: " + custOne.get(1) + " " + custOne.get(2));
										System.out.println("Customer Two: " + custTwo.get(1) + " " + custTwo.get(2));
										System.out.println("-----------------------------------------");
										System.out.println("1. Approve " + custOne.get(1) + " " + custOne.get(2) + " and " + custTwo.get(1) + " " + custTwo.get(2));
										System.out.println("2. Disapprove " + custOne.get(1) + " " + custOne.get(2) + " and " + custTwo.get(1) + " " + custTwo.get(2));
										System.out.println("-----------------------------------------");
										choice = sc.nextInt();
										
										if(choice == 1) {
											fi.approveApplication(appId);
											System.out.println("Application has been approved successfully!");
											System.out.println("1. Main Menu");
											choice = sc.nextInt();
										}
										
										
									} else {
										System.out.println("-----------------------------------------");
										System.out.println("Account Type: Single");
										
										ArrayList<String> custOne = fi.getUserById(custIdOne);
										
										System.out.println("Customer: " + custOne.get(1) + " " + custOne.get(2));
										
										System.out.println("-----------------------------------------");
										System.out.println("1. Approve " + custOne.get(1) + " " + custOne.get(2));
										System.out.println("2. Disapprove " + custOne.get(1) + " " + custOne.get(2));
										System.out.println("-----------------------------------------");
										choice = sc.nextInt();
										
										if(choice == 1) {
											fi.approveApplication(appId);
											System.out.println("Application has been approved successfully!");
											System.out.println("1. Main Menu");
											choice = sc.nextInt();
										}
									}
								}
							
							}
							break;
							
						case 5:
							new MyScanner();
							break;
							
						default:
							break;
					}
					
				}	
				
			} else {
				
				// After confirming username exists, get that user
				ArrayList<String> user = fi.getUser(username);
				
				int custId = Integer.parseInt(user.get(0));
				Customer cust = new Customer(custId, user.get(1), user.get(2), user.get(3), user.get(4));
				
				// Get password
				System.out.println("Please enter your password:");
				String password = sc.next();
				
				// Validate password
				if(!password.equals(cust.getPassword())) {
					boolean wrongPassword = true;
					while(wrongPassword) {
						System.out.println("Error! Wrong password. Please try again.");
						password = sc.next();
						if(password.equals(cust.getPassword())) {
							wrongPassword = false;
						}
					}
				}
				
				// Check for existing account
				boolean hasAccount = fi.checkForAccount(custId);
				
				if(hasAccount) {
					
					ArrayList<String> cal = fi.getCustAccount(custId);
					
					// id
					String cal0 = cal.get(0);
					int cal00 = Integer.parseInt(cal0);
					
					// is_approved
					String cal1 = cal.get(1);
					int cal11 = Integer.parseInt(cal1);
					
					// custIdOne
					String cal2 = cal.get(2);
					int cal22 = Integer.parseInt(cal2);
					
					// custIdTwo
					String cal3 = cal.get(3);
					int cal33 = Integer.parseInt(cal3);
					
					// accountBalance
					String cal4 = cal.get(4);
					double cal44 = Double.parseDouble(cal4);
					
					Account custAccount = new Account(cal00, cal11, cal22, cal33, cal44);
					
					if(cal.get(1).equals("0")) {
						System.out.println("Hi " + cust.getFirstName() + " " + cust.getLastName() + "! Your account is currently pending approval! Please check back later.");
						System.out.println("1. Logout");
						int choice = sc.nextInt();
						if(choice == 1) {
							new MyScanner();
						}
					} else {
						
						System.out.println("Hi " + cust.getFirstName() + " " + cust.getLastName() + "! Welcome to your account!");
						System.out.println("Please choose from one of the following:");
						System.out.println("1. View account balance");
						System.out.println("2. Make a withdrawl");
						System.out.println("3. Make a deposit");
						System.out.println("4. Logout");
						int choice = sc.nextInt();
						
						boolean loggedIn = true;
						boolean mainMenu = false;
						
						while(loggedIn) {
							
							if(mainMenu) {
								
								mainMenu = false;
								
								System.out.println("Please choose from one of the following:");
								System.out.println("1. View account balance");
								System.out.println("2. Make a withdrawl");
								System.out.println("3. Make a deposit");
								System.out.println("4. Logout");
								choice = sc.nextInt();
							}
							
							switch(choice) {
								case 1:
									System.out.println("Your current account balance is: $" + custAccount.getAccountBalance());
									System.out.println("1. Main Menu");
									int result = sc.nextInt();
									if(result == 1) {
										mainMenu = true;
									}
									break;
									
								case 2:
									System.out.println("How much would you like to withdraw from your account?");
									double withdrawAmount = sc.nextDouble();
									
									boolean withdrawing = true;
									
									while(withdrawing) {
										if(withdrawAmount > custAccount.getAccountBalance()) {
											System.out.println("Error! Please enter an amount less than or equal to $" + custAccount.getAccountBalance());
											withdrawAmount = sc.nextDouble();
										} else {
											double newAmount = custAccount.getAccountBalance() - withdrawAmount;
											custAccount.setAccountBalance(newAmount);
											
											fi.updateAccountBalance(custAccount.getId(), custAccount.getAccountBalance());
											
											withdrawing = false;
										}
									}
									
									System.out.println("Your new account balance is $" + custAccount.getAccountBalance());
									System.out.println("1. Main Menu");
									choice = sc.nextInt();
									
									mainMenu = true;
									
									break;
									
								case 3:
									System.out.println("How much would you like to deposit to your account?");
									double depositAmount = sc.nextDouble();
									
									boolean depositing = true;
									
									while(depositing) {
										if(depositAmount < 0) {
											System.out.println("Error! Please enter a positive amount.");
											depositAmount = sc.nextDouble();
										} else {
											double newAmount = custAccount.getAccountBalance() + depositAmount;
											custAccount.setAccountBalance(newAmount);
											
											fi.updateAccountBalance(custAccount.getId(), custAccount.getAccountBalance());
											
											depositing = false;
										}
									}
									
									System.out.println("Your new account balance is $" + custAccount.getAccountBalance());
									System.out.println("1. Main Menu");
									choice = sc.nextInt();
									
									mainMenu = true;
									
									break;
									
								case 4:
									new MyScanner();
									break;
									
								default:
									break;
							}
						}
						

					}
					
				} else {
					System.out.println("Hi! " + cust.getFirstName() + " " + cust.getLastName() + ". Please choose an action!");
					System.out.println("1. Apply for bank account");
					System.out.println("2. Logout");
					int action = sc.nextInt();
					
					boolean apply = false;
					switch(action) {
						case 1:
							apply = true;
							break;
								
						case 2:
							new MyScanner();
							break;
					}
					
					if(apply) {
						System.out.println("Is this a single or joint account?");
						System.out.println("1. Single");
						System.out.println("2. Joint");
						int accountMemberType = sc.nextInt();
						
						if(accountMemberType == 2) {
							System.out.println("What is the username of the person you would like a joint account with?");
							String jointUsername = sc.next();
							
							ArrayList<String> jointUser = fi.getUser(jointUsername);
							
							int accountId = fi.getLastAccountId();
							accountId++;
							
							String newAccount = accountId + ":" + "0:" + cust.getCustomerId() + ":" + jointUser.get(0) + ":0";             
							fi.createJointAccount(newAccount);
						} else {
							
							int accountId = fi.getLastAccountId();
							accountId++;
							
							String newAccount = accountId + ":" + "0:" + cust.getCustomerId() + ":0:0";
							fi.createNewAccount(newAccount);
						}
					}
					
					System.out.println("Application received! Your account is pending approval!");
					System.out.println("1. Logout");
					int choice = sc.nextInt();
					
					if(choice == 1) {
						new MyScanner();
					}
					
				}
				
			}
			
		}
			
		// Close scanner
		sc.close();
	}

}
