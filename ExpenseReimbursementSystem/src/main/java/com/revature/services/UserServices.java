package com.revature.services;

import java.sql.*;
import java.util.ArrayList;
import com.revature.bean.User;
import com.revature.dao.UserDAO;
import com.revature.util.ConnManager;

/* UserServices
 * The services class that works with the user class and table in the DB
 * have CRUD operation implementations from the UserDAO
 * have multiple read functionalities
 */
public class UserServices extends LookupServices implements UserDAO{
	//private connection for this Service class
	private Connection conn;
	
    //insert/create new record on the DB
    //register employee, optional
    public void insertNewUser(User u) throws ClassNotFoundException, SQLException{
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//use stored procedure
    	CallableStatement callInsert= null;
    	String insertString = "{CALL insert_user(?,?,?,?,?,?)";
    	callInsert = this.conn.prepareCall(insertString);
    	
    	//set up fields
    	callInsert.setString(1, u.getUsername());
    	callInsert.setString(2, u.getPassword());
    	callInsert.setString(3, u.getFirstName());
    	callInsert.setString(4, u.getLastName());
    	callInsert.setString(5, u.getEmail());
    	callInsert.setInt(6, this.lookupRoleID(u.getRole()));
    	
    	//execute delete
    	callInsert.executeUpdate();
    }

    //read/select from the DB
    //used for view all employees
    public ArrayList<User> getUsersByRole(String role) throws ClassNotFoundException, SQLException{
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement selectPrepared = null;
    	String selectStatement = "SELECT * FROM ers_users WHERE ur_id=?";
    	
    	//lookup Role using lookupServices
    	int rID = this.lookupRoleID(role);
    	selectPrepared = this.conn.prepareStatement(selectStatement);
    	selectPrepared.setInt(1, rID);
    	
    	ResultSet rs = selectPrepared.executeQuery();
    	
    	ArrayList<User> usersByRole = new ArrayList<User> ();
    	while(rs.next()) {
    		User uToAdd = this.rsToUser(rs);
    		usersByRole.add(uToAdd);
    	}

    	return usersByRole;
    }
    
    //view single employee/user
    public User getUserByID(int uID) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    
    	PreparedStatement selectPrepared = null;
    	String selectStatement = "SELECT * FROM ers_users WHERE u_id=?";
    	
    	selectPrepared = this.conn.prepareStatement(selectStatement);
    	selectPrepared.setInt(1, uID);
    	
    	ResultSet rs = selectPrepared.executeQuery();
    	if(rs.next()) {
    		return this.rsToUser(rs);
    	}
    	return null;
    	
    }
    //used to register, optional
    public User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
    	//get connection from ConnManager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement selectPrepared = null;
    	String selectStatement = "SELECT * FROM ers_users WHERE u_username=?";
    	
    	selectPrepared = this.conn.prepareStatement(selectStatement);
    	selectPrepared.setString(1,username);
    	
    	ResultSet rs = selectPrepared.executeQuery();
    	if(rs.next()) {
    		return this.rsToUser(rs);
    	}
    	return null;
    }
    //used for login
    public User getUserForLogin(String username, String password) throws ClassNotFoundException, SQLException{
    	//get connection
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	PreparedStatement selectPrepared = null;
    	String selectStatment = "SELECT * FROM ers_users WHERE u_username=? AND u_password=?";
//    	String selectStatment = "SELECT * FROM ers_users WHERE u_username=\'khsieh\' AND u_password=\'pass\'";
    	selectPrepared = this.conn.prepareStatement(selectStatment);
    	selectPrepared.setString(1, username);
    	selectPrepared.setString(2, password);
    	
    	ResultSet rs = selectPrepared.executeQuery();
    	if(rs.next()) {
    		return this.rsToUser(rs);
    	}
    	return null;
    	
    }
    //update DB record
    //update employee information
    public void updateUser(User u) throws ClassNotFoundException, SQLException {
    	this.conn = ConnManager.getInstance().getConnection();
    	//user stored procedure
    	//use callable statements    	
    	CallableStatement callUpdate = null;
    	String updateString = "{CALL update_user(?,?,?,?,?,?,?)";
    	callUpdate = this.conn.prepareCall(updateString);
    	
    	//set all fields
    	callUpdate.setInt(1, u.getUserID());
    	callUpdate.setString(2, u.getUsername());
    	callUpdate.setString(3, u.getPassword());
    	callUpdate.setString(4, u.getFirstName());
    	callUpdate.setString(5, u.getLastName());
    	callUpdate.setString(6, u.getEmail());
    	callUpdate.setInt(7, this.lookupRoleID(u.getRole()));
    	
    	//execute update
    	callUpdate.executeUpdate();
    }

    //delete DB record
    public void deleteUser(User u) throws ClassNotFoundException, SQLException {
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	//use stored procedure
    	CallableStatement callDelete = null;
    	String deleteString = "{CALL delete_user(?)";
    	callDelete = this.conn.prepareCall(deleteString);
    	
    	//set up fields
    	callDelete.setInt(1, u.getUserID());
    	
    	//execute delete
    	callDelete.executeUpdate();
    }
}