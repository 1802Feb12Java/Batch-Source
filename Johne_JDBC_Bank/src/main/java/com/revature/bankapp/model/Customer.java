package com.revature.bankapp.model;


public class Customer {
	private int customerID;
	private String custUsername;
	private String custPW;
	
	public Customer() {};
	
	/**
	 * @param customerID
	 * @param custUsername
	 * @param custPW
	 * @param notes
	 * @param dateEntered
	 */
	public Customer(int customerID, String custUsername, String custPW) {
		this.customerID = customerID;
		this.custUsername = custUsername;
		this.custPW = custPW;
	}
	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return the custUsername
	 */
	public String getCustUsername() {
		return custUsername;
	}
	/**
	 * @param custUsername the custUsername to set
	 */
	public void setCustUsername(String custUsername) {
		this.custUsername = custUsername;
	}
	/**
	 * @return the custPW
	 */
	public String getCustPW() {
		return custPW;
	}
	/**
	 * @param custPW the custPW to set
	 */
	public void setCustPW(String custPW) {
		this.custPW = custPW;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", custUsername=" + custUsername + ", custPW=" + custPW + "]\n";
	}
	
	
}
