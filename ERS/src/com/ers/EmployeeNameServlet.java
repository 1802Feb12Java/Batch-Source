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
 * Servlet implementation class EmployeeNameServlet
 */
@WebServlet("/EmployeeNameServlet")
public class EmployeeNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(EmployeeInfoServlet.class);
	ManagerRequests managerRequests = new ManagerRequests(DatabaseConnection.getDatabaseConnection());
    public EmployeeNameServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("GET Processed From: " + this.getClass());
		response.getWriter().write(Arrays.toString(managerRequests.getEmployeeNames()));
	}

}
