package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.controllers.User;

public class UpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Update information of 1 user
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");

		String oldusername = req.getParameter("ousr");
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		String firstName = req.getParameter("fnam");
		String lastName = req.getParameter("lnam");
		String email = req.getParameter("emal");

		try {
			User.updateUserInformation(oldusername, username, password, firstName, lastName, email);
			req.getRequestDispatcher("/manager-update.html").forward(req, resp);
		} catch (SQLException e) {
			resp.addHeader("isDone", "no");
		}

		resp.addHeader("isDone", "yes");
	}

}