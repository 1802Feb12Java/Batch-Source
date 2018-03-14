package com.ers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class ManagerRequestViewServlet
 */
@WebServlet("/ManagerRequestViewServlet")
public class ManagerRequestViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ManagerRequestViewServlet.class);
	private ManagerRequests managerRequests = new ManagerRequests(DatabaseConnection.getDatabaseConnection());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerRequestViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.html");
		}
		String viewType = request.getParameter("viewType");
		String employeeName = request.getParameter("employeeName");
		
		switch (viewType) {
		case "'ALL'":
			logger.info("GET Processed From: " + this.getClass());
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("ALL", "NONE")));
			break;
			
		case "'PENDING'":
			logger.info("GET Processed From: " + this.getClass());
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("PENDING", "NONE")));
			break;
			
		case "'APPROVED'":
			logger.info("GET Processed From: " + this.getClass());
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("APPROVED", "NONE")));
			break;
			
		default:
			logger.info("GET Processed From: " + this.getClass());
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("ALL", employeeName)));
			break;
		}

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		int userId = (int) session.getAttribute("userid");
		String resolutionType = request.getParameter("type");
		int requestId = Integer.parseInt(request.getParameter("id"));
		managerRequests.approveOrDenyRequest(userId, requestId, resolutionType);
		logger.info("PUT processed from: " + this.getClass());
		response.setStatus(200);
	}

}
