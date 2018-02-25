import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Test {
	
	  static Logger log = Logger.getLogger(Test.class.getName());
	   
    public static void main(String[] argv) {
//
//        System.out.println("-------- Oracle JDBC Connection Testing ------");
//
//        try {
//
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//
//        } catch (ClassNotFoundException e) {
//
//            System.out.println("Where is your Oracle JDBC Driver?");
//            e.printStackTrace();
//            return;
//
//        }
//
//        System.out.println("Oracle JDBC Driver Registered!");
//
//        Connection connection = null;
//
//        try {
//
//            connection = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@feb12usf.cxs74lhezzsm.us-east-2.rds.amazonaws.com:1521:ORCL", "trevorfortner", "awsdbpass");
//
//        } catch (SQLException e) {
//
//            System.out.println("Connection Failed! Check output console");
//            e.printStackTrace();
//            return;
//
//        }
//        
//        if (connection != null) {
//            System.out.println("You made it, take control your database now!");
//        } else {
//            System.out.println("Failed to make connection!");
//        }
        
        
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        
    }
}
