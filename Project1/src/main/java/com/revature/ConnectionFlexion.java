package com.revature;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFlexion {

	protected static ConnectionFlexion cf = new ConnectionFlexion();
	
	protected ConnectionFlexion() {
		super();
	}
	
	public static synchronized ConnectionFlexion getInstance() {
		if(cf==null) {
			cf= new ConnectionFlexion();
		}
		return cf;
	}
	
	public Connection getConnection() {
		
	 try {

         Class.forName("oracle.jdbc.driver.OracleDriver");

     } catch (ClassNotFoundException e) {

         System.out.println("Where is your Oracle JDBC Driver?");
         e.printStackTrace();
         return null;

     }

     //System.out.println("Oracle JDBC Driver Registered!");

     Connection connection = null;

     try {

         connection = DriverManager.getConnection(
                 "jdbc:oracle:thin:@mydb.cicndbpkh2ka.us-east-1.rds.amazonaws.com:1521:ORCL", "starkjosh", "mattsfav1");

     } catch (SQLException e) {

         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return null;

     }

     if (connection != null) {
         //System.out.println("You made it, take control your database now!");
         return connection;
     } else {
         System.out.println("Failed to make connection!");
     }
     return null;
}
}
