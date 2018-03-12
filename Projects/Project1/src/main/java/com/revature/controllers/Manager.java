package com.revature.controllers;

import java.sql.SQLException;

import com.revature.dao.implementation.ImplementationReimbursementDAO;
import com.revature.dao.implementation.ImplementationUserDAO;

public class Manager extends User {
	public static final ImplementationReimbursementDAO ird = new ImplementationReimbursementDAO();
	public static final ImplementationUserDAO iud = new ImplementationUserDAO();
	
	public static String viewAll() throws SQLException {
		return ird.getReimbursementsAll();
	}

	public static void viewPendingAll() throws SQLException {
		ird.getPendingReimbursementsAll();
	}

	public static void approveOrDeny(int appId, int approveDeny) throws SQLException {
		ird.approveDenyApplication(appId, approveDeny);
	}

	public static void viewResolvedAll() throws SQLException {
		ird.getResolvedAll();
	}

	public static String viewInformationAll() throws SQLException {
		return iud.getAllUsers();
	}

}