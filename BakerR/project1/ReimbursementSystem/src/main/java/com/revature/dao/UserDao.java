package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.User;
import com.revature.bean.UserRole;

public class UserDao {
	private ConnectionManager cm;
	private UserRoleDao urDao;
	
	/**
	 * Creates a UserDao.
	 * @throws SQLException	@see ConnectionManager.getInstance()
	 * @throws IOException	@see ConnectionManager.getInstance()
	 */
	public UserDao() throws SQLException, IOException {
		cm = ConnectionManager.getInstance();
		urDao = new UserRoleDao();
	}
	
	
	//////////// Lookup functions //////////// 
	/**
	 * Gets a list of users in the database.
	 * @return A list of all users in the database.
	 * @throws SQLException	Caused by query or connection.
	 */
	public List<User> getAllUsers() throws SQLException {
		List<User> userList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet userSet = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_all_users();");
			userSet = ps.executeQuery();
			
			while(userSet.next()) {
				User user = new User();
				
				user.setId(userSet.getInt("id"));
				user.setUsername(userSet.getString("username"));
				user.setFirstName(userSet.getString("firstname"));
				user.setLastName(userSet.getString("lastname"));
				user.setEmail(userSet.getString("email"));
				UserRole role = urDao.getUserRoleById(userSet.getInt("roleid"));
				user.setRole(role);
				
				userList.add(user);
			}
			
		} finally {
			if(userSet != null) {
				ps.close();
			}
			if(ps != null) {
				ps.close();
			}
		}
		
