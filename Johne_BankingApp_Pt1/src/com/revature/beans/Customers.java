package com.revature.beans;

/**
 * 
 * @author johne
 *
 */
public class Customers {
	private String username;	//username to login to bank app
	private String password;	//password to login to bank app
	private int customerID = 0;	//customerID will associate with the account
	private static int count = 0;
	private Account checkings;
	private Account savings;
	private double amountForCheckings;	//amount added to checkings
	private double amountForSavings;	//amount added to savings

	/**
	 * @param username
	 * @param password
	 * @param customerID
	 * @param checkings
	 * @param savings
	 */
	public Customers(String username, String password, Account checkings, Account savings) {
		this.username = username;
		this.password = password;
		this.customerID = count++;
		this.checkings = checkings;
		this.savings = savings;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [Username = " + username + ", Password = " + password + ", CustomerID = " + customerID
				+ "]\n" + checkings + "\n" + savings;
	}

	/**
	 * @return the savings
	 */
	public Account getSavings() {
		return savings;
	}

	/**
	 * @param savings the savings to set
	 */
	public void setSavings(Account savings) {
		this.savings = savings;
	}

	/**
	 * @return the checkings
	 */
	public Account getCheckings() {
		return checkings;
	}

	/**
	 * @param checkings the checkings to set
	 */
	public void setCheckings(Account checkings) {
		this.checkings = checkings;
	}
	
	
	
}
