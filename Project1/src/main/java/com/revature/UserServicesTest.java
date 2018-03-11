package com.revature;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class UserServicesTest {

	UserServices us = new UserServices();
	
	@Test
	public void getAllUsers() throws SQLException {
		assertTrue(us.getAllUsers().size() == 8);
		assertTrue(us.getAllEmployees().size() == 6);
	}
	
	@Test
	public void getUser() throws SQLException {
		assertTrue(us.getUsers(1).getfName().equals("Maerlyn"));
		
	}

}