		return userList;
	}
	
	/**
	 * Gets a user by ID.
	 * @param id	The user's ID.
	 * @return	The user with the given ID. If none found, returns null.
	 * @throws SQLException	Caused by query or invalid id.
	 */
	public User getUserById(int id) throws SQLException {
		PreparedStatement ps = null;
		ResultSet userResult = null;
		User user = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_user_by_id(?)");
			ps.setInt(1, id);
			userResult = ps.executeQuery();
			
			if(userResult.next()) {
				user = new User();
				
				user.setId(userResult.getInt("id"));
				user.setUsername(userResult.getString("username"));
				user.setFirstName(userResult.getString("firstname"));
				user.setLastName(userResult.getString("lastname"));
				user.setEmail(userResult.getString("email"));
				UserRole role = urDao.getUserRoleById(userResult.getInt("roleid"));
				user.setRole(role);
			}
		} finally {
			if(userResult != null) userResult.close();
			if(ps != null) ps.close();
		}
		
		return user;
	}
	
	/**
	 * Gets a user with the given username.
	 * @param username	The username of the user to find.
	 * @return	A User with the given username. If none found, returns null.
	 * @throws SQLException	Caused by query or invalid username.
	 */
	public User getUserByUsername(String username) throws SQLException {
		PreparedStatement ps = null;
		ResultSet userResult = null;
		User user = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_user_by_username(?)");
			ps.setString(1, username);
			userResult = ps.executeQuery();
			
			if(userResult.next()) {
				user = new User();
				
				user.setId(userResult.getInt("id"));
				user.setUsername(userResult.getString("username"));
				user.setFirstName(userResult.getString("firstname"));
				user.setLastName(userResult.getString("lastname"));
				user.setEmail(userResult.getString("email"));
				UserRole role = urDao.getUserRoleById(userResult.getInt("roleid"));
				user.setRole(role);
			}
		} finally {
			if(userResult != null) userResult.close();
			if(ps != null) ps.close();
		}
		
		return user;
	}
	
	/**
	 * Determines whether or not the provided username-password pair is valid.
	 * @param username	The user's username.
	 * @param password	The user's password.
	 * @return	true if the username-password pair is valid. Otherwise, returns false.
	 * @throws SQLException Error in query or connection.
	 */
	public boolean verifyPassword(String username, String password) throws SQLException {
		boolean isValid = false;
		CallableStatement cs = null;
		try {
			cs = cm.getConnection().prepareCall("{? = call verify_password(?, ?)}");
			cs.registerOutParameter(1, Types.BOOLEAN);
			cs.setString(2, username);
			cs.setString(3, password);
			cs.execute();
			
			isValid = cs.getBoolean(1);
		} finally {
			if(cs != null) cs.close();
		}
		
		return isValid;
	}
	
	
	//////////// Insert functions ////////////
	
	/**
	 * If a user with a matching username is not in the database, user is inserted.
	 * If one already exists, no insertion is performed and the function returns false.
	 * @param user	Inserts this user into the 
	 * @param password	The user's password.
	 * @return Returns true if the user does not already exist. Otherwise, returns false.
	 * @throws SQLException Caused by query or connection.
	 */
	public boolean addUser(User user, String password) throws SQLException {
		// Check if user already exists.
		User existingUser = getUserByUsername(user.getUsername());
		if(existingUser != null) {
			return false;
		}
		
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call add_user(?, ?, ?, ?, ?, ?)}");
			cs.setString(1, user.getUsername());
			cs.setString(2, password);
			cs.setString(3, user.getFirstName());
			cs.setString(4, user.getLastName());
			cs.setString(5, user.getEmail());
			cs.setInt(6, user.getRole().getId());
			
			cs.execute();
			return true;
		} finally {
			if(cs != null) cs.close();
		}
	}
	
	
	//////////// Update functions ////////////
	/**
	 * Updates a user with the given info if the user exists.
	 * Null fields in updateInfo are not updated.
	 * This function does nothing and returns false if the user id does not exist.
	 * @param id	The ID of the user to update.
	 * @param updateInfo	Contains the updated info.
	 * @return	true if the user is updated. Otherwise, false.
	 * @throws SQLException	Caused by query or connection.
	 */
	public boolean updateUser(int id, User updateInfo) throws SQLException {
		User existingUser = getUserById(id);
		
		if(existingUser == null) {
			return false;
		}
		
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call update_user(?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, id);
			cs.setString(2, updateInfo.getUsername() == null ? 
					existingUser.getUsername() : updateInfo.getUsername());
			cs.setString(3, updateInfo.getFirstName() == null ? 
					existingUser.getFirstName() : updateInfo.getFirstName());
			cs.setString(4, updateInfo.getLastName() == null ? 
					existingUser.getLastName() : updateInfo.getLastName());
			cs.setString(5, updateInfo.getEmail() == null ? 
					existingUser.getEmail() : updateInfo.getEmail());
			cs.setInt(6, updateInfo.getRole() == null ? 
					existingUser.getRole().getId() : updateInfo.getRole().getId());
			
			cs.executeQuery();
			
			return true;
		} finally {
			if(cs != null) cs.close();
		}
	}
	
	/**
	 * Updates the password of the given user.
	 * Nothing changed if id does not exist.
	 * @param id	The id of the user to update.
	 * @param password	The new password.
	 * @throws SQLException	Caused by query or connection.
	 */
	public void updatePassword(int id, String password) throws SQLException {
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call update_password(?, ?)}");
			cs.setInt(1, id);
			cs.setString(2, password);
			cs.executeQuery();
		} finally {
			if(cs != null) cs.close();
		}
	}
	
	
	//////////// Delete functions ////////////
	/**
	 * Deletes the user with the given id.
	 * @param id	The id to the user to delete.
	 * @throws SQLException	Caused by query or connection.
	 */
	public void deleteUserById(int id) throws SQLException {
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call delete_user_by_id(?)}");
			cs.setInt(1, id);
			cs.execute();
		} finally {
			cs.close();
		}
	}
	
	/**
	 * Deletes the user with the given username.
	 * @param username	The username of the user to delete.
	 * @throws SQLException	Caused by query or connection.
	 */
	public void deleteUserByUsername(String username) throws SQLException {
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call delete_user_by_username(?)}");
			cs.setString(1, username);
			cs.execute();
		} finally {
			cs.close();
		}
	}
}
