package com.revature;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/*
 * Server Class
 * Handles all logic behind the scene
 * Methods:
 * 		boolean login() - login a user into the current session
 * 		boolean checkAccountExist(String accNum) - check if accountNumber is taken or not
 * 		boolean registerClient() - create a new user
 * 		boolean removeClient() - remove a user and closes all of their accounts
 * 		boolean addAccount(Account a) - add an account
 * 		boolean closeAccount(Account a) - remove an account
 * 		
 * 		DON'T NEED THE FOLLOWING FOUR METHODS RIGHT NOW
 * 		boolean addEmployee(Employee e) - add an employee
 * 		boolean removeEmpployee(Employee e) - remove an employee
 * 		boolean addAdmin(Admin a) - add an admin
 * 		boolean removeAdmin(Admin a) - remove an admin
 * 
 * 		boolean addApp(Application a) - add an App
 * 		boolean removeApp(Application a) - remove an app
*/

final class Server {
	final private String accListFileName = "AccountList.txt";
	final private String clientListFileName = "ClientList.txt";
	final private String employeeListFileName = "EmployeeList.txt";
	final private String adminListFileName = "AdminList.txt";
	final private String appListFileName = "ApplicationList.txt";
	
	//using private static, don't need to re-instanciate
	private static Scanner sc = new Scanner(System.in);
	//Default Constructor
	Server() {}
	
