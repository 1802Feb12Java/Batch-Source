package com.revature.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.revature.model.Admin;
import com.revature.model.BankAccount;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.model.UserType;

public class UserDAOImpl implements UserDAO {
	private Connection conn;
	
	public UserDAOImpl() throws ClassNotFoundException, SQLException {
		conn = ConnectionManager.getInstance().getConnection();
	}
	
	@Override
	public boolean isPasswordValid(String username, String str) throws SQLException {
		// Create query.
		String query = "{call VERIFYPW[(?, ?, ?)]}";
		CallableStatement verifyPassSQL = conn.prepareCall(query);
		
		// Set parameters.
		verifyPassSQL.registerOutParameter(1, Types.BOOLEAN);
		verifyPassSQL.setString(2, username);
		verifyPassSQL.setString(3, str);
		
		// Run stored procedure.
		verifyPassSQL.executeUpdate();
		
		// get<type>() to get items.
		Boolean verified = verifyPassSQL.getBoolean(1);
		
		// Close query.
		verifyPassSQL.close();
		
		return verified;
	}

	@Override
	public void getInfo(User usr) throws SQLException {
		// Prepare query
		String query = "SELECT USERID, FIRSTNAME, LASTNAME, USERTYPE FROM USERS WHERE USERNAME=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, usr.getUserName());
		
		// Run query.
		ResultSet rs = ps.executeQuery();
		
		// Get data from result set and update usr with it.
		rs.next();
		usr.setUserId(rs.getInt("USERID"));
		usr.setFirstName(rs.getString("FIRSTNAME"));
		usr.setLastName(rs.getString("LASTNAME"));
		
		int usrtype = rs.getInt("USERTYPE");
		ps.close();
		
		// Perform additional update if user is a customer.
		if(usrtype == UserType.CUSTOMER.getId()) {
			Customer cust = (Customer)usr;
			try {
				BankAccountDAO acctDao = new BankAccountDAOImpl();
				List<BankAccount> acctList = acctDao.getAllUserAccounts(cust);
				acctList.forEach((BankAccount acct) -> {
					cust.getAccounts().put(acct.getAccountId(), acct);
				});
			} catch (ClassNotFoundException e) {
			}
		}
	}

	@Override
	public void updateInfo(User usr) throws SQLException {
		String query = "UPDATE USERS SET FIRSTNAME=?, LASTNAME=? WHERE USERID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, usr.getFirstName());
		ps.setString(2, usr.getLastName());
		ps.setInt(3, usr.getUserId());
		
		ps.executeUpdate();
		ps.close();
		
		// Update accounts
		if(usr instanceof Customer) {
			Customer cust = (Customer)usr;
			Collection<BankAccount> acctList = cust.getAccounts().values();
			try {
				BankAccountDAO acctDao = new BankAccountDAOImpl();
				acctList.forEach((BankAccount acct) -> {
					try {
						acctDao.updateAccount(acct);
					} catch (SQLException e) {
					}
				});
			} catch (ClassNotFoundException e) {
			}
		}
	}

	@Override
	public void deleteUser(User usr) throws SQLException {
		String query = "DELETE FROM USERS WHERE USERID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, usr.getUserId());
		
		ps.executeUpdate();
		
		ps.close();
	}

	@Override
	public void updatePassword(User usr, String pw) throws SQLException {
		String query = "UPDATE USERS SET PASSHASH=? WHERE USERID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, pw);
		ps.setInt(2, usr.getUserId());
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		List<User> userList = new ArrayList<>();
		String query = "SELECT USERID, USERNAME, USERTYPE, FIRSTNAME, LASTNAME FROM USERS";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int type = rs.getInt("USERTYPE");
			UserType uType = UserType.fromId(type);
			User usr = null;
			
			switch(uType) {
			case ADMIN:
				usr = new Admin();
				break;
			case EMPLOYEE:
				usr = new Employee();
				break;
			case CUSTOMER:
				usr = new Customer();
				break;
			}
			
			usr.setUserId(rs.getInt("USERID"));
			usr.setUserName(rs.getString("USERNAME"));
			usr.setFirstName(rs.getString("FIRSTNAME"));
			usr.setLastName(rs.getString("LASTNAME"));
			
			if(usr instanceof Customer) {
				Customer cust = (Customer)usr;
				try {
					BankAccountDAO acctDao = new BankAccountDAOImpl();
					List<BankAccount> acctList = acctDao.getAllUserAccounts(cust);
					acctList.forEach((BankAccount acct) -> {
						cust.getAccounts().put(acct.getAccountId(), acct);
					});
				} catch (ClassNotFoundException e) {
				}
			}
			
			userList.add(usr);
		}
		
