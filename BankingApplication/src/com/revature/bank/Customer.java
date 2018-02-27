package com.revature.bank;

public class Customer extends User{

	
	private static final long serialVersionUID = 1L;
	public String address = "";
	public String phoneNumber = "";
	public String customerID = "null";
	public double accountBal = 0;
	

	public Customer( String firstname, String lastname, String username, String password) {
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
	}
	
	public Customer(String firstName, String lastName, String username, String password, String address,
			 String phoneNumber) {
		//super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Customer [address=" + address + ", phoneNumber=" + phoneNumber + ", customerID=" + customerID
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	

	
	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}
	

	
}
