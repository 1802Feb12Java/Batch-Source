package com.revature.banking.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.jdbc.model.Employee;
import com.revature.banking.jdbc.utilities.DAOUtilities;

public class EmployeeDAO extends UserDAO implements InterfaceEmployeeDAO {

	private static Connection  connection = null;
	private static PreparedStatement stmt = null;
	public EmployeeDAO() {
	}
	public List<Employee> getAllEmployees() {
		List <Employee> employees = new ArrayList<Employee>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKEMPLOYEE";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Employee employee = new Employee(results.getInt("employeeId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getAllEmployees");
			e.printStackTrace();
		}
		return employees;
	}
	public Employee getEmployeeByEmployeeId(int employeeId) {
		Employee employee = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKEMPLOYEE where employeeId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(employeeId));
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				employee = new Employee(results.getInt("employeeId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getEmployeeByEmployeeId");
			e.printStackTrace();
		}
		return employee;
	}
	public Employee getEmployeeByUsername(String username) {
		Employee employee = null;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKEMPLOYEE where username=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, username);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				employee = new Employee(results.getInt("employeeId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getEmployeeByEmployeeUsername");
			e.printStackTrace();
		}
		return employee;
	}
	public List<Employee> getEmployeeByEmail(String email) {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT * FROM BANKEMPLOYEE where email=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, email);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				Employee employee = new Employee(results.getInt("employeeId"), results.getString("username"), results.getInt("password"),
						results.getString("email"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getEmployeeByEmployeeEmail");
			e.printStackTrace();
		}
		return employees;
	}
	public boolean updateEmployee(Employee employee) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "UPDATE BANKEMPLOYEE SET username=?, password=?, email=? WHERE employeeId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, employee.getUsername());
			stmt.setInt(2, employee.getPassword());
			stmt.setString(3, employee.getEmail());
			stmt.setInt(4, employee.getUserId());
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: updateEmployee");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean addEmployee(Employee employee) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "INSERT INTO BANKEMPLOYEE (username, "
					+ "password, email)"
					+ "VALUES( ?, ?, ?)";
			stmt = connection.prepareStatement(sqlQuery, new String[] {"employeeId"});
			stmt.setString(1, employee.getUsername());
			stmt.setInt(2, employee.getPassword());
			stmt.setString(3, employee.getEmail());
			if( stmt.executeUpdate() != 0) {
				return true;
			}
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: addEmployee");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public boolean deleteEmployee(Employee employee) {
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "DELETE FROM BANKEMPLOYEE WHERE employeeId=?";
			stmt = connection.prepareStatement(sqlQuery);
			stmt.setString(1, Integer.toString(employee.getUserId()));
			if( stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			System.out.println("ERROR: deleteEmployee");
			e.printStackTrace();
			return false;
		}finally {
			DAOUtilities.closeResources(connection, stmt);
		}
	}

	public int getNumEmployees() {
		int count=0;
		try {
			connection = DAOUtilities.getConnection();
			String sqlQuery = "SELECT count(*) FROM BANKEMPLOYEE";
			stmt = connection.prepareStatement(sqlQuery);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				count = results.getInt(1);
				break;
			}
		} catch (SQLException e) {
			System.out.println("ERROR: getNumEmployees");
			e.printStackTrace();
		}
		
		return count;
	}


}
