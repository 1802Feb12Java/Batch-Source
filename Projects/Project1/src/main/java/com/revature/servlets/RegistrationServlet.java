package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.controllers.User;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/html;charset=utf-8");

		String username = req.getParameter("user1");
		String password = req.getParameter("pass1");
		String firstName = req.getParameter("fnam1");
		String lastName = req.getParameter("lnam1");
		String email = req.getParameter("emal1");

		try {
			User.register(username, password, firstName, lastName, email);
			req.getRequestDispatcher("/login.html").forward(req, resp);
		} catch (SQLException e) {
			resp.addHeader("isDone", "no");
		}

		resp.addHeader("isDone", "yes");
	}

}