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
	public void addCustomer(EmployeeAccount employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(EmployeeAccount employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(EmployeeAccount employee) {
		// TODO Auto-generated method stub
		
	}

}
