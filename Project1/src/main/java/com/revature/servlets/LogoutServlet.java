package com.revature.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.implement.EmployeeService;
import com.revature.implement.ManagerService;

public class LogoutServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		PrintWriter pw = resp.getWriter();
//		resp.setContentType("text/html");
//		req.getRequestDispatcher("Index.html").include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("username");
		log.info(user+" logged out");
		session.invalidate();
		resp.sendRedirect("Index.html");
	}
}