package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.User;

/**
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(EditUserServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get user session info
		User u = (User) request.getSession().getAttribute("user");
		if (u != null) {
			//send JSON with user info
			
			User tmp = new User(0, u.getUsername(), "", u.getFirstName(), u.getLastName(), u.getEmail(), "" )
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Got post request for Edit user");
		// get submitted data
		logger.info(request.getParameter("username"));
		logger.info(request.getAttribute("username"));
		logger.info(request.getHeader("username"));
		logger.info(request.getRequestURL());
		// doGet(request, response);
	}

}
