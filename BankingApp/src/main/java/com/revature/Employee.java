package com.revature;

import java.util.ArrayList;

import javafx.util.Pair;

/*
 * Employee Class
 * 	Have View access to Client's information including:
 * 		Personal information
 * 		Account information
 * 	Have permission to modify balance of a Client's account:
 * 		Deposit
 * 		Withdraw
 * 		Transfer
 *	Have permission to Access/Approve/Deny the Application list
*/
public class Employee extends BankPersonnel{

	private static final long serialVersionUID = -1256879417116003075L;
	//for validation of Employee
	private int employeeID;
	
	//Default Constructor
	public Employee() {
		super();
		this.employeeID = 0;
	}
	
	//Constructor
	public Employee(Pair<String,String> name,String addr, String phone, String username, String password, int eID) {
		super(name,addr,phone,username,password);
		this.employeeID = eID;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	//override toString method for readablity
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer(super.toString());
		buff.append("Employee ID: " + this.getEmployeeID() + "\n");
		return buff.toString();
	}
	
	/*
	 * Withdraw from Client's account 
	 * return true when withdrawn successfully
	 * return false otherwise
	*/
	public boolean withdraw(double amount, String accNum, Client client) {
		//look for client's account
		ArrayList<Account> clientAccounts = client.getAccountList();
		for(Account a: clientAccounts) {
			if(a.getAccountNumber().equals(accNum)) {
				//withdraw amount
				return a.withdraw(amount);
			}
		}
		return false;//account num mismatch
	}
	
	/*
	 * Deposit to Client's account
	 * return true when deposit successful
	 * return false otherwise
	*/
	public boolean deposit(double amount, String accNum, Client client) {
		//look for client's account
		ArrayList<Account> clientAccounts = client.getAccountList();
		for(Account a: clientAccounts) {
			if(a.getAccountNumber().equals(accNum)) {
				//deposit amount
				return a.deposit(amount);
			}
		}
		return false;
	}

	/*
	 * Transfer within Client's owned accounts
	 * return true when successful
	 * return false otherwise
	*/
	public boolean transfer(double amount, String fromAcc, Client fromClient, String toAcc, Client toClient) {
		if(fromAcc == toAcc) {
			return false; //cannot transfer to same accuont
		}
		
		for(Account a: fromClient.getAccountList()) {
			if(a.getAccountNumber().equals(fromAcc)) {
				for(Account b: toClient.getAccountList()) {
					if(b.getAccountNumber().equals(toAcc)) {
						if(a.withdraw(amount)) {
							//return true after modify acc balance
							return b.deposit(amount);
						}
						return false;//withdraw from fromAcc failed
					}
				}
				return false;//toAcc does not exist
			}
		}
		return false; //fromAcc does not exist
	}

	/*
	 * View a specific Client's info
	*/
	public void viewClientInfo(Client c) {
		System.out.println(c.toString());
	}
	
	/*
	 * List Clients
	*/
	public void listClients(ArrayList<Client> clientList) {
		for(Client c: clientList) {
			System.out.println(c.toString());
		}
	}
	
	/*
	 * List Accounts
	*/
	public void listAcc(ArrayList<Account> accList) {
		for(Account a: accList) {
			System.out.println(a.toString());
		}
	}
	
	/*
	 * List Applications
	*/
	public void listApp(ArrayList<Application> appList) {
		for(Application a: appList) {
			if(!a.getAccount().isJoint())
				System.out.println(a.toString());
		}
	}
	
	/*
	 * List Joint Account Applications
	*/
	public void listJointApp(ArrayList<Application> appList) {
		for(Application a: appList) {
			if(a.getAccount().isJoint())
				System.out.println(a.toString());
		}
	}
	
	public void setAppStatus(String appNum, int newStatus, ArrayList<Application> appList) {
		for(Application a: appList) {
			if(a.getApplicationNumber().equals(appNum))
				a.setStatus(newStatus);
		}
	}
}
