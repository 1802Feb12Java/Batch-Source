package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.User;

/**
 * Servlet implementation class SessionServlet
 * 
 * Used for getting user session info
 */
public class SessionServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(SessionServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 */
	public SessionServlet() {
		super();
	}

	/**
	 * Returns the current user object in session field requested
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Received a GET Request");

		User currentUser = (User) request.getSession(false).getAttribute("user");

		String send = JsonfierUtil.userJson(currentUser);

		logger.debug("Sending json " + send);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(send);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
