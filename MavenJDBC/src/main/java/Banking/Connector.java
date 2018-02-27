package Banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector 
{
	private static Connection con;
	
	public Connector() 
	{
		super();
	}
	
	public static Connection getConnection()
	{
		if(con == null) 
		{
			try
			{
				con = DriverManager.getConnection("jdbc:oracle:thin:@mydatabase.ckcbgjmfavpl.us-east-2.rds.amazonaws.com:1521:ORCL", "talanianj", "password");
			}
			catch (SQLException E)
			{
				System.out.println("Connection Failed!");
			}
		}
		
		return con;
	}

	
}
