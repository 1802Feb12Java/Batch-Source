package com.revature.services.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Users;
import com.revature.beans.User;
import com.revature.services.JsonfierUtil;

/**
 * Servlet implementation class UserServletA
 */
public class UsersServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(UsersServlet.class);

	private static final long serialVersionUID = 1L;

	private Connection con = com.revature.database.ConnectionFactory.getInstance().getConnection();

	public UsersServlet() {
		super();
	}

	/**
	 * Gets all employee users
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Received GET Request");

		// get all employees
		ArrayList<User> reqs = (ArrayList<User>) Users.getAllUsers(con, "Employee");

		// jsonify them
		// write string to response
		String jsonReqs = JsonfierUtil.userListJson(reqs);

		logger.info("Sending users " + jsonReqs);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonReqs);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 * Posts a new user
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Received POST Request");

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

		// Managers can create employees only
		String userType = "Employee";
		// create User obj
		User u = new User(0, username, password, firstName, lastName, email, userType);

		// put user in db
		if (!Users.addUser(con, u)) {
			response.getWriter().println("ERROR POST USER FAILED");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
			logger.info("Posted User");
		}

		// redirect user back to home page
		response.sendRedirect("/ERSApp/admin/home.html");
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
