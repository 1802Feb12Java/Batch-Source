package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.User;

public class LogInServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		if (!(role == null)) {
			String forwardPage = role + ".html";
			// req.getRequestDispatcher(forwardPage).forward(req, resp);
			resp.sendRedirect(forwardPage);
		} else {
			// req.getRequestDispatcher("login.html").forward(req, resp);
			resp.sendRedirect("login.html");
		}
	}

	// redirect to correct page on successful logged in
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String role = "wrong";

		String username = req.getParameter("user");
		String password = req.getParameter("pass");

		try {
			if (User.logIn(username, password)) {
				role = User.getRole(username);
			} else {
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception");
		}
		// System.out.println(role);

		if (role.equals("employee") || role.equals("manager")) {
		} else {
			// req.getRequestDispatcher("login.html").forward(req, resp);
			resp.sendRedirect("login.html");
		}

		session.setAttribute("role", role);
		session.setAttribute("user", username);
		String webpage = role + ".html";
		// req.getRequestDispatcher(webpage).forward(req, resp);
		resp.sendRedirect(webpage);
	}

}
