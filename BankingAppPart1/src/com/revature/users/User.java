package com.revature.users;

public class User {

	private static final long serialVersionUID = -2795373050638956075L;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String streetAddress = null;
	private String city = null;
	private String state = null;
	private String phoneNumber = null;
	private String userType = null;
	private int customerID = 0;
	private boolean accountHolder = false;
	private boolean applyingForSavings = false;

	public User() {
		super();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public boolean isAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(boolean accountHolder) {
		this.accountHolder = accountHolder;
	}

	public boolean isApplyingForSavings() {
		return applyingForSavings;
	}

	public void setApplyingForSavings(boolean applyingForSavings) {
		this.applyingForSavings = applyingForSavings;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User name:  " + userName + "\nName:  " + firstName + " "+ lastName + 
				"\nAddress:  " + streetAddress + "\nCity:  " + city + "\nState: " + state
				+ "\nTelephone number:  " + phoneNumber;
	}
}
