package com.revature.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.revature.accounts.Account;
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
		
		//filenames
		String adminFilename = "admins.dat";
		String employeeFilename = "employees.dat";
		String customerFilename = "customers.dat";
		String accountFilename = "accounts.dat";
		
		//Data structures
		HashMap<String, Admin> adminsMap = new HashMap<>();
		HashMap<String, Employee> employeesMap = new HashMap<>();
		HashMap<String, Customer> customersMap = new HashMap<>();
		HashMap<String, Customer> pendingRequestsMap = new HashMap<>();
		HashMap<String, List> accountsMap = new HashMap<>();
		List<Account> accounts = new ArrayList<>();
		
		//active session objects
		Customer customer = null;
		Employee employee = null;
		Admin admin = null;
		char userType = 'n';
		
		//login and validation variables
		int option = 0;
		String userName = null;
		String password = null;
		Scanner getInput = null;

		//initialize the system
		System.out.println("Populating HashMaps...");
		System.out.print("  Loading administrators...");

		//load the admin HashMap
		try {
			adminsMap = FileOperations.readAdmins(adminFilename);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("none found.");
		}
		
		//load the employee HashMap
		System.out.print("  Loading employees...");
		try {
			employeesMap = FileOperations.readEmployees(employeeFilename);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("none found.");
		}
		
		//load the customer HashMap
		System.out.print("  Loading customers...");
		try {
			customersMap = FileOperations.readCustomers(customerFilename);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("none found.");
		}
		
		System.out.println("HashMaps loaded.");
		
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
						
						//update the customers.dat file
						try {
							FileOperations.writeCustomers(customersMap, employeeFilename);
						} catch (IOException e) {
							System.out.println("IO error in writeEmployees");
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
						getInput.nextLine();
					}catch(Exception e) {
						System.out.println("Please enter an appropriate selection");
					}
					
					switch(option) {
					
					//begin login procedure
					case 1:
						option = 0;
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
						break;
					
					case 2:
						System.out.println(option);
						option = 0;
						//register the user
						customer = UserFunctions.register(getInput);
						customersMap.put(customer.getUserName(), customer);
						
						//update the customers.dat file
						try {
							FileOperations.writeCustomers(customersMap, customerFilename);
						} catch (IOException e) {
							System.out.println("IO error in writeEmployees");
							e.printStackTrace();
						}											

						validated = true;
						loggingIn = false;
						break; 
						
					case 3:
						option = 0;
						userConnected = false;
						break;
						
					default:
						break;
					}//end login/register switch
				}//end logging in while loop
				
				while(validated) {
					switch(userType) {
					case 'c':
						Menus.displayCustomerMenu(customer.getFirstName(), customer.getLastName());
						System.out.print("Please make a selection: ");
						
						try {
							option = getInput.nextInt();
							}catch(Exception e) {
								System.out.println("Please enter an appropriate selection");
							}
							
						getInput.nextLine();

						break;
						
					case 'e':
						Menus.displayEmployeeMenu(employee.getFirstName(), employee.getLastName());
						System.out.print("Please make a selection: ");
						
						try {
							option = getInput.nextInt();
							}catch(Exception e) {
								System.out.println("Please enter an appropriate selection");
							}
							
						getInput.nextLine();
						break;
						
					case 'a':
						Menus.displayAdminMenu(admin.getFirstName(), admin.getLastName());
						System.out.print("Please make a selection: ");
						
						try {
							option = getInput.nextInt();
							}catch(Exception e) {
								System.out.println("Please enter an appropriate selection");
							}
							
						getInput.nextLine();
						break;
					}//end switch
					
				break;
				}//end banking system loop
			}//end user connected while loop
		}//system running loop
	}//end main
}//end Driver class