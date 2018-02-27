package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.util.ConnFactory;

public class AccountServices implements AccountDao {
	
	// Instantiate ConnFactory object
	ConnFactory cf = new ConnFactory();

	@Override
	public void insertNewAccount(int is_approved, double balance) throws SQLException {
		
		try(Connection conn = cf.getConnection()) {
			
			// create statement
			String sqlInsert = "INSERT INTO bank_account(ID, IS_APPROVED, BALANCE) ";
			sqlInsert += "VALUES(accountIdSequence.NEXTVAL, ?, ?)";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			
			// set sql statement values
			ps.setInt(1, is_approved);
			ps.setDouble(2, balance);
			
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted > 0) {
				// Success
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account getAccount(int id) throws SQLException {
		
		Account account = null;
		
		try(Connection conn = cf.getConnection()) {
			
			// Create get user query
			String sqlGet = "SELECT * FROM bank_account WHERE id = ?";
			
			// Instantiate ps
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			// Set username value in statement
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id2 = rs.getInt(1);
				int is_approved = rs.getInt(2);
				double balance = rs.getDouble(3);
				
				account = new Account(id2, is_approved, balance);
			}
			
			return account;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Account> retrieveAllAccounts() throws SQLException {
		
		List<Account> accounts = new ArrayList<Account>();
		Account account = null;
		
		try(Connection conn = cf.getConnection()) {
			
			// create statement
			String sqlGet = "SELECT * FROM bank_account ORDER BY id DESC";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				int is_approved = rs.getInt(2);
				double balance = rs.getDouble(3);
				account = new Account(id, is_approved, balance);
				accounts.add(account);
			}
			
			return accounts;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void updateAccount(Account account) throws SQLException {
		
		try(Connection conn = cf.getConnection()) {
			
			// Update query
			String sqlUpdate = "UPDATE bank_account SET id=?, is_approved=?, balance=?";
			sqlUpdate += "WHERE id = ?";
			
			// Instantiate PreparedStatement object
			PreparedStatement ps = conn.prepareStatement(sqlUpdate);
			
			// Set values
			ps.setInt(1, account.getId());
			ps.setInt(2, account.getIs_approved());
			ps.setDouble(3, account.getBalance());
			ps.setInt(4, account.getId());
			
			int rowsUpdate = ps.executeUpdate();
			if (rowsUpdate > 0) {
				// User updated
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAccount(int accountId) throws SQLException {
		
	}

	@Override
	public boolean pendingAccount() throws SQLException {
		
		try(Connection conn = cf.getConnection()) {
			
			// Create get user query
			String sqlGet = "SELECT * FROM bank_account WHERE is_approved = ?";
			
			// Instantiate ps
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			// Set username value in statement
			ps.setInt(1, 0);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			
			return false;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	


}
