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
		
		Log.info("Info to log");
		
		if(session!=null && session.getAttribute("username") != null){
			
			req.getRequestDispatcher("admin-dashboard.html").forward(req, resp);
			/*
			String username = (String) session.getAttribute("username");
			req.getRequestDispatcher("Base.html").include(req, resp);
			pw.println("Hello, "+username+". Welcome to your profile.");
			pw.println("<a href=\"Index.html\">Go back!!</a>");
			pw.write("</div></body></html>");
			*/
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
