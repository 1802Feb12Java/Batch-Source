package com.revature.banking;

import java.io.File;

import com.revature.banking.Storage;

public class User implements Storage {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String fName;
	private String lName;
	private int addressNumber;
	private String addressStreet;
	private int zipCode;
	private int accountType; // -3 = application, -2 = no account, -1 = not approved, 0 = customer, 1 = employee, 2 = admin
	protected float balance;

	@Override
	public String toString() {
		return username + "\nName:\t\t" + fName + " " + lName + "\nAddress:\t" + addressNumber + " " + addressStreet
				+ "\nZip Code:\t" + zipCode + "\nAccountType:\t" + accountType + "\nBalance:\t" + balance;
	}

	public User(String username, String password, String fName, String lName, int addressNumber, String addressStreet,
			int zipCode, int accountType) {
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.addressNumber = addressNumber;
		this.addressStreet = addressStreet;
		this.zipCode = zipCode;
		this.accountType = accountType;
	}

	// for creation
	public User(String username, String password, int accountType) {
		this(username, password, null, null, 0, null, 0, accountType);
	}

	// for login
	public User(String username, String password) {
		this(username, password, null, null, 0, null, 0, -2);
	}

	// for transfers
	public User(String username) {
		this(username, null, null, null, 0, null, 0, 0);
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
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

	public void storeFile() {
		this.storeToFile("users\\" + this.username);
	}

	public User getUser(String pass) {
		User returnThis = new User(null, null);
		File checkExistence = new File("..\\BankingApp1\\ignorethis\\users\\" + this.username + ".txt");

		try {
			if (checkExistence.isFile()) {
				returnThis = (User) Storage.readFromFile("users\\" + this.username);
				if (returnThis.password.equals(pass)) {
					System.out.println("Logged in as " + returnThis.getfName());
				} else {
					System.out.println("Wrong password.");
					returnThis = new User(null, null);
				}
			} else {
				System.out.println("User does not exist.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnThis;
	}

	public User getUser1(String usr) {
		User returnThis = new User(null, null);
		File checkExistence = new File("..\\BankingApp1\\ignorethis\\users\\" + usr + ".txt");

		try {
			if (checkExistence.isFile()) {
				returnThis = (User) Storage.readFromFile("users\\" + usr);

			} else {
				System.out.println("User does not exist.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnThis;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}