package com.revature.beans;

import java.util.ArrayList;

import com.revature.dao.ReimbursementDAOImpl;
import com.revature.dao.UserDAOImpl;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserDAOImpl userDAO = new UserDAOImpl();
		ArrayList<User> users = new ArrayList<User>();
		
		User user = new User();
//		user.setUserID(2);
//		user.setUsername("JimBob");
//		user.setPassword("sdjsadk");
//		user.setFirstName("kdnjk");
//		user.setLastName("Testsdajkdsham");
//		user.setEmail("sdanksd@gmail.com");
//		user.setUserRoleID(0);
		
		//parent key not found
//		userDAO.addUser(user);

		user = userDAO.getUserByUserPass("test", "pass");
		
		System.out.println(user.toString());
		
		Reimbursement reimbursement = new Reimbursement();
		ReimbursementDAOImpl reimbursementDAO = new ReimbursementDAOImpl();
		
		reimbursement.setReiumbursementID(0);
		reimbursement.setAmount(100);
		reimbursement.setDescription("Travel Expenses");
		//reimbursement.setReciept("IMG");
		//reimbursement.setSubmitted("2017-07-23");
		reimbursement.setUserIDAuthor(0);
		reimbursement.setUserIDResolver(1);
		reimbursement.setReimbursementTypeID(0);
		reimbursement.setReimbursementStatusID(0);
		
		reimbursementDAO.addReimbursement(reimbursement);
		
		
	}

}
