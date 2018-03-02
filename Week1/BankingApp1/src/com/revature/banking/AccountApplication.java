package com.revature.banking;

import java.util.Scanner;

public class AccountApplication implements Storage {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String fName;
	private String lName;
	private int addressNumber;
	private String addressStreet;
	private int zipCode;
	private int accountType; // -1 = not approved, 0 = customer, 1 = employee, 2 = admin, -3 = application
	protected float balance;

	public AccountApplication(String username, String password, String fName, String lName, int addressNumber,
			String addressStreet, int zipCode, int accountType) {
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.addressNumber = addressNumber;
		this.addressStreet = addressStreet;
		this.zipCode = zipCode;
		this.accountType = accountType;
	}

	// before process
	public AccountApplication() {
		this(null, null, null, null, 0, null, 0, -3);
	}

	public AccountApplication(String username, String password, String[] applicationProcess) {
		this(username, password, applicationProcess[0], applicationProcess[1], Integer.valueOf(applicationProcess[2]),
				applicationProcess[3], Integer.valueOf(applicationProcess[4]), -3);
	}

	public void storeApplication() {
		this.storeToFile("applications\\" + this.username);
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

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String[] applicationProcess() {
		boolean done = false;
		Scanner sc = new Scanner(System.in);
		String name = null;
		String address = null;
		String[] returnThis = new String[5];

		while (!done) {
			System.out.println("Please enter first and last name.");
			name = sc.nextLine();
			String[] splitName = name.split(" ");
			if (splitName.length != 2) {
				continue;
			}
			System.out.println("Please enter address in this format[1234:Street Rd:90210]");
			address = sc.nextLine();
			String[] splitAddress = address.split(":");
			if (splitAddress.length != 3) {
				continue;
			}

			System.out.println("Does this look correct? (Y/N)\n\tFirst Name:\t" + splitName[0] + "\n\tLast Name:\t"
					+ splitName[1]);
			System.out.println("\n\tAddress:\t" + String.valueOf(splitAddress[0]) + " "
					+ String.valueOf(splitAddress[1]) + "\n\tZip Code:\t" + String.valueOf(splitAddress[2]));

			String yesOrNo = sc.nextLine();
			yesOrNo = yesOrNo.substring(0, 1).toUpperCase();

			switch (yesOrNo) {
			case "Y":
				done = true;
				System.out.println(
						"Application submitted. We will respond in 5-10 business days.\nLogging out for your safety.");

				returnThis[0] = splitName[0];
				returnThis[1] = splitName[1];
				returnThis[2] = splitAddress[0];
				returnThis[3] = splitAddress[1];
				returnThis[4] = splitAddress[2];
				break;
			case "N":
				break;
			default:
				System.out.println("Please enter correct input.");
			}
		}

		return returnThis;
	}

}
