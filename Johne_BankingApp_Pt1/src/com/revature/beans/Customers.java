package com.revature.beans;

/**
 * 
 * @author johne
 *
 */
public class Customers extends User{
	
	private CheckingsAccount checkings;
	private SavingsAccount savings;

	/**
	 * @param username
	 * @param password
	 * @param customerID
	 * @param checkings
	 * @param savings
	 */
	public Customers(String username, String password, CheckingsAccount checkings, SavingsAccount savings) {
		super("Customer", username, password);
		this.checkings = checkings;
		this.savings = savings;
	}

	/**
	 * @return the savings
	 */
	public BankAccount getSavings() {
		return savings;
	}

	/**
	 * @param savings the savings to set
	 */
	public void setSavings(SavingsAccount savings) {
		this.savings = savings;
	}

	/**
	 * @return the checkings
	 */
	public BankAccount getCheckings() {
		return checkings;
	}

	/**
	 * @param checkings the checkings to set
	 */
	public void setCheckings(CheckingsAccount checkings) {
		this.checkings = checkings;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getTypeOfUser() +" [Username = " + getUsername() + ", Password = " + getPassword() + ", CustomerID = " + getUserID()
				+ "]\n" + checkings + "\n" + savings;
	}
	
}
