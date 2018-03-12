package mavenJdbcTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		
		try {
	//		Class.forName("org.postgresql.Driver");
			Properties props;

			props = new Properties();
//			String url = "jdbc:postgresql://pgsql.chpmycag1sts.us-east-2.rds.amazonaws.com:5432/PGSQL";
//			props.setProperty("user", "wamuu");
//			props.setProperty("password", "mezametamae");
//			props.setProperty("ssl", "true");
//			props.setProperty("sslmode", "verify-full");
//			props.setProperty("sslrootcert", "/home/ryuujin/employment/revature/repos/Batch-Source/BakerR/project1/ReimbursementSystem/src/main/resources/rds-combined-ca-bundle.pem");
			
			InputStream is = Main.class.getClassLoader().getResourceAsStream("database.properties");
			props.load(is);
			
			conn = DriverManager.getConnection(props.getProperty("host"), props);
			if(conn.isClosed()) {
				System.out.println("Connection is closed");
			} else {
				System.out.println("Connection is open");
			}
			Properties p = conn.getClientInfo();
			p.stringPropertyNames().forEach((String propName) -> {
				System.out.println(propName + ": " + p.getProperty(propName));
			});
			
			// Test registering a user with a function.
	//		CallableStatement reguser = conn.prepareCall("{? = call reguser()}");
	//		
	//		boolean status = reguser.execute();
	//		System.out.println("Reguser call status: " + status);
			
			cs = conn.prepareCall("{? = call verify_pass(?, ?)}");
			cs.registerOutParameter(1, Types.BOOLEAN);
			cs.setString(2, "esidisi");
			cs.setString(3, "heat");
			cs.execute();
			
			Boolean isValid = cs.getBoolean(1);
			
			System.out.println("Verify Pass: " + isValid);
		} finally {
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
