package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.bean.User;

//CRUD operations
public interface UserDAO{
    //insert/create new record on the DB
    public void insertNewUser(User u) throws ClassNotFoundException, SQLException; //register employee, optional
    //read/select from the DB
    public ArrayList<User> getUserByRole(String role) throws ClassNotFoundException, SQLException; //used for view all employees
    public User getUserByID(int uID) throws ClassNotFoundException, SQLException; //view single employee/user
    public User getUserByUsername(String username) throws ClassNotFoundException, SQLException; //used to register, optional
    public User getUserByUsername(String username, String password) throws ClassNotFoundException, SQLException; //used for login
    //update DB record
    public void updateUser(User u) throws ClassNotFoundException, SQLException; //update employee information
    //delete DB record
    public void deleteUser(User u) throws ClassNotFoundException, SQLException;
}
