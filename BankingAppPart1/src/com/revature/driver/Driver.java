package com.revature.driver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
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
		
		//HashMaps
		HashMap<String, Admin> adminsMap = new HashMap<>();
		HashMap<String, Employee> employeesMap = new HashMap<>();
		HashMap<String, Customer> customersMap = new HashMap<>();
		
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

		//open files and populate the HashMaps
		System.out.println("Populating HashMaps...");
		System.out.print("  Loading administrators...");

		try {
			adminsMap = FileOperations.readAdmins(adminFilename);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("none found.");
		}
		
		System.out.print("  Loading employees...");
		try {
			employeesMap = FileOperations.readEmployees(employeeFilename);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("none found.");
		}
		
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

		while(systemRunning) {
			do {
				Menus.displayStartMenu();
			
				option = getInput.nextInt();
					
				if (option == 1) {
					runningBackend = false;
					userConnected = true;
				}//end option 1
						
				if (option == 2) {	
					Menus.displayBackendAccountCreationMenu();							
					option = getInput.nextInt();
					getInput.nextLine();
						
					switch(option) {
					case 1:
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
						userConnected = true;
						runningBackend = false;
						break;
								
					default:
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
							userType = 'e';
						}
							
						//search the admin database
						else if (adminsMap.containsKey(userName)) {
							admin = adminsMap.get(userName);
							userType = 'a';
						}
						
						else {
							System.out.println("User not found.");
							break;
						}
						
						System.out.print("Please enter your password: ");
						password = getInput.next();
						getInput.next();
						
						//identify login type and validate login
						switch(userType) {
						
						case 'c':
							//validate customer login
							validated = UserFunctions.validateCustomer(password, customer);
							if(validated) {
								System.out.println("success");
								loggingIn = false;
								break;
							}
							
							else { 
								System.out.println();
								userType = 'n';
								customer = null;
							}
						
						case 'e':
							//validate employee login
							validated = UserFunctions.validateEmployee(password, employee);
							if(validated) {
								loggingIn = false;
								break;
							}
							
							else {
								System.out.println();
								userType = 'n';
								employee = null;
							}
							
						
						case 'a':
							//validate admin login
							validated = UserFunctions.validateAdmin(password, admin);
							if(validated) {
								loggingIn = false;
								break;
							}
							
							else {
								System.out.println();
								userType = 'n';
								admin = null;
							}
						}//end user type switch
							
	
					case 2:
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
						userConnected = false;
					default:
						break;
					}//end login/register switch			
				}//end logging in while loop
				
				while(validated) {
				//access the banking system	
				}//end banking system loop

			}//end user connected while loop
		}//system running loop
	}//end main
}//end Driver class
