package com.revature.banking.jdbc;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;

public class BankJDBCTest {

	@Test
	public void testConnectionToDatabase() throws SQLException {
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("database.properties"));
			Connection conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pwd"));
			assertNotNull(conn.getMetaData());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetPassword() throws SQLException {
		assertEquals("passw0rd", UserDB.getPasswordToCheck("admin"));
	}

	@Test
	public void testGetAccountType() throws SQLException {
		assertEquals(2, UserDB.getAccountType("admin"));
	}

	@Test
	public void testGet1Balance() throws SQLException {
		assertEquals(1234.56, UserDB.get1Balance(0), 0.009);
	}

	@Test
	public void testApprovedAccount() throws SQLException {
		assertTrue(UserDB.getApproved(0));
	}

	@Test
	public void testGetFirstName() throws SQLException {
		assertEquals("Michael", UserDB.getFirstName("admin"));
	}

}
