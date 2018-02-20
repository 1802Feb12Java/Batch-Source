package com.revature.banking;

import java.io.Serializable;

import com.revature.banking.people.Customer;
import com.revature.banking.people.People;

/**
 * Class for creating account requests
 */
public class Request implements Serializable {
	// private static final Logger logger = LogManager.getLogger(Request.class);

	private static final long serialVersionUID = 3706048833008904437L;

	private String userId;
	private Customer customer;
	private String jointAccountNumber;

	public Request(String userID, Customer customer, String jointAccountNumber) {
		super();
		this.userId = userID;
		this.customer = customer;
		this.jointAccountNumber = jointAccountNumber;
	}

	public void approveRequest() {
		// give user their bank account or add them to joint
		Customer customer = (Customer) People.getPersonById(userId);
		if (jointAccountNumber != null && BankAccounts.findAccount(jointAccountNumber) != null) {
			customer.setBankAccountId(jointAccountNumber);
		} else {
			// create a new bank account
			BankAccount account = new BankAccount(0.0);
			// store pk in customer
			customer.setBankAccountId(account.getAccountId());
			// write account to file
			BankAccounts.addAccountToFile(account);
			// update customer
			People.updatePerson(customer);

		}
		// delete request from file
		Requests.removeRequestFromFile(userId);
	}

	public void denyRequest() {
		// delete request and account from 'db'
		Requests.removeRequestFromFile(userId);
	}

	public String getUserID() {
		return userId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getJointAccountNumber() {
		return jointAccountNumber;
	}

	@Override
	public String toString() {
		return "Request [userId=" + userId + ", customer=" + customer + ", jointAccountNumber=" + jointAccountNumber
				+ "]";
	}

}
