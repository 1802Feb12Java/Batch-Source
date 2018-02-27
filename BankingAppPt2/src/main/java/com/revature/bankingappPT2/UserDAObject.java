package com.revature.bankingappPT2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class UserDAObject implements UserDAO {

	private Connection connection;
	
	public UserDAObject() throws SQLException {
		super();
		connection = ConnectionFactory.getConnection();
	}

	public void addAccountToTable(Account acc, Double balance) throws SQLException {
		String sql = "SELECT BANK_ACCOUNT_ACCOUNTID_GEN.NEXTVAL FROM DUAL";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			acc.setAccountID(rs.getInt(1));
		} else {
			System.out.println("Failed to get account ID.");
		}
		
		sql = "INSERT INTO BANKACCOUNT VALUES (?, ?, ?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setDouble(1, acc.getAccountID());
		ps.setInt(2, acc.getCustomerID());
		ps.setString(3, acc.getAccountName());
		ps.setString(4, acc.getAccountUser());
		ps.setDouble(5, balance);
		//ps.setInt(6,0) //Null or False.  Account hasn't been approved yet
		ps.setInt(6, 1);
		
		ps.execute();
	}
	public void addCustomerToTable(User newUser) throws SQLException {
		//INSERT INTO BANKCUSTOMER VALUES (5, 'j', 'b', 1);
		//SELECT BANK_CUSTOMER_CUSTOMERID_GEN.NEXTVAL FROM DUAL
		
		String sql = "SELECT BANK_CUSTOMER_CUSTOMERID_GEN.NEXTVAL FROM DUAL";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			newUser.setCustomerID(rs.getInt(1));
		} else {
			System.out.println("Failed to get Customer ID.");
		}
		
		sql = "INSERT INTO BANKCUSTOMER VALUES (?, ?, ?, ?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, newUser.getCustomerID());
		ps.setString(2, newUser.getUsername());
		ps.setString(3, newUser.getPassword());
		ps.setInt(4, newUser.getUserAccess());
		
		ps.execute();
		
		return;
	}

	public void updateAccountBalance(Double balance, Integer accountID) throws SQLException {
		String sql = "{call MODIFY_BALANCE(?,?)}";
		CallableStatement cs = connection.prepareCall(sql);
		
		cs.setDouble(1, balance);
		cs.setInt(2, accountID);
		cs.execute();
		
	}

	public void updateAccountUsernameAndPassword(String usernameOld, String usernameNew, String passwordNew) throws SQLException {
		String sql = "{call NEW_NAME_PASS(?,?,?)}";
		CallableStatement cs = connection.prepareCall(sql);
		
		if(passwordNew.equals("")) {
			String sql2 = "SELECT PASSWORD FROM BANKCUSTOMER WHERE USERNAME = ?";
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ps2.setString(1, usernameOld);
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				passwordNew = rs.getString(1);
			}
			
		}
		
		cs.setString(1, usernameOld);
		cs.setString(2, usernameNew);
		cs.setString(3, passwordNew);
		cs.execute();
		
	}
	public Boolean getAccountApproval(Account acc) throws SQLException {
		String sql = "SELECT APPROVED FROM BANKACCOUNT WHERE ACCOUNTID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, acc.getAccountID());
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt(1) == 1 ? true: false;
		} else {
			System.out.println("Failed to get Account Approval status.");
			return null;
		}
	}
	
	public User rebuildUserAccount(String username) throws SQLException {
		
		String sql = "SELECT * FROM BANKCUSTOMER WHERE USERNAME = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			User user = new Customer(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(1));
			//repopulate Accounts arrayList
			repopulateAccountsList(user);
			return user;
		} else {
			System.out.println("Failed to get Customer Object status.");
			return null;
		}
	
		
	}
	private void repopulateAccountsList(User user) throws SQLException {
		String sql = "SELECT * FROM BANKACCOUNT WHERE CUSTOMERID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, user.getCustomerID());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Boolean approved = rs.getInt(6)==1?true:false;
			Account acc = new Account(rs.getString(4), rs.getString(3), rs.getInt(1), rs.getDouble(5), approved, rs.getInt(2));
			user.addAccount(acc);
		}
		return;
	}
	public boolean userAccountExists(String username) throws SQLException{
		
		String sql = "SELECT USERNAME FROM BANKCUSTOMER WHERE USERNAME=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		//returns true if anything is returned
		if(rs.next()) {
			return true;
		}
		//else it returns false if result set was empty.
		return false;
	
	}
	public boolean validatePassword(String username, String password) throws SQLException {

		String sql = "SELECT PASSWORD FROM BANKCUSTOMER WHERE USERNAME=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			//System.out.println("PassWord exists");
			return rs.getString(1).equals(password) ? true: false;
		}
		System.out.println("Password was wrong");
		return false;
	}
	public void deleteAccount(Integer accountID) throws SQLException {
		String sql = "DELETE FROM BANKACCOUNT WHERE ACCOUNTID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, accountID);
		ps.execute();
	}
	public ArrayList<String[]> getAllUserInformation() throws SQLException {
		String sql = "SELECT * FROM BANKCUSTOMER";
		PreparedStatement ps = connection.prepareStatement(sql);
				
		ResultSet rs = ps.executeQuery();
		//returns true if anything is returned
		String str;
		ArrayList<String[]> returnList = new ArrayList<String[]>();
		while(rs.next()) {
			str = "Username: "+rs.getString(2)+
					", Password: "+rs.getString(3)+
					", Access Level: "+rs.getInt(4)+
					", CustomerID: "+rs.getInt(1);
			String[] addS = {rs.getString(2), str};
			returnList.add(addS);
			
		}
		
		return returnList;
	}
	public String getAccountDetails(String username) throws SQLException{
		
		String sql = "SELECT * FROM BANKACCOUNT WHERE USERNAME = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		String str = "";
		while(rs.next()) {
			str += "\nAccount Name: " + rs.getString(3)
			+" :: User: "+rs.getString(4)
			+" :: Balance: $"+rs.getDouble(5)
			+" :: AccountID: "+rs.getInt(1)
			+" :: CustomerID: "+rs.getInt(2);
		}
		
		return str;
		
	}
	public void deleteUserAccount(String username) throws SQLException{
		
		String sql = "{call DELETE_USER_FROM_BANK(?)}";
		CallableStatement cs = connection.prepareCall(sql);
		
		cs.setString(1, username);
		cs.execute();
		System.out.println("Account successfully deleted");
		
	}
}


























