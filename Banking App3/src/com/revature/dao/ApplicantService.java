package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pt1.Applier;
import com.revature.util.ConnFactory;

public class ApplicantService implements ApplicantDao {
	
	ConnFactory cf = new ConnFactory();
	
	@Override
	public void insertNewApp(String username, String password, Double balance) {
		try (Connection con = cf.getConnection();) {
			String sqlInsert = "INSERT INTO Applicants(a_id,a_username,a_password,a_balance) VALUES(APPSEQ.nextVal,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setDouble(3, balance);
			ps.executeUpdate();
			System.out.println(username + " is now an applicant !");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<Applier> retrieveAllApp() {
		try (Connection con = cf.getConnection();) {
			
			List<Applier> appList = new ArrayList<>();
			
			String sql = "select * FROM Applicants";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Applier thisApp = new Applier (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
				appList.add(thisApp);
			}
			return appList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteAppByName(String name) {
		// Delete from applicants table
		try (Connection con = cf.getConnection();) {	
			String getInfo = "Select * from Applicants where a_username = ?";
			PreparedStatement ps1 = con.prepareStatement(getInfo);
			ps1.setString(1, name);
			ResultSet rs1 = ps1.executeQuery();
			
			// find if given username is an applicant
			if(rs1.next()) {		
			String delInfo = "Delete from Applicants where a_username = ?";
			PreparedStatement ps = con.prepareStatement(delInfo);
			ps.setString(1, name);
			ps.execute();
			System.out.println(name+" is no longer an applicant!");
			} else {
				System.out.println(name+" is not an applicant!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
}
