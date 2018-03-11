package com.revature.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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

    @Override
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
			us = null;
			e.printStackTrace();
		}
		
		//if the user has entered the correct password, determine whether he is
		//a manager or employee, and redirect to the appropriate page.
		if(validated) {
			try {
				if(us.getUserRole(username) == 1) {
					logger.info("User '" + username + "' logged in as manager.");
					us = null;
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/manager.html");
					rd.forward(request, response);
					//response.sendRedirect("manager.html");
				}
				
				else if(us.getUserRole(username) == 2) {
					logger.info("User '" + username + "' logged in as employee.");
					us = null;
					response.sendRedirect("employee.html");
				}
			} catch (SQLException e) {
				logger.error("There was a problem accessing the user role.");
				us = null;
				e.printStackTrace();
			}
		}
		
		//if the user doesn't exist or has entered the wrong password, reload the 
		//main page
		else {
			logger.info("Improper password for user '" + username + ", or user does not exist'.");
			us = null;
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
		}
	}
}
