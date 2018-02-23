package com.revature.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.accounts.Pending;
import com.revature.fileoperations.FileOperations;
import com.revature.menus.Menus;
import com.revature.userfunctions.*;
import com.revature.users.*;

public class Driver {
	public static void main(String[] args) {
		//loop controllers
		boolean userConnected = false;  
		boolean loggingIn = true;
		boolean validated = false;
		boolean runningBackend = true;
		boolean systemRunning = true;
		boolean logoff = false;
		
		//filenames
		String adminFilename = "admins.dat";
		String employeeFilename = "employees.dat";
		String customerFilename = "customers.dat";
		String accountFilename = "accounts.dat";
		
		//Data structures
		HashMap<String, Admin> adminsMap = new HashMap<>();
		HashMap<String, Employee> employeesMap = new HashMap<>();
		HashMap<String, Customer> customersMap = new HashMap<>();
		HashMap<String, ArrayList<Account>> accountsMap = new HashMap<>();
		ArrayList<Account> accountsList = new ArrayList<>();
		ArrayList<Account> customerAccounts = new ArrayList<>();
		ArrayList<Customer> pendingRequests = new ArrayList<>();
		
		//active session objects
		Customer customer = null;
		Employee employee = null;
		Admin admin = null;
		Account newAccount = null;
		char userType = 'n';
		boolean customerPending = false;
		
		//login and validation variables
		int option = 0;
		String userName = null;
		String password = null;
		Scanner getInput = null;

		//initialize the system
		System.out.println("Populating HashMaps...");


		//load the admin HashMap
		System.out.print("  Loading administrators...");
		adminsMap = FileOperations.readAdmins(adminFilename);
		
		//load the employee HashMap
		System.out.print("  Loading employees...");
		employeesMap = FileOperations.readEmployees(employeeFilename);

		//load the customer HashMap
		System.out.println("  Loading customers...");
		customersMap = FileOperations.readCustomers(customerFilename);
		
		System.out.println("HashMaps loaded.");
		System.out.println("  Number of customers: " + customersMap.size());
		System.out.println("  Number of employees: " + employeesMap.size());
		System.out.println("  Number of administrators: " + adminsMap.size());
		System.out.println("  Number of accounts pending: " + pendingRequests.size());
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
						option = 0;
						//create a new administrator account and add it to the map
						Admin newAdmin = BackendAdministration.createAdmin(getInput);
						adminsMap.put(newAdmin.getUserName(), newAdmin);

						//update the admins.dat file
						try {
							FileOperations.writeAdmins(adminsMap, adminFilename);
						} catch (IOException e) {
							System.out.println("IO error in writeAdmins");
							e.printStackTrace();
						}
						break;
							
					case 2:
						option = 0;
						//create a new employee account and add it to the map
						Employee newEmployee= BackendAdministration.createEmployee(getInput);
						employeesMap.put(newEmployee.getUserName(), newEmployee);
						
						//update the employees.dat file
						try {
							FileOperations.writeEmployees(employeesMap, employeeFilename);
						} catch (IOException e) {
							System.out.println("IO error in writeEmployees");
							e.printStackTrace();
						}											
						break;
								
					case 3:
						option = 0;
						//create a new customer account and add it to the map
						Customer newCustomer = BackendAdministration.createCustomer(getInput);
						customersMap.put(newCustomer.getUserName(), newCustomer);
						System.out.println("  Number of customers: " + customersMap.size());
						
						//update the customers.dat file
						try {
							FileOperations.writeCustomers(customersMap, customerFilename);
						} catch (IOException e) {
							System.out.println("IO error in writeCustomers");
							e.printStackTrace();
						}											

						break;
								
					case 4:
						option = 0;
						userConnected = true;
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
						//validate user
						System.out.print("Please enter your user name: ");
						userName = getInput.nextLine();
						
						//search the customer database
						if (customersMap.containsKey(userName)) {
							customer = customersMap.get(userName);
							userType = 'c';
						}
							
						//search the employee database
						else if(employeesMap.containsKey(userName)) {
							employee = employeesMap.get(userName);
							System.out.println("Found, pass: "+ employee.getPassword());
							userType = 'e';
						}
							
						//search the admin database
						else if (adminsMap.containsKey(userName)) {
							admin = adminsMap.get(userName);
							System.out.println("Found, pass: "+ admin.getPassword());
							userType = 'a';
						}
						
						else {
							System.out.println("User not found.");
							break;
						}
						
						System.out.print("Please enter your password: ");
						password = getInput.nextLine();
						
						//identify login type and validate login
						switch(userType) {
						
						case 'c':
							//validate customer login
							validated = UserFunctions.validateCustomer(password, customer);
							if(validated) {
								loggingIn = false;

							}
							
							else { 
								userType = 'n';
								customer = null;
							}
							break;
						
						case 'e':
							//validate employee login
							validated = UserFunctions.validateEmployee(password, employee);
							if(validated) {
								loggingIn = false;
							}
							
							else {
								System.out.println();
								userType = 'n';
								employee = null;
							}
							break;
						
						case 'a':
							//validate admin login
							validated = UserFunctions.validateAdmin(password, admin);
							if(validated) {
								loggingIn = false;
							}
							
							else {
								System.out.println();
								userType = 'n';
								admin = null;
							}
							
							break;
						}//end user type switch	
						option = 0;
						break;
					
					case 2:
						//register new customer
						customer = UserFunctions.register(getInput);
						customersMap.put(customer.getUserName(), customer);
						
						//update the customers.dat file
						try {
							FileOperations.writeCustomers(customersMap, customerFilename);
						} catch (IOException e) {
							System.out.println("IO error in writeCustomers");
							e.printStackTrace();
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
					switch(userType) {
					case 'c':
						//CUSTOMER
						Menus.displayCustomerMenu(customer.getFirstName(), customer.getLastName());
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
								customer.setApplyingForJoint(false); 
								customer.setApplyingForSavings(false);
								break;
								
							case 2:
								customer.setApplyingForJoint(true);
								customer.setApplyingForSavings(false);
								break;
								
							case 3:
								customer.setApplyingForJoint(false);
								customer.setApplyingForSavings(true);
								break;
								
							case 4:
								customer.setApplyingForJoint(true);
								customer.setApplyingForSavings(true);
								break;
							}//end account type switch
							
							for(Customer current : pendingRequests) {
								if(current.equals(customer)) {
									customerPending = false;
								}
							}
							
							//if the customer already has already applied for an account, they must await approval
							if(customerPending) {
								System.out.println("You are already awaiting account approval");
							}
							
							//otherwise, add the customer to the pending list
							else {								
								pendingRequests.add(customer);
							}//end account request else

							option = 0;
							break;
							
						case 2:
							//List customer's accounts
							if(!customer.isAccountHolder()) {
								System.out.print("You currently have no open accounts");
								if(customer.getUserName() == "js") {
									System.out.print(" (Loser)");
								}
								System.out.println();
							}
							
							else {
								Account.listAccounts(accountsMap.get(userName), userName);
							}
							
							option = 0;
							break;
							
						case 3:
							//View account
							option = 0;
							break;
							
						case 4:
							//Withdraw from account
							option = 0;
							break;
							
						case 5:
							//Deposit to account
							option = 0;
							break;
							
						case 6:
							//Transfer funds
							option = 0;
							break;
							
						case 7:
							//exit the system
							option = 0;
							validated = false;
							
							break;
							
					    default:
					    	System.out.println();
					    	break;
						}
						break;
						
					case 'e':
						while(!logoff) {
							//EMPLOYEE
							Menus.displayEmployeeMenu(employee.getFirstName(), employee.getLastName());
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
								UserFunctions.viewCustomerInformation(customersMap, getInput);
								option = 0;
								break;
								
							case 2:
								//view customer accounts
								option = 0;
								break;
	
							case 3:
								//View pending account applications
								customer = Pending.view(pendingRequests, getInput);
								
								//if applicable, create the approved account
								if(customer != null) {
									accountsMap = Account.createAccount(customer, accountsMap);
									System.out.println("Account created.");

								}
								option = 0;
								break;
	
							case 4:
								logoff = true;
								break;
							}//end switch

						}//end while
						break;
						
					case 'a':
						//ADMIN
						Menus.displayAdminMenu(admin.getFirstName(), admin.getLastName());
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
							UserFunctions.viewCustomerInformation(customersMap, getInput);
							option = 0;
							break;

						case 2:
							//view customer accounts
							option = 0;
							break;

						case 3:
							//View pending account applications
							customer = Pending.view(pendingRequests, getInput);

							//if applicable, create the approved account
							if(customer != null) {
								accountsMap = Account.createAccount(customer, accountsMap);
								System.out.println("Account created.");

							}
							
							option = 0;
							break;

						case 4:
							//modify accounts
							option = 0;
							break;

						case 5:
							//exit
							option = 0;
							break;

						}
						break;
					}//end switch
					
				break;
				}//end banking system loop
			System.out.println();
			logoff = false;
			break;
			}//end user connected while loop
		}//system running loop
	}//end main
}//end Driver class