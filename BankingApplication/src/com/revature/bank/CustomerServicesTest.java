package com.revature.bank;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class CustomerServicesTest {

	CustomerServices cs = new CustomerServices();
//	@Test
//	void testAddCustomer() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetFalseCustomer() throws SQLException {
		assertTrue(cs.getCustomer(500) == null);
		assertTrue(cs.getCustomer(9) != null);
	}

	@Test
	void testLogin() throws SQLException {
		assertTrue(cs.login("alkf", "a;af") == null);
		assertTrue(cs.login("admin", "admin") == null);
		assertTrue(cs.login("starkjosh", "password") != null);
	}

}
