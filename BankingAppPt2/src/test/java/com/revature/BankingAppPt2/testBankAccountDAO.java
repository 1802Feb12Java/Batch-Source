package com.revature.BankingAppPt2;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class testBankAccountDAO {

	@Test
	public void test() {
		Connection connection = DatabaseConnection.getDatabaseConnection();
		BankAccountDAO bankAccountDAO = new BankAccountDAO(connection);
		@SuppressWarnings("unused")
		BankAccount bankAccount = new BankAccount(999, "checking",22,"A");
		bankAccountDAO.addAccount(999, "checking", 0);
		int accountId = bankAccountDAO.getBankAccountId(999);
		assertNotNull(accountId);
		bankAccountDAO.updateBalance(accountId, 20, 0);
		
		assert(bankAccountDAO.getBalance(accountId) == 20);
		bankAccountDAO.updateBalance(accountId, -20, bankAccountDAO.getBalance(accountId));
		
		assert(bankAccountDAO.getBalance(accountId) == 0);
		//clean up
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("DELETE FROM bankAccounts WHERE primaryOwnerId = 999");
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
