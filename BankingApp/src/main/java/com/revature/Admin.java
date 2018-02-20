package com.revature;

import java.util.ArrayList;

import javafx.util.Pair;

/*
 * Admin Class
 * 	Have View access to Client's information including:
 * 		Personal information
 * 		Account information
 * 	Have permission to modify balance of a Client's account:
 * 		Deposit
 * 		Withdraw
 * 		Transfer
 * Have permission to close accounts:
 * 		remove from accountList
 * Have permission to Access/Approve/Deny the Application list
*/

public final class Admin extends Employee {

	private static final long serialVersionUID = -1390489306845611961L;
	
	//Default Constructor, same as Employee
	public Admin() {
		super();
	}
	
	//Constructor, same as Employee
	public Admin(Pair<String,String> name,String addr, String phone, String username, String password, int eID) {
		super(name,addr,phone,username,password,eID);
	}
	
	/*
	 * List Employees
	*/
	void listEmplyoees(ArrayList<Employee> employeeList) {
		for(Employee e: employeeList) {
			System.out.println(e.toString());
		}
	}
	
	/*
	 * List Admins
	*/
	void viewAdmins(ArrayList<Admin> adminList) {
		for(Employee a: adminList) {
			System.out.println(a.toString());
		}
	}
	
	void closeAccount(String accNumber, ArrayList<Account> accList, ArrayList<Client> clientList) {
		Account toRemove = null;
		for(Account a: accList) {
			if(a.getAccountNumber().equals(accNumber)) {
				toRemove = a;
			}
		}
		if(toRemove != null) {
			accList.remove(toRemove);
		}
		
		Client toRemovFrom = null;
		Account toDelete = null;
		for(Client c: clientList) {
			for(Account aC: c.getAccountList()) {
				if(aC.getAccountNumber().equals(accNumber)) {
					toDelete = aC;
					toRemovFrom=c;
				}
			}
		}
		if(toRemovFrom != null && toDelete !=null) {
			toRemovFrom.getAccountList().remove(toDelete);
		}
	}
	
}
