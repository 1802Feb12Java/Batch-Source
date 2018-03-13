package com.ers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class EmployeeDashServlet
 */
@WebServlet("/EmployeeDashServlet")
public class EmployeeDashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(EmployeeDashServlet.class);

	private EmployeeRequests employeeRequests;
    public EmployeeDashServlet() {
        super();
        employeeRequests = new EmployeeRequests(DatabaseConnection.getDatabaseConnection());
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Mailer.SendMail("scott.g.bennett@gmail.com", "anotherTest", "Still another Test");
		if (session != null) {
			int userId = (int) session.getAttribute("userid");
			int pendingRequests = employeeRequests.getNumberOfPendingRequests(userId);
			int approvedRequests = employeeRequests.getNumberOfApprovedRequests(userId);
			int totalRequests = employeeRequests.getTotalNumberOfRequests(userId);
			ResponseToClient responseToClient = new ResponseToClient(pendingRequests, approvedRequests, totalRequests);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String json = gson.toJson(responseToClient);
			logger.info("GET processed from: " + this.getClass());
	        response.getWriter().write(json);
		}
		else {
			response.sendRedirect("login.html");
		}
	}

	//inner class to make JSON-ifying easier
	class ResponseToClient {
		int pending;
		int approved;
		int total;
		
		ResponseToClient(int pending, int approved, int total) {
			this.pending = pending;
			this.approved = approved;
			this.total = total;
		}
	}
}
