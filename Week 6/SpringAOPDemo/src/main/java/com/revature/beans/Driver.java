package com.revature.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Theatre myVisit = (Theatre) ac.getBean("theatre");
		
		myVisit.watchMovie();

	}

}
