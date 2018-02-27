package JDBCBank.JDBCBank.oraclejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQueries {
	//-------------------------------Retrieves all customer 
	public void selectAllCustomer()
	{
		String sql = "Select * From Customer";
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);

		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Customer is created");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	//-----------------------Insert into Customer table
	public void insertCustomerTableQuery(String user, String pass, String fname, String lname, String addr, String phone)
	{
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement("insert into customer values (?,?,?,?,?,?,?)");
		insertStatement.setInt(1, 0);
		insertStatement.setString(2, user);
		insertStatement.setString(3, pass);
		insertStatement.setString(4, fname);
		insertStatement.setString(5, lname);
		insertStatement.setString(6, addr);
		insertStatement.setString(7, phone);
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Customer is created");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	//------------------------Insert into CustomerAccountTable
	public void insertCustomerAccountTableQuery(double amount, int id)
	{
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement("insert into customeraccount values (?,?,?)");
		insertStatement.setInt(1, 0);
		insertStatement.setInt(2, id);
		insertStatement.setDouble(3, amount);
		
		//execute insert
		insertStatement.executeUpdate();
		//connect.commit();

	//	System.out.println("Customer is created");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	//------------------------Update into CustomerAccountTable
		public void updateCustomerAccountTableQuery(double amount, int caid)
		{
			ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
			Connection connect = conn.getConnection();//get connection to database
			try {
				//prepare an insert statement into customer database
			PreparedStatement insertStatement = connect.prepareStatement("update customeraccount set balance = ? where caid = ?");
			insertStatement.setInt(2, caid);
			insertStatement.setDouble(1, amount);
			//execute insert
			insertStatement.executeUpdate();
			//connect.commit();

		//	System.out.println("Customer is created");
			} catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

		}
		//--------------------------------Remove customer account
		public void removeCustomerAccount(int id)
		{
			ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
			Connection connect = conn.getConnection();//get connection to database
			try {
				//prepare an insert statement into customer database
			PreparedStatement insertStatement = connect.prepareStatement("delete from CustomerAccount where caid = ?");
			insertStatement.setInt(1, id);
			//execute insert
			insertStatement.executeUpdate();
			//connect.commit();

		//	System.out.println("Customer is created");
			} catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

		}
		//------------------------NOT NEEDED YET
	public boolean selectCustomerQuery(String user )
	{
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//select a matching user name 
		PreparedStatement insertStatement = connect.prepareStatement("select username from customer where username = ?");
		insertStatement.setString(1, user);
		//execute select statement
		///insertStatement.executeUpdate();
		ResultSet rs = insertStatement.executeQuery();
		while(rs.next())
		{
			return true;
		}
		System.out.println("Customer is created");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}
	//---------------------------------test
	public boolean selectCustomerAccountQuery(String user )
	{
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//select a matching user name 
		PreparedStatement insertStatement = connect.prepareStatement("select username from customer where username = ?");
		insertStatement.setString(1, user);
		//execute select statement
		///insertStatement.executeUpdate();
		ResultSet rs = insertStatement.executeQuery();
		while(rs.next())
		{
			return true;
		}
		System.out.println("Customer is created");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}



	public static void main(String[] args) {
		
		
	}


}
