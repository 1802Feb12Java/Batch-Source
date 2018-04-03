package mavenJdbcTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			Properties props;

			props = new Properties();
			
			try(InputStream is = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
				props.load(is);
			}
			
			conn = DriverManager.getConnection(props.getProperty("host"), props);
			if(conn.isClosed()) {
				System.out.println("Connection is closed");
			} else {
				System.out.println("Connection is open");
			}
			
			

			ps = conn.prepareStatement("INSERT INTO test VALUES(?)");
			ps.setString(1, "山田源氏");
//			rs = ps.executeQuery();
			
//			ResultSetMetaData rsMeta = rs.getMetaData();
//			
//			while(rs.next()) {
//				System.out.println(rsMeta.getColumnName(1) + ": " + rs.getString(1));
//			}
			
			
			
			
		} finally {
			if(rs != null) rs.close();
			
			if(ps != null) {
				ps.close();
			} 
			
			if(cs != null) {
				cs.close();
			}
			
			if(conn != null){
				conn.close();
			}
			
		}
		System.out.println("Program terminated.");
	}
}
