package com.revature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.repository.AssociateDAOImpl;
import com.revature.service.PersonService;

public class App {

	static PersonService personService;

	static AssociateDAOImpl associateDAO;
	
	@Autowired
	public static void setAssociateDAO(AssociateDAOImpl associateDAO) {
		App.associateDAO = associateDAO;
	}

	public static void main(String[] args) {

		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = ac.getBean("personService", PersonService.class);
		System.out.println(personService.getMe().getName());
		
		System.out.println(
				ac.getBean("associateDAOImpl", AssociateDAOImpl.class)
				.getPerson().getName());

	}
	
	public static void setPersonService(PersonService ps) {
		personService = ps;
	}

}
