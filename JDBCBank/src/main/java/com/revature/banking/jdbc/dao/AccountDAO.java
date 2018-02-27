package com.revature.banking.jdbc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.jdbc.model.Account;
import com.revature.banking.jdbc.model.Application;
import com.revature.banking.jdbc.utilities.DAOUtilities;

public class AccountDAO implements InterfaceAccountDAO {
	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public AccountDAO() {
	}
	public List<Account> getAllAccounts() {
		List <Account> accounts = new ArrayList<Account>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKACCOUNT";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Account account = new Account(results.getInt("accountId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getInt("applicationId"), results.getString("status"), results.getDouble("balance"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAllAccounts");
			e.printStackTrace();
		}
		
		return accounts;
	}
	public List<Account> getAllOpenAccounts() {
		List <Account> accounts = new ArrayList<Account>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKACCOUNT WHERE status=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, "Open");
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Account account = new Account(results.getInt("accountId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getInt("applicationId"), results.getString("status"), results.getDouble("balance"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAllAccounts");
			e.printStackTrace();
		}
		
		return accounts;
	}
	public Account getAccountByAccountId(int accountId) {
		Account account = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKACCOUNT where accountId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, accountId);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				account = new Account(results.getInt("accountId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getInt("applicationId"), results.getString("status"), results.getDouble("balance"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAccountByAccountId");
			e.printStackTrace();
		}
		return account;
	}public Account getAccountByApplicationId(int applicationId) {
		Account account = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKACCOUNT where applicationId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, applicationId);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				account = new Account(results.getInt("accountId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getInt("applicationId"), results.getString("status"), results.getDouble("balance"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAccountByapplicationId");
			e.printStackTrace();
		}
		return account;
	}
	public List<Account> getAccountsByCustomerId(int customerId) {
		List <Account> accounts = new ArrayList<Account>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKACCOUNT where primaryCustomerId=? OR secondaryCustomerID=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, customerId);
			stmt.setInt(2, customerId);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Account account = new Account(results.getInt("accountId"), results.getInt("primaryCustomerId"), results.getInt("secondaryCustomerId"),
						results.getInt("applicationId"), results.getString("status"), results.getDouble("balance"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAccountsByCustomerId");
			e.printStackTrace();
		}		
		return accounts;
	}

	public boolean updateAccount(Account account) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE BANKACCOUNT SET status=?, balance=? WHERE accountId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, account.getStatus());
			stmt.setDouble(2, account.getBalance());
			stmt.setInt(3, account.getAccountId());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: updateAccount");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	public boolean addAccount(Account account, Application application) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO BANKACCOUNT (primaryCustomerId, secondaryCustomerId, applicationId, status, balance) "
					+ "VALUES( ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sqlQuery);
			//stmt.setString(1, Integer.toString(account.getAccountId()));
			stmt.setInt(1, account.getPrimaryCustomerId());
			stmt.setInt(2, account.getSecondaryCustomerId());
			stmt.setInt(3, application.getApplicationId());
			stmt.setString(4, account.getStatus());
			stmt.setDouble(5, account.getBalance());
			if( stmt.executeUpdate() != 0) {
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: addAccount");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	public boolean accountTransfer(Account toAccount, Account fromAccount) {
		try {
			connection = DAOUtilities.getConnection();
			CallableStatement call = connection.prepareCall("{CALL BANKACCOUNTTRANSFER(?,?,?,?)}");
			//stmt.setString(1, Integer.toString(account.getAccountId()));
			call.setInt(1, toAccount.getAccountId());
			call.setDouble(2, toAccount.getBalance());
			call.setInt(3, fromAccount.getAccountId());
			call.setDouble(4, fromAccount.getBalance());
			if( stmt.executeUpdate() != 0) {
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: addAccount");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	public boolean deleteAccount(Account account) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "DELETE FROM BANKACCOUNT WHERE accountId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, account.getAccountId());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: deleteAccount");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}
	public int getNumAccounts() {
		int count=0;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT count(*) FROM BANKACCOUNT";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				count = results.getInt(1);
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getNumAccounts");
			e.printStackTrace();
		}
		
		return count;
	}
	public boolean accountExists(int accountId) {
		if(getAccountByAccountId(accountId)!=null)
			return true;
		else
			return false;
	}
}
