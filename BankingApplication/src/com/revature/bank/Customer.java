package com.revature.bank;

public class Customer extends User{

	
	private static final long serialVersionUID = 1L;
	public String username = "";
	public String password = "";
	public String firstName = "";
	public String lastName = "";
	public String address = "";
	public String phoneNumber = "";
	public int customerID = -1;
	

	public Customer(String username, String password, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
	}
	
	public Customer(String username, String password, String firstName, String lastName, String address,
			 String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
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
		return "Customer [ customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}
	

	
//	public double getAccountBal() {
//		return accountBal;
//	}
//
//	public void setAccountBal(double accountBal) {
//		this.accountBal = accountBal;
//	}
//	
//	public String getAccountNumber() {
//		return accountNumber;
//	}
//	public void setAccountNumber(String x) {
//		accountNumber = x;
//	}
//	
//	public void withdraw(double w) {
//		if(accountBal < w) {
//			System.out.println("You do not have the appropriate funds to withdraw that much.");
//		}else if(w < 0) {
//			System.out.println("You cannot withdraw a negative amount.");
//		}else {
//			accountBal = accountBal - w;
//			System.out.println("You have withdrawn $" + w);
//			System.out.println("Your new balance is $" + accountBal);
//		}
//	}
//	
//	public void deposit(double d) {
//		if(d < 0) {
//			System.out.println("You cannot deposit a negative amount.");
//		} else {
//			accountBal = accountBal + d;
//			System.out.println("You deposited $" + d);
//			System.out.println("Your new balance is $" + accountBal);
//		}
//	}
//	
//	
//	public void getAcct() {
//		hasAcct = true;
//	}
	
}
