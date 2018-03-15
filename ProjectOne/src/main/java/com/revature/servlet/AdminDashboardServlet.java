package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminDashboardServlet extends HttpServlet {
	
	private static final Logger Log = Logger.getLogger(AdminDashboardServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		
		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("admin-dashboard.html").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
