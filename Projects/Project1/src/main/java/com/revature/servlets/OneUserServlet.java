package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonArray;
import com.revature.controllers.User;

public class OneUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Return information of 1 user
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[Get] One User Servlet");

		JsonArray jsa = null;
		String username = req.getHeader("user");

		try {
			jsa = User.viewUserInformation(username);
		} catch (SQLException e) {
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(jsa);
	}

	// Update information of 1 user
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[Put] One User Servlet");
		resp.setContentType("text/html;charset=utf-8");

		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String data = br.readLine();
		String[] splitData = data.split("&");

		String usernameUnsplit = splitData[0];
		String passwordUnsplit = splitData[1];
		String firstNameUnsplit = splitData[2];
		String lastNameUnsplit = splitData[3];
		String emailUnsplit = splitData[4];

		String[] usernameArray = usernameUnsplit.split("=");
		String[] passwordArray = passwordUnsplit.split("=");
		String[] firstNameArray = firstNameUnsplit.split("=");
		String[] lastNameArray = lastNameUnsplit.split("=");
		String[] emailArray = emailUnsplit.split("=");

		String username = usernameArray[1];
		String password = passwordArray[1];
		String firstName = firstNameArray[1];
		String lastName = lastNameArray[1];
		String email = emailArray[1].split("%40")[0] + "@" + emailArray[1].split("%40")[1];

		System.out.println("Username:\t" + username + "\nPassword:\t" + password + "\nFirst:\t\t" + firstName
				+ "\nLast:\t\t" + lastName + "\nEmail:\t\t" + email);

		try {
			User.updateUserInformation(username, password, firstName, lastName, email);
		} catch (SQLException e) {
			resp.addHeader("isDone", "no");
		}

		resp.addHeader("isDone", "yes");
	}

}
