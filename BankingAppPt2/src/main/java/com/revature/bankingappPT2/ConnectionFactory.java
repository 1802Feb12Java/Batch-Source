package com.revature.bankingappPT2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static Connection connection;
	
	public ConnectionFactory() {
		
	}
	public static void createConnection() throws FileNotFoundException {
		
		connection = null;
		
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("src//main//java//database.properties"));
			Class.forName(prop.getProperty("driver"));//"oracle.jdbc.OracleDriver"); 
	        connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pwd")); //"jdbc:oracle:thin:@feb12usf.cdtweqx1hydx.us-east-2.rds.amazonaws.com:1521:ORCL", "tehuberler", "MasterPasswordForAWS1!");
	         System.out.println("Connection Established");
	     } catch (SQLException e) {

	         //System.out.println("Connection Failed! Check output console");
	         e.printStackTrace();
	         return;

	     } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't establish a connection");
			System.exit(0);
		}
	}
	
	public static synchronized Connection getConnection() throws SQLException {
		try {
			if(connection == null) {
				createConnection();
			} else if(connection.isClosed()) {
				connection = DriverManager.getConnection(
		                 "jdbc:oracle:thin:@feb12usf.cdtweqx1hydx.us-east-2.rds.amazonaws.com:1521:ORCL", "tehuberler", "MasterPasswordForAWS1!");
	
			} 
			
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't establish a connection");
			System.exit(0);
		}
		return connection;
	}
	
}
