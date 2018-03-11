package com.ers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Servlet implementation class EmployeeInfoServlet
 */
@WebServlet("/EmployeeInfo")
public class EmployeeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeRequests employeeRequests;
    
	public EmployeeInfoServlet() {
        super();
        employeeRequests = new EmployeeRequests(DatabaseConnection.getDatabaseConnection());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userid");
		String[] info = employeeRequests.getEmployeeInfo(userId);
		
		EmployeeInfo employeeInfo = new EmployeeInfo(info[0], info[1], info[2], info[3]);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(employeeInfo);
        response.getWriter().write(json);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int userId = (int) session.getAttribute("userid");
		String oldUserName = (String) session.getAttribute("username");
		String newUserName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String result = employeeRequests.updateEmployee(userId, oldUserName, newUserName, firstName, lastName, email);
		if (result.equals("FAILURE")) {
			response.getWriter().write("FAILURE");
		}
		else {
			//update first name in cookie
			Cookie cookie = new Cookie("firstName", firstName);
	        cookie.setMaxAge(60*60*24); //set cookie to live for one day
	        response.addCookie(cookie);
	        
	        //update session variable
	        request.getSession(false).setAttribute("username", newUserName);
	        
			response.getWriter().write("SUCCESS");
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