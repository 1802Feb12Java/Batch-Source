package com.revature;

import java.awt.Color;
import javax.swing.*;

import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import userPackage.LoginDataNode;
import userPackage.LoginDataOps;
import userPackage.User;

public class Driver {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		File fileUser = new File("src\\userPackage\\users.txt");			
		Scanner s = null;
		
		try {
			
			s = new Scanner(fileUser);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		LoginDataOps.readFile(s);	// prompt command to load customers in 'users.txt' file, as nodes in linked list 
		makeLogo();
		
		System.out.println("Welcome to the online banking simulation.");
		int result = 0;
		
		while(true) {
			
			System.out.println("Are you a Customer or Employee? If your the admin, type Admin");		// identify yourself		
			String occ = sc.next();
			
			if(occ.equals("Employee") || occ.equals("employee")) {
				
				while(true) {
			
					System.out.println();
					System.out.println("What would you like to review?\n"				// Employee menu options
							+ "1.\tAccount Information\n"
							+ "2.\tAccount Balances\n"
							+ "3.\tPersonal Information\n"
							+ "4.\tAnswer Account Requests\n"
							+ "5.\tQuit");
					
					int res = sc.nextInt();
					
					switch(res) {
					
					case 1:								// Employee can review a customers account details by looking up the node linked to the name
						
						int j = 0, v = 0;
						
						System.out.println("Provide the name under which the account is made");
						String name = sc.next();
						
						LoginDataNode n = LoginDataOps.check(name);
	
						if(n == null) {
							
							System.out.println("There is no account linked to that name.");
							continue;
						}
						
						System.out.println("User "+n.fullName+"'s Account Information:");
						System.out.println("Username: "+n.username);
						System.out.println("Password: "+n.password);
						System.out.println("Account Holders: ");
						
						while(j < n.users.length) {
					    	
				    		System.out.println((n.users)[j]);
					    	j++;
					    }
						
						continue;
						
					case 2:								// Employee can review a customer's balance by looking up the node linked to the name
						
						System.out.println("Provide the name under which the account is made");
						String name2 = sc.next();
						LoginDataNode k = LoginDataOps.check(name2);
						
						if(k == null) {
							
							System.out.println("There is no account linked to that name.");
							continue;
						}
						
						System.out.println("User "+k.fullName+"'s Account Balance: "+k.Balance+"\n");
						continue;
						
					case 3:								// Employee can review a customer's personal info by looking up the node linked to the name
						
						System.out.println("Provide the name under which the account is made");
						String name3 = sc.next();
						LoginDataNode  i= LoginDataOps.check(name3);
	
						if(i == null) {
							
							System.out.println("There is no account linked to that name.\n");
							continue;
						}
						
						System.out.println("User "+i.fullName+"'s Personal Information:");
						System.out.println("Full Name: "+i.fullName);
						System.out.println("Birth date: "+i.birth);
						System.out.println("Age: "+i.age);
						System.out.println("Phone: "+i.phone+"\n");
						continue;
						
					case 4:								// Employee can accept or deny new user applications
							
						File f = new File("src\\userPackage\\requests.txt");
						Scanner sc1 = null;
						Scanner scanInput = new Scanner(System.in);
						
						try {
							
							sc1 = new Scanner(f);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(!sc1.hasNextLine()) {	// the request file is empty
							
							System.out.println("There are no requests to be processed.\n");
							continue;
						}
						
						while(sc1.hasNextLine()) {
		
							String use = sc1.nextLine();
							int z= 1;
							
							while(use.length() == 0) {		// skip blank lines
						
								System.out.println(use);
								z++;
								
								if(z == 2) {
									
									break;
								}
								
								System.out.println(z);
								use = sc1.nextLine();
							}
							
							if(use.length() == 0 || z == 2) {	// all requests have been processed
								
								System.out.println("That's all the requests so far.\n");
								continue;
							}
							
							String birth = sc1.nextLine();
							String ageS = sc1.nextLine();
							int age = Integer.parseInt(ageS);
							String phone = sc1.nextLine();
							
							if(use == null || birth == null || age == 0 || phone == null) {		// if user did not enter their info, the application is denied
								
								System.out.println("This Account Application is denied. Required details are not filled in.\n");
								continue;
								
							} else {
							
								System.out.println("Applicant: "+use+"\n"
										+ "Birth Date: "+birth+"\n"
										+ "Age: "+age+"\n"
										+ "Phone Number: "+phone+"\n");
								
								String decision = null;
								System.out.println("Would you want to approve this application?\n");		// Employee accepts or denies, after verifying details
								decision = sc.next();
								
								if(decision.equals("yes")) {		// if employee accepts
									
									String user = null, pwd = null;
									System.out.println("What name is the account under? Separate multiple names with ';'");
									String userNew = scanInput.nextLine();		
									String[] users = userNew.split(";");
									
									System.out.println("How much money would like to deposit");
									double depo = scanInput.nextDouble();
										
									while(depo < 0 && depo == 0) {			// error check for negative or 0 dollar amount transfers
											
										System.out.println("That is an invalid amount to transfer. Try Again.");
										System.out.println("How much money would they like to initially deposit?\n");
										depo = scanInput.nextDouble();				
									}
									
									System.out.println("Enter username");
									user = scanInput.next();
								
									System.out.println("Enter password");
									pwd = scanInput.next();
									
									while(LoginDataOps.traverse(user, pwd) != null) {		// error check for whether that login has been taken
										
										System.out.println("That username and password combination is taken. Try Again\n");
										System.out.println("Enter username");
										user = scanInput.next();
									
										System.out.println("Enter password");
										pwd = scanInput.next();
									}
									
									LoginDataNode temp = LoginDataOps.createNode(user, pwd, depo, users);		// node made for account with data collected
									
									temp.fullName = use;
									temp.birth = birth;
									temp.age = age;
									temp.phone = phone;
									
									LoginDataOps.printToFile();								// I wanted this to overwrite current users.txt
									System.out.println("Account was successfully made.");
									
								}else {
									
									System.out.println("Ok. This Application is denied.");
								}
							}
						}
						
						continue;
						
					case 5:			// quit out of the employee perspective
						
						System.out.println("Ok, thank you for working with us.");
						result++;
						break;
					}
					
					if(result == 1) {
						
						break;
					}
				}
			} else if(occ.equals("Customer") || occ.equals("customer")) {
				
				System.out.println("Do you have an account with us?");			// if yes, then prompted to login
				String res = sc.next(), username = null, password = null;
				
				if(res.equals("yes")) {
					
					System.out.println("Username: ");
					username = sc.next();
					
					System.out.println("Password: ");
					password = sc.next();
				}
				
				User.runThis(username, password);
				
			}else if(occ.equals("Admin") || occ.equals("admin")){
					
				while(true) {
					
					System.out.println("Whose account details would you like to view or change");		// only option for admins
					String name = sc.next();
					
					if(name.equals("quit")) {		// quit out of loop
						
						System.out.println("");
						break;
					}
					
					LoginDataNode tmp = LoginDataOps.check(name);
					
					if(tmp == null) {		// if node not found
						
						System.out.println("There is no account under that name.\n");
						continue;
					}
					
					System.out.println("User "+tmp.fullName+"'s Personal Information:");		// list out customers info
					System.out.println("Full Name: "+tmp.fullName);
					System.out.println("Birth date: "+tmp.birth);
					System.out.println("Age: "+tmp.age);
					System.out.println("Phone: "+tmp.phone);
					
					System.out.println();
					
					System.out.println("User "+tmp.fullName+"'s Account Information:");
					System.out.println("Username: "+tmp.username);
					System.out.println("Password: "+tmp.password);
					System.out.println("Names: "+tmp.users+"\n");
					
					System.out.println("Would you like to edit anything?");			// admin can edit any field of info
					String y = sc.next();
					
					if(y.equals("yes")) {		// if yes, then prompted
						
						System.out.println("What field would you like to edit");
						String field = sc.next();
						
						switch(field) {
						
						case "name":
							
							System.out.println("What do you want to change "+tmp.fullName+" to?");
							String full = sc.next();
							
							tmp.fullName = full;
							System.out.println(tmp.fullName+"'s name is now "+full);
							continue;
							
						case "birthdate":
							
							System.out.println("What do you want to change "+tmp.birth+" to?");
							String birth = sc.next();
							
							tmp.birth = birth;
							System.out.println(tmp.fullName+"'s birth date is now "+birth);
							continue;
							
						case "age":
							
							System.out.println("What do you want to change "+tmp.age+" to?");
							int age = sc.nextInt();
							
							tmp.age = age;
							System.out.println(tmp.fullName+"'s age is now "+age);
							
							continue;
							
						case "phone":
							
							System.out.println("What do you want to change "+tmp.phone+" to?");
							String phone = sc.next();
							
							tmp.phone = phone;
							System.out.println(tmp.fullName+"'s phone number is now "+phone);
							continue;
							
						case "username":
							
							System.out.println("What do you want to change "+tmp.username+" to?");
							String user = sc.next();
							
							tmp.username = user;
							System.out.println(tmp.fullName+"'s username is now "+user);
							continue;
							
						case "password":
							
							System.out.println("What do you want to change "+tmp.password+" to?");
							String pwd = sc.next();
							
							tmp.password = pwd;
							System.out.println(tmp.fullName+"'s password is now "+pwd);
							continue;
						}
						
//						LoginDataOps.printToFile();
					} else if(name == "no") {
						
						System.out.println("Ok.\n");
						continue;
					}
				}
			}
		}
	}

	private static void makeLogo() {
		// TODO Auto-generated method stub
		
		System.out.println("           __              __      __        __        __         ______  \r\n" + 
				"          /  |            /  |    /  |      /  |      /  |       /      \\ \r\n" + 
				"  _______ $$ |  ______   _$$ |_   $$ |____  $$ |      $$ |      /$$$$$$  |\r\n" + 
				" /       |$$ | /      \\ / $$   |  $$      \\ $$ |      $$ |      $$ |  $$/ \r\n" + 
				"/$$$$$$$/ $$ |/$$$$$$  |$$$$$$/   $$$$$$$  |$$ |      $$ |      $$ |      \r\n" + 
				"$$      \\ $$ |$$ |  $$ |  $$ | __ $$ |  $$ |$$ |      $$ |      $$ |   __ \r\n" + 
				" $$$$$$  |$$ |$$ \\__$$ |  $$ |/  |$$ |  $$ |$$ |_____ $$ |_____ $$ \\__/  |\r\n" + 
				"/     $$/ $$ |$$    $$/   $$  $$/ $$ |  $$ |$$       |$$       |$$    $$/ \r\n" + 
				"$$$$$$$/  $$/  $$$$$$/     $$$$/  $$/   $$/ $$$$$$$$/ $$$$$$$$/  $$$$$$/  ");
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
}
