package com.revature.ers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.revature.ers.controller.ERSUserRolesController;
import com.revature.ers.dao.ERSUserRolesDAO;
import com.revature.ers.model.User_Roles;

class ERSUserRolesDAOTest {

	private User_Roles user1;
	private User_Roles user2;
	private User_Roles user3;
	
	@Before
	public void setup() throws SQLException {
			user1 = new User_Roles();
			user1.setUr_id(1002);
			user1.setUr_role("EMPLOYEE");
			
			user2 = new User_Roles();
			user2.setUr_id(1003);
			user2.setUr_role("MANAGER");
			
			user3 = new User_Roles();
			user3.setUr_id(1003);
			user3.setUr_role("MANAGER");
			
	}
	
	/**
	 * test if there are users in database
	 */
	@Test
	public void testGetAllUserRoles()  {
			try {
				assertTrue(new ERSUserRolesDAO().getAllUserRoles().size() > 0);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error Testing for getting all employees");
			}	
	}

	
	

}
