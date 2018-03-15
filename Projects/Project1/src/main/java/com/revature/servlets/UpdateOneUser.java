package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.User;

public class UpdateOneUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Update information of 1 user
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession(false);

		String oldusername = (String) session.getAttribute("user");
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		String firstName = req.getParameter("fnam");
		String lastName = req.getParameter("lnam");
		String email = req.getParameter("emal");

		try {
			User.updateUserInformation(oldusername, username, password, firstName, lastName, email);
			req.getRequestDispatcher("/login").forward(req, resp);
		} catch (SQLException e) {
			resp.addHeader("isDone", "no");
		}

		resp.addHeader("isDone", "yes");
	}

}