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

	public static void approveOrDeny(int appId, int approveDeny, String approver) throws SQLException {
		ird.approveDenyApplication(appId, approveDeny, approver);
	}

	public static String viewInformationAll() throws SQLException {
		return iud.getAllUsers();
	}

	public static String totalCostReimbursments() throws SQLException {
		return ird.getAppliedAmount();
	}

	public static String totalApprovedReimbursments() throws SQLException {
		return ird.getApprovedAmount();
	}

}