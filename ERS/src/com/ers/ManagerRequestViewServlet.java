package com.ers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
