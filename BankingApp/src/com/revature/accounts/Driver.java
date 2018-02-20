package com.revature.accounts;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.system.Core;
import com.revature.system.FilterAccounts;
import com.revature.system.Menu;
import com.revature.utility.FileIO;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String option = "";
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		ArrayList<CustomerAccount> allCustomerAccounts = new ArrayList<CustomerAccount>();
		ArrayList<EmployeeAccount> allEmployeeAccounts = new ArrayList<EmployeeAccount>();
		ArrayList<AdminAccount> allAdminAccounts = new ArrayList<AdminAccount>();

		
		//read in all accounts
		allAccounts = FileIO.readFromAccounts();
		//Filter all account types into their own arraylist
		allCustomerAccounts = FilterAccounts.getAllCustomerAccounts(allAccounts);
		allEmployeeAccounts = FilterAccounts.getAllEmployeeAccounts(allAccounts);
		allAdminAccounts = FilterAccounts.getAllAdminAccounts(allAccounts);
		
		
		while(!exit) {
			//display main menu
			Menu.mainMenu();
			
			option = sc.next();
			sc.nextLine();
			
			switch(option) {
			case "1":
				System.out.print("Enter your username: ");
				
				String username = sc.next();
				sc.nextLine();
				
				System.out.println();
				
				System.out.print("Enter your password: ");
				
				String password = sc.next();
				sc.nextLine();
				System.out.println();
				
				Core.authenticateLogin(username, password, allAccounts, allCustomerAccounts);
				
				break;
			case "2":
				CustomerAccount newCustomer = new CustomerAccount();
				newCustomer = Core.createCustomerAccount();
				
				//if the newCustomer is not null, add him to the list
				if(newCustomer != null) {
					allAccounts.add(newCustomer);
					allCustomerAccounts.add(newCustomer);
				}
				break;
			case "3":
				JointAccount newJoint = new JointAccount();
				newJoint = Core.createJointAccount(allCustomerAccounts);
				
				//if the newCustomer is not null, add him to the list
				if(newJoint != null) {
					allAccounts.add(newJoint);
				}
				else {
					System.out.println("That user does not exist!");
				}
				break;
			case "4":
				exit = true;	
				break;
			default:
				System.out.println("INVALID OPTION");
				Menu.mainMenu();
				break;
			}
			
		}
		
		/*AdminAccount admin1 = new AdminAccount("Seth", "Maize", "Seth", "Maize", 1111);
		AdminAccount admin2 = new AdminAccount("Admin", "Admin", "Admin", "Admin", 1112);
		EmployeeAccount emp1 = new EmployeeAccount("Steve3789", "secret", "Steve", "Burgandy", 1032);
		EmployeeAccount emp2 = new EmployeeAccount("Alissa21", "secreter", "Alissa", "Wollywhirl", 1011);
		
		allAccounts.add(emp1);
		allAccounts.add(emp2);
		allAccounts.add(admin1);
		allAccounts.add(admin2);*/
		
		
		//write out all accounts on termination
		FileIO.writeToAccounts(allAccounts);
	}
}

/*	test case FILE IO
 * 	test case casting  
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayList<Account> readAccounts = new ArrayList<Account>();
	ArrayList<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
	CustomerAccount test1 = new CustomerAccount("Seth", "Maize", 23, false);
	CustomerAccount test2 = new CustomerAccount("Jen", "Traitor", 80, false);
	
	accounts.add(test1);
	accounts.add(test2);
	
	FileIO.writeToAccounts(accounts);
	
	readAccounts = FileIO.readFromAccounts();
	
	if(readAccounts != null) {
		for(Account i: readAccounts) {
			if(i.getPriorityLevel() == 0) {
				//this is to test casting, which will allow me to put all accounts in one file, instead of several
				customerAccounts.add((CustomerAccount)i);
			}
		}
	}
	//read back to write out again
	readAccounts.addAll(customerAccounts);*/

/*CustomerAccount test1 = new CustomerAccount("Seth", "Maize", 23, false);
CustomerAccount test2 = new CustomerAccount("Jen", "Traitor", 80, false);

accounts.add(test1);
accounts.add(test2);

FileIO.writeToAccounts(accounts);*/

