package com.revature.dao;

import java.util.ArrayList;

import com.revature.beans.UserRole;

public interface UserRoleDAO {

	public ArrayList<UserRole> getUsers();
	public void addUserRole(UserRole user);
	public void deleteUserRole(UserRole user);
	public void updateUserRole(UserRole user);
	
}
