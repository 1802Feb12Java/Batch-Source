package com.revature.BankingAppPt2;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class testUserDAO {

	@Test
	public void test() {
		Connection connection = DatabaseConnection.getDatabaseConnection();
		CustomerDAO cus = new CustomerDAO(connection);
		BankAccountDAO bad = new BankAccountDAO(connection);
		
		PreparedStatement ps;
		try {		
			ps = connection.prepareStatement("INSERT INTO userAccounts(userPassword, userName, firstName, lastName, userType) VALUES('abc', 'abc', 'abc', 'abc', 'C')");
			ps.execute();
			int userID = cus.getUserId("abc");
			assert(cus.getFirstName(userID).equals("abc"));
			assert(cus.getLastName(userID).equals("abc"));
			assert(cus.getUsername(userID).equals("abc"));
			ps = connection.prepareStatement("DELETE FROM userAccounts WHERE userName = 'abc'");
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
