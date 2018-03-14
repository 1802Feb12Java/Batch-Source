package com.revature.bankingappnew;

import java.sql.SQLException;

public class Employee extends User{
	
	public Employee(String username, String password, String firstName, String lastName) throws SQLException {
		super(username, password, firstName, lastName, "Employee");
	}
	
	public Employee(int userID, String username, String password, String firstName, String lastName) throws SQLException {
		super(userID, username, password, firstName, lastName, "Employee");
	}//end constructor. this constructor will be used for creating Employee objects from DB records
	
	public Employee(String username, String password, String firstName, String lastName, String type) throws SQLException {
		super(username, password, firstName, lastName, type);
	}
	
	public Employee(int userID, String username, String password, String firstName, String lastName, String type) throws SQLException {
		super(userID, username, password, firstName, lastName, type);
	}//end constructor. this constructor will be used for creating Customer objects from DB records
	
	public void viewCustomerInfo(String username) throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.viewUserRecord(username);
	}//end viewCustomerInfo method
	
	public void viewAccountDetails(String username) throws SQLException {
		com.revature.bankingappnew.daoimplementations.AccountLogImp.viewUserAccounts(username);
	}//end viewAccountDetails method
	
	public void viewAllAccountDetails() throws SQLException {
		com.revature.bankingappnew.daoimplementations.AccountLogImp.viewUserAccounts("all");
	}//end viewAllAccountDetails method
	
	public void viewAllPendingApplications() throws SQLException{
		com.revature.bankingappnew.daoimplementations.ApplicationLogImp.viewPendingApplications("all");
	}//end viewAllPendingApplications method
	
	public void viewUsersPendingApplications(String username) throws SQLException {
		com.revature.bankingappnew.daoimplementations.ApplicationLogImp.viewPendingApplications(username);
	}//end viewUsersPendingApplications method
	
	public void changeApplicationStatus(int appID, String newStatus) throws SQLException {
		Application a = com.revature.bankingappnew.daoimplementations.ApplicationLogImp.getApplicationObject(appID);
		a.setApplicationStatus(newStatus);
		if(newStatus.equalsIgnoreCase("Approved")) {
			if(a.getAuthorizedUser2() != null) {
				createNewJointAccount(a.getAuthorizedUser1(), a.getAuthorizedUser2(), a.getApplicationType());
			} else {
				createNewAccount(a.getAuthorizedUser1(), a.getApplicationType());
			}
		}
	}//end changeApplicationStatus method
	
	public void createNewAccount(Customer c1, String type) throws SQLException {
		new Account(c1, type);
	}//end createNewAccount method
	
	public void createNewJointAccount(Customer c1, Customer c2, String type) throws SQLException {
		new Account(c1, c2, type);
	}//end createNewAccount method
	
}//end class
