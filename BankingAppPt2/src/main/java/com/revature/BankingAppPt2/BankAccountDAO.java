package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class BankAccountDAO {
	final static Logger logger = Logger.getLogger(BankAccountServices.class);
	private Connection connection;

	public BankAccountDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<BankAccount> getBankAccountList(int userId) {
		return new ArrayList<BankAccount>();
	}
	
	public int getBankAccountId(int userId) {
		return 1;
	}
	
	public boolean addAccount(int userId, String accountType, int jointOwnerId) {
		boolean accountAdded = false;
		String addAccountString = "INSERT INTO bankAccounts (accountType, balance, approval, primaryOwnerId) VALUES (?, 0, 'P', ?)";
		try {
			PreparedStatement addAccountQuery = connection.prepareStatement(addAccountString);
			addAccountQuery.setString(1, accountType);
			addAccountQuery.setInt(2, userId);
			int result = addAccountQuery.executeUpdate();
			if (result > 0) {
				accountAdded = true;
			}
			else {
				throw new SQLException("Error inserting into account table");
			}
			
			int accountNumber = getBankAccountId(userId);
			addToAccountLookupTable(accountNumber, userId, jointOwnerId);
		} catch (SQLException e) {
			System.out.println("Error adding account to DB. Failure in add account");
			logger.error(e.toString());
			
		}
		return accountAdded;
	}
	
	private void addToAccountLookupTable(int accountId, int primaryId, int jointId) {
		String addToOwnerAccountLookup = "INSERT INTO userAccountsLookup VALUES (?, ?)";
		try {
			PreparedStatement addToAccountLookupQuery = connection.prepareStatement(addToOwnerAccountLookup);
			addToAccountLookupQuery.setInt(1, primaryId);
			addToAccountLookupQuery.setInt(2, accountId);
			addToAccountLookupQuery.executeUpdate();
			if (jointId > 0) {
				addToAccountLookupQuery = connection.prepareStatement(addToOwnerAccountLookup);
				addToAccountLookupQuery.setInt(1, jointId);
				addToAccountLookupQuery.setInt(2, accountId);
				addToAccountLookupQuery.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error adding account to DB. Failure in add to lookup table.");
			logger.error(e.toString());
		}
		
	}
}
