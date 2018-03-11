package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Employee;
import com.revature.beans.User;
import com.revature.connectionfactory.ConnectionManager;

public class EmpDAO implements EmployeeDAO {

	private Connection connection;
	private static EmpDAO instance;
	
	public static EmpDAO getInstance() throws ClassNotFoundException, SQLException{
		if(instance == null) {
			instance = new EmpDAO();
		}
		return instance;
	}
	
	private EmpDAO() throws ClassNotFoundException, SQLException {
		super();
		this.connection = ConnectionManager.getInstance().getConnection();
	}

	
	
	@Override
	public ArrayList<Employee> getAllEmployees() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployee(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		boolean validate = true;
		if(password.equalsIgnoreCase("admin")) {
			validate = false;
		}
		Employee employee = new Employee();
		//TODO: fix sql
		String sql = "~~~~ ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, username);
		//ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			//TODO: Make sure those are the correct columns
			employee.setEmpId(rs.getInt(1));
			employee.setUsername(rs.getString(2));
			employee.setPassword(rs.getString(3));
		} else {
			//no employee with username found in DB
			return null;
			//System.out.println("Failed to get account ID.");
		}
		
		//TODO: the rest of the everything
		/*
		 * get employee information from DB, if no username return null
		 * If(validate) return null if passwords don't match
		 * else return an employee object that has been populated with information
		 * from DB
		 */
		
		return null;
	}

	@Override
	public String getEmployeePassword(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getEmployeeID(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccountInformation(String newUsername, String oldUsername, String password) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserToTable(User user) throws SQLException {
		
		String sql = "INSERT INTO ERS_USERS VALUES (? ,?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, 1); //will auto increment to the correct ID
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getFirstname());
		ps.setString(5, user.getLastname());
		ps.setString(6, user.getEmail());
		ps.setInt(7, user.getAccess());
		
		
		ps.execute();
		
	}

}
