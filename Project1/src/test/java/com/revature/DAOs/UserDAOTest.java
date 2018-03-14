package com.revature.DAOs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.revature.beans.User;
import com.revature.util.ConnectionFactory;

import static org.junit.Assert.*;

public class UserDAOTest {
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
		String nameBeforeCopy = userDao.readUser(1).getFirstname();
		assertEquals(nameBefore, nameBeforeCopy);
	}

	
	@Test
	public void testUpdateLastName() throws SQLException {
		String nameBefore = userDao.readUser(1).getLastname();
		userDao.updateLastname("Smith", 1);
		String nameAfter = userDao.readUser(1).getLastname();
		assertNotSame(nameBefore, nameAfter);
		userDao.updateLastname(nameBefore, 1);		//put it back
		String nameBeforeCopy = userDao.readUser(1).getLastname();
		assertEquals(nameBefore, nameBeforeCopy);
	}

	
	@Test
	public void testUpdateUserName() throws SQLException {
		String usernameBefore = userDao.readUser(1).getUsername();
		userDao.updateUsername("jimjob", 1);
		String usernameAfter = userDao.readUser(1).getUsername();
		assertNotSame(usernameBefore, usernameAfter);
		userDao.updateUsername(usernameBefore, 1);		//put it back
		String usernameBeforeCopy = userDao.readUser(1).getUsername();
		assertEquals(usernameBefore, usernameBeforeCopy);
	}

	
	@Test
	public void testUpdatePassword() throws SQLException {
		String passwordBefore = userDao.readUser(1).getPassword();
		userDao.updatePassword("@$", 1);
		String nameAfter = userDao.readUser(1).getPassword();
		assertNotSame(passwordBefore, nameAfter);
		userDao.updatePassword(passwordBefore, 1);		//put it back
		String passwordBeforeCopy = userDao.readUser(1).getPassword();
		assertEquals(passwordBefore, passwordBeforeCopy);
	}

	
	@Test
	public void testUpdateEmail() throws SQLException {
		String emailBefore = userDao.readUser(1).getEmail();
		userDao.updateEmail("this@email.com", 1);
		String emailAfter = userDao.readUser(1).getEmail();
		assertNotSame(emailBefore, emailAfter);
		userDao.updateEmail(emailBefore, 1);		//put it back
		String emailBeforeCopy = userDao.readUser(1).getEmail();
		assertEquals(emailBefore, emailBeforeCopy);
	}

	
	@Test
	public void testGetAllUsers() throws SQLException {
		ArrayList<User> allUsers = userDao.getAllUsers();
		assertTrue(allUsers.size() >= 2);	//should at least have my 2 defaults in there
	}

}
