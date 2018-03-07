package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Users;
import com.revature.beans.User;

public class LoginServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(LoginServlet.class);

	private static final long serialVersionUID = -9109911146582857848L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Got a request " + request.getRequestURL());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		logger.info("got username: " + n + ", password " + p);
		User u = Users.getUser(n, p);

		if (u != null) {
			logger.info("Logged in user " + u);
			request.getSession().setAttribute("user", u); // Put user in session.
			response.sendRedirect("secure/home.html"); // Go to some start page.
		} else {
			request.setAttribute("error", "Unknown login, try again"); // Set error msg for ${error}
			request.getRequestDispatcher("/login.html").forward(request, response); // Go back to login page.
		}

		out.close();
	}
}
