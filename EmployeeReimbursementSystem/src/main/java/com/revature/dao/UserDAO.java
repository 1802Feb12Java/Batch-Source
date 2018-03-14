package com.revature.dao;

import java.util.ArrayList;

import com.revature.beans.User;

public interface UserDAO {

	public ArrayList<User> getUsers();
	public void addUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	
	
}
