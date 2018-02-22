package com.revature.driver;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.fileoperations.FileOperations;
import com.revature.menus.Menus;
import com.revature.userfunctions.*;
import com.revature.users.*;

public class Driver {
	public static void main(String[] args) {
		//loop controllers
		boolean userConnected = true;  
		boolean loggingIn = true;
		boolean validated = false;
		boolean runningBackend = true;
		boolean systemRunning = true;
		
		//filenames
		String adminFilename = "admins.dat";
		String employeeFilename = "employees.dat";
		String customerFilename = "customers.dat";
		String accountFilename = "accounts.dat";
		
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
		System.out.print("Populating HashMaps...");
		HashMap<String, Admin> adminsMap = FileOperations.readAdmins(adminFilename);
		HashMap<String, Employee> employeesMap = FileOperations.readEmployees(employeeFilename);
		HashMap<String, Customer> customersMap = FileOperations.readCustomers(customerFilename);
		System.out.println("Success!");
		
		//open the scanner to be used for all input
		getInput = new Scanner(System.in);

		while(systemRunning) {
			do {
				Menus.displayStartMenu();
			
				option = getInput.nextInt();
					
				if (option == 1) {
					runningBackend = false;
				}//end option 1
						
				if (option == 2) {	
					Menus.displayBackendAccountCreationMenu();							
					option = getInput.nextInt();
						
					switch(option) {
					case 1:
						Admin newAdmin = BackendAdministration.createAdmin(getInput);
						adminsMap.put(newAdmin.getUserName(), newAdmin);
						try {
							FileOperations.writeAdmins(adminsMap, adminFilename);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
							
					case 2:
						Employee newEmployee= BackendAdministration.createEmployee(getInput);
						employeesMap.put(newEmployee.getUserName(), newEmployee);
						break;
								
					case 3:
						Customer newCustomer = BackendAdministration.createCustomer(getInput);
						customersMap.put(newCustomer.getUserName(), newCustomer);
						break;
								
					case 4:
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
					}catch(Exception e) {
						System.out.println("Please enter an appropriate selection");
					}
					
					switch(option) {
					
					//begin login procedure
					case 1:
						//validate user
						System.out.print("Please enter your user name: ");
						userName = getInput.next();
						
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
							System.out.println();
							break;
						}
						
						System.out.print("Please enter your password: ");
						password = getInput.next();
						
						//identify login type and validate login
						switch(userType) {
						
						case 'c':
							//validate customer login
							validated = UserFunctions.validateCustomer(password, customer);
							if(validated) {
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
						customer = UserFunctions.register(getInput);
						customersMap.put(customer.getUserName(), customer);
						
	
						validated = true;
						loggingIn = false;
						break; 
						
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
