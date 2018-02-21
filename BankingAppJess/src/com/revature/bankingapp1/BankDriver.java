package com.revature.bankingapp1;

import java.util.ArrayList;
//import java.util.Scanner;

public class BankDriver {
	/*
	 * The purpose of this class is to run the banking app.
	 */
	public static void main(String[] args) {
		/*
		 * All the the commented code below is meant for a one time run to basically hard code
		 * in a single item to each file. This code should not be run every time the app is run.
		 */
		 
		//Initialize the ArrayLists
//		ArrayList<String> emptyUserLog = new ArrayList<String>();
//		ArrayList<Customer> emptyCustomerLog = new ArrayList<Customer>();
//		ArrayList<BankEmployee> emptyEmployeeLog = new ArrayList<BankEmployee>();
//		ArrayList<Integer> emptyAccountLog = new ArrayList<Integer>();
//
//		/*
//		 *	In order to be able to read from and write to the Logs, there has to be
//		 *initial objects stored within those files. Since, as of right now all of my methods
//		 *start by reading in the file, we have to first write to the file.
//		 */
//		 
//		Customer firstC = new Customer("inactiveCustomer", "password", "John", "Doe");
//		BankEmployee firstE = new BankEmployee("Employee1", "password", "Jane", "Doe");
//		BankAdmin firstA = new BankAdmin("Admin", "Admin", "Mickey", "Mouse");
//		Integer firstAccountNum = 0;
//		emptyCustomerLog.add(firstC);
//		emptyEmployeeLog.add(firstE);		 
//		emptyEmployeeLog.add(firstA);
//		emptyAccountLog.add(firstAccountNum);
//		FileKeeping.addToUserLog(emptyUserLog, firstC);
//		FileKeeping.addToUserLog(emptyUserLog, firstE);
//		FileKeeping.addToUserLog(emptyUserLog, firstA);
//		FileKeeping.writeToCustomerLogFile(emptyCustomerLog);
//		FileKeeping.writeToEmployeeLogFile(emptyEmployeeLog);
//		FileKeeping.writeToUsernamePasswordFile(emptyUserLog);
//		FileKeeping.writeToAccountNumberFile(emptyAccountLog);
		
		
		ArrayList<String> userLog = FileKeeping.readInUsernamePasswordLog();
		ArrayList<Customer> customerLog = FileKeeping.readInCustomerLog();
		ArrayList<BankEmployee> employeeLog = FileKeeping.readInEmployeeLog();
		Menu.mainMenu(userLog, customerLog, employeeLog);
				
		
	}//end main

}//end class
