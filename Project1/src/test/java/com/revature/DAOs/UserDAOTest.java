package com.revature.DAOs;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.revature.beans.User;
import com.revature.factory.ConnectionFactory;

import static org.junit.Assert.*;

class UserDAOTest {
	ConnectionFactory cf = ConnectionFactory.getInstance();
	Connection conn = cf.getConnection();
	UserDAO userDao;
	
	@Before
	public void setup() {
		userDao = new UserDAOClass(conn);
	}
	
	@Test
	public void testReadUser() throws SQLException {
		User u = userDao.readUser(1);	//should always be my manager
		assertEquals("joe", u.getUsername());
		assertEquals("@@", u.getPassword());
		assertEquals("Jim", u.getFirstname());
		assertEquals("George", u.getLastname());
		assertEquals("joe@email.com", u.getEmail());
	}

	
	@Test
	public void testUpdateFirstName() throws SQLException {
		String nameBefore = userDao.readUser(1).getFirstname();
		userDao.updateFirstname("Jack", 1);
		String nameAfter = userDao.readUser(1).getFirstname();
		assertNotSame(nameBefore, nameAfter);
		userDao.updateFirstname(nameBefore, 1);		//put it back
	}

}
