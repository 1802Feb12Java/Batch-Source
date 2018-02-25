package revature.maven.bankingapp;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {
    	
    	
    		ConnFactory conn = new ConnFactory();
    		Connection con = conn.getConnection();
    		
    		// initialize statement variable
    		Statement s = con.createStatement();
    		
    		// execute query and store in result set
    		ResultSet rs = s.executeQuery("select * from employee");
    		
    		int row = 0;
        while (rs.next()) {
          System.out.println("----------Row " + (++row) + " ------------");
          System.out.println("EMP_ID=" + rs.getString(1));
          System.out.println("FIRSTNAME=" + rs.getString(2));
          System.out.println("LASTNAME=" + rs.getString(3));
          System.out.println();
        }
        
        // close connection to db
        con.close();
    		
    		
    	
	    	
    }
}
