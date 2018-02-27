package com.revature.db;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	/**
	 * Tests the password with the given Base64 hash string.
	 * @param str	The Base64 hash string to test.
	 * @return	true if password is valid. Otherwise, false.
	 */
	public boolean isPasswordValid(String username, String str) throws SQLException;
	
	/**
	 * Updates usr with info from the database.
	 * @param usr Updates usr with acquired info.
	 */
	public void getInfo(User usr) throws SQLException;
	
	/**
	 * Gets a user by ID.
	 * @param id	The user id.
	 * @return	The user corresponding to the given ID.
	 */
	public User getUserById(int id) throws SQLException;
	
	/**
	 * Updates the database with the info from usr.
	 * @param usr The user info to update the database with.
	 */
	public void updateInfo(User usr) throws SQLException;
	
	/**
	 * Deletes usr from the database.
	 * @param usr The User to delete from the database.
	 */
	public void deleteUser(User usr) throws SQLException;
	
	/**
	 * Updates the user's password in the database.
	 * @param usr The user to update.
	 * @param pw The new hashed password.
	 */
	public void updatePassword(User usr, String pw) throws SQLException;
	
	/**
	 * Gets all registered users.
	 * @return All registered users.
	 */
	public List<User> getAllUsers() throws SQLException;
	
	/**
	 * Registers a user into the database using the data given.
	 * @param usr	The user the register.
	 * @return	The registered user with updated data.
	 */
	public User createUser(User usr, String pw) throws SQLException;
}
