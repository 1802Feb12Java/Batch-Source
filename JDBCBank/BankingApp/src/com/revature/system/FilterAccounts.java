package com.revature.system;

import java.util.ArrayList;

import com.revature.accounts.Account;
import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;

public class FilterAccounts {
	
	//filter customer accounts and return all the customerAccounts in the list
	public static ArrayList<CustomerAccount> getAllCustomerAccounts(ArrayList<Account> accounts) {
		
		ArrayList<CustomerAccount> customers= new ArrayList<CustomerAccount>();
		
		//go through all accounts
		for(Account i: accounts) {
			//if priority is 0, it is a customer account
			if(i.getPriorityLevel() == 0) {
				//cast account to customer account and add it so customers ArrayList
				customers.add((CustomerAccount)i);
			}
		}
		
		return customers;
	}

	//filter employee accounts and return all the EmployeeAccounts in the list
	public static ArrayList<EmployeeAccount> getAllEmployeeAccounts(ArrayList<Account> accounts) {
		
		ArrayList<EmployeeAccount> employees = new ArrayList<EmployeeAccount>();
		
		//go through all accounts
		for(Account i: accounts) {
			//if priority is 1, it is an employee account
			if(i.getPriorityLevel() == 1) {
				//cast account to employee account and add it so employee ArrayList
				employees.add((EmployeeAccount)i);
			}
		}
		
		return employees;
	}
	
	//filter admin accounts and return all the AdminAccounts in the list
		public static ArrayList<AdminAccount> getAllAdminAccounts(ArrayList<Account> accounts) {
			
			ArrayList<AdminAccount> admins = new ArrayList<AdminAccount>();
			
			//go through all accounts
			for(Account i: accounts) {
				//if priority is 2, it is an admin account
				if(i.getPriorityLevel() == 2) {
					//cast account to employee account and add it so employee ArrayList
					admins.add((AdminAccount)i);
				}
			}
			
			return admins;
		}
	
}
