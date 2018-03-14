package com.ers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Servlet implementation class EmployeeInfoServlet
 */
@WebServlet("/EmployeeInfo")
public class EmployeeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(EmployeeInfoServlet.class);
	EmployeeRequests employeeRequests;
    LoginDAO loginDAO;
	public EmployeeInfoServlet() {
        super();
        employeeRequests = new EmployeeRequests(DatabaseConnection.getDatabaseConnection());
        loginDAO = new LoginDAO(DatabaseConnection.getDatabaseConnection());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.html");
		}
		int userId = (int) session.getAttribute("userid");
		String[] info = employeeRequests.getEmployeeInfo(userId);
		
		EmployeeInfo employeeInfo = new EmployeeInfo(info[0], info[1], info[2], info[3]);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(employeeInfo);
		logger.info("GET processed from: " + this.getClass());
        response.getWriter().write(json);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userid");
		
		String column = request.getParameter("column");
		String newValue = request.getParameter("newValue");
		
		String result = employeeRequests.updateEmployee(userId, column, newValue);
		if (result.equals("EMAILFAIL")) {
			logger.info("PUT FAILURE FROM: " + this.getClass());
			response.setStatus(401);
		}
		if (result.equals("USERNAMEFAIL")) {
			logger.info("PUT FAILURE FROM: " + this.getClass());
			response.setStatus(402);
		}
		else {
			
			//update first name in cookie
			if (column.equals("firstName")) {
				Cookie cookie = new Cookie("firstName", newValue);
		        cookie.setMaxAge(60*60*24); //set cookie to live for one day
		        response.addCookie(cookie);
			}
			
			//update username session variable
			if(column.equals("userName")) {
				 request.getSession(false).setAttribute("username", newValue);
			}
	        
	       
	        logger.info("PUT successfully processed from: " + this.getClass());
			response.setStatus(200);
		}
	}
	
	//inner class to make JSON-ifying easier
	private class EmployeeInfo {
		@SuppressWarnings("unused")
		String firstName, lastName, userName, email;
		EmployeeInfo(String fName, String lName, String uName, String email) {
			this.firstName = fName;
			this.lastName = lName;
			this.userName = uName;
			this.email = email;
		}
	}
}