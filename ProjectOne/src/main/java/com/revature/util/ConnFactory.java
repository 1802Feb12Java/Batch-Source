package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	
	private static ConnFactory cf = null;
	
	public ConnFactory(){
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
			
			Properties props = new Properties();
            
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties");
            props.load(inputStream);
            inputStream.close();

            String driver = props.getProperty("driver");
            if (driver != null) {
                Class.forName(driver) ;
            }

            String url = props.getProperty("url");
            String dbusername = props.getProperty("user");
            String password = props.getProperty("pass");
            conn = DriverManager.getConnection(url, dbusername, password);
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return conn;
	}

}
