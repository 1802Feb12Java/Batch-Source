package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Banking.Customer;
import Banking.BankingApplication;
import Banking.Connector;;

public  class CustomerDAO implements CustomerDAOInterface{
	
	@Override 
	public ArrayList<Customer> getCustomers()
	{
		// TODO Auto-generated method stub
		ArrayList<Customer> customers = new 	ArrayList<Customer>();
		
		try {
			Connection con = Connector.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Customers");
			ResultSet rs = ps.executeQuery();
			int idcount = 1;
			while(rs.next())
			{
				Customer customer = new Customer();
				
				customer.getIdentity().setID(idcount);
				customer.getIdentity().setUsername(rs.getString(2));
				customer.getIdentity().setPassword(rs.getString(3));
				customer.getIdentity().setName(rs.getString(4));
				customer.setBalance(rs.getBigDecimal(5));
				customers.add(customer);
				idcount ++;
			}
			
		} catch (SQLException e) 
		{
			System.out.println("Error");
		}
				
		return customers;
	}
	
	public Customer getCustomer(String username)
	{
		Customer customer = new Customer();
		Connection conn = Connector.getConnection();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM Customers WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				customer.getIdentity().setID(rs.getInt(1));
				customer.getIdentity().setUsername(rs.getString(2));
				customer.getIdentity().setPassword(rs.getString(3));
				customer.getIdentity().setName(rs.getString(4));
				customer.setBalance(rs.getBigDecimal(5));
				
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//add customer to the database
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = Connector.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Customers Values(?, ?, ?, ?, ?,?,?)");
			
			ps.setInt(1, customer.getID()); //customer.getID());
			ps.setString(2, customer.username());
			ps.setString(3, customer.password());
			ps.setString(4, customer.name());
			ps.setBigDecimal(5, customer.getBalance());
			ps.setInt(6, customer.approved());
			ps.setInt(7, customer.cancelled());
			//ps.setBoolean(6, customer.());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stubs
		
		Connection con = Connector.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE Customers SET +"
					+ ". = ?, Balance = ?, Active = ?"
					+ " WHERE username = ?");
			
			ps.setInt(1, customer.getID());
			ps.setString(2, customer.username());
			ps.setString(3, customer.password());
			ps.setString(4, customer.name());
			ps.setBigDecimal(5, customer.getBalance());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}







	@Override
	public void deleteCustomer(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomers(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCustomer1(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer1(Customer customer) {
		// TODO Auto-generated method stub
		
	}




}