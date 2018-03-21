package com.revature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.revature.model.Cave;
import com.revature.repository.CaveDao;

@Component
public class Driver {
	
	@Autowired
	private CaveDao caveDao;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = 
				new AnnotationConfigApplicationContext("com.revature");
		Driver driver = (Driver) ac.getBean("driver");
		Cave cave = driver.caveDao.findCave(2);
		System.out.println(cave.toString());

	}

}
