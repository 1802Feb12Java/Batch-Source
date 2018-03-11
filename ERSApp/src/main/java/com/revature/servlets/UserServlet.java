package com.revature.servlets;

import java.io.IOException;

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
 * 
 * Used for /User Service Requests
 */
public class UserServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(UserServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Got post req");

		// go through post form fields
		String username = request.getParameter("username");
		logger.info("Got username from post " + username);
		String password = request.getParameter("password");
		logger.info("Got password from post " + password);
		String firstName = request.getParameter("firstname");
		logger.info("Got firstName from post " + firstName);
		String lastName = request.getParameter("lastname");
		logger.info("Got lastname from post " + lastName);
		String email = request.getParameter("email");
		logger.info("Got email from post " + email);

		User sessionUser = (User) request.getSession(false).getAttribute("user");
		// update User obj
		sessionUser.setUsername(username);
		sessionUser.setPassword(password);
		sessionUser.setFirstName(firstName);
		sessionUser.setLastName(lastName);
		sessionUser.setEmail(email);

		// put user in db
		if (!Users.updateUser(sessionUser)) {
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
		request.getRequestDispatcher("/home.html").forward(request, response);
		// doGet(request, response);
	}

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get user session info
		User u = (User) request.getSession().getAttribute("user");
		if (u != null) {
			// send JSON with user info
			String jsonString = new JSONObject().put("username", u.getUsername()).put("firstname", u.getFirstName())
					.put("lastname", u.getLastName()).put("email", u.getEmail()).toString();

			logger.info("Writing string user info " + jsonString);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonString);
		} else {
			logger.error("No user in session!");
		}

		// generated code
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.error("No Put API implmented here");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.error("No Delete API implmented here");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

	}

}
