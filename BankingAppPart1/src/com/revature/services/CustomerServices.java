package com.revature.services;

import java.sql.*;
import java.util.List;

import com.revature.dao.CustomerDAO;
import com.revature.users.Customer;
import com.revature.util.ConnFactory;

public class CustomerServices implements CustomerDAO {

	ConnFactory cf = new ConnFactory();
	Connection conn = cf.getConnection();

	public void addCustomer(Customer customer) throws SQLException {
		String accHolder = "N";
		
		if(customer.isAccountHolder()) {
			accHolder = "Y";
		}
		
		String sql = "INSERT INTO CUSTOMER(customerID, userName, pass, firstName, lastName, streetAddress, city, state, phoneNumber, accountHolder) VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		//TODO: Change first variable to sequence number
		ps.setInt(1, 1);
		ps.setString(2, customer.getUserName());
		ps.setString(3, customer.getPassword());
		ps.setString(4, customer.getFirstName());
		ps.setString(5, customer.getLastName());
		ps.setString(6, customer.getStreetAddress());
		ps.setString(7, customer.getCity());
		ps.setString(8, customer.getState());
		ps.setString(9, customer.getPhoneNumber());
		ps.setString(10, accHolder);
		
		ps.executeUpdate();
	}

	public Customer getCustomer(String uName, Customer customer) throws SQLException {
		String sql = "SELECT * FROM CUSTOMER WHERE userName = " + uName;
		ResultSet rs = (ResultSet) conn.prepareCall(sql);
		
		customer.setUserName(rs.getString("userName"));
		customer.setPassword(rs.getString("pass"));
		customer.setFirstName(rs.getString("firstName"));
		customer.setLastName(rs.getString("lastName"));
		customer.setStreetAddress(rs.getString("streetAddress"));
		customer.setCity(rs.getString("city"));
		customer.setState(rs.getString("state"));
		customer.setPhoneNumber(rs.getString("phoneNumber"));
		
		if(rs.getString("accountHolder") == "Y") {
			customer.setAccountHolder(true);
		}
		
		else {
			customer.setAccountHolder(false);
		}
		
		return customer;
	}

	public void updateCustomer(Customer customer) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void deleteCustomer(Customer customer) throws SQLException {
		String uName = customer.getUserName();
		String sql = "DELETE * FROM CUSTOMER WHERE USERNAME = " + uName;
	}

	public List<Customer> getAllCustomers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
