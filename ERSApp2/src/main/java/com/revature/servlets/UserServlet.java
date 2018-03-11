package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.revature.beans.User;

/**
 * Servlet implementation class UserServlet
 * 
 * Used for /User Service Requests
 */
public class UserServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(UserServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 */
	public UserServlet() {
		super();
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO check user type & request
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
		doGet(request, response);
	}

	/**
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
