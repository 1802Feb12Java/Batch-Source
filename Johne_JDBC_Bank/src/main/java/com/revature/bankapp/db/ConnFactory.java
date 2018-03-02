package com.revature.bankapp.db;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnFactory {

	private static ConnFactory cf = null;
	public static final String url = "jdbc:oracle:thin:@bankapp.cdwduyxmqnpi.us-east-2.rds.amazonaws.com:1521:ORCL";
	public static final String user = "BankAdmin";
	public static final String pw = "moneybank";
	
	private ConnFactory(){
		super();
		
	}
	
	public static synchronized ConnFactory getInstance(){
		
		if(cf == null){
			
			cf = new ConnFactory();
			
		}
		
		return cf;
		
	}
	
	public Connection getConnection(){
		
		Connection conn = null;
		
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("src/main/resources/database.properties"));
			Class.forName(prop.getProperty("oracle.jdbc.driver.OracleDriver"));
			conn = DriverManager.getConnection(prop.getProperty(url), prop.getProperty(user), prop.getProperty(pw));
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
				
		return conn;
		
	}
	
}
