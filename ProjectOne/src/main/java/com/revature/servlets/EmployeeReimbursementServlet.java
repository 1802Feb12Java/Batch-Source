package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.SystemController;

public class EmployeeReimbursementServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("EmployeeReimbursementTableServlet doGet called");
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			String reimbs;
			try {
				reimbs = SystemController.getJsonReimbursements(req.getParameter("username"));
				resp.getWriter().write(reimbs);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			resp.sendRedirect("loginservlet");
		}
	}
	
}
