package com.revature.bankingappnew;

import java.sql.SQLException;


public class Application{
	
	private final int applicationNumber;
	private Customer authorizedUser1;
	private Customer authorizedUser2;
	private String applicationType;//checking, savings, joint checking, joint savings
	private String applicationStatus;//pending, approved, denied
	
	public Application(Customer cust1, Customer cust2, String type) throws SQLException {
		super();  
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = cust2;
		this.applicationType = "Joint " + type;
		this.applicationStatus = "Pending";
		com.revature.bankingappnew.daoimplementations.ApplicationLogImp.createApplicationRecord("Joint" + type, cust1.getUsername(), cust2.getUsername());
		
		this.applicationNumber = com.revature.bankingappnew.daoimplementations.ApplicationLogImp.getMaxApplicationID();
	}//end joint application constructor
	
	public Application(Customer cust1, String type) throws SQLException {
		super();  
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = null;
		this.applicationType = type;
		this.applicationStatus = "Pending";
		com.revature.bankingappnew.daoimplementations.ApplicationLogImp.createApplicationRecord(type, cust1.getUsername());
		
		this.applicationNumber = com.revature.bankingappnew.daoimplementations.ApplicationLogImp.getMaxApplicationID();
	}//end single application constructor

	public Application(int appID, Customer cust1, String type, String status) {
		this.applicationNumber = appID;
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = null;
		this.applicationType = type;
		this.applicationStatus = status;
	}//end constructor. will be used to create Application Objects from DB records
	
	public Application(int appID, Customer cust1, Customer cust2, String type, String status) {
		this.applicationNumber = appID;
		this.authorizedUser1 = cust1;
		this.authorizedUser2 = cust2;
		this.applicationType = type;
		this.applicationStatus = status;
	}//end constructor. will be used to create Application Objects from DB records
	
	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) throws SQLException {
		this.applicationStatus = applicationStatus;
		com.revature.bankingappnew.daoimplementations.ApplicationLogImp.updateApplicationStatus(this.applicationNumber, applicationStatus);
	}

	public String getApplicationType() {
		return applicationType;
	}
	
	public void displayApplicantInfo() throws SQLException {
		com.revature.bankingappnew.daoimplementations.UserLogImp.viewUserRecord(authorizedUser1.getUsername());
		if(authorizedUser2 != null) {
			com.revature.bankingappnew.daoimplementations.UserLogImp.viewUserRecord(authorizedUser2.getUsername());
		}
	}//end displayApplicantInfo

	public int getApplicationNumber() {
		return applicationNumber;
	}

	

	public Customer getAuthorizedUser1() {
		return authorizedUser1;
	}



	public Customer getAuthorizedUser2() {
		return authorizedUser2;
	}

	

	
	
	
}//end class
