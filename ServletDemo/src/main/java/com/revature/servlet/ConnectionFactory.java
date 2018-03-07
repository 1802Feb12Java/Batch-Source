package com.revature.servlet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf= new ConnectionFactory();
	private ConnectionFactory() {
		super();
	}
	
	private Connection conn;
	
	public static synchronized ConnectionFactory getInstance() {
		if(cf==null) {
			cf= new ConnectionFactory();
		}
		return cf;
	}
	
	public Connection getConnection() {
		
		String filePath = "C:\\Users\\tehub\\Desktop\\git\\Batch-Source\\ServletDemo\\src\\main\\java\\database.properties";
		try {
			Properties prop = new Properties();
			prop.load(new FileReader(filePath));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pwd"));
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