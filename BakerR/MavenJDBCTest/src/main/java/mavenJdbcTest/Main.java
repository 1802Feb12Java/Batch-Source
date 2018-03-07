package mavenJdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn;
//		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://pgsql.chpmycag1sts.us-east-2.rds.amazonaws.com:5432/PGSQL";
		Properties props = new Properties();
		props.setProperty("user", "wamuu");
		props.setProperty("password", "mezametamae");
//		props.setProperty("ssl", "true");
		conn = DriverManager.getConnection(url, props);
		if(conn.isClosed()) {
			System.out.println("Connection is closed");
		} else {
			System.out.println("Connection is open");
		}
		Properties p = conn.getClientInfo();
		p.stringPropertyNames().forEach((String propName) -> {
			System.out.println(propName + ": " + p.getProperty(propName));
		});
		
		conn.close();
	}
}
