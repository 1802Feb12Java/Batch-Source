package com.revature.bankapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bankapp.db.ConnFactory;
import com.revature.bankapp.model.BankAccount;
import com.revature.bankapp.model.Customer;

public class CustomerDAO {
	
	/**
	 * this method will insert a new customer to the database
	 * @param customer
	 * @throws SQLException
	 */
	public static void register(Customer customer) throws SQLException {
		String insertString = "INSERT INTO CUSTOMER "
				+ "(CUSTOMER_ID, CUSTOMER_USERNAME, CUSTOMER_PASSWORD) "
				+ "VALUES (?,?,?)";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(insertString);
			ps.setInt(1, customer.getCustomerID());
			ps.setString(2, customer.getCustUsername());
			ps.setString(3, customer.getCustPW());
			ps.executeUpdate();
		
	}

	/**
	 * customer can delete their accounts
	 * @param b
	 * @throws SQLException
	 */
	public static void deleteAccount(BankAccount b) throws SQLException{
		String deleteString = "DELETE FROM BANKACCOUNT "
				+ "WHERE ACCOUNT_ID = ?";
			Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
			PreparedStatement ps =  conn.prepareStatement(deleteString);
			ps.setInt(1, b.getAccountID());
		
		
	}

	/**
	 * this will allow a customer to view their accounts
	 * @return
	 * @throws SQLException
	 */
	public static List<BankAccount> viewAccounts() throws SQLException{
		String allAccountString = "SELECT ACCOUNT_ID, CUSTOMER_ID, BALANCE "
				+ "FROM BANKACCOUNT";
		List<BankAccount> accounts = new ArrayList<BankAccount>();
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps = (PreparedStatement) conn.createStatement();
		ResultSet rs = ps.executeQuery(allAccountString);
				
			while(rs.next()) {
				BankAccount b = new BankAccount();
				b.setAccountID(rs.getInt("ACCOUNT_ID"));
				b.setCustomerID(rs.getInt("CUSTOMER_ID"));
				b.setBalance(rs.getDouble("BALANCE"));
				
				accounts.add(b);
			}
		return accounts;
	}

	/**
	 * customer can create their own accounts
	 * @return
	 * @throws SQLException
	 */
	public static BankAccount createAccount() throws SQLException {
		String insertString = "INSERT INTO BANKACCOUNT (BANKACCOUNT_ID, CUSTOMER_ID, BALANCE)"
				+ " VALUES (?, ?, ?)";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(insertString);
		BankAccount account = new BankAccount();
		ps.setInt(1, account.getAccountID());
		ps.setInt(2, account.getCustomerID());
		ps.setDouble(3, account.getBalance());
		
		return account;
	}

}
