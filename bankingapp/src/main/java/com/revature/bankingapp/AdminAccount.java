package com.revature.bankingapp;

import java.io.File;
import java.util.Scanner;

public class AdminAccount {
	static Scanner scanner = new Scanner(System.in);
	
	public void registerEmployee() {
		System.out.println("To register a new employee, enter your admin username");
		String userName = scanner.nextLine();
		File file = new File("./adminProfiles" + userName);
	}

	public void viewEmployeeInformation() {
		// TODO Auto-generated method stub
		
	}

	public void editCustomerAccount() {
		// TODO Auto-generated method stub
		
	}

}
