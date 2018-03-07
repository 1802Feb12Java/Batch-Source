package com.revature.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.main.User;

public interface UserDAO {
	public void addUser(User u) throws SQLException;
    public User getUsers(int id) throws SQLException;
    public void updateUsers(User u) throws SQLException;
    public void deleteUsers(int id) throws SQLException;
    public ArrayList<User> getAllUsers() throws SQLException;
}
