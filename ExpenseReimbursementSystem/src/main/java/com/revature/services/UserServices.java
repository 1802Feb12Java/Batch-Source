package com.revature.services;

import com.revature.bean.*;
import com.revature.dao.UserDAO;
import java.sql.*;

/* UserServices
 * The services class that works with the user class and table in the DB
 * have CRUD operation implementations from the UserDAO
 * have multiple read functionalities
 */
public class UserServices extends LookupServices implements UserDAO{

    //insert/create new record on the DB
    //register employee, optional
    public void insertNewUser(User u) throws SQLException{
        PreparedStatement insertPrepared = null;
        String insertStatement = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
        //TODO: setup sequences for u_id
    }

    //read/select from the DB
    //used for view all employees
    public void getUserByRole(String role) throws SQLException{

    } 
    //view single employee/user
    public void getUserByID(int uID) throws SQLException {

    }
    //used to register, optional
    public void getUserByUsername(String username) throws SQLException {

    }
    //used for login
    public void getUserByUsername(String username, String password) throws SQLException{

    }
    //update DB record
    //update employee information
    public void updateUser(User u) throws SQLException {

    }

    //delete DB record
    public void deleteUser(User u) throws SQLException {

    }
}