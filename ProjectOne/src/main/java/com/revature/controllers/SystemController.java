package com.revature.controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.connectionfactory.ConnectionManager;
import com.revature.daos.EmpDAO;
import com.revature.daos.RembDAO;



public class SystemController {

	private static User currentUser;
	private static Connection connection;
	
	public static void setUser(User user) throws ClassNotFoundException, SQLException {
		System.out.println("User Create and stored in SystemController");
		SystemController.currentUser = user;
	}
	
	public static User getUser() {
		return currentUser;
	}
	
	public static String getJsonReimbursements() {
//		System.out.println("cUser: "+currentUser.toString());
		String json = new Gson().toJson(currentUser.getReimbursements());
		return json;
	}
//	
	public static String getJsonReimbursements(String username) throws ClassNotFoundException, SQLException {
		return new Gson().toJson(EmpDAO.getInstance().getEmployee(username).getReimbursements());
	}
	
	public static String getJsonUser() {
		String json = new Gson().toJson(currentUser);
		return json;
	}
	
	public static User validateUser(String username, String password) {
			
		try {
			User user = EmpDAO.getInstance().getEmployee(username);
			currentUser = user;
			if(user == null) {
				return null;
			} else if(user.getPassword().equals(password)) {
				return user;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Validating password failed. SystemController.validateUser");
		}
		
		return null;
	}
	
	public static void addReimbursementToDB(Double amount, String description, java.sql.Date sqlDate, Integer empId, Integer reimbType, byte[] image) throws ClassNotFoundException, SQLException {
		
		Reimbursement reimb = new Reimbursement((Integer) null, amount,
				description, sqlDate, empId,(Integer) null,
				reimbType, 0, (Date) null,(Object) null);
//		if(image!=null) {
//			reimb.setImage(image);
//			System.out.println("image: "+image.toString());
//		} 
		
		RembDAO.getInstance().addReimbursement(reimb);
	}
	
	//update information then returns update user
	//has 2 DB calls. sql functions require statement (not prepared/callable)
	public static User updateUserInfo(String username, String firstname, String lastname, String email, String password) throws ClassNotFoundException, SQLException {
		if(username == null || username.equals("")) {
			username = currentUser.getUsername();
		}
		if(firstname == null || firstname.equals("")) {
			firstname = currentUser.getFirstname();
		}
		if(lastname == null || lastname.equals("")) {
			lastname = currentUser.getLastname();
		}
		if(email == null || email.equals("")) {
			email = currentUser.getEmail();
		}
		if(password == null || password.equals("")) {
			password = currentUser.getPassword();
		}
		EmpDAO.getInstance().updateAccountInformation(currentUser.getEmpId(), username, password, email, firstname, lastname);
		currentUser = EmpDAO.getInstance().getEmployee(currentUser.getUsername());
		
		return currentUser;
		
	}
	
	public static void updateReimbursement(Integer id, Integer status) throws ClassNotFoundException, SQLException {
	
		RembDAO.getInstance().resolved(id, currentUser.getEmpId(), status);
		
	}

	public static String getJsonAllUser() throws ClassNotFoundException, SQLException{
		return new Gson().toJson(EmpDAO.getInstance().getAllEmployees());

	}

	public static byte[] getImagefromDB(Integer id) throws ClassNotFoundException, SQLException {
		return RembDAO.getInstance().getReimbursementImage(id);
	}

	public static void updateSelf() throws ClassNotFoundException, SQLException {
		currentUser = EmpDAO.getInstance().getEmployee(currentUser.getUsername());
	}
	
}



























