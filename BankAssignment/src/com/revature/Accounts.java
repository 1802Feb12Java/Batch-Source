package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Accounts implements AccountsDAO {

	@Override
	public void viewAccount(Connection c, User usr) throws SQLException {
		PreparedStatement stmt = c.prepareStatement("select * from accounts where accounts.userid = ?");
		stmt.setInt(1, usr.getUserid());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			System.out.println("Account Number: "+ rs.getInt(1));
			System.out.println("Account Balance: "+ rs.getDouble(3));
		}
		
	}

	@Override
	public void deleteAccount(Connection c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deposit(Connection c, double amount, User usr) throws SQLException {
		if (amount <0)
		{
			System.err.println("You may not deposit a negative ammount");
			return;
		}
		
		PreparedStatement stmt = c.prepareStatement("select * from accounts where accounts.userid = ?");
		stmt.setInt(1, usr.getUserid());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			PreparedStatement deposit = c.prepareStatement("update accounts set balance = ? where accounts.userid= ?");
			deposit.setDouble(1, (rs.getDouble(3)+ amount));
			System.out.println("Your new balace is: "+ (rs.getDouble(3)+amount));
			deposit.setInt(2, usr.getUserid());
			deposit.executeQuery();
		}
	}

	@Override
	public void withdraw(Connection c, double amount, User usr) throws SQLException {
		if (amount <0)
		{
			System.err.println("You may not withdraw a negative ammount");
			return;
		}
		
		PreparedStatement stmt = c.prepareStatement("select * from accounts where accounts.userid = ?");
		stmt.setInt(1, usr.getUserid());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next())
		{
			PreparedStatement withdraw = c.prepareStatement("update accounts set balance = ? where accounts.userid= ?");
			withdraw.setDouble(1, (rs.getDouble(3)- amount));
			System.out.println("Your new balace is: "+ (rs.getDouble(3)-amount));
			withdraw.setInt(2, usr.getUserid());
			withdraw.executeQuery();
		}
	}

	@Override
	public void newAcct(Connection c, User user) throws SQLException {
		
		PreparedStatement stmt = c.prepareStatement("insert into accounts values (?,?,?)");
		PreparedStatement id = c.prepareStatement("SELECT ACCOUNTIDSEQ.NEXTVAL FROM DUAL");
		ResultSet rs = id.executeQuery();
		int myId = 0;
		if(rs.next())
			myId = rs.getInt(1);
				stmt.setInt(1, myId);
				stmt.setInt(2, user.getUserid());
				stmt.setDouble(3, 0);
	            stmt.executeQuery();
		
		
	}

}
