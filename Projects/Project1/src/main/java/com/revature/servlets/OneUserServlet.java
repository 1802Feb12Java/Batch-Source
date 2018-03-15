package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.revature.controllers.User;

public class OneUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Return information of 1 user
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);

		JsonArray jsa = null;
		String username = (String) session.getAttribute("user");

		try {
			jsa = User.viewUserInformation(username);
		} catch (SQLException e) {
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(jsa);
	}

}