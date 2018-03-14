package com.revature.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/*
 * Verify email address
 * 
 */


public class Verification {
	
	private static Logger log = Logger.getLogger(Verification.class.getName());

	
	public static Boolean usernameIsUnique(String username) throws SQLException {
		Boolean isUnique = true;
		//System.out.println("Inside of usernameIsUnique. The parameter passed is: " + username);
		ArrayList<String> usernameList = com.revature.dao.UserDAOImp.getUsernames();
		for(int i = 0; i < usernameList.size(); i++) {
			if(usernameList.get(i).equalsIgnoreCase(username)) {
				isUnique = false;
				return isUnique;
			}
		}
		return isUnique;
		
	}//end newUsernameIsUnique
	
	public static Boolean passwordIsCorrect(User u, String pass) {
		Boolean isCorrect = false;
		if(u.getPassword().equals(pass)) {
			isCorrect = true;
		}
		return isCorrect;
		
	}//end passwordIsCorrect
	
	public static int loginVerify(String username, String pass) throws SQLException {
		int response;
		if(!usernameIsUnique(username)) {
			int userRoleID = com.revature.dao.UserDAOImp.getUserRoleID(username);
			switch (userRoleID) {
				case 0:
					Employee e = com.revature.dao.UserDAOImp.getEmployeeObject(username);
					if(passwordIsCorrect(e, pass)) {
						response = 1;
						log.info("Employee Login Successful.");
					} else {
						response = 0;
						log.info("Employee tried logging in with incorrect password.");
					}
					break;
				case 1:
					Manager m = com.revature.dao.UserDAOImp.getManagerObject(username);
					if(passwordIsCorrect(m, pass)) {
						response = 2;
						log.info("Manager Login Successful.");
					} else {
						response = 0;
						log.info("Manager tried logging in with incorrect password.");
					}
					break;
				default:
					response = 0;
					log.debug("ERROR: User Role ID Was Not Accessed Successfully.");
					break;
			}
		} else {
			response = 3;
			log.info("A user tried to log in with a username that does not exist.");
		}
		
		return response;
		
	}//end logInVerify
	
	
}//end class

