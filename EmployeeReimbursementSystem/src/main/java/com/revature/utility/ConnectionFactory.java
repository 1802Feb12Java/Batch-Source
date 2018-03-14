package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
//oracle.jdbc.OracleDriver  
//import java.util.Properties;

public class ConnectionFactory {
	
	private static ConnectionFactory conn = null;


	public ConnectionFactory() {
		super();
//		OracleDriver od = new OracleDriver();
//		od.connect(arg0, arg1)
	}
	
	public static synchronized ConnectionFactory getInstance() {
		if (conn == null) {
			conn = new ConnectionFactory();
		}
		return conn;
	}
	
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@ers.cpcjj0oba8us.us-east-2.rds.amazonaws.com:1521:orcl", "sgm4789", "Howareyou!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return conn;
	}//end getConnection
}// end class
	
//	private static Connection conn;
//	
//	public ConnectionFactory() {
//		super();
//	}
//	
//	public static Connection getConnection() {
//		if(conn == null) {
//			try {
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@ers.cpcjj0oba8us.us-east-2.rds.amazonaws.com:1521:orcl", "sgm4789", "Howareyou!");
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Connection Failed");
//			}
//		}
//		
//		return conn;
//	}

	

