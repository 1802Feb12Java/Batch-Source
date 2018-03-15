package com.revature.servlet;

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AdminDashboardServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(AdminDashboardServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);
		
		if(session!=null && session.getAttribute("username") != null){
			logger.info("Confirming session is valid before redirecting to admin-dashboard");
			req.getRequestDispatcher("admin-dashboard.html").forward(req, resp);
		} else {
			logger.info("User tried to access admin-dashboard without being logged in");
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
