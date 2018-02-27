package com.revature.banking.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.jdbc.model.Customer;
import com.revature.banking.jdbc.utilities.DAOUtilities;

public class CustomerDAO extends UserDAO implements InterfaceCustomerDAO {

	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public CustomerDAO() {
	}
	public List<Customer> getAllCustomers() {
		List <Customer> customers = new ArrayList<Customer>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKCUSTOMER";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Customer customer = new Customer(results.getInt("customerId"), results.getString("username"), results.getInt("password"),
						results.getString("email"), results.getString("firstName"), results.getString("lastName"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAllCustomers");
			e.printStackTrace();
		}
		return customers;
	}
	public Customer getCustomerByCustomerId(int customerId) {
		Customer customer = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKCUSTOMER where customerId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, customerId);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				customer = new Customer(results.getInt("customerId"), results.getString("username"), results.getInt("password"),
						results.getString("email"), results.getString("firstName"), results.getString("lastName"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getCustomerByCustomerId");
			e.printStackTrace();
		}
		return customer;
	}
	public List<Customer> getCustomersByName(String firstName, String lastName) {
		List <Customer> customers = new ArrayList<Customer>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKCUSTOMER where firstName=?, lastName=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, firstName);
			stmt.setString(1, lastName);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Customer customer = new Customer(results.getInt("customerId"), results.getString("username"), results.getInt("password"),
						results.getString("email"), results.getString("firstName"), results.getString("lastName"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getCustomerByCustomerName");
			e.printStackTrace();
		}
		return customers;
	}
	public Customer getCustomerByUsername(String username) {
		Customer customer = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKCUSTOMER where username=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, username);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				customer = new Customer(results.getInt("customerId"), results.getString("username"), results.getInt("password"),
						results.getString("email"), results.getString("firstName"), results.getString("lastName"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getCustomerByCustomerUsername");
			e.printStackTrace();
		}
		return customer;
	}
	public List<Customer> getCustomerByEmail(String email) {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKCUSTOMER where email=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, email);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Customer customer = new Customer(results.getInt("customerId"), results.getString("username"), results.getInt("password"),
						results.getString("email"), results.getString("firstName"), results.getString("lastName"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getCustomerByCustomerEmail");
			e.printStackTrace();
		}
		return customers;
	}
	public boolean updateCustomer(Customer customer) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE BANKCUSTOMER SET customerId=?, username=?, password=?, email=?, firstName=?, lastName=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, customer.getUserId());
			stmt.setString(2, customer.getUsername());
			stmt.setInt(3, customer.getPassword());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getFirstName());
			stmt.setString(5, customer.getLastName());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: updateCustomer");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean addCustomer(Customer customer) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO BANKCUSTOMER (username, "
					+ "password, email, firstName, lastName)"
					+ " VALUES (?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sqlQuery);
			//stmt.setString(1, Integer.toString(customer.getUserId()));
			stmt.setString(1, customer.getUsername());
			stmt.setInt(2, customer.getPassword());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getFirstName());
			stmt.setString(5, customer.getLastName());
			if( stmt.executeUpdate() != 0) {
				customer = getCustomerByUsername(customer.getUsername());
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: addCustomer");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean deleteCustomer(Customer customer) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "DELETE FROM BANKCUSTOMER WHERE adminId=?, username=?, "
					+ "password=?, email=?)";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setInt(1, customer.getUserId());
			stmt.setString(2, customer.getUsername());
			stmt.setInt(3, customer.getPassword());
			stmt.setString(4, customer.getEmail());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: deleteCustomer");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public int getNumCustomers() {
		int count=0;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT count(*) FROM BANKCUSTOMER";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				count = results.getInt(1);
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getNumCustomers");
			e.printStackTrace();
		}
		
		return count;
	}

}
