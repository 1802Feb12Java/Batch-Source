package com.revature.banking.fileio.dao;

import java.util.List;

import com.revature.banking.fileio.model.UserFileIO;

public interface InterfaceUserDAOFileIO {
	public List<UserFileIO> getAllUsers();
	public UserFileIO getUserByUserId(int userId);
	public UserFileIO getUserByUsername(String username);
	public List<UserFileIO> getUsersByEmail(String email);
	public boolean usernamesExists(String username);
	public boolean updateUser(UserFileIO user);
	public boolean addUser(UserFileIO user);
	public boolean deleteUser(UserFileIO user);
	public int getNumUsers();
}
