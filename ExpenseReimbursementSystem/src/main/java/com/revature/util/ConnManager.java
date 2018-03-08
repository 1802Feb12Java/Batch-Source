package com.revature.util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public final class ConnManager implements Closeable {
	private static ConnManager instance;
	
	private Connection conn;
	
	public ConnManager() throws ClassNotFoundException, SQLException {
		Properties props = new Properties();
	
		
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties")) {
			props.load(inputStream);
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
	
	public static ConnManager getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null)
			instance = new ConnManager();
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
