package bankingapp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public final class Driver {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@thetestdb.chpmycag1sts.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "kage";
		String password = "kagami30";
		Connection con = DriverManager.getConnection(url, username, password);
		
		String state = con.isClosed() ? "Connection is closed" : "Connection is open";
		
		System.out.println(state);
		
		if(!con.isClosed()) {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLOYEE");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			
			for(int i = 1; i <= meta.getColumnCount(); ++i) {
				System.out.print(meta.getColumnLabel(i) + " | ");
			}
			System.out.println();
			
			while(rs.next()) {
				for(int i = 1; i <= meta.getColumnCount(); ++i)
					System.out.print(rs.getString(i) + " | ");
				System.out.println();
			}
			
			con.close();
		}
	}

}
