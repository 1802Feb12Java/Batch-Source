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
		String gen = "SELECT C_ID_GEN.NEXTVAL FROM DUAL";
		int i = 0;
		PreparedStatement ps = conn.prepareStatement(gen);
		ResultSet rs = ps.executeQuery();
		rs.next();
		i = rs.getInt(1);
		String flex = Integer.toString(i);
		c.setCustomerID(flex);
		String str = "{CALL NEW_BANKER(?,?,?,?,?,?,?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, i);
		cs.setString(2, c.getFirstName());
		cs.setString(3, c.getLastName());
		cs.setString(4, c.getUsername());
		cs.setString(5, c.getPassword());
		cs.setString(6, c.getPhoneNumber());
		cs.setString(7, c.getAddress());
		cs.execute();
//		str ="{CALL NEW_LOGIN(?,?,?,'CUSTOMER')}";
//		CallableStatement cs2 = conn.prepareCall(str);
//		cs2.setString(1, c.getCustomerID());
//		cs2.setString(2, c.getUsername());
//		cs2.setString(3, c.getPassword());
//		cs2.execute();
//		
//		
	}

	@Override
	public Customer getCustomer(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM BANK_CUSTOMER WHERE C_ID = ?");
		ps.setInt(1, id);
		ResultSet rs1 = ps.executeQuery();
		if(rs1.next()) {
		Customer c = new Customer(rs1.getString(4), rs1.getString(5), rs1.getString(2), rs1.getString(3));
		c.setCustomerID(rs1.getString(1));
		return c;
		}
		return null;
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(int id) throws SQLException {
		// TODO Auto-generated method stub
		String str = "{CALL DELETE_HELP(?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, id);
		cs.executeQuery();
		str = "{CALL DELETE_HELP2(?)}";
		cs = conn.prepareCall(str);
		cs.setInt(1, id);
		cs.executeQuery();
		
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM BANK_CUSTOMER");
		ResultSet rs1 = ps.executeQuery();
		ArrayList<Customer> thisguy = new ArrayList<Customer>();
		PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM ACCOUNTS");
		ResultSet rs2 = ps2.executeQuery();
		while(rs1.next()) {
			Customer c = new Customer(rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5));
			c.setCustomerID(rs1.getString(1));
			c.setAddress(rs1.getString(7));
			c.setPhoneNumber(rs1.getString(6));
			System.out.println(c.toString());
			thisguy.add(c);
		}
		while(rs2.next()) {
			for(int i = 0; i < thisguy.size(); i++) {
				if(rs2.getString(2).equals(thisguy.get(i).getCustomerID())) {
					thisguy.get(i).setAccountBal(rs2.getDouble(4));
				}
			}
		}
		return null;
	}

	public void approveCustomer(int x) throws SQLException{
		String gen = "SELECT A_ID_GEN.NEXTVAL FROM DUAL";
		int i = 0;
		PreparedStatement ps = conn.prepareStatement(gen);
		ResultSet rs = ps.executeQuery();
		rs.next();
		i = rs.getInt(1);
		
		String str = "{CALL NEW_ACCT(?, ?)}";
		CallableStatement cs = conn.prepareCall(str);
		cs.setInt(1, x);
		cs.setInt(2, i);
		cs.execute();
	}
	
	public String approveCustomer1() throws SQLException {
		String str = "SELECT C_ID, FNAME, LNAME FROM BANK_CUSTOMER WHERE APP_STATUS = '0'";
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
	
	public Customer login(String u, String p) throws SQLException {
		String str = "SELECT * FROM BANK_CUSTOMER WHERE USERNAME=? AND PASSWORD=?"; //gets the info for the login input
		PreparedStatement ps = conn.prepareStatement(str);
		ps.setString(1, u);
		ps.setString(2, p);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Customer c = new Customer(rs.getString(4), rs.getString(5), rs.getString(2), rs.getString(3));
			c.setCustomerID(rs.getString(1));
			c.setAddress(rs.getString(6));
			c.setPhoneNumber(rs.getString(7)); //builds the customer
			return c;
		}
		return null; //in case login info is faulty
	}
	
	public void showAccounts(Customer c) throws SQLException {
		String str = "SELECT * FROM ACCOUNTS WHERE C_ID=? OR C_ID2=?";
		PreparedStatement ps = conn.prepareStatement(str);
		int i = Integer.parseInt(c.getCustomerID()); 		//converts the string form of cid to an int
		ps.setInt(1, i);
		ps.setInt(2, i);
		ResultSet rs = ps.executeQuery();
		String c2 = ", ";
		while(rs.next()) {
			if(rs.getString(3) != "null") {
				c2 = rs.getString(3) + ", ";
			}
			String output = "[Account: " + rs.getString(1) + ", CustomersIDs: " + rs.getString(2) + ", "
					+ c2 + "Balance: " + rs.getString(4) + "]";
			System.out.println(output);
		}
	}
	
}