		ps.close();
		
		return userList;
	}

	@Override
	public User createUser(User usr, String pw) throws SQLException {
		String query = "{call REGISTER_USER[(?, ?, ?, ?, ?, ?)]}";
		CallableStatement cs = conn.prepareCall(query);
		// UID OUT NUMBER, UNAME IN VARCHAR2, UTYPE IN NUMBER, 
		// FNAME IN VARCHAR2, LNAME IN VARCHAR2, PWHASH IN VARCHAR2
		
		UserType uType = null;
		
		if(usr instanceof Customer) {
			uType = UserType.CUSTOMER;
		} else if(usr instanceof Admin) {
			uType = UserType.ADMIN;
		} else if(usr instanceof Employee) {
			uType = UserType.EMPLOYEE;
		}
		
		cs.registerOutParameter(1, Types.NUMERIC);
		cs.setString(2, usr.getUserName());
		cs.setInt(3, uType.getId());
		cs.setString(4, usr.getFirstName());
		cs.setString(5, usr.getLastName());
		cs.setString(6, pw);
		
		cs.executeUpdate();
		
		usr.setUserId(cs.getInt(1));
		
		cs.close();
		return usr;
	}

	
	
	@Override
	public User getUserById(int id) throws SQLException {
		User usr;
		String query = "SELECT USERID, USERNAME, FIRSTNAME, LASTNAME, USERTYPE FROM USERS WHERE USERID=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int type = rs.getInt("USERTYPE");
			UserType uType = UserType.fromId(type);
			usr = null;
			
			switch(uType) {
			case ADMIN:
				usr = new Admin();
				break;
			case EMPLOYEE:
				usr = new Employee();
				break;
			case CUSTOMER:
				usr = new Customer();
				break;
			}
			
			usr.setUserId(rs.getInt("USERID"));
			usr.setUserName(rs.getString("USERNAME"));
			usr.setFirstName(rs.getString("FIRSTNAME"));
			usr.setLastName(rs.getString("LASTNAME"));
			
			if(usr instanceof Customer) {
				Customer cust = (Customer)usr;
				try {
					BankAccountDAO acctDao = new BankAccountDAOImpl();
					List<BankAccount> acctList = acctDao.getAllUserAccounts(cust);
					acctList.forEach((BankAccount acct) -> {
						cust.getAccounts().put(acct.getAccountId(), acct);
					});
				} catch (ClassNotFoundException e) {
				}
			}
			
		} else {
			usr =  null;
		}
		
		return usr;
	}

	@Override
	public String getPWHash(User usr) throws SQLException {
		String query = "SELECT PASSHASH FROM USERS WHERE USERNAME=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, usr.getUserName());
		
		ResultSet rs = ps.executeQuery();
		String pwHash = null;
		if(rs.next()) {
			pwHash = rs.getString("PASSHASH");
		}
		
		return pwHash;
	}

	@Override
	public User getUserByUsername(String username) throws SQLException {
		User usr;
		String query = "SELECT USERID, USERNAME, FIRSTNAME, LASTNAME, USERTYPE FROM USERS WHERE USERNAME=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int type = rs.getInt("USERTYPE");
			UserType uType = UserType.fromId(type);
			usr = null;
			
			switch(uType) {
			case ADMIN:
				usr = new Admin();
				break;
			case EMPLOYEE:
				usr = new Employee();
				break;
			case CUSTOMER:
				usr = new Customer();
				break;
			}
			
			usr.setUserId(rs.getInt("USERID"));
			usr.setUserName(rs.getString("USERNAME"));
			usr.setFirstName(rs.getString("FIRSTNAME"));
			usr.setLastName(rs.getString("LASTNAME"));
			
			if(usr instanceof Customer) {
				Customer cust = (Customer)usr;
				try {
					BankAccountDAO acctDao = new BankAccountDAOImpl();
					List<BankAccount> acctList = acctDao.getAllUserAccounts(cust);
					acctList.forEach((BankAccount acct) -> {
						cust.getAccounts().put(acct.getAccountId(), acct);
					});
				} catch (ClassNotFoundException e) {
				}
			}
			
		} else {
			usr =  null;
		}
		
		return usr;
	}
}
