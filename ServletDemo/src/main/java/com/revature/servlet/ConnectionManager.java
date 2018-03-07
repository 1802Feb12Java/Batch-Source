package com.revature.servlet;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager implements Closeable {
	private static ConnectionManager instance;
	
	private Connection conn;
	
	public ConnectionManager() throws ClassNotFoundException, SQLException {
		Properties props = new Properties();
		
		try(InputStream in = new FileInputStream("C:\\Users\\tehub\\Desktop\\git\\Batch-Source\\ServletDemo\\src\\main\\java\\database.properties")) {
			props.load(in);
		} catch(IOException ex) {
		}
		
		// Connect.
		conn = null;
		Class.forName(props.getProperty("driver"));
		String url, username, password;
		
		url = props.getProperty("url");
		username = props.getProperty("usr");
		password = props.getProperty("pwd");
		
		conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(true);
	}
	
	public static ConnectionManager getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new ConnectionManager();
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void close() {
		try {
			conn.close();
		} catch(SQLException ex) {
			// Do nothing.
		}
	}
	
	
	public static void main(String[] args) {
		
	}
}
