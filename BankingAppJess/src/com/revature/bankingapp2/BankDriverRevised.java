package com.revature.bankingapp2;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bankingapp2.beans.CustomerBean;
import com.revature.bankingapp2.beans.UserBean;

//import java.util.ArrayList;
//import java.util.Scanner;

public class BankDriverRevised {
	/*
	 * The purpose of this class is to run the banking app.
	 */
	public static void main(String[] args) {
		/*
		 * All the the commented code below is meant for a one time run to basically hard code
		 * in a single item to each file. This code should not be run every time the app is run.
		 */
		 
//		//Initialize the ArrayLists
//		ArrayList<String> emptyUserLog = new ArrayList<String>();
//		ArrayList<CustomerBean> emptyCustomerLog = new ArrayList<CustomerBean>();
//		ArrayList<BankEmployee> emptyEmployeeLog = new ArrayList<BankEmployee>();
//		ArrayList<BankAdmin> emptyAdminLog = new ArrayList<BankAdmin>();
//		ArrayList<Account> emptyAccountLog = new ArrayList<Account>();
//
//		/*
//		 *	In order to be able to read from and write to the Logs, there has to be
//		 *initial objects stored within those files. Since, as of right now all of my methods
//		 *start by reading in the file, we have to first write to the file.
//		 */
//		 
//		CustomerBean firstC = new CustomerBean("inactiveCustomer", "password", "John", "Doe");
//		BankEmployee firstE = new BankEmployee("Employee1", "password", "Jane", "Doe");
//		BankAdmin firstA = new BankAdmin("Admin", "Admin", "Mickey", "Mouse");
//		Integer firstAccountNum = 0;
//		emptyCustomerLog.add(firstC);
//		emptyEmployeeLog.add(firstE);		 
//		emptyAdminLog.add(firstA);
//		emptyAccountLog.add(firstAccountNum);
//		FileKeeping.addToUserLog(emptyUserLog, firstC);
//		FileKeeping.addToUserLog(emptyUserLog, firstE);
//		FileKeeping.addToUserLog(emptyUserLog, firstA);
//		FileKeeping.writeToCustomerLogFile(emptyCustomerLog);
//		FileKeeping.writeToEmployeeLogFile(emptyEmployeeLog);
//		FileKeeping.writeToAdminLogFile(emptyAdminLog);
//		FileKeeping.writeToUsernamePasswordFile(emptyUserLog);
//		FileKeeping.writeToAccountNumberFile(emptyAccountLog);
		 
		 		
		ArrayList<String> userLog = FileIOHandling.readInUsernameLog();
//		ArrayList<CustomerBean> customerLog = FileIOHandling.readInCustomerLog();
//		ArrayList<BankEmployee> employeeLog = FileIOHandling.readInEmployeeLog();
//		ArrayList<Account> accountLog = FileIOHandling.readInAccountLog();

		Scanner read = new Scanner(System.in);
		String usernameInUse = "";
		String password = "";
		String userType = "";
		UserBean user;
		
		Menu.mainMenu();
		int selection = read.nextInt();
		read.nextLine();
		
		switch(selection) {
			case 1:
				Menu.logInScreen1();
				usernameInUse = read.nextLine();
				Menu.logInScreen2();
				password = read.nextLine();
				userType = Validation.checkUsernamePasswordMatch(userLog, usernameInUse, password);
				//customerMenu?
				break;
			case 2:
				Menu.newUserScreen1();
				usernameInUse = read.nextLine();
				Menu.newUserScreen2();
				password = read.nextLine();
				System.out.print("First Name: ");
				String firstName = read.nextLine();
				System.out.println("Last Name: ");
				String lastName = read.nextLine();
				CustomerBean newCustomer = new CustomerBean(usernameInUse, password, firstName, lastName);
				customerLog.add(newCustomer);
				//return to main or send to customer menu
				break;
			case 3:
				//call an exit function that will update all files before exiting
				//or remove this case and put everything in a loop?
				break;
			default:
				//invalid input statement
				break;
		}//end switch
		
		
	}//end main

}//end class