package com.revature.repository;

import com.revature.model.Person;

public class PersonDAOImpl implements PersonDAO {

	public Person getMe() {
		return new Person("Mehrab", 0);
	}

}
