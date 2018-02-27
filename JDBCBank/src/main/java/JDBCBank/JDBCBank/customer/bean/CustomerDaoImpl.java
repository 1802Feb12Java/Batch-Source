package JDBCBank.JDBCBank.customer.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import JDBCBank.JDBCBank.oraclejdbc.ConnFactory;

public class CustomerDaoImpl implements CustomerDao {

	//create arraylist of customer
	ArrayList<Customer> custList = new ArrayList();
	private double amount;
	private int caId;

	public CustomerDaoImpl() {
		super();
	}
	
	//retrieve customer's database
	public ArrayList<Customer> getAllCustomer() {
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
		PreparedStatement ps = connect.prepareStatement("select * from customer");
		ResultSet rs = ps.executeQuery();
			//save db into a arraylist of object
		while (rs.next()) {
	        Customer customer = new Customer();
	        		//(rs.getInt("cId"), rs.getString("username"), rs.getString("password"), rs.getString("fname"), rs.getString("lname"), rs.getString("address"), rs.getString("phonenumber"));
		     customer.setAddress(rs.getString(6));
		     customer.setcId(rs.getInt(1));
		     customer.setFname(rs.getString(4));
		     customer.setLname(rs.getString(5));
		     customer.setPhoneNumber(rs.getString(7));
		     customer.setUserName(rs.getString(2));
		     customer.setPassWord(rs.getString(3));
		     custList.add(customer);

		    }
		rs.close();//close file
		ps.close();//close file
        }
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return custList;
	}
	public Customer getOneCustomer(int id) {
		Customer c = new Customer();
		String sql = "Select * from customer where cid = ?";
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
		PreparedStatement ps = connect.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
			//save db into a arraylist of object
		while (rs.next()) {
		     c.setAddress(rs.getString(6));
		     c.setcId(rs.getInt(1));
		     c.setFname(rs.getString(4));
		     c.setLname(rs.getString(5));
		     c.setPhoneNumber(rs.getString(7));
		     c.setUserName(rs.getString(2));
		     c.setPassWord(rs.getString(3));
		  //   custList.add(customer);

		    }
		rs.close();
		ps.close();
        }
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return c;
	}
	
	//return login information
	public boolean matchCustomer(String pass, String name)
	{
		custList = getAllCustomer();
		for(int i = 0 ; i < custList.size(); i++)
		{
			if(custList.get(i).getUserName().equals(name) && custList.get(i).getPassWord().equals(pass))
			{
				return true;
			}
		}
		return false;
	}
	//check for user id
	public boolean matchCustomerId(int id) {
		custList = getAllCustomer();
		for(int i = 0; i < custList.size(); i++) {
			if(custList.get(i).getcId() == id)
			{
				return true;
			}
		}
		return false;
	}
	public void updateCustomer(Customer c) {
		
	}

	public void deleteCustomer(int cid) {
		String sql = "delete from customer where cid = ?";//delete primary key
		ConnFactory conn = new ConnFactory(); //create a new conn of class ConFactory
		Connection connect = conn.getConnection();//get connection to database
		try {
			//prepare an insert statement into customer database
		PreparedStatement insertStatement = connect.prepareStatement(sql);
		insertStatement.setInt(1, cid);
		//execute insert
		insertStatement.executeUpdate();
	//	connect.commit();
		System.out.println("Customer account has been deleted");
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }			
	}
	//check matching username
	public boolean matchUsername(String username) {

		custList = getAllCustomer();
		for(int i = 0 ; i < custList.size(); i++)
		{
			if(custList.get(i).getUserName().equals(username))
			{
				return true;
			}
		}
		return false;	}

	//return customer object for other functionality
	public Customer getCustomer(String name) {
		ArrayList<Customer> custList  = getAllCustomer();//retrieves customer database

		for(int i = 0; i < custList.size(); i++ )
		{
			if(( custList.get(i)).getUserName().equals(name))
			{
				return custList.get(i);
			}
		}		
		return null;
	}

	
}
