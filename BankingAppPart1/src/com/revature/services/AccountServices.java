package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.accounts.Account;
import com.revature.dao.AccountDAO;
import com.revature.users.User;
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

	public Account getAccount(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		
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

		return pendingList;
	}

}
