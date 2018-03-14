package com.revature.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {
	private static ConnectionManager instance = null;
	
	
	private Connection connection;
	
	/**
	 * Dependencies: 
	 * 	resource: "database.properties".
	 * @throws SQLException	Failed to create resource.
	 * @throws IOException	IO Error in reading "database.properties"
	 */
	private ConnectionManager() throws SQLException, IOException {
		Properties props = new Properties();
		try(InputStream propsInStream = getClass().getClassLoader().getResourceAsStream("database.properties")) {
			props.load(propsInStream);
		}
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(props.getProperty("host"), props);
		} catch(ClassNotFoundException ex) {
			throw new IOException(ex.getMessage());
		}
	}
	
	
	/**
	 * Gets an instance of the ConnectionManager.
	 * @return
	 * @throws SQLException Failed to create instance.
	 */
	public static ConnectionManager getInstance() throws SQLException, IOException {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Discards & invalidates the currently held instance. 
	 * The connection held by the instance is also closed.
	 */
	public static void destroy() {
		try {
			if(instance != null)
				instance.connection.close();
		} catch(SQLException ex) {}
		
		instance = null;
	}
	
	@Override 
	public void finalize() {
		destroy();
	}
}
