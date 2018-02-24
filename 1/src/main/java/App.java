import java.sql.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
	    	try {  
	    		// create connection to db
	    		Class.forName("oracle.jdbc.driver.OracleDriver");
	    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@fev12rev.cgdmzrirwoab.us-west-2.rds.amazonaws.com:1521:ORCL","joshb","skittlespox");
	    		
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
	    		    		
	    	} catch(Exception e) {
				System.out.println(e);
		}
	    	
    }
}
