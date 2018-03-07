package com.revature.servlet;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class TestStuff {

	
	
	@Test
	public void test() throws SQLException, ClassNotFoundException {
		
		PersonDAO dao = new PersonDAO();
		Person person = new Person("John", 150);
		
		dao.addPersonToDB(person);
		dao.updateUser(150, "DAO");
		dao.removeUser(100);
		System.out.println(dao.getNameFromDB(150));
		
//		dao.addPersonToDB(person);
//		System.out.println(dao.getNameFromDB(person));
		
		
	}

}
