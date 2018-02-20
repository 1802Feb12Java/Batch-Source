package com.revature.banking.part1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements InterfaceCustomer, Serializable {
	
	private static final long serialVersionUID = 56L;
	
	
	protected List<Account> accounts = new ArrayList<Account>();
	protected List<Application> applications = new ArrayList<Application>(); 
	protected String name, address, phone;
	
//	public Customer(int customerID, String username, String password) {
//		this(customerID, username, password, "","","");
//	}

	public Customer(int customerID, String username, String password, String name, String address, String phone) {
		super(customerID, username, password, "Customer");
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		if(!accounts.contains(account))
			this.accounts.add(account);
	}
	
	public List<Application> getApplications() {
		return applications;
	}
	
	public void add(Application element) {
		applications.add(element);
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + getID() + ", name=" + name
				 + ", \naddress=" + address + ", phone=" + phone
				 + ",\n Accounts=" + accounts.toString() +"]";
	}

//	public Application applyOpenAccount() {
//		Application app = this.applyOpenAccount(0);
//		return app;
//	}
//	public Application applyOpenAccount(int secondaryCustomerID) {
//		
//	}
//	public void applyCloseAccount() {
//		
//	}
	
	


}
