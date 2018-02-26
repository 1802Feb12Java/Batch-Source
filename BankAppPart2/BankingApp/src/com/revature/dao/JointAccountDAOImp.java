package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.accounts.AdminAccount;
import com.revature.accounts.JointAccount;
import com.revature.utility.ConnectionFactory;

public class JointAccountDAOImp implements JointAccountDAO{
	
	@Override
	public ArrayList<JointAccount> getJointAccounts() {
		
		ArrayList<JointAccount> joints = new ArrayList<JointAccount>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM JointAccount");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				JointAccount joint = new JointAccount();
				
				joint.setJointID(rs.getInt(1));
				joint.setCustomerID(rs.getInt(2));
				joint.setUsername(rs.getString(3));
				joint.setPassword(rs.getString(4));
				joint.setFirstName(rs.getString(5));
				joint.setLastName(rs.getString(6));
				
				joints.add(joint);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error getJoints");
		}
		
		return joints;
	}

	@Override
	public void addCustomer(JointAccount joint) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO JointAccount Values(?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, joint.getJointID());
			ps.setInt(2, joint.getCustomerID());
			ps.setString(3, joint.getUsername());
			ps.setString(4, joint.getPassword());
			ps.setString(5, joint.getFirstName());
			ps.setString(6, joint.getFirstName());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: addJointCustomer");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCustomer(JointAccount employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(JointAccount employee) {
		// TODO Auto-generated method stub
		
	}

}
