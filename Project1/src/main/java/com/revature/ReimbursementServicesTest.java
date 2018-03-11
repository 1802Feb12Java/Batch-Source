package com.revature;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class ReimbursementServicesTest {

	ReimbursementServices rs = new ReimbursementServices();
	
	@Test
	public void getEmpty() throws SQLException {
		assertTrue(rs.getAllPending().isEmpty());
		assertTrue(rs.getAllReimbursements().isEmpty());
	}

}
