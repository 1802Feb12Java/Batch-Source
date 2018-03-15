package com.revature.ers.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.revature.ers.model.Users;

public interface ERSUsersInterface {
	
	/**
	 * gets the list of all users (managers and employees)
	 * @return
	 */
	public List<Users> getAllUsers() throws SQLException;
	
	/**
	 * using the viewUsers from oracle 
	 * @return
	 * @throws SQLException
	 */
	public List<Users> viewAllUsers() throws SQLException;
	
	
	/**
	 * get all user first name
	 * @return
	 */
	public List<Users> getUserFirstName() throws SQLException;
	
	/**
	 * get all user last name
	 * @return
	 */
	public List<Users> getUserLastName() throws SQLException;
	
	/**
	 * get all user emails
	 * @return
	 */
	public List<Users> getUserEmail() throws SQLException;
	
	/**
	 * get all users' username and passwords
	 * @return
	 * @throws SQLException
	 */
	public List<Users> getUserUsernameAndPassword() throws SQLException;
	
	/**
	 * get all passwords
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllPasswords() throws SQLException;
	
	/**
	 * get all usernames
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllUsernames() throws SQLException;
	
	/**
	 * allow user to update their password
	 * @throws SQLException
	 */
	public void updatePassword(Users user) throws SQLException;
	
	/**
	 * allow user to update their username
	 * @throws SQLException
	 */
	public void updateUsername(Users user) throws SQLException;
	
	/**
	 * allow user to update their email
	 * @param user
	 * @throws SQLException
	 */
	public void updateEmail(Users user) throws SQLException;
	
	/**
	 * return a list of role ids
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getUserRoleID() throws SQLException;
	
}
