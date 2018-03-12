package com.revature.controllers;

import java.sql.SQLException;

import com.revature.dao.implementation.ImplementationReimbursementDAO;

public class Employee extends User {

	public static final ImplementationReimbursementDAO ird = new ImplementationReimbursementDAO();

	public static void submitRequest(String username, double amount, String description, int type) throws SQLException {
		ird.submitReimbursement(username, amount, description, type);
	}

	public static void uploadImage() {

	}

}