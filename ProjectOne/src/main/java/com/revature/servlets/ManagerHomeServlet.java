package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerHomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ManagerHomeServlet doGet called");
//		resp.setContentType("text/html");
//		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("managerhome.html").forward(req, resp);

			
		} else {
			resp.sendRedirect("loginservlet");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//this.doGet(req, resp);
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("managerhome.html").forward(req, resp);
		} else {
			resp.sendRedirect("loginservlet");
		}
	}
	
	
}
