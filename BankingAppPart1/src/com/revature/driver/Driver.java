package com.revature.driver;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.revature.accounts.Account;
import com.revature.userfunctions.UserFunctions;
import com.revature.users.*;

public class Driver {
	public static void main(String[] args) {
		//loop controllers
		boolean userConnected = true;
		boolean loggingIn = true;
		boolean loggedIn = false;
		boolean userFound = false;
		
		//filenames
		String customerFilename = "customers.dat";
		String employeeFilename = "employees.dat";
		String adminFilename = "admins.dat";
		String accountFilename = "accounts.dat";
		
		//storage structures
		HashMap <String, Customer> customers = new HashMap<>();
		HashMap <String, Employee> employees = new HashMap<>();
		HashMap <String, Admin> admins = new HashMap<>();
		HashMap <String, List<Account>> accounts = new HashMap<>();
		
		//active session objects
		Customer customer = null;
		Employee employee = null;
		Admin admin = null;
		Account account = null;
		Object currentObject = null;
		ObjectInputStream inputStream = null;
		char userType = 'n';
		
		//login and validation variables
		int option = 0;
		int loginAttempts = 0;
		String userName = null;
		String password = null;
		Scanner getInput = null;

		//open files and populate the HashMaps
		System.out.print("Populating HashMaps...");
		try {
			inputStream = new ObjectInputStream(new FileInputStream("users.dat"));
			
			while ((currentObject = inputStream.readObject()) != null) {
				if(currentObject instanceof Customer) {
					customers.put(((Customer) currentObject).getUserName(), (Customer) currentObject);
				}
				
				if(currentObject instanceof Employee) {
					employees.put(((Employee) currentObject).getUserName(), (Employee) currentObject);
	
				}
				
				if (currentObject instanceof Admin) {
					admins.put(((Admin) currentObject).getUserName(), (Admin) currentObject);
	
				}
			}//end inputStream while loop
		}catch (EOFException e) {  //This exception will be caught when EOF is reached
	        System.out.println("Success!");
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }catch (IOException e) {
	        e.printStackTrace();
	    }finally {
	        //Close the ObjectInputStream
	        try {
	            if (inputStream != null) {
	                inputStream.close();
	            }
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
	    }//end finally

		System.out.print("Reading in accounts");
		try {
			inputStream = new ObjectInputStream(new FileInputStream("accounts.dat"));
			
			while ((currentObject = inputStream.readObject()) != null) {
				if(currentObject instanceof Account) {
					accounts.put(((Account) currentObject).getUserName(), (Account) currentObject);
				}
			}//end inputStream while loop
		}catch (EOFException e) {  //This exception will be caught when EOF is reached
	        System.out.println("Success!");
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }catch (IOException e) {
	        e.printStackTrace();
	    }finally {
	        //Close the ObjectInputStream
	        try {
	            if (inputStream != null) {
	                inputStream.close();
	            }
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
	    }//end finally
		
		
		while(userConnected) {			
			//initiate login		
			while(loggingIn) {
				//determine if new or returning user
				System.out.println("1.  Login existing user");
				System.out.println("2.  Register new user");
				System.out.print("Please select an option: ");

				getInput = new Scanner(System.in);
				
				try {
					option = getInput.nextInt();
				}catch(Exception e) {
					System.out.println("Please enter an appropriate selection");
				}
				
				switch(option) {
				
				case 1:
					//validate user
					System.out.print("Please enter your user name: ");
					userName = getInput.next();
					
					//search the customer database
					if (customers.containsKey(userName)) {
						customer = customers.get(userName);
						userType = 'c';
					}
						
					//search the employee database
					else if(employees.containsKey(userName)) {
						employee = employees.get(userName);
						userType = 'e';
					}
						
					//search the admin database
					else if (admins.containsKey(userName)) {
						admin = admins.get(userName);
						userType = 'a';
					}
					
					else {
						System.out.println();
						break;
					}
					
					System.out.print("Please enter your password: ");
					password = getInput.next();
					
					switch(userType) {
					case 'c':
						loggedIn = UserFunctions.validateCustomer(password, customer);
						if(loggedIn) {
							loggingIn = false;
							break;
						}
						
						else { 
							System.out.println();
							userType = 'n';
							customer = null;
						}
						
					case 'e':
						loggedIn = UserFunctions.validateEmployee(password, employee);
						if(loggedIn) {
							loggingIn = false;
							break;
						}
						
						else {
							System.out.println();
							userType = 'n';
							employee = null;
						}
						
					case 'a':
						loggedIn = UserFunctions.validateAdmin(password, admin);
						if(loggedIn) {
							loggingIn = false;
							break;
						}
						
						else {
							System.out.println();
							userType = 'n';
							admin = null;
						}
					}
						

				case 2:
					customer = UserFunctions.register(getInput);
					loggedIn = true;
					loggingIn = false;
					break; 
					
				default:
					break;
				}//end switch			
			}//end logging in while loop
			
			while(loggedIn) {
			//access the banking system	
			}//end banking system loop
			
		}//end user connected while loop
	}//end main
}//end Driver class
