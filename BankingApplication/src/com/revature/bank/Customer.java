package com.revature.bank;

public class Customer extends User{

	
	private static final long serialVersionUID = 1L;
	public String username = "";
	public String password = "";
	public String firstName = "";
	public String lastName = "";
	public String address = "";
	public String city = "";
	public String state = "";
	public String phoneNumber = "";
	public String acctNum = "";
	public boolean hasAcct = false;
	public double accountBal = 0;
	public String accountNumber = "";
	
	public Customer(String username, String password, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
	}
	
	public Customer(String username, String password, String firstName, String lastName, String address,
			String city, String state, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
	}
	

	public String getAddress() {
		return address + "\n" + city + ", " + state;
	}

	public void setAddress(String address, String city, String state) {
		this.address = address;
		this.city = city;
		this.state = state;
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
	
	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String x) {
		accountNumber = x;
	}
	
	public void withdraw(double w) {
		if(accountBal < w) {
			System.out.println("You do not have the appropriate funds to withdraw that much.");
		}else if(w < 0) {
			System.out.println("You cannot withdraw a negative amount.");
		}else {
			accountBal = accountBal - w;
			System.out.println("You have withdrawn $" + w);
			System.out.println("Your new balance is $" + accountBal);
		}
	}
	
	public void deposit(double d) {
		if(d < 0) {
			System.out.println("You cannot deposit a negative amount.");
		} else {
			accountBal = accountBal + d;
			System.out.println("You deposited $" + d);
			System.out.println("Your new balance is $" + accountBal);
		}
	}
	
	
	public void getAcct() {
		hasAcct = true;
	}
	
}
