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
		 * in a single item to each file that will be used throughout the app.
		 */
//		ArrayList<String> initialUserLog = FileKeeping.readInUsernamePasswordLog();
//		ArrayList<Customer> initialCustomerLog = new ArrayList<Customer>();
//		initialCustomerLog.add(new Customer(initialUserLog, "inactiveCustomer", "password", "John", "Doe"));
//		FileKeeping.writeToCustomerLogFile(initialCustomerLog);
//		FileKeeping.writeToUsernamePasswordFile(initialUserLog);
//		ArrayList<String> userLog = FileKeeping.readInUsernamePasswordLog();
//		ArrayList<BankEmployee> initialEmployeeLog = new ArrayList<BankEmployee>();
//		initialEmployeeLog.add(new BankEmployee(userLog, "Employee1", "password", "Jane", "Doe"));
//		FileKeeping.writeToEmployeeLogFile(initialEmployeeLog);
//		ArrayList<Integer> initialAccountLog = new ArrayList<Integer>();
//		initialAccountLog.add(0);
//		FileKeeping.writeToAccountNumberFile(initialAccountLog);
//		ArrayList<String> userLog = FileKeeping.readInUsernamePasswordLog();
//		ArrayList<BankEmployee> employeeLog = FileKeeping.readInEmployeeLog();
//		employeeLog.add(new BankAdmin(userLog, "Admin", "Admin", "Mickey", "Mouse"));
//		FileKeeping.writeToEmployeeLogFile(employeeLog);
//		FileKeeping.writeToUsernamePasswordFile(userLog);
		
		ArrayList<String> userLog = FileKeeping.readInUsernamePasswordLog();
		ArrayList<Customer> customerLog = FileKeeping.readInCustomerLog();
		ArrayList<BankEmployee> employeeLog = FileKeeping.readInEmployeeLog();
		Menu.chooseOption(userLog, customerLog, employeeLog, Menu.mainMenu());
				
		
	}//end main

}//end class
