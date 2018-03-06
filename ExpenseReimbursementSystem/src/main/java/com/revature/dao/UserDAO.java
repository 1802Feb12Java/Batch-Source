package com.revature.dao;

import com.revature.bean.User;
import java.sql.*;

//CRUD operations
public interface UserDAO{
    //insert/create new record on the DB
    public void insertNewUser(User u) throws SQLException; //register employee, optional
    //read/select from the DB
    public void getUserByRole(String role) throws SQLException; //used for view all employees
    public void getUserByID(int uID) throws SQLException; //view single employee/user
    public void getUserByUsername(String username) throws SQLException; //used to register, optional
    public void getUserByUsername(String username, String password) throws SQLException; //used for login
    //update DB record
    public void updateUser(User u) throws SQLException; //update employee information
    //delete DB record
    public void deleteUser(User u) throws SQLException;
}
