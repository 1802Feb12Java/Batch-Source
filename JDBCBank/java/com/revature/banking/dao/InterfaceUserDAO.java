package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.model.User;

public interface InterfaceUserDAO {
	public List<User> getAllUsers();
	public User getUserByUserId(int userId);
	public User getUserByUsername(String username);
	public List<User> getUsersByEmail(String email);
	public boolean usernamesExists(String username);
	public boolean updateUser(User user);
	public boolean addUser(User user);
	public boolean deleteUser(User user);
	public int getNumUsers();
}
