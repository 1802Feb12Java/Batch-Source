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
 * Servlet implementation class ManagerRequestViewServlet
 */
@WebServlet("/ManagerRequestViewServlet")
public class ManagerRequestViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		String viewType = request.getParameter("viewType");
		
		
		switch (viewType) {
		case "'ALL'":
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("ALL")));
			break;
			
		case "'PENDING'":
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("PENDING")));
			break;
			
		case "'APPROVED'":
			response.getWriter().write(Arrays.toString(managerRequests.getRequests("APPROVED")));
			break;
			
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		int userId = (int) session.getAttribute("userid");
		String resolutionType = request.getParameter("type");
		int requestId = Integer.parseInt(request.getParameter("requestid"));
		managerRequests.approveOrDenyRequest(userId, requestId, resolutionType);
	}

}
