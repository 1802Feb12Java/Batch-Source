package com.revature.bankingapp1;
/*
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Application implements Serializable {
	
	private static final long serialVersionUID = 1917959126037545869L;
	private ArrayList<Customer> customerList;
	private String applicationType;//checking, savings
	private String applicationStatus;//pending, approved, denied
	private Boolean joint; //true if joint, false if not
	
	public Application(ArrayList<Customer> customerList, String applicationType) {
		super();
		this.customerList = customerList;
		this.applicationType = applicationType;
		this.applicationStatus = "Pending";
		if(customerList.size() > 1) {
			this.joint = true;
		} else {
			this.joint = false;
		}
	}//end two parameter constructor

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public Boolean getJoint() {
		return joint;
	}
	
	public String displayApplicantInfo() {
		String s = "";
		for(Customer c : customerList) {
			s = s.concat("Customer Username: " + c.getUsername()
					+ "\nFirst Name: " + c.getFirstName()
					+ "\nLast Name: " + c.getLastName()
					+ "\n");
		}
		return s;
	}//end displayApplicantInfo

	@Override
	public String toString() {
		return "Application\nApplication Type:" + applicationType + "\nApplicant Information: " + displayApplicantInfo() 
		+ "\nApplication Status: " + applicationStatus;
	}
	
	
	
}//end class
