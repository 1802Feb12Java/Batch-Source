package com.revature.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;

import com.revature.controllers.Manager;
import com.revature.controllers.User;
import com.revature.dao.implementation.ConnFactory;

public class Project1Test {

	/* Commented out for Jenkins
	@Test
	public void testConnectionToDatabase() throws SQLException {
		try {
			assertNotNull(ConnFactory.getInstance().getConnection());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetUserInformation() throws SQLException {
		assertTrue(User.viewFirstName("admin").equals("Michael"));
		assertNotNull(User.viewUserInformation("admin"));
		assertTrue(User.getRole("admin").equals("manager"));
	}

	@Test
	public void testLogin() throws SQLException {
		assertTrue(User.logIn("admin", "passw0rd"));
		assertFalse(User.logIn("admin", "p1"));
	}

	@Test
	public void testGetReimbursements() throws SQLException {
		assertNotNull(Manager.viewAll());
	}

	@Test
	public void testGetUserReimbursements() throws SQLException {
		assertNotNull(User.viewReimbursementRequests("admin"));
	}

	@Test
	public void testDataRetrieval() throws SQLException {
		assertNotNull(Manager.totalCostReimbursments());
		assertNotNull(Manager.totalApprovedReimbursments());
	}
	*/

}