package com.revature.ers.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.model.User_Roles;

public interface ERSUserRolesInterface {
	
	/**
	 * gets all users
	 * @return
	 * @throws SQLException
	 */
	public List<User_Roles> getAllUserRoles() throws SQLException;
	
	/**
	 * gets all employees
	 * @return
	 * @throws SQLException
	 */
	public List<User_Roles> getAllEmployees() throws SQLException;
	
	/**
	 * gets all managers
	 * @return
	 * @throws SQLException
	 */
	public List<User_Roles> getAllManagers() throws SQLException;
	
	/**
	 * insert a user to the database
	 * @return
	 * @throws SQLException
	 */
	public void createUserRole(User_Roles userRole) throws SQLException;
	
	/**
	 * checks if user exists
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean exists(int id) throws SQLException;
	
	/**
	 * update the user role
	 * @param user
	 * @throws SQLException
	 */
	public void updateUserRole(User_Roles user) throws SQLException;
}
