package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String bankAccountLookupString = "SELECT * FROM userAccountsLookup WHERE userId = ?";
		String bankAccountSelectString = "SELECT * FROM bankAccounts WHERE accountId = ? AND approval = 'A'";
		List<BankAccount> bankAccountList = new ArrayList<BankAccount>();
		try {
			PreparedStatement bankAccountLookupQuery = connection.prepareStatement(bankAccountLookupString);
			PreparedStatement bankAccountSelectQuery = connection.prepareStatement(bankAccountSelectString);
			//get list of accountIds from account lookup table
			bankAccountLookupQuery.setInt(1, userId);
			ResultSet resultSet = bankAccountLookupQuery.executeQuery();

			while(resultSet.next()) {
				int bankAccountNumber = resultSet.getInt(2);
				bankAccountSelectQuery.setInt(1, bankAccountNumber);
				ResultSet accountResultSet = bankAccountSelectQuery.executeQuery();
				while (accountResultSet.next()) {
					int accountId = accountResultSet.getInt(1);
					String accountType = accountResultSet.getString(2);
					float balance = accountResultSet.getFloat(3);
					String approvalStatus = accountResultSet.getString(4);
					BankAccount bankAccount = new BankAccount(accountId, accountType, balance, approvalStatus);
					bankAccountList.add(bankAccount);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
		return bankAccountList;
	}
	
	//used to return last inserted account
	public int getBankAccountId(int userId) {
		String bankAccountIdString = "SELECT MAX(accountId) FROM bankAccounts WHERE primaryOwnerId = ?";
		int accountId = 0;
		try {
			PreparedStatement bankAccountQuery = connection.prepareStatement(bankAccountIdString);
			bankAccountQuery.setInt(1, userId);
			ResultSet resultSet = bankAccountQuery.executeQuery();
			if (resultSet.next()) {
				accountId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.info(e.toString());
		}
		return accountId;
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
	
	public void updateBalance(int accountId, float amountToDeposit, float currentBalance) {
		String updateBalanceString = "UPDATE bankAccounts SET balance = ? WHERE accountId = ?";
		currentBalance += amountToDeposit;
		try {
			PreparedStatement updateBalanceQuery = connection.prepareStatement(updateBalanceString);
			updateBalanceQuery.setFloat(1, currentBalance);
			updateBalanceQuery.setInt(2, accountId);
			int result = updateBalanceQuery.executeUpdate();
			if (result > 0) {
				System.out.println("Successful Update");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	public int getBalance(int accountId) {
		int balance = 0;
		String getBalanceString = "SELECT balance FROM bankAccounts WHERE accountId = ?";
		try {
			PreparedStatement getBalanceQuery = connection.prepareStatement(getBalanceString);
			getBalanceQuery.setInt(1, accountId);
			ResultSet resultSet = getBalanceQuery.executeQuery();
			if (resultSet.next()) {
				balance = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return balance;
	}
}
