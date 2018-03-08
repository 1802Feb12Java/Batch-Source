package com.revature.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.main.User;

public interface UserDAO {
	public void createUser(User u) throws SQLException;
    public User readUser(int id) throws SQLException;
    public void updateUser(String attribute, String newVal, int id) throws SQLException;
    public ArrayList<User> getAllUsers() throws SQLException;
}
