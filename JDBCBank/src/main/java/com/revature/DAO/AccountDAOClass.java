package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOClass implements AccountDAO {
	private Connection conn;

	@Override
	public ResultSet readAllAccounts() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select * from accounts");
		return ps.executeQuery();
	}
	
	@Override
	public int insertAccount(int user_id, double balance, boolean approved) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Insert into accounts (account_id, user_id, balance, approved) values (?,?,?,?)");
		PreparedStatement idps = conn.prepareStatement("select accountidsequence.nextval from dual");
		ResultSet rs = idps.executeQuery();
		int myID = 0;
		if(rs.next())
			myID = rs.getInt(1);
		ps.setInt(1, myID);
		ps.setInt(2, user_id);
		ps.setDouble(3, balance);
		if(approved) {
			ps.setInt(4, 1);
		}
		else {
			ps.setInt(4, 0);
		}
		ps.executeQuery();  
		
		return myID;
	}

	@Override
	public int insertJointAccount(int user_id, int second_user_id, double balance, boolean approved) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("Insert into accounts values (?,?,?,?,?)");
		PreparedStatement idps = conn.prepareStatement("select accountidsequence.nextval from dual");
		ResultSet rs = idps.executeQuery();
		int myID = 0;
		if(rs.next())
			myID = rs.getInt(1);
		ps.setInt(1, myID);
		ps.setInt(2, user_id);
		ps.setInt(3, second_user_id);
		ps.setDouble(4, balance);
		if(approved) {
			ps.setInt(5, 1);
		}
		else {
			ps.setInt(5, 0);
		}
		ps.executeQuery();
		
		return myID;
	}
	
	@Override
	public ResultSet readAccount(int account_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("select * from usertable where user_id = ?");
		ps.setInt(1, account_id);

		return ps.executeQuery();
	}
	
	@Override
	public void approveAccount(int account_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update accounts set approved = 1 where account_id = ?");
		ps.setInt(1, account_id);
		ps.executeQuery();
	}

	@Override
	public void decreaseBalance(int account_id, int amount) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update accounts set balance = balance-? where account_id = ?");
		ps.setInt(1, amount);
		ps.setInt(2, account_id);
		ps.executeQuery();
	}

	@Override
	public void increaseBalance(int account_id, int amount) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update accounts set balance = balance+? where account_id = ?");
		ps.setInt(1, amount);
		ps.setInt(2, account_id);
		ps.executeQuery();
	}
	
	@Override
	public void transferMoney(int withdrawAccountID, int depositAccountID, int amount) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("update accounts set balance = balance-? where account_id = ?");
		ps.setInt(1, amount);
		ps.setInt(2, withdrawAccountID);
		ps.executeQuery();
		
		ps = conn.prepareStatement("update accounts set balance = balance+? where account_id = ?");
		ps.setInt(1, amount);
		ps.setInt(2, depositAccountID);
		ps.executeQuery();
	}

	@Override
	public void deleteAccount(int account_id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("delete from accounts where account_id = ?;");
		ps.setInt(1, account_id);
		ps.executeQuery();
	}
	
	public AccountDAOClass(Connection conn){
		this.conn = conn;
	}

}
