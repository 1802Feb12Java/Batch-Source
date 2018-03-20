package com.revature.repository;

import org.springframework.stereotype.Component;

import com.revature.model.Person;

@Component
public class AssociateDAOImpl {
	
	public Person getPerson() {
		return new Person("Tim", 5000);
	}

}