	//read from serialized file
	private Serializable readFile(String filename) {
		Serializable s = null;
		try(FileInputStream iStream = new FileInputStream(filename);
			ObjectInputStream iObject = new ObjectInputStream(iStream)) {
			s = (Serializable) iObject.readObject();
		}
		catch(FileNotFoundException e) {
			//Don't need to print stacktrace
			//let the first write to file create the file
			//e.printStackTrace();
			return s;
		}
		catch(EOFException e) {
			//see FileNotFoundException
			return s;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//write to serialized file
	private void writeFile(Serializable s, String filename) {
		try(FileOutputStream oStream= new FileOutputStream(filename);
			ObjectOutputStream oObject = new ObjectOutputStream(oStream)) {
			oObject.writeObject(s);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Write methods to update the text file
	private void writeToAccount(ArrayList<Account> accountList) {
		writeFile(accountList, accListFileName);
	}	
	private void writeToClient(ArrayList<Client> clientList) {
		writeFile(clientList, clientListFileName);
	}
	private void writeToEmployee(ArrayList<Employee> employeeList) {
		writeFile(employeeList, employeeListFileName);
	}
	private void writeToAdmin(ArrayList<Admin> adminList) {
		writeFile(adminList, adminListFileName);
	}
	private void writeToApp(ArrayList<Application> appList) {
		writeFile(appList, appListFileName);
	}
	
	/* 
	 * Handles user login
	 * Return current BankPersonnel subclass
	 * return null otherwise
	 */ 
	Admin loginAdmin(String username, String password, ArrayList<Admin> adminList) {
		for(Admin a: adminList) {
			if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
				return a;
			}
		}
		return null;
	}
	
	Employee loginEmployee(String username, String password, ArrayList<Employee> employeeList) {
		for(Employee e: employeeList) {
			if(e.getUsername().equals(username) && e.getPassword().equals(password)) {
				return e;
			}
		}
		return null;
	}
	
	Client loginClient(String username, String password, ArrayList<Client> clientList) {
		for(Client c: clientList) {
			if(c.getUsername().equals(username) && c.getPassword().equals(password)) {
				return c;
			}
		}
		return null;
	}
	
	/*
	 * Check if a account exists in the list
	 * return true if account exists
	 * 		  false otherwise
	*/
	boolean checkAccountExist(String accNum, ArrayList<Account> accountList) {
		for(Account curAcc: accountList) {
			if(curAcc.getAccountNumber().equals(accNum))
				return true;
		}
		return false;
	}
	
	/* 
	 * Create a new Client with an account
	*/
	boolean registerClient(Client c, ArrayList<Client> clientList) {
		for(Client curC: clientList) {
			if(curC.getName().equals(c.getName())) {
				return false;//user already exist, use openAccount
			}
		}
		clientList.add(c);//validated, add to List
		writeToClient(clientList); //write to file
		return true;
	}
	
	/*
	 * Remove a Client from the list and update file
	*/
	boolean removeClient(Client c, ArrayList<Client> clientList, ArrayList<Account> accountList) {
		for(Account aInC: c.getAccountList()) {
			for(Account accToDel: accountList) {
				if(aInC.equals(accToDel))
					accountList.remove(accToDel);
			}
		}
		for(Client curC: clientList) {
			if(curC.equals(c)) {
				clientList.remove(curC);
				writeToClient(clientList);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Add a new Account to the account list then update file
	*/
/*	private void addAccount(Account a,ArrayList<Account> accountList) {
		accountList.add(a);
		writeToAccount(accountList);
	}*/
	
	/*
	 * Close Accounts
	 * remove from accountList
	 * write to file in Server.java
	 * return true when account closed successful
	 * 		  false otherwise
	*/
	boolean closeAccount(Account a, ArrayList<Account> accountList) {
		for(Account curA: accountList) {
			if(curA.equals(a)) {
				accountList.remove(curA);
				writeToAccount(accountList);
				return true;//account closed successful
			}
		}
		return false;//account not found
	}

	/*
	 * Add an Application to open normal/joint account
	*/
	boolean addApp(Application a, ArrayList<Application> appList) {
		for(Application curA: appList) {
			if(curA.equals(a))
				return false;
		}
		appList.add(a);
		writeToApp(appList);
		return true;
	}
	
	/*
	 * Remove an Application
	*/
	boolean removeApp(Application a, ArrayList<Application> appList) {
		for(Application curA: appList) {
			if(curA.equals(a))
				appList.remove(a);
				writeToApp(appList);
				return true;
		}
		return false;
	}
	
	
	//Hard code initialization
	void hardCodeBank(ArrayList<Account> accountList,
					  ArrayList<Client> clientList, 
					  ArrayList<Employee> employeeList, 
					  ArrayList<Admin> adminList,
					  ArrayList<Application>appList) {
		//Hard code initialization of clientList
		Pair<String, String> r2d2 = new Pair<String,String>("D2","R2");
		Pair<String, String> k2so = new Pair<String,String>("SO","K2");
		Account a1 = new Account("5428-9856-7512-4585",225.25d,r2d2);
		Account a2 = new Account("1238-5584-8888-9999",1000.50d,k2so);
		Account a3 = new Account("7777-1562-1234-4421",0.0d,r2d2,true,k2so);
		ArrayList<Account> al1= new ArrayList<Account>(2);
		ArrayList<Account> al2= new ArrayList<Account>(1);
		ArrayList<Account> al3= new ArrayList<Account>(3);
		al1.add(a1);
		al1.add(a3);
		al2.add(a2);
		al2.add(a3);
		
		al3.add(a1);
		al3.add(a2);
		al3.add(a3);
		writeToAccount(al3);
		Client c1 = new Client(r2d2,
							   "66 Galaxy Drive",
							   "(951)824-0227", "beepboop","boopbeep",al1);
		Client c2 = new Client(k2so,
							   "1 Rouge Lane",
							   "(159)156-8542", "blaster", "iwantone",al2);
		clientList.add(c1);
		clientList.add(c2);
		writeToClient(clientList);
		
		//Hard Code initialization of appList
		Account a4 = new Account("0000-1111-2222-3333",0.0d,r2d2);
		Account a5 = new Account("6666-7777-8888-9999",0.0d,k2so);
		Application app1 = new Application(a4,0,"102");
		Application app2 = new Application(a5,0,"84");
		appList.add(app1);
		appList.add(app2);
		writeToApp(appList);
		
		//Hard code initialization of employeeList
		Employee e1 = new Employee(new Pair<String,String>("Hsieh","Kevin"),
								   "241 Hello World Drive",
								   "(951)824-0227", "khsieh","123456",125);
		Employee e2 = new Employee(new Pair<String,String>("Knighten","Matt"),
				   				   "241 Goobye World Lane",
				   				   "(456)256-5218", "knightenM","rolltide",64);
		employeeList.add(e1);
		employeeList.add(e2);
		writeToEmployee(employeeList);
		//Hard code initialization of adminList
		Admin a = new Admin(new Pair<String,String>("admin","admin"),
				   				   "1 hacker drive",
				   				   "(000)000-0000", "admin","password",0);
		adminList.add(a);
		writeToAdmin(adminList);
	}
	
	void runAdmin(Menu menu, Admin curAdmin, ArrayList<Account> accountList, 
		     ArrayList<Client> clientList, ArrayList<Application> appList) {
		
		boolean loggedIn = true;
		Scanner scA = new Scanner(System.in);
		while(loggedIn) {
			menu.printAdmin();
			System.out.println("Select option");
			String option = scA.nextLine();
			if(option.equals("1")) {//View Customer Info
				curAdmin.listClients(clientList);
			}
			else if(option.equals("2") || option.equals("3")) {//Acc creation app.
				if(option.equals("2"))
					curAdmin.listApp(appList);
				else if(option.equals("3"))
					curAdmin.listJointApp(appList);
				if(appList.isEmpty()) {
					System.out.println("No Applications!");
					continue;
				}
				
				System.out.println("Enter applciation number: ");
				String appNum = sc.nextLine();
				System.out.println("Enter new Status(1:Approved, 0:Pending, -1:Denied): ");
				int setStatus = sc.nextInt();
				curAdmin.setAppStatus(appNum, setStatus, appList);
				if(setStatus == 1) {
					Account toAdd = null;
					Client toAddC = null;
					Application toRemove = null;
					for(Application a:appList) {
						if(a.getApplicationNumber().equals(appNum)) {
							accountList.add(a.getAccount());
							for(Client c: clientList) {
								if(a.getAccount().getOwner().equals(c.getName())) {
									toAddC = c;
									toAdd = a.getAccount();
								}
							}
							toRemove = a;
						}
					}
					if(toAddC != null && toAdd != null)
						toAddC.getAccountList().add(toAdd);
					if(toRemove != null)
						appList.remove(toRemove);
				}
				else if(setStatus == -1) {
					Application toRemove = null;
					for(Application a:appList) {
						if(a.getApplicationNumber().equals(appNum)) {
							toRemove = a;
						}
					}
					if(toRemove != null)
						appList.remove(toRemove);
				}
			}
			else if(option.equals("4")) {//Withdraw
				System.out.println("Enter Account Number: ");
				String accN = sc.nextLine();
				
				System.out.println("Enter Amount to withdraw: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(accN)) {
							curAdmin.withdraw(amount, accN, c);
						}
					}
				}
			}
			else if(option.equals("5")) {//Deposit
				System.out.println("Enter Account Number: ");
				String accN = sc.nextLine();
				
				System.out.println("Enter Amount to Deposit: ");
				double amount = Double.parseDouble(sc.nextLine());
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(accN)) {
							curAdmin.deposit(amount, accN, c);
						}
					}
				}
			}
			else if(option.equals("6")) {//Transfer
				System.out.println("Enter From Account Number: ");
				String fromAcc= sc.nextLine();
				
				System.out.println("Enter Amount to Transfer: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				System.out.println("Enter To Account Number: ");
				String toAcc= sc.nextLine();
				
				Client fromC = null;
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(fromAcc)) {
							fromC = c; 
						}
					}
				}
				Client toC = null;
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(toAcc)) {
							toC = c; 
						}
					}
				}
				if(fromC != null && toC != null)
					curAdmin.transfer(amount, fromAcc, fromC, toAcc, toC);
			}
			else if(option.equals("7")) {
				System.out.println("Enter Account Number to delete: ");
				String delAcc= sc.nextLine();
				curAdmin.closeAccount(delAcc, accountList, clientList);


			}
			else if(option.equals("8")) {
				loggedIn = false;
			}
			else {
				System.out.println("Invalid option, please try again");
			}
		}
		scA.close();
		writeToAccount(accountList);
		writeToClient(clientList);
		writeToApp(appList);
		}
	
	void runEmployee(Menu menu, Employee curEm, ArrayList<Account> accountList, 
				     ArrayList<Client> clientList, ArrayList<Application> appList) {
		
		boolean loggedIn = true;
		Scanner scE = new Scanner(System.in);
		while(loggedIn) {
			menu.printEmployee();
			System.out.println("Select option");
			String option = scE.nextLine();
			if(option.equals("1")) {//View Customer Info
				curEm.listClients(clientList);
			}
			else if(option.equals("2") || option.equals("3")) {//Acc creation app.
				if(option.equals("2"))
					curEm.listApp(appList);
				else if(option.equals("3"))
					curEm.listJointApp(appList);
				if(appList.isEmpty()) {
					System.out.println("No Applications!");
					continue;
				}
				
				System.out.println("Enter applciation number: ");
				String appNum = sc.nextLine();
				System.out.println("Enter new Status(1:Approved, 0:Pending, -1:Denied): ");
				int setStatus = Integer.parseInt(sc.nextLine());
				curEm.setAppStatus(appNum, setStatus, appList);
				if(setStatus == 1) {
					Account toAdd = null;
					Client toAddC = null;
					Application toRemove = null;
					for(Application a:appList) {
						if(a.getApplicationNumber().equals(appNum)) {
							accountList.add(a.getAccount());
							for(Client c: clientList) {
								if(a.getAccount().getOwner().equals(c.getName())) {
									toAddC = c;
									toAdd = a.getAccount();
								}
							}
							toRemove = a;
						}
					}
					if(toAddC != null && toAdd != null)
						toAddC.getAccountList().add(toAdd);
					if(toRemove != null)
						appList.remove(toRemove);
				}
				else if(setStatus == -1) {
					Application toRemove = null;
					for(Application a:appList) {
						if(a.getApplicationNumber().equals(appNum)) {
							toRemove = a;
						}
					}
					if(toRemove != null)
						appList.remove(toRemove);
					
				}
			}
			else if(option.equals("4")) {//Withdraw
				System.out.println("Enter Account Number: ");
				String accN = sc.nextLine();
				
				System.out.println("Enter Amount to withdraw: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(accN)) {
							curEm.withdraw(amount, accN, c);
						}
					}
				}
			}
			else if(option.equals("5")) {//Deposit
				System.out.println("Enter Account Number: ");
				String accN = sc.nextLine();
				
				System.out.println("Enter Amount to Deposit: ");
				double amount = Double.parseDouble(sc.nextLine());
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(accN)) {
							curEm.deposit(amount, accN, c);
						}
					}
				}
			}
			else if(option.equals("6")) {//Transfer
				System.out.println("Enter From Account Number: ");
				String fromAcc= sc.nextLine();
				
				System.out.println("Enter Amount to Transfer: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				System.out.println("Enter To Account Number: ");
				String toAcc= sc.nextLine();
				
				Client fromC = null;
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(fromAcc)) {
							fromC = c; 
						}
					}
				}
				Client toC = null;
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(toAcc)) {
							toC = c; 
						}
					}
				}
				if(fromC != null && toC != null)
					curEm.transfer(amount, fromAcc, fromC, toAcc, toC);
			}
			else if(option.equals("7")) {
				loggedIn = false;
			}
			else {
				System.out.println("Invalid option, please try again");
			}
		}
		scE.close();
		writeToClient(clientList);
		writeToApp(appList);
	}
	
	void runClient(Menu menu, Client curClient, ArrayList<Account> accountList, 
		     	   ArrayList<Client> clientList, ArrayList<Application> appList) {

		boolean loggedIn = true;
		Scanner scC = new Scanner(System.in);
		
		while(loggedIn) {
			menu.printClient();
			String option = scC.nextLine();
			if(option.equals("1")) {
				System.out.println(curClient.getAccountList().toString());
			}
			else if(option.equals("2")) {//Withdraw
				System.out.println("Enter Account Number: ");
				String accN = sc.nextLine();
				
				System.out.println("Enter Amount to withdraw: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				for(Account a: curClient.getAccountList()) {
					if(a.getAccountNumber().equals(accN)) {
						curClient.withdraw(amount, accN, curClient);
					}
				}
			}
			else if(option.equals("3")) {//Deposit
				System.out.println("Enter Account Number: ");
				String accN = sc.nextLine();
				
				System.out.println("Enter Amount to Deposit: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				for(Account a: curClient.getAccountList()) {
					if(a.getAccountNumber().equals(accN)) {
						curClient.deposit(amount, accN, curClient);
					}
				}
			}
			else if(option.equals("4")) {//Transfer
				System.out.println("Enter From Account Number: ");
				String fromAcc= sc.nextLine();
				
				System.out.println("Enter Amount to Transfer: ");
				double amount = Double.parseDouble(sc.nextLine());
				
				System.out.println("Enter To Account Number: ");
				String toAcc= sc.nextLine();
				
				Client fromC = null;
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(fromAcc)) {
							fromC = c; 
						}
					}
				}
				Client toC = null;
				for(Client c:clientList) {
					for(Account a: c.getAccountList()) {
						if(a.getAccountNumber().equals(toAcc)) {
							toC = c; 
						}
					}
				}
				if(fromC != null && toC != null)
					curClient.transfer(amount, fromAcc, fromC, toAcc, toC);
			}
			else if(option.equals("5")) {
				//TODO:! Apply for new Acc
			}
			else if(option.equals("6")) {
				//TODO:! Apply for Joint Acc!
			}
			else if(option.equals("7")) {
				loggedIn = false;
			}
			else {
				System.out.println("Invalid option, please try again");
			}
		}
		scC.close();
	}
	
	/*
	 * Main method
	 * Handles the User interaction
	 * 		uses the Server class for account/user interaction logic
	 * 		uses the Menu class to print out UI
	 * 		uses Scanner for user input.
 	*/	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("Initializing...");
		ArrayList<Account> accountList = new ArrayList<Account>(20);
		ArrayList<Client> clientList = new ArrayList<Client>(20);
		ArrayList<Employee> employeeList = new ArrayList<Employee>(20);
		ArrayList<Admin> adminList = new ArrayList<Admin>(20);
		ArrayList<Application> appList = new ArrayList<Application>(20);
		
		Server s = new Server();
		//s.hardCodeBank(accountList, clientList, employeeList, adminList, appList);
		
		accountList = (ArrayList<Account>) s.readFile(s.accListFileName);
		clientList = (ArrayList<Client>) s.readFile(s.clientListFileName);
		employeeList = (ArrayList<Employee>) s.readFile(s.employeeListFileName);
		adminList = (ArrayList<Admin>) s.readFile(s.adminListFileName);
		appList = (ArrayList<Application>) s.readFile(s.appListFileName);
		
		System.out.println("Initialization Complete! ");
		
		Menu menu = new Menu();
		boolean done = false;
		
		//Main Loop
		do{
			
			menu.printMain();
			String input = sc.nextLine();
			if(input.equals("1")) {//LOGIN
				boolean loginSuccess = false;
				int userLevel = -1;
				Admin curAdmin = new Admin();
				Employee curEm = new Employee();
				Client curClient = new Client();
				
				while(!loginSuccess) {//ATTEMPT LOGIN
					System.out.println("User Name: ");
					String username = sc.nextLine();
					System.out.println("Password: ");
					String password = sc.nextLine();
					
					//checking Admin
					if((curAdmin = s.loginAdmin(username, password, adminList))!=null) {
						loginSuccess = true;
						userLevel = 0;
					}
					//checking Employee
					else if((curEm = s.loginEmployee(username, password, employeeList))!=null) {
						loginSuccess = true;
						userLevel = 1;
					}
					//checking Clients
					else if((curClient = s.loginClient(username, password, clientList))!=null) {
						loginSuccess = true;
						userLevel = 2;
					}
					else {
						System.out.println("\nLogin failed, please try again");
					}
				}//login successful!
				
				if(userLevel == 0) {//curAdmin
					s.runAdmin(menu, curAdmin, accountList, clientList, appList);
				}
				else if(userLevel == 1) {
					s.runEmployee(menu, curEm, accountList, clientList, appList);
				}
				else if(userLevel == 2) {
					s.runClient(menu, curClient, accountList, clientList, appList);
				}
				done = true;
			}
			else if(input.equals("2")) {//register
				System.out.println("Last Name: ");
				String lName = sc.nextLine();
				System.out.println("Fist Name: ");
				String fName = sc.nextLine();
				Pair<String,String> customer = new Pair<String,String>(lName,fName);
				System.out.println("Address: ");
				String newAddr = sc.nextLine();
				System.out.println("Phone: ");
				String newPhone = sc.nextLine();
				System.out.println("Username: ");
				String newUsrname = sc.nextLine();
				System.out.println("Password: ");
				String newPw = sc.nextLine();
				
				Client newC = new Client(customer,newAddr,newPhone,newUsrname,newPw);
				s.registerClient(newC, clientList);
				System.out.println(clientList.toString());
				
			}
			else if(input.equals("3")) {
				done = true;
				System.out.println("Exiting!");
			}
			else {
				System.out.println("Invalid input! Please try again");
			}
		}while(!done);//Main loop
	}

}
