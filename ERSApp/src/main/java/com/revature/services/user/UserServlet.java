package com.revature.services.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.revature.Users;
import com.revature.beans.User;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(UserServlet.class);
	private static final long serialVersionUID = 1L;

	private Connection con = com.revature.database.ConnectionFactory.getInstance().getConnection();

	public UserServlet() {
		super();
	}

	/**
	 * Get current session - user
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received GET Request");

		// get user session info
		User u = (User) request.getSession().getAttribute("user");
		if (u != null) {
			// create and send JSON with user info
			String jsonString = new JSONObject().put("username", u.getUsername()).put("firstname", u.getFirstName())
					.put("lastname", u.getLastName()).put("email", u.getEmail()).toString();

			logger.info("Writing string user info " + jsonString);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonString);
		} else {
			logger.error("No user in session!");
		}

	}

	/**
	 * Update user info
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received POST Request");

		// go through post form fields
		String username = request.getParameter("username");
		logger.debug("Got username from post " + username);
		String password = request.getParameter("password");
		logger.debug("Got password from post " + password);
		String firstName = request.getParameter("firstname");
		logger.debug("Got firstName from post " + firstName);
		String lastName = request.getParameter("lastname");
		logger.debug("Got lastname from post " + lastName);
		String email = request.getParameter("email");
		logger.debug("Got email from post " + email);

		User sessionUser = (User) request.getSession(false).getAttribute("user");
		// update User obj
		sessionUser.setUsername(username);
		sessionUser.setPassword(password);
		sessionUser.setFirstName(firstName);
		sessionUser.setLastName(lastName);
		sessionUser.setEmail(email);

		// update user in db
		if (!Users.updateUser(con, sessionUser)) {
			response.getWriter().println("ERROR POST USER FAILED");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			logger.debug("Updated User");
			// update cookie
			request.getSession(false).removeAttribute("user");
			request.getSession(false).setAttribute("user", sessionUser);
			logger.debug("Updated User's session cookie");
		}

		// redirect user back to home page
		response.sendRedirect("/ERSApp/secure/home.html");

	}

	@Override
	public void destroy() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	}

}
