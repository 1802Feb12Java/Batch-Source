package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.accounts.CustomerAccount;
import com.revature.utility.ConnectionFactory;

public  class CustomerAccountDAOImp implements CustomerAccountDAO{
	
	//get all customer accounts from database, and return a list of customers
	@Override
	public ArrayList<CustomerAccount> getCustomers() {
		// TODO Auto-generated method stub
		
		ArrayList<CustomerAccount> customers = new 	ArrayList<CustomerAccount>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM CustomerAccount");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CustomerAccount customer = new CustomerAccount();
				
				customer.setCustomerID(rs.getInt(1));
				customer.setUsername(rs.getString(2));
				customer.setPassword(rs.getString(3));
				customer.setFirstName(rs.getString(4));
				customer.setLastName(rs.getString(5));
				customer.setAge(rs.getInt(6));
				customer.setAccountNumber(rs.getInt(7));
				customer.setTotalBalance(rs.getDouble(8));
				customer.setCheckingBalance(rs.getDouble(9));
				customer.setSavingsBalance(rs.getDouble(10));
				customer.setActive(rs.getBoolean(11));
				
				customers.add(customer);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: getCustomers");
		}
				
		return customers;
	}
	
	public CustomerAccount getCustomer(String username) {
		CustomerAccount customer = new CustomerAccount();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM CustomerAccount WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				customer.setCustomerID(rs.getInt(1));
				customer.setUsername(rs.getString(2));
				customer.setPassword(rs.getString(3));
				customer.setFirstName(rs.getString(4));
				customer.setLastName(rs.getString(5));
				customer.setAge(rs.getInt(6));
				customer.setAccountNumber(rs.getInt(7));
				customer.setTotalBalance(rs.getDouble(8));
				customer.setCheckingBalance(rs.getDouble(9));
				customer.setSavingsBalance(rs.getDouble(10));
				customer.setActive(rs.getBoolean(11));
				
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//add customer to the database
	@Override
	public void addCustomer(CustomerAccount customer) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO CustomerAccount Values(?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, customer.getCustomerID());
			ps.setString(2, customer.getUsername());
			ps.setString(3, customer.getPassword());
			ps.setString(4, customer.getFirstName());
			ps.setString(5, customer.getLastName());
			ps.setInt(6, customer.getAge());
			ps.setInt(7, customer.getAccountNumber());
			ps.setDouble(8, customer.getTotalBalance());
			ps.setDouble(9, customer.getCheckingBalance());
			ps.setDouble(10, customer.getSavingsBalance());
			ps.setBoolean(11, customer.isActive());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteCustomer(CustomerAccount customer) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void updateCustomer(CustomerAccount customer) {
		// TODO Auto-generated method stubs
		
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE customerAccount SET TotalBalance = ?, CheckingBalance = ?, SavingsBalance = ?, Active = ?"
					+ " WHERE username = ?");
			
			ps.setDouble(1, customer.getTotalBalance());
			ps.setDouble(2, customer.getCheckingBalance());
			ps.setDouble(3, customer.getSavingsBalance());
			ps.setBoolean(4, customer.isActive());
			ps.setString(5, customer.getUsername());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
