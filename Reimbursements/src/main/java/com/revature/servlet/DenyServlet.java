package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.reimbursements.User;
import com.revature.reimbursements.UserServices;




public class DenyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserServices us = new UserServices();

    public DenyServlet() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession(false).getAttribute("userID")==null)
{
	System.out.println("returns null");
}
		int u = (int)request.getSession(false).getAttribute("userID");
		String rid = request.getParameter("remid");
		int remid = Integer.parseInt(rid);
		try {
			us.deny(u, remid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/Reimbursements/ReimburseTable.html");
		
	}

}