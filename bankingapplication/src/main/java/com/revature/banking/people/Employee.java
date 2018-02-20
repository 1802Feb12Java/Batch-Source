package com.revature.banking.people;

import java.time.LocalDate;

/**
 * Employee Class
 *
 */
public class Employee extends Person {

	private static final long serialVersionUID = -7512382184506363149L;

	public Employee(String name, String address, LocalDate birthdate) {
		super(name, address, birthdate);
	}
}
