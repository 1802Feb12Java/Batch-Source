package com.revature.database;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

public class ConnectionFactoryTest {

	@Test
	public void testGetInstance() {
		// Test getting instance
		assertNotNull(ConnectionFactory.getInstance());
	}

	@Test
	public void testGetConnection() {
		// Make connection
		assertNotNull(ConnectionFactory.getInstance().getConnection());
		try {
			assertNotNull(ConnectionFactory.getInstance().getConnection().getClientInfo());
		} catch (SQLException e) {
			assertTrue(false);
		}
	}

}
