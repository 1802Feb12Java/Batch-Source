package com.revature.bankingapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PasswordChecker {
	static Scanner scanner = new Scanner(System.in);
	public boolean checkPassword(String userName, String accountType) {
		try {
			File file = null;
			if (accountType.equals("customer")) {
				file = new File("./customerProfiles/" + userName);
			}
			else if (accountType.equals("employee")) {
				file = new File("./employeeProfiles/" + userName);
			}
			else if (accountType.equals("admin")) {
				file = new File("./adminProfiles/" + userName);
			}
			else {
				System.out.println("Error in password checker");
				MenuSystem.runMenu();
			}
			if (file.exists()) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				String line = bufferedReader.readLine();
				String[] userString = line.split(":");
				System.out.println("Enter your password.");
				
				String passwordInput = scanner.nextLine();
				bufferedReader.close();
				if (passwordInput.equals(userString[1])) {
					return true;
					
				}
				else {
					System.out.println("Wrong Password!");
					return false;
				}

			} 
			else {
				System.out.println("Error in password checker.");
				MenuSystem.runMenu();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
