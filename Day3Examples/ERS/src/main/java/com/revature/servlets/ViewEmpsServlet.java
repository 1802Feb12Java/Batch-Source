package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAOUtilities;
import com.revature.ManagerDAO;
import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public class ViewEmpsServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		ManagerDAO m = DAOUtilities.getManDAO();
		ArrayList<User> employees = new ArrayList<User>(); 
		try {
			employees = m.viewAllEmps();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		response.getWriter().write("[");
		int count = 0;
		
		for(User u : employees) {
			response.getWriter().append("{\"u_id\":\"" + u.getUserID() + "\"," + "\"u_username\":\"" + u.getUsername() + "\"," + 
					"\"u_firstname\":\"" + u.getFirstname() + "\"," + 
					"\"u_lastname\":\"" + u.getLastname() + "\"," + "\"u_email\":\"" + u.getEmail() + "\"" + "}");
			count++;

			if(count < employees.size()) {
				response.getWriter().append(",");
	
			} else {
				response.getWriter().append("]");
			}
			
			
		}
		System.out.println("Should be leaving the for loop. Count is " + count);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int userid = Integer.parseInt(request.getParameter("uid"));
		System.out.println("user id"+userid);
		session.setAttribute("sel_uid", userid);
		System.out.println("selected uid"+session.getAttribute("sel_uid"));
		//response.sendRedirect("singleEmpReqs.html");
		request.getRequestDispatcher("singleEmpReqs.html").include(request, response);
		
	}
	
}
