package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.util.ConnFactory;

public class AdminUsersServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(AdminDashboardServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
		
		if(session!=null && session.getAttribute("username") != null){
			logger.info("Validating admin session and redirecting to /users");
			req.getRequestDispatcher("users.html").forward(req, resp);
			
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
