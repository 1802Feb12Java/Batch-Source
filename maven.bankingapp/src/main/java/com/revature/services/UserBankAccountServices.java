package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.UserBankAccount;
import com.revature.dao.UserBankAccountDao;
import com.revature.util.ConnFactory;

public class UserBankAccountServices implements UserBankAccountDao {
	
	// Instantiate CF object
	ConnFactory cf = new ConnFactory();

	@Override
	public void insertRecord(int customerId, int accountId) {
		
		try(Connection conn = cf.getConnection()) {
			
			// create statement
			String sqlInsert = "INSERT INTO bank_user_account(CUSTOMER_ID, ACCOUNT_ID) ";
			sqlInsert += "VALUES(?, ?)";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			
			// set sql statement values
			ps.setInt(1, customerId);
			ps.setInt(2, accountId);
			
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted > 0) {
				// Success
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public UserBankAccount getRecordByCustId(int cust_id) {
		
		UserBankAccount uba = null;
		int customer_id = 0;
		int account_id = 0;
		
		try(Connection conn = cf.getConnection()) {
			
			// create statement
			String sqlInsert = "SELECT * FROM bank_user_account WHERE customer_id = ?";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			
			// set sql statement values
			ps.setInt(1, cust_id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer_id = rs.getInt("CUSTOMER_ID");
				account_id = rs.getInt("ACCOUNT_ID");
				
				uba = new UserBankAccount(customer_id, account_id);
				return uba;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void deleteRecord(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserBankAccount> retrieveAllRecords() {
		
		List<UserBankAccount> ubas = new ArrayList<UserBankAccount>();
		UserBankAccount uba = null;
		
		try(Connection conn = cf.getConnection()) {
			
			int customer_id = 0;
			int account_id = 0;
			
			// retrieve record
			String sqlGet = "SELECT * FROM bank_user_account";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customer_id = rs.getInt(1);
				account_id = rs.getInt(2);
				
				uba = new UserBankAccount(customer_id, account_id);
				
				ubas.add(uba);
			}
			
			return ubas;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	public UserBankAccount getRecordExcludingCustId(int cust_id, int acc_id) {
		
		UserBankAccount uba = null;
		int customer_id = 0;
		int account_id = 0;
		
		try(Connection conn = cf.getConnection()) {
			
			// create statement
			String sqlInsert = "SELECT * FROM bank_user_account WHERE account_id = ? AND customer_id != ?";
			
			// instantiate ps object
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			
			// set sql statement values
			ps.setInt(1, acc_id);
			ps.setInt(2, cust_id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				customer_id = rs.getInt("CUSTOMER_ID");
				account_id = rs.getInt("ACCOUNT_ID");
				
				uba = new UserBankAccount(customer_id, account_id);
				return uba;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
