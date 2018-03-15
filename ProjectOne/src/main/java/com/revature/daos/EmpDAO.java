package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.revature.beans.Employee;
import com.revature.beans.Manager;
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
	public ArrayList<User> getAllEmployees() throws SQLException {

		ArrayList<User> allEmps = new ArrayList<>();
		
		String sql = "SELECT * FROM ERS_USERS";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			User emp;
			if(rs.getInt(7)>0) {
				emp = new Manager(rs.getString(2), rs.getString(3),rs.getString(6),rs.getString(4),rs.getString(5));
			} else {
				emp = new Employee(rs.getString(2), rs.getString(3),rs.getString(6),rs.getString(4),rs.getString(5));
			}
			allEmps.add(emp);
		}
		
		return allEmps;
	}

	@Override
	public User getEmployee(String username) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		User employee;
		//TODO: fix sql
		String sql = "SELECT * FROM ERS_USERS WHERE U_USERNAME = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, username);
		//ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			//TODO: Make sure those are the correct columns
			if(rs.getInt(7) == 0) {
				employee = new Employee(rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5));
				employee.setEmpId(rs.getInt(1));
			} else {
				employee = new Manager(rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5));
				employee.setEmpId(rs.getInt(1));
				System.out.println("dao User: "+employee.toString());
			}
			employee.setReimbursements(RembDAO.getInstance().getReimbursements(employee));
			//System.out.println(employee.toString());
			//System.out.println("Array of Reimb: "+employee.getReimbursements().toString());
			return employee;
		} else {
			//no employee with username found in DB
			return null;
			//System.out.println("Failed to get account ID.");
		}
		
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
	public void updateAccountInformation(Integer empId, String newUsername, String password, String email, String firstname, String lastname) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "{call CHANGE_USER_INFO (?, ?, ?, ?, ?, ?)}";
		CallableStatement cs = connection.prepareCall(sql);
		
		cs.setInt(1, empId);
		cs.setString(2, newUsername);
		cs.setString(3, password);
		cs.setString(4, firstname);
		cs.setString(5, lastname);
		cs.setString(6, email);
		
		cs.execute();
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
