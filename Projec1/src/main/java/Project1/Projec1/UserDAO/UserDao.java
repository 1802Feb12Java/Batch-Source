package Project1.Projec1.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Project1.Projec1.pojo.Users;

public interface UserDao {
	public ArrayList<Users> getAllUsers() throws SQLException;
	public Users getOneUser(String username) throws SQLException;
	public boolean checkUser(String user, String pass) throws SQLException ;
	public void updateUser(String username,String pass, String fname, String lname, String email) throws SQLException;
	public Users returnOneUser(int id) throws SQLException;
	public void updatePass(String pass, String username) throws SQLException;
	public void updateFname(String fname ,String username) throws SQLException;
	public void updateLname(String lname, String username) throws SQLException;
	public void updateEmail(String email, String username) throws SQLException;
	//public boolean compareUser();

}
