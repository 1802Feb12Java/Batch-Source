package com.revature.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.beans.User;

public class UserDaoImplTest {
	private UserDaoImpl userDao;
	private User permanentUser = new User(111, "Permanent", "Forever", null, false, null);
	private User updatableUser = new User(999, "Update", "Update", null, false, null);
	private User deleteUser = new User(666, "Delete", "Last Delete Name", null, false, null);

	@Before
	public void refreshDatabase() {
		// RefreshDatabase.main(null);
		// input test data
		userDao = new UserDaoImpl();
		// add user to delete
		userDao.addUser(deleteUser);

	}

	@Test
	public void testGetAllUsers() {

		List<User> fetched = userDao.getAllUsers();
		assertTrue(fetched.size() > 0);
		boolean found = false;
		for (User u : fetched)
			if (u.getUserId() == permanentUser.getUserId())
				found = true;
		assertTrue(found);

	}

	@Test
	public void testGetUser() {
		assertTrue(userDao.getUser(permanentUser.getUserId()).equals(permanentUser));
	}

	@Test
	public void testUpdateUser() {
		updatableUser.setBirthdate(Date.valueOf("2010-01-31"));
		updatableUser.setFirstName("First Name now");
		updatableUser.setLastName("Changed");
		updatableUser.setSuper(!updatableUser.isSuper());

		userDao.updateUser(updatableUser);
		assertTrue(userDao.getUser(updatableUser.getUserId()).equals(updatableUser));

	}

	@Test
	public void testDeleteUser() {
		int userId = deleteUser.getUserId();
		userDao.deleteUser(userId);
		boolean found = false;
		for (User u : userDao.getAllUsers())
			if (u.getUserId() == userId)
				found = true;
		assertFalse(found);

	}

	@Test
	public void testAddUser() {
		User user = new User(100, "Test", "LastName", null, false, null);
		userDao.addUser(user);
		User user2 = userDao.getUser(user.getUserId());
		assertTrue(user2.equals(user));

	}

}
