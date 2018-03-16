package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
//oracle.jdbc.OracleDriver  
//import java.util.Properties;

public class ConnFactory {
	
	private static ConnFactory conn = null;


	public ConnFactory() {
		super();
//		OracleDriver od = new OracleDriver();
//		od.connect(arg0, arg1)
	}
	
	public static synchronized ConnFactory getInstance() {
		if (conn == null) {
			conn = new ConnFactory();
		}
		return conn;
	}
	
	
	public Connection getConnection() {

		String url = "jdbc:oracle:thin:@feb12usf.ckt1urwuyrt5.us-east-2.rds.amazonaws.com:1521:ORCL";
        String user = "ps763";
        String password = "PriyaFlower15";
        Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return conn;
	}
}