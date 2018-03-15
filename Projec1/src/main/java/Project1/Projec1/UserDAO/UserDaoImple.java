package Project1.Projec1.UserDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Project1.Projec1.oraclejdbc.ConnFactory;
import Project1.Projec1.pojo.Users;

public class UserDaoImple implements UserDao{
	private Logger logger = Logger.getLogger(UserDaoImple.class);

	//	//------------------- Return all user objects -----------------------------

	public ArrayList<Users> getAllUsers() throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "Select * from ers_users";

		ArrayList<Users> userList = new ArrayList();
		
		PreparedStatement ps =  con.prepareStatement(sql); //prepared statement
		ResultSet rs = ps.executeQuery();//pull information
		
		while (rs.next()) {
			Users user = new Users();
			user.setUr_id(rs.getInt(7));
			user.setU_id(rs.getInt(1));
			user.setU_username(rs.getString(2));
			user.setU_password(rs.getString(3));
			user.setU_firstname(rs.getString(4));
			user.setU_lastname(rs.getString(5));
			user.setU_email(rs.getString(6));
			userList.add(user);// save data in arraylist

			
		}
		//close connection
		ps.close();
		rs.close();
		con.close();
		
		return userList;
	}

	//------------------- Return user object -----------------------------
	public Users getOneUser(String username) throws SQLException {
		
		Users user = new Users();
		
		ArrayList<Users> list = getAllUsers();//retrieved all users
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getU_username().equals(username)) {
				user = list.get(i);
				return user;
			}
		}
		
		return null;
	}
	//------------------- check if user's data is in db -----------------------------

	public boolean checkUser(String user, String pass) throws SQLException {
		ArrayList<Users> list = getAllUsers();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getU_username().equals(user) && list.get(i).getU_password().equals(pass)) {
				return true;
			}
		}
		return false;
	}
//---------------------------- Update user account
	public void updateUser(String username, String pass, String fname, String lname, String email) throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "call updateUser (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, pass);
		ps.setString(3, fname);
		ps.setString(4, lname);
		ps.setString(5, email);
		ps.executeUpdate();// update query
		
		ps.close();
		con.close();
		

	}
//------------------- Get user object using id
	public Users returnOneUser(int id) throws SQLException{
		ArrayList<Users> userlist = getAllUsers();
		for(int i = 0; i < userlist.size(); i++) {
			if(userlist.get(i).getU_id() == id) {
				return userlist.get(i);
			}
		}
		return null;
	}
//--------------------------- update user info
	public void updatePass(String pass, String username) throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "{call updatePass(?,?)}";
	    CallableStatement stmt=con.prepareCall(sql);  
		stmt.setString(1,pass);  
		stmt.setString(2,username);  

		stmt.executeUpdate();  
		
		stmt.close();
		con.close();
		

		
	}

	public void updateFname(String fname, String username) throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "{call updateFname(?,?)}";
		CallableStatement stmt=con.prepareCall(sql);  
		stmt.setString(1,fname);  
		stmt.setString(2,username);  

		stmt.executeUpdate();  
		
		stmt.close();
		con.close();
				
	}

	public void updateLname(String lname, String username) throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "{call updateLname(?,?)}";
		CallableStatement stmt=con.prepareCall(sql);  
		stmt.setString(1,lname);  
		stmt.setString(2,username);  

		stmt.executeUpdate();  
		
		stmt.close();
		con.close();
				
	}

	public void updateEmail(String email,String username) throws SQLException {
		ConnFactory fac = new ConnFactory();
		Connection con = fac.getConnection(); // get connection from db property file
		String sql = "{call updateEmail(?,?)}";
		CallableStatement stmt=con.prepareCall(sql);  
		stmt.setString(1,email);  
		stmt.setString(2,username);  

		stmt.executeUpdate();  
		
		stmt.close();
		con.close();
				
				
	}

	
}
