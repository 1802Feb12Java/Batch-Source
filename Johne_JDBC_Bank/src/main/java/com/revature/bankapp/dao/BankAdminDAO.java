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
import com.revature.bankapp.model.BankAdmin;
import com.revature.bankapp.model.Customer;
/**
 * 
 * @author johne
 *
 */
public class BankAdminDAO {

	public static List<Customer> viewAllCustomers() throws SQLException {
		String allCustomerString = "SELECT CUSTOMER_ID, CUSTOMER_USERNAME, CUSTOMER_PASSWORD"
				+ " FROM CUSTOMER";
		List<Customer> customerList = new ArrayList<Customer>();
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps = conn.prepareStatement(allCustomerString);
		ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
				Customer c = new Customer();
				c.setCustomerID(rs.getInt("CUSTOMER_ID"));
				c.setCustUsername(rs.getString("CUSTOMER_USERNAME"));
				c.setCustPW(rs.getString("CUSTOMER_USERNAME"));
				
				customerList.add(c);
			}
		return customerList;
		
	}
	
	/**
	 * view a list of admin
	 * @return
	 * @throws SQLException
	 */
	public static List<BankAdmin> viewAllAdmins() throws SQLException {
		String allAdminString = "SELECT BANKADMIN_ID, BANKADMIN_USERNAME, BANKADMIN_PASSWORD"
				+ " FROM BANKADMIN";
		List<BankAdmin> adminList = new ArrayList<BankAdmin>();
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps = conn.prepareStatement(allAdminString);
		ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
				BankAdmin b = new BankAdmin();
				b.setAdminID(rs.getInt("BANKADMIN_ID"));
				b.setAdminUsername(rs.getString("BANKADMIN_USERNAME"));
				b.setAdminPW(rs.getString("BANKADMIN_PASSWORD"));
				
				adminList.add(b);
			}
		return adminList;
		
	}
	
	/**
	 * create a new bank admin
	 * @return
	 * @throws SQLException
	 */
	public static BankAdmin createBankAdmin(BankAdmin admin) throws SQLException {
		String insertString = "INSERT INTO BANKADMIN (BANKADMIN_ID, BANKADMIN_USERNAME, BANKADMIN_PASSWORD)"
				+ " VALUES (?, ?, ?)";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(insertString);
		ps.setInt(1, admin.getAdminID());
		ps.setString(2, admin.getAdminUsername());
		ps.setString(3, admin.getAdminPW());
		
		return admin;
	}

	/**
	 * create a new customer
	 * @param customer
	 * @return
	 * @throws SQLException
	 */
	public static Customer createCustomer(Customer customer) throws SQLException {
		String insertString = "INSERT INTO CUSTOMER (CUSTOMER_ID, CUSTOMER_USERNAME, CUSTOMER_PASSWORD)"
				+ " VALUES (?, ?, ?)";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(insertString);
		Customer cust = new Customer();
		ps.setInt(1, cust.getCustomerID());
		ps.setString(2, cust.getCustUsername());
		ps.setString(3, cust.getCustPW());
		
		return cust;
	}

	/**
	 * update customer username
	 * @param customer
	 * @throws SQLException
	 */
	public static void updateCustUsername(String username, int id) throws SQLException {
		String updateString = "UPDATE CUSTOMER SET"
				+ " CUSTOMER_USERNAME = ? WHERE CUSTOMER_ID = ?";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(updateString);
		
		ps.setString(1, username);
		ps.setInt(2, id);
		
		ps.executeQuery();
		
	}
	
	/**
	 * update customer password
	 * update customer password
	 * @param customer
	 * @throws SQLException
	 */
	public static void updateCustPassword(String pw, int id) throws SQLException {
		String updateString = "UPDATE CUSTOMER SET"
				+ " CUSTOMER_PASSWORD = ? WHERE CUSTOMER_ID = ?";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(updateString);
		
		ps.setString(1, pw);
		ps.setInt(2, id);
		ps.executeQuery();
	}

	/**
	 * delete a customer
	 * @param customer
	 * @throws SQLException 
	 */
	public static void deleteCustomer(int id) throws SQLException {
		String deleteString = "DELETE FROM CUSTOMER "
				+ "WHERE CUSTOMER_ID = ?";
		Connection conn = DriverManager.getConnection(ConnFactory.url, ConnFactory.user, ConnFactory.pw);
		PreparedStatement ps =  conn.prepareStatement(deleteString);
		
		ps.setInt(1, id);
		ps.executeQuery();
		
	}
	
	
}
