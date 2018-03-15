package com.revature.controllers;

import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.dao.implementation.ImplementationReimbursementDAO;
import com.revature.dao.implementation.ImplementationUserDAO;

public class User {

	private static final ImplementationUserDAO iud = new ImplementationUserDAO();
	private static final ImplementationReimbursementDAO ird = new ImplementationReimbursementDAO();
	public static final Gson gsonBuilder = new GsonBuilder().create();

	public static boolean logIn(String username, String password) throws SQLException {
		return iud.logIn(username, password);
	}

	public static String getRole(String username) throws SQLException {
		return iud.getRole(username);
	}

	public static JsonArray viewUserInformation(String username) throws SQLException {
		JsonArray oneUserJSONArray = new JsonArray();
		String[] oneUserJavaArray = iud.getUserInformation(username);
		for (int i = 0; i < oneUserJavaArray.length; i++) {
			oneUserJSONArray.add(oneUserJavaArray[i]);
		}

		return oneUserJSONArray;
	}

	public static void updateUserInformation(String oldusername, String username, String password, String firstName,
			String lastName, String email) throws SQLException {
		iud.updateInformation(oldusername, username, password, firstName, lastName, email);
	}

	public static String viewReimbursementRequests(String username) throws SQLException {
		return ird.getReimbursements(username);
	}

	public static String viewFirstName(String username) throws SQLException {
		return iud.getFirstName(username);
	}

}