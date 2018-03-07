package com.revature.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.beans.User;
import com.revature.bl.Users;

public class UserDaoImplTest {
	private User permanentUser = new User(1, "Permanent", "Forever", "test", "test", null, false, null);
	private User updatableUser = new User(2, "Update", "Update", "tset", "test", null, false, null);
	private User deleteUser = new User(3, "Delete", "Last Delete Name", "test", "test", null, false, null);

	@Before
	public void refreshDatabase() {
		// RefreshDatabase.main(null);
		// input test data
		// add user to delete
		Users.addUser(permanentUser);
		Users.addUser(updatableUser);
		Users.addUser(deleteUser);

	}

	@Test
	public void testGetAllUsersNotNull() {

		List<User> fetched = Users.getAllUsers();
		assertTrue(fetched.size() > 0);
		boolean found = false;
		for (User u : fetched)
			if (u.getUserId() == permanentUser.getUserId())
				found = true;
		assertTrue(found);

	}

	@Test
	public void testGetUser() {
		assertTrue(Users.getUser(permanentUser.getUserId()).getUserId() == permanentUser.getUserId());
	}

	@Test
	public void testUpdateUser() {
		updatableUser.setBirthdate(Date.valueOf("2010-01-31"));
		updatableUser.setFirstName("First Name now");
		updatableUser.setLastName("Changed");
		updatableUser.setSuper(!updatableUser.isSuper());

		Users.updateUser(updatableUser);
		assertTrue(Users.getUser(updatableUser.getUserId()).getUserId() == updatableUser.getUserId());

	}

	@Test
	public void testDeleteUser() {
		int userId = deleteUser.getUserId();
		Users.deleteUser(userId);
		boolean found = false;
		for (User u : Users.getAllUsers())
			if (u.getUserId() == userId)
				found = true;
		assertFalse(found);

	}

	@Test
	public void testAddUser() {
		User user = new User(100, "Test", "LastName", "a", "a", null, false, null);
		Users.addUser(user);
		User user2 = Users.getUser(user.getUserId());
		assertTrue(user2.equals(user));

	}

}
