package com.revature.banking.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.revature.banking.jdbc.controller.Bank;
import com.revature.banking.jdbc.model.Account;
import com.revature.banking.jdbc.model.Application;
import com.revature.banking.jdbc.model.Customer;
import com.revature.banking.jdbc.model.Employee;

public class EmployeeView extends UserView {
	private static final long serialVersionUID = -2098377206089222431L;
	public EmployeeView(Bank bank) {
		super(bank);
	}
	public void home(Employee employee, Scanner scan) {
		//***ACCESS VIEW ONLY**
		//	account information
		//	Account balances
		//	Personal information
		System.out.println("\nHello " + employee.toString() + "\nSelect one of the following options\n"
				+"1:\tView open applications\n2:\tView customer information\n"
				+"3:\tChange login information\n4:\tLogout / Go back to the main menu");
		int choice =getNumber(scan);
//		try {
//			choice = Integer.parseInt(scan.nextLine().trim());
//		}catch(NumberFormatException e) {
//			System.out.println("");
//			this.home(employee, scan);
//			return;
//		}	
		if(choice ==1) {
			this.viewApplicationsEmployee(employee, scan);
		}else if (choice == 2){
			this.viewCustomerInfo(employee, scan);;			
		}else if (choice ==3) {
			this.userProfile(employee, scan);
		}else if (choice == 4) {
			this.welcome(scan);
		}else {
			System.out.println("Incorrect choice detected!!\nTry again...");
			this.home(employee, scan);
		}
		this.home(employee, scan);
	}
	public void viewCustomerInfo(Employee employee, Scanner scan) {
		List<Customer> customers = bank.getAllCustomers();
		if (customers.size()==0) {
			System.out.println("There are no customers at this time");
		}
		for (Customer customer: customers) {
			System.out.println(customer.toString());
		}
	}
	public void viewApplicationsEmployee(Employee employee, Scanner scan) {
		// Print all applications
		if(this.bank.getNumApplications()==0) {
			System.out.println("There are no applications at this time");
			return;
		}
		List<Application> applications = bank.getAllApplications();
		for(int i=0; i< applications.size(); i++) {
			System.out.println((i+1) + "\t" + applications.get(i).getApplicationId()+ "\t"+ applications.get(i).getStatus());
		}
		//Select application to approve/deny
		boolean success = false;
		int choice =0;
		while (!success) {
			System.out.println("Select Application\n");
			choice =this.getNumber(scan);
			if(choice ==-1) 
				return;
			else if((choice <= 0) || choice > applications.size()+1 ) 
				System.out.println("Incorrect choice detected!!\nTry again...");
			else
				success=true;
		}
		Application select = applications.get(choice-1);
		System.out.println("What would you like to do with this application\n"
				+"1:\tApprove\n"
				+"2:\tDeny");
		success = false;
		choice =0;
		while (!success) {
			System.out.println("Select Application\n");
			choice =this.getNumber(scan);
			if(choice ==-1) 
				return;
			if((choice <= 0) || choice >2 ) {
				System.out.println("Incorrect choice detected!!\nTry again...");
			}else
				success=true;
		}
		if(choice==1) {
			bank.approveApplication(select, employee);
		}else {
			bank.denyApplication(select, employee);
		}
	}
	public void viewAccountInfo(Employee employee, Scanner scan) {
		List<Account> accounts = bank.getAllAccounts();
		if (accounts.size()==0) {
			System.out.println("There are no customers at this time");
		}
		for (Account account: accounts) {
			System.out.println(account.toString());
		}
	}
}
