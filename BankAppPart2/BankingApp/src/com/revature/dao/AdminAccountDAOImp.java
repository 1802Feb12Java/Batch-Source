package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.accounts.AdminAccount;
import com.revature.utility.ConnectionFactory;

public class AdminAccountDAOImp implements AdminAccountDAO{

	private static Logger log = Logger.getLogger(AdminAccount.class.getName());
	
	@Override
	public ArrayList<AdminAccount> getAdmins() {
		// TODO Auto-generated method stub
		
		ArrayList<AdminAccount> admins = new ArrayList<AdminAccount>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM AdminAccount");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				AdminAccount admin = new AdminAccount();
				
				admin.setAdminID(rs.getInt(1));
				admin.setUsername(rs.getString(2));
				admin.setPassword(rs.getString(3));
				admin.setFirstName(rs.getString(4));
				admin.setLastName(rs.getString(5));
				admins.add(admin);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug("ERROR RETRIEVING ALL ADMINS");
		}
		
		return admins;
	}

	@Override
	public void addAdmin(AdminAccount admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdmin(AdminAccount admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAdmin(AdminAccount admin) {
		// TODO Auto-generated method stub
		
	}

	
	
}
