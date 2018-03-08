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
    	
        PreparedStatement insertPrepared = null;
        String insertStatement = "INSERT INTO users(u_username,u_password,u_firstname,u_lastname,u_email,ur_id) VALUES (?,?,?,?,?,?)";
        insertPrepared = this.conn.prepareStatement(insertStatement);
        //inert the values
        //username and password
        insertPrepared.setString(1, u.getUsername());
        insertPrepared.setString(2, u.getPassword());
        //first and last names
        insertPrepared.setString(3, u.getFirstName());
        insertPrepared.setString(4, u.getLastName());
        //email
        insertPrepared.setString(5, u.getEmail());
        //user role
        //convert role to id
        int urID = this.lookupRoleID(u.getRole());
        insertPrepared.setInt(6, urID);
        
        //execute the insert
        insertPrepared.executeUpdate();
        //close connection
        conn.close();
    }

    //read/select from the DB
    //used for view all employees
    public ArrayList<User> getUserByRole(String role) throws ClassNotFoundException, SQLException{
    	//get connection from Conn Manager
    	this.conn = ConnManager.getInstance().getConnection();
    	
    	
    } 
    //view single employee/user
    public User getUserByID(int uID) throws ClassNotFoundException, SQLException {

    }
    //used to register, optional
    public User getUserByUsername(String username) throws ClassNotFoundException, SQLException {

    }
    //used for login
    public User getUserByUsername(String username, String password) throws ClassNotFoundException, SQLException{

    }
    //update DB record
    //update employee information
    public void updateUser(User u) throws ClassNotFoundException, SQLException {

    }

    //delete DB record
    public void deleteUser(User u) throws ClassNotFoundException, SQLException {

    }
}