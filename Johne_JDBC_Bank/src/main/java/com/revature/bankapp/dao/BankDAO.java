package com.revature.bankapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.bankapp.db.ConnFactory;
import com.revature.bankapp.model.BankAccount;

public class BankDAO {

	public static void deposit(BankAccount account) throws SQLException {
		String updateString = "UPDATE BANKACCOUNT SET "
				+ "BALANCE = ? WHERE BANKACCOUNT_ID = ?";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps = conn.prepareStatement(updateString);
		
		ps.setDouble(1, account.getBalance());
		ps.setInt(2, account.getAccountID());
		ps.executeQuery();
		
	}

	public static void withdraw(BankAccount account) throws SQLException {
		String updateString = "UPDATE BANKACCOUNT SET "
				+ "BALANCE = ? WHERE BANKACCOUNT_ID = ?";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(updateString);
		
		ps.setDouble(1, account.getBalance());
		ps.setInt(2, account.getAccountID());
		ps.executeQuery();
		
	}

}
