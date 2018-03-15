package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objects.User;
import objects.UserType;

public class UserDAO {

	private Connection conn;
	
	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/* SQL - get all users in the system */

	public List<User> getAll() throws SQLException, ClassNotFoundException {
		
		List<User> results = new ArrayList<User>();		
		String sql = "SELECT * FROM ERS_USERS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}
	
	/*  */

	private void mapRows(ResultSet rs, List<User> results) throws SQLException {

		while(rs.next()) {
			// get values from rows
			int id = rs.getInt("ers_users_id");
			String username = rs.getString("ers_username");
			String firstName = rs.getString("user_first_name");
			String lastName = rs.getString("user_last_name");
			String email = rs.getString("user_email");
			UserType role = new UserType();
			role.setId(rs.getInt("user_role_id"));
			
			// create user object using those values
			User obj = new User(id, username, firstName, lastName, email, role);
			
			// add objects to list and print to console
			results.add(obj);
			System.out.println(obj);
		}	
	}
	
	/* SQL - get user, given username */

	public User getUserLoginInfo(String username) throws SQLException {
		
		String sql = "SELECT *"
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
			
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			UserType role;
			if(rs.getInt("USER_ROLE_ID")==2) {
				role = new UserType(2, "Employee");
			}else {
				role = new UserType(1, "Manager");
			}
			User user = new User(
					rs.getInt("ERS_USERS_ID"),
					  rs.getString("ERS_USERNAME"),
					  rs.getString("USER_FIRST_NAME"),
					  rs.getString("USER_LAST_NAME"),
					  rs.getString("USER_EMAIL"),
					  role);
			System.out.println("Get user login info for user ID: " + user.getUser_id());
			System.out.println(user);
			return user;			
		}
		return null;
	}
	
	/* SQL -  */
	
	User setUser(ResultSet rs, boolean isAuthor) throws SQLException{
		
		int id;
		String username, lastName, firstName, email;
		if(isAuthor){
			System.out.println("Do we get to setUser()?");
			id = rs.getInt("ERS_USERS_ID");
			System.out.println("ID?" + id);
			username = rs.getString("ERS_USERNAME");
			System.out.println("Username?" + username);
			firstName = rs.getString("USER_FIRST_NAME");
			System.out.println("FirstName?" + firstName);
			lastName = rs.getString("USER_LAST_NAME");
			System.out.println("LastName?" + lastName);
			email = rs.getString("USER_EMAIL");
			System.out.println("Email?" + email);
		}else{
			System.out.println("Do we get to setUser() not author?");
			id = rs.getInt("ERS_USERS_ID");
			username = rs.getString("ERS_USERNAME");
			lastName = rs.getString("USER_LAST_NAME");
			firstName = rs.getString("USER_FIRST_NAME");
			email = rs.getString("USER_EMAIL");
		}
		User user = new User(id, username, firstName, lastName, email, null);
		System.out.println("setUser(): user: " +user);
		return user;
	}
	
	/* SQL - get the user's first and last name, given their id */

	public String getById(int id) throws SQLException {
		
		String firstName = null;
		String lastName = null;

		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME"
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();		
		while (rs.next()) {
				firstName = rs.getString("USER_FIRST_NAME");
				lastName = rs.getString("USER_LAST_NAME");
				System.out.println("User Full Name: " + firstName+ " " +lastName + " for User ID: " + id);
				return firstName + " " + lastName;
		}
		return null;
	}
	
	/* SQL - get user and their role, given their username */

	public User getUser(String username) {
		
		User user = null;
		String sql = "SELECT *" 
				+ " FROM ERS_USERS"
				+ " JOIN ERS_USER_ROLES"
				+ " ON ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USER_ROLE_ID"
				+ " WHERE ERS_USERNAME = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			user = constructUser(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	/* SQL - make a user */
	
	private User constructUser(ResultSet rs) throws SQLException{
		
		if(rs.next()){
			int id = rs.getInt("ERS_USERS_ID");
			String username = rs.getString("ERS_USERNAME");
			String lastName = rs.getString("USER_LAST_NAME");
			String firstName = rs.getString("USER_FIRST_NAME");
			String email = rs.getString("USER_EMAIL");
			UserType role = new UserType(rs.getInt("USER_ROLE_ID"), rs.getString("USER_ROLE"));
			return new User(id, username, firstName, lastName, email, role);
		}
		return null;
	}
	
	/* SQL - get password of user, given their username */

	public String getPassword(String username) throws SQLException{
			
		String password = null; 
		String sql = "SELECT ERS_USERNAME, ERS_PASSWORD"
					+ " FROM ERS_USERS"
					+ " WHERE ERS_USERNAME = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) password = rs.getString("ERS_PASSWORD");
			return password;
		}
}