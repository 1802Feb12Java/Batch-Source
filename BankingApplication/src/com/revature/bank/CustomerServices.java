package com.revature.bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerServices implements CustomerDAO{

	ConnectionFlexion cf = new ConnectionFlexion();
	Connection conn = cf.getConnection();
	
	@Override
	public void addCustomer(Customer c) throws SQLException{
		String gen = "SELECT C_ID_GEN FROM DUAL";
		int i = 0;
		PreparedStatement ps = conn.prepareStatement(gen);
		ResultSet rs = ps.executeQuery();
		i = rs.getInt(1);
		String str = "EXECUTE NEW_BANKER(?,?,?,?,?,?,?);";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, i);
		cs.setString(2, c.getFirstName());
		cs.setString(3, c.getLastName());
		cs.setString(4, c.getUsername());
		cs.setString(5, c.getPassword());
		cs.setString(6, c.getAddress());
		cs.setString(7, c.getPhoneNumber());
		cs.execute();
		
		
		
	}

	@Override
	public Customer getCustomer(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM BANK_CUSTOMER WHERE C_ID = ?;");
		ps.setInt(1, id);
		ResultSet rs1 = ps.executeQuery();
		Customer c = new Customer(rs1.getString(4), rs1.getString(5), rs1.getString(2), rs1.getString(3));
		c.setCustomerID(rs1.getInt(1));
		return c;
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = conn.prepareStatement("DELETE FROM BANK_CUSTOMER WHERE C_ID = ?;");
		ps.setInt(1, id);
		ps.executeQuery();
		
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM BANK_CUSTOMER;");
		ResultSet rs1 = ps.executeQuery();
		PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM ACCOUNTS;");
		ResultSet rs2 = ps2.executeQuery();
		while(rs1.next()) {
			Customer c = new Customer(rs1.getString(4), rs1.getString(5), rs1.getString(2), rs1.getString(3));
			c.setCustomerID(rs1.getInt(1));
			c.setAddress(rs1.getString(6));
			c.setPhoneNumber(rs1.getString(7));
			System.out.println(c.toString());
		}
		return null;
	}

	public void approveCustomer(int x) throws SQLException{
		String gen = "SELECT A_ID_GEN FROM DUAL";
		int i = 0;
		PreparedStatement ps = conn.prepareStatement(gen);
		ResultSet rs = ps.executeQuery();
		i = rs.getInt(1);
		
		String str = "EXECUTE NEW_ACCT(?, ?);";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, x);
		cs.setInt(2, i);	
	}
	
	public String approveCustomer1() throws SQLException {
		String str = "SELECT C_ID, FNAME, LNAME FROM BANK_CUSTOMER WHERE APP_STATUS = '0';";
		PreparedStatement ps = conn.prepareStatement(str);
		ResultSet rs = ps.executeQuery();
		String output = "";
		while(rs.next()) {
			String i = rs.getString(1);
			String fn = rs.getString(2);
			String ln = rs.getString(3);
			output += i + ", " + fn + " " + ln + "\n";
		}
		if(output == "") {
			output = "There are no applicants pending approval.";
		}
		return output;
	}
	
}
