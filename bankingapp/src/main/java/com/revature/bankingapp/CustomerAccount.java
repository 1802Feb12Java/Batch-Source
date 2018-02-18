package com.revature.bankingapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CustomerAccount {
	private int customerId;
	private String lastName;
	private String firstName;
	private String userName;
	private String password;
	
	public void enterCustomerInformation() {
		Scanner scanner =  new Scanner(System.in);
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
		this.password = scanner.next();
		scanner.nextLine();
		
		//validate info
		File file = new File("bankingapp/customerProfiles/" + this.userName);
		while (file.exists()) {
			System.out.println("Sorry. That username is already taken. Please enter a new username");
			scanner.next();
			scanner.nextLine();
			file = new File("./" + this.userName);
		}
		scanner.close();
		registerCustomer(this.firstName, this.lastName, this.userName, this.password);
	}
	void registerCustomer(String firstName, String lastName,
				String userName, String password) {
		File file = new File("./" + userName);
		try {
			if (file.createNewFile()) {
				FileWriter writer = new FileWriter(file);
				writer.write(customerId);
				writer.write(userName);
				writer.write(password);
				writer.write(lastName);
				writer.write(password);
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void viewCustomerInformation() {
		// TODO Auto-generated method stub
		
	}
	public void openCustomerAccount() {
		// TODO Auto-generated method stub
		
	}
	public void withdrawMoney() {
		// TODO Auto-generated method stub
		
	}
	public void depositMoney() {
		// TODO Auto-generated method stub
		
	}
	public void transferMoney() {
		// TODO Auto-generated method stub
		
	}
	
}
