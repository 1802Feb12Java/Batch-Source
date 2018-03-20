package com.revature.service;

import com.revature.model.Person;
import com.revature.repository.PersonDAO;

public class PersonService {

	private PersonDAO dao;
	
	public Person getMe() {
		return dao.getMe();
	}
	
	public void setDao(PersonDAO dao) {
		this.dao = dao;
	}
	
	public PersonService(PersonDAO dao) {
		this.dao = dao;
	}
	
	public PersonService() {
		
	}

}
