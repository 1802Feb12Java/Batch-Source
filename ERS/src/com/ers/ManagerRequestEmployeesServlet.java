package com.ers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ManagerRequestEmployeesServlet
 */
@WebServlet("/ManagerRequestEmployeesServlet")
public class ManagerRequestEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ManagerRequestEmployeesServlet.class);
    ManagerRequests managerRequests;   

    public ManagerRequestEmployeesServlet() {
        super();
        managerRequests = new ManagerRequests(DatabaseConnection.getDatabaseConnection());
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().write(Arrays.toString(managerRequests.getAllEmployees()));
		logger.info("GET Processed from: " + this.getClass());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		
		int result = managerRequests.registerEmployee(firstName, lastName, email, userName);
		if (result == -1) {
			response.setStatus(401);
		}
		else if (result == -2) {
			response.setStatus(402);
		}
		else {
			response.setStatus(200);
		}
		logger.info("POST Processed from: " + this.getClass());
	}

}
