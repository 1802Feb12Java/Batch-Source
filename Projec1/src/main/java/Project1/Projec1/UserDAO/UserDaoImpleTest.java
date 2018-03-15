package Project1.Projec1.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import Project1.Projec1.pojo.Users;
import junit.framework.TestCase;


public class UserDaoImpleTest extends TestCase {

//
//	@Test
//	public void test() throws SQLException, ClassNotFoundException {
//		
//		PersonDAO dao = new PersonDAO();
//		Person person = new Person("John", 150);
//		
//		dao.addPersonToDB(person);
//		dao.updateUser(150, "DAO");
//		dao.removeUser(100);
//		System.out.println(dao.getNameFromDB(150));
//		
////		dao.addPersonToDB(person);
////		System.out.println(dao.getNameFromDB(person));
//		
//		
//	}
	@Test
	public void test() throws SQLException{
		
		ArrayList<Users> list = new ArrayList();
		UserDaoImple dao = new UserDaoImple();
		list = dao.getAllUsers();
		
		System.out.println(list.size());
		
		//test update email
		dao.updateEmail("newemail@gmail.com", "nnguyen");
	}
	@Test
	public void getUserTest() throws SQLException{
		
		UserDaoImple dao = new UserDaoImple();
		Users user = dao.getOneUser("nnguyen");
		
		System.out.println(user.toString());
	}
	@Test
	public void updateUserTest() throws SQLException{
		UserDaoImple dao = new UserDaoImple();
		Users user = dao.getOneUser("nnguyen1");
		
		//dao.updateUser("nnguyen", pass, fname, lname, email);
	}
	
	

}
