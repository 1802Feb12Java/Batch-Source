package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.UserServices;
import com.revature.Users;


public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserServices us = new UserServices();

    public ApproveServlet() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Users u = (Users) request.getSession(false).getAttribute("userobj");
		String rid = request.getParameter("remid");
		int remid = Integer.parseInt(rid);
		try {
			us.approve(u, remid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("./managers/tables.html");
		
	}

}
