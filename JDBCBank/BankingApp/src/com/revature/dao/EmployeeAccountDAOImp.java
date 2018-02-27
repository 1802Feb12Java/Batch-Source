package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.utility.ConnectionFactory;

public class EmployeeAccountDAOImp implements EmployeeAccountDAO{

	@Override
	public ArrayList<EmployeeAccount> getEmployees() {
		// TODO Auto-generated method stub
		
		ArrayList<EmployeeAccount> employees = new 	ArrayList<EmployeeAccount>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM EmployeeAccount");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				EmployeeAccount employee = new EmployeeAccount();
				
				employee.setEmployeeID(rs.getInt(1));
				employee.setUsername(rs.getString(2));
				employee.setPassword(rs.getString(3));
				employee.setFirstName(rs.getString(4));
				employee.setLastName(rs.getString(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: getCustomers");
		}
				
		return employees;
	}

	@Override
	public void addEmployee(EmployeeAccount employee) {
		// TODO Auto-generated method stub
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("INSERT INTO EmployeeAccount Values(?, ?, ?, ?, ?)");
			ps.setInt(1, employee.getEmployeeID());
			ps.setString(2, employee.getUsername());
			ps.setString(3, employee.getPassword());
			ps.setString(4, employee.getFirstName());
			ps.setString(5, employee.getLastName());
			
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteEmployee(EmployeeAccount employee) {
		// TODO Auto-generated method stub
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM EmployeeAccount WHERE EmployeeID = ?");
			ps.setInt(1, employee.getEmployeeID());
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateEmployee(EmployeeAccount employee) {
		// TODO Auto-generated method stub
		
	}

}
