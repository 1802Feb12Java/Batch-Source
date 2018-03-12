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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerRequestEmployeesServlet() {
        super();
        managerRequests = new ManagerRequests(DatabaseConnection.getDatabaseConnection());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().write(Arrays.toString(managerRequests.getAllEmployees()));
		logger.info("GET Processed from: " + this.getClass());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		logger.info("POST Processed from: " + this.getClass());
	}

}
