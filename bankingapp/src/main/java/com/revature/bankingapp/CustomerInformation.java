package com.revature.bankingapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CustomerInformation {
	private String lastName;
	private String firstName;
	private String userName;
	private String password;
	private static Scanner scanner =  new Scanner(System.in);
	private PasswordChecker passwordChecker = new PasswordChecker();
	
	public void enterCustomerInformation() {
		
		System.out.println("Enter First Name");
		this.firstName = scanner.next();
		scanner.nextLine();
		System.out.println("Enter Last Name");
		this.lastName = scanner.next();
		scanner.nextLine();
		System.out.println("Enter a User Name");
		this.userName = scanner.next();
		scanner.nextLine();
		System.out.println("Enter a password");
		this.password = scanner.nextLine();
		
		//validate info
		File file = new File("./customerProfiles/" + this.userName);
		while (file.exists()) {
			System.out.println("Sorry. That username is already taken. Please enter a new username");
			this.userName = scanner.next();
			scanner.nextLine();
			file = new File(this.userName);
		}
		registerCustomer(this.firstName, this.lastName, this.userName, this.password);
	}
	void registerCustomer(String firstName, String lastName,
				String userName, String password) {
		File file = new File("./customerProfiles/" + userName);
		try {
			if (file.createNewFile()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(userName + ":");
				writer.write(password + ":");
				writer.write(firstName + ":");
				writer.write(lastName + ":");
				writer.close();
			}
			file = new File("./customerAccounts/" + userName);
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Customer Registration Successful!");
		MenuSystem.runMenu();
	}
	
	public void viewCustomerInformation() {
		System.out.println("Type the user name of the customer you wish to view");
		String userName = scanner.next();
		scanner.nextLine();
		File file = new File("./customerProfiles/" + userName);
		if (file.exists()) {
			if (passwordChecker.checkPassword(userName, "customer")) {
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(file));
					String customerInformation = reader.readLine();
					String[] customerArray = customerInformation.split(":");
					System.out.println("Username: " + customerArray[0] + 
										"\nPassword: " + customerArray[1] +
										"\nFirst Name: " + customerArray[2] +
										"\nLast Name " + customerArray[3]);
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			
		else {
			System.out.println("No such customer!");
		}
		MenuSystem.runMenu();
	}
	
	
}
