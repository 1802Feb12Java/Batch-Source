package com.revature.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.ers.users.UserServices;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getRootLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserServices us = new UserServices();
		boolean validated = false;
		
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		
		//attempt to validate the user
		try {
			validated = us.validateUser(username, password);
		} catch (SQLException e) {
			logger.error("There was a problem with the validation request");
			e.printStackTrace();
		}
		
		//if the user has entered the correct password, determine whether he is
		//a manager or employee, and redirect to the appropriate page.
		if(validated) {
			try {
				if(us.getUserRole(username) == 1) {
					logger.info("User '" + username + "' logged in as manager.");
					response.sendRedirect("manager.html");
				}
				
				else if(us.getUserRole(username) == 2) {
					logger.info("User '" + username + "' logged in as employee.");
					response.sendRedirect("employee.html");
				}
			} catch (SQLException e) {
				logger.error("There was a problem accessing the user role.");
				e.printStackTrace();
			}
		}
		
		//if the user doesn't exist or has entered the wrong password, reload the 
		//main page
		else {
			logger.info("Improper password for user '" + username + ", or user does not exist'.");
			response.sendRedirect("index.html");
		}
	}
}
