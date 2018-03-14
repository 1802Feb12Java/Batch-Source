package com.revature.beans;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverForTest {

	public static void main(String[] args) {
//		ArrayList<String> usernames;
//		try {
//			usernames = com.revature.dao.UserDAOImp.getUsernames();
//			System.out.println(usernames.toString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			ArrayList<String> pending = com.revature.dao.ReimbursementDAOImp.viewAllPendingRequests();
//			for(String s : pending) {
//				System.out.println(s);
//			}
//			for(String str : pending) {
//				String[] s = str.split(":");
//				System.out.println("{\"r_id\":\"" + s[0] + "\"," + "\"r_amount\":\"" + s[1] + "\"," + "\"r_description\":\"" + s[2] + "\"," + "\"r_receipt\":\"" + s[3] + "\"," + "\"r_submitted\":\"" + s[4] + "\"," + "\"u_id_author\":\"" + s[5] + "\"," + "\"rt_type\":\"" + s[6] + "\"," + "\"u_firstname\":\"" + s[7] + "\"," + "\"u_lastname\":\"" + s[8] + "\"," + "\"u_email\":\"" + s[9] + "\"}");
//				
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			String user = com.revature.dao.UserDAOImp.viewUserRecord("jColson");
			System.out.println(user);
			String[] s = user.split(":");
			for(String str : s) {
				System.out.println(str);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      
	      
	}


	
	
}//end class
