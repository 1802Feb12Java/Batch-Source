package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.accounts.Account;
import com.revature.dao.AccountDAO;
import com.revature.util.ConnFactory;

public class AccountServices implements AccountDAO{
	ConnFactory cf = new ConnFactory();
	Connection conn = cf.getConnection();
	
	public void addAccount(Account account) throws SQLException {
		//set the string to use in the prepared statement and create the statement
		String sql = "INSERT INTO ACCOUNT(accountnumber, balance, status, accountType, accountHolder) VALUES(GENERATE_ACCOUNT_NUMBER.nextVal,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//set the appropriate fields
		ps.setDouble(1, account.getBalance());
		ps.setString(2, account.getStatus());
		ps.setString(3, account.getAccountType());
		ps.setString(4, account.getPrimaryAccountHolder());
		
		ps.execute();
		ps.close();
	}

	public Account getAccount(int accountNumber) throws SQLException {
		Account account = null;
		String sql = "SELECT * FROM ACCOUNT WHERE accountNumber = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, accountNumber);
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			System.out.println("Account number not found");
			ps.close();
			rs.close();
			return account;
		}
		
		else {
			account = new Account();
			account.setAccountNumber(rs.getInt("accountNumber"));
			account.setBalance(rs.getDouble("balance"));
			account.setStatus(rs.getString("status"));
			account.setAccountType(rs.getString("accountType"));
			account.setPrimaryAccountHolder(rs.getString("accountHolder"));
			ps.close();
			rs.close();
			return account;
		}
	}

	public void updateAccount(Account account) throws SQLException {
		String sql = "UPDATE ACCOUNT SET balance = ? WHERE accountNumber = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, account.getBalance());
		ps.setInt(2, account.getAccountNumber());
		
		ps.executeUpdate();
		ps.close();		
	}

	public void deleteAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Account> getPendingAccounts() throws SQLException {
		String sql = "SELECT * FROM ACCOUNT WHERE STATUS = ?";
		ArrayList<Account> pendingList = null;
		Account current = null;
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "Pending");
		ResultSet rs = ps.executeQuery();
		
		if(rs.next() == false) {
			System.out.println("No accounts currently pending");
			return pendingList;
		}
		
		else {
			pendingList = new ArrayList<>();
			
			do {
				current = new Account();
				current.setAccountNumber(rs.getInt("accountNumber"));
				current.setBalance(rs.getDouble("balance"));
				current.setStatus(rs.getString("status"));
				current.setAccountType(rs.getString("accountType"));
				current.setPrimaryAccountHolder(rs.getString("accountHolder"));
				pendingList.add(current);
			}while(rs.next());
		}

		ps.close();
		rs.close();
		return pendingList;
	}

	public ArrayList<Account> getCustomerAccounts()throws SQLException{
		String sql = "SELECT * FROM ACCOUNT WHERE accountHolder = ?";
		ArrayList<Account> customerAccountList = null;
		Account current = null;
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "Pending");
		ResultSet rs = ps.executeQuery();
		
		if(rs.next() == false) {
			System.out.println("No accounts currently pending");
			return customerAccountList;
		}
		
		else {
			customerAccountList = new ArrayList<>();
			
			do {
				current = new Account();
				current.setAccountNumber(rs.getInt("accountNumber"));
				current.setBalance(rs.getDouble("balance"));
				current.setStatus(rs.getString("status"));
				current.setAccountType(rs.getString("accountType"));
				current.setPrimaryAccountHolder(rs.getString("accountHolder"));
				customerAccountList.add(current);
			}while(rs.next());
		}

		ps.close();
		rs.close();
		return customerAccountList;
	}
}
