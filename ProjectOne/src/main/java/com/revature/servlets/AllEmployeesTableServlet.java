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

public class AllEmployeesTableServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AllEmployeestTableServlet doGet called");
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			//System.out.println("Curret User: "+session.getAttribute("username"));
			String reimbs = "";
			try {
				reimbs = SystemController.getJsonAllUser();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println("reimbs: "+reimbs);
			resp.getWriter().write(reimbs);

		} else {
			resp.sendRedirect("loginservlet");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AllEmployeesTable doPost called");
		this.doGet(req, resp);
	}
	
}
