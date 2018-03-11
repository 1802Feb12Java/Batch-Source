package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Users;
import com.revature.beans.User;

/**
 * Servlet implementation class AdminServlet
 * 
 * Used for managers to make manager-user-type filtered requests
 * 
 * ../secure/admin
 */
public class AdminServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AdminServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Got Get Req");

		// admins get format is?

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Got post req");

		// go through post form fields
		String username = (String) request.getParameter("username");
		logger.info("Got username from post " + username);
		String password = (String) request.getParameter("password");
		logger.info("Got password from post " + password);
		String firstName = (String) request.getParameter("firstname");
		logger.info("Got firstName from post " + firstName);
		String lastName = (String) request.getParameter("lastname");
		logger.info("Got lastname from post " + lastName);
		String email = (String) request.getParameter("email");
		logger.info("Got email from post " + email);
		String userType = (String) request.getParameter("userType");
		logger.info("Got userType from post " + userType);

		// create User obj
		User u = new User(0, username, password, firstName, lastName, email, userType);

		// put user in db
		if (!Users.addUser(u)) {
			response.getWriter().println("ERROR POST USER FAILED");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
			logger.info("Posted User");
		}

		// redirect user back to home page
		response.sendRedirect("secure/home.html");
		// doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("DELETE request received");

		// user session exists and user is manager
		// get fields
		String username = request.getParameter("username");
		logger.debug("Deleting user with username " + username);

		if (!Users.deleteUser(username)) {
			logger.error("Unable to delete user with username " + username + " from database");
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		} else {
			logger.debug("Successfully deleted user");
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

}
