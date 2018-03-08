package ers;
import org.junit.Test;

import com.revature.ers.users.UserServices;
import com.revature.ers.util.ConnFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;


public class TestJunit {
	UserServices us = new UserServices();
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
   
   @Test
   //test user validation
   public void testGetPassword() { 
	   try {
		assertTrue(us.validateUser("pm", "eyepatch"));
		assertFalse(us.validateUser("mm", "supplaya"));
		assertTrue(us.validateUser("mm", "lookatme"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}//end testGetPassword
