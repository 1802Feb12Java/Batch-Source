package ers;
import org.junit.Test;

import com.revature.ers.users.User;
import com.revature.ers.users.UserServices;
import com.revature.ers.util.ConnFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;


public class TestJunit {
	UserServices us = new UserServices();
	User testUser = new User();
	
   @Test
   //tests that the connection factory is connecting to the database
   public void testConn() {
	   ConnFactory cf = new ConnFactory();
	  String result = "Failed";
	  
	  Connection conn = cf.getConnection();
	  
	  if (conn != null) {
		  result = "Conn Factory connected";
	  }
      assertEquals("Conn Factory connected", result);
   }//end testConn
   
   //Populates user fields and attempts to add user, the actual test will take place in testGetPassword;
   //if the assertion comes back as valid, the user has been created
//   @Test
//   public void testAddUser() {
//	   testUser.setU_userName("Kbop");
//	   testUser.setU_firstName("Krombopulous");
//	   testUser.setU_lastName("Michael");
//	   testUser.setU_password("killinagain");
//	   testUser.setUr_ID(2);
//	   testUser.setU_email("ThereIGo@killinagain.com");
//	   
//	   try {
//		us.addUser(testUser);
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//   }
   @Test
   //test user validation
   public void testGetPassword() { 
	   try {
		assertTrue(us.validateUser("pm", "eyepatch"));
		assertFalse(us.validateUser("mm", "supplaya"));
		assertTrue(us.validateUser("mm", "lookatme"));
		assertTrue(us.validateUser(testUser.getU_userName(), testUser.getU_password()));
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
   
   @Test
   public void testGetUser() {
   	User user = null;
   	UserServices us = new UserServices();
   	int id = 8;
   	try {
   		System.out.println("Trying getUser");
   		user = us.getUser(id);
   	} catch (SQLException e) {
   		System.out.println("Exception-al.");
   		e.printStackTrace();
   	}
   	
   	System.out.println(user.getU_email());
   	
  }
}


