package com.revature.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class LogoutServlet
 * 
 * Logout Service
 */
public class LogoutServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	/**
	 * Called when ending session
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		logger.info("logging user out");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			logger.info("logged out");
		}

	}

}
