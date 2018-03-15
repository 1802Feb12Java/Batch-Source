package connect;

import java.sql.Connection;
import java.sql.SQLException;

public class UserImplementation {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		ConnFactory connect = new  ConnFactory();
		Connection conn = connect.getConnection();
		
		//Test UserDAO
		UserDAO userDao = new UserDAO(conn);
		
		// Test that users are being successfully retrieved from the database
		// and that the SQL statements are being executed correctly
		
		//userDao.getById(1);
		userDao.getAll();
		//userDao.getUserByLoginInfo("jane", "$2a$06$1RYeJilQUPbEuhKz0mb/2ORPM2H4jXo3NDqbH5/n4wWtmJjW1T0dq");
		//userDao.getUserLoginInfo("jane");
		

		//Close connection, check that entire main method runs
		conn.close();
		System.out.println("Finished");

	}

}