package com.revature.banking.people;

import java.time.LocalDate;

public class Customer extends Person {

	private static final long serialVersionUID = 4960031950098816042L;

	private String bankAccountId;

	public Customer() {
		super();
	}

	public Customer(String name, String address, LocalDate birthdate) {
		super(name, address, birthdate);
	}

	public String getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(String bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Customer))
			return false;

		Customer c = (Customer) o;

		return c.getPersonId() == this.getPersonId();
	}

	@Override
	public String toString() {
		return super.toString() + "\nCustomer [bankAccountId=" + bankAccountId + "]";
	}

}
