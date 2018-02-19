package com.revature.bankingapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminAccount {
	static Scanner scanner = new Scanner(System.in);
	private PasswordChecker passwordChecker = new PasswordChecker();
	
	public void registerEmployee() {
		System.out.println("To register a new employee, enter your admin username");
		String userName = scanner.nextLine();
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "admin")) {
				System.out.println("Enter the username of the employee.");
				String employeeUsername = scanner.nextLine();
				System.out.println("Enter a password for the employee");
				String employeePassword = scanner.nextLine();
				file = new File("./employeeProfiles/" + employeeUsername);
				try {
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
					bufferedWriter.write(employeeUsername + ":" + employeePassword);
					bufferedWriter.close();
					System.out.println("Employee Created Successfully");
					MenuSystem.runMenu();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				MenuSystem.runMenu();
			}
		}
		else {
			System.out.println("Sorry. No such admin.");
		}
	}

	public void viewEmployeeInformation() {
		System.out.println("To view employee enter your admin username");
		String userName = scanner.nextLine();
		File file = new File("./adminProfiles/" + userName);
		if (file.exists()) {
			if(passwordChecker.checkPassword(userName, "admin")) {
				
			}
			else {
				MenuSystem.runMenu();
			}
		}
		
	}

	public void editCustomerAccount() {
		// TODO Auto-generated method stub
		
	}

}
