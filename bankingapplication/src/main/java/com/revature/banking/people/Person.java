package com.revature.banking.people;

import java.io.Serializable;
import java.time.LocalDate;

import com.revature.database.DataStore;

/**
 * Super class to every type of account in the Application
 */
public abstract class Person implements Serializable {

	private static final long serialVersionUID = -2670846487104265523L;
	private String name;
	private String address;
	private LocalDate birthdate;

	private final String personId = DataStore.generateUID();

	public Person() {
	}

	public Person(String name, String address, LocalDate birthdate) {
		super();
		this.name = name;
		this.address = address;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getPersonId() {
		return personId;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", birthdate=" + birthdate + ", personId=" + personId
				+ "]";
	}

}
