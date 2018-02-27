package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

public class testCustomerDAO {

	@Test
	public void test() {
		Connection connection = DatabaseConnection.getDatabaseConnection();
		CustomerDAO cus = new CustomerDAO(connection);
		BankAccountDAO bad = new BankAccountDAO(connection);
		bad.addAccount(999, "checking", 0);
		cus.deleteAccount(999);
		List<BankAccount> accounts = bad.getBankAccountList(999);
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("DELETE FROM bankAccounts WHERE primaryOwnerId = 999");
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assert(accounts.isEmpty());
	}

}
