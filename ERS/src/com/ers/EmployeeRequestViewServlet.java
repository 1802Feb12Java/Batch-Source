package com.ers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeRequestViewServlet
 */
@WebServlet("/EmployeeRequestViewServlet")
public class EmployeeRequestViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeRequests employeeRequests;
	
    public EmployeeRequestViewServlet() {
        super();
        employeeRequests = new EmployeeRequests(DatabaseConnection.getDatabaseConnection());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewType = request.getParameter("viewType");
		String[] viewResults;
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userid");
		
		switch (viewType) {
		case "'ALL'":
			viewResults = viewAll(userId);
			response.getWriter().write(Arrays.toString(viewResults));
			break;
			
		case "'PENDING'":
			viewResults = viewPending(userId);
			response.getWriter().write(Arrays.toString(viewResults));
			break;
			
		case "'APPROVED'":
			viewResults = viewApproved(userId);
			response.getWriter().write(Arrays.toString(viewResults));
			break;
			
		default:
			break;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String[] viewAll(int userId) {
		return employeeRequests.getAllEmployeeRequests(userId, "");
	}
	
	private String[] viewPending(int userId) {
		return employeeRequests.getAllEmployeeRequests(userId, "PENDING");
	}
	
	private String[] viewApproved(int userId) {
		return employeeRequests.getAllEmployeeRequests(userId, "APPROVED");
	}
}
