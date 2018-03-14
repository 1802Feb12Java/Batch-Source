package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.util.ConnFactory;

public class EmployeeReimbursementsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);

		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("employee-dashboard.html").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		String formUsername = req.getParameter("username");
		String formEmail = req.getParameter("email");
		Integer sessId = (Integer) session.getAttribute("userId");
		
		try {
			// Create ConnFactory object
			ConnFactory cf = new ConnFactory();
			
			// Create connection
			Connection conn = cf.getConnection();
			
			// Create get user query
			String sqlGet = "UPDATE ers_users SET u_username = ?, u_email = ? WHERE u_id = ?";
			
			// Create PreparedStatement object
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			// Set username value in statement
			ps.setString(1, formUsername);
			ps.setString(2, formEmail);
			ps.setInt(3, sessId);
			
			// Execute update
			int rowsUpdated = ps.executeUpdate();
			if(rowsUpdated > 0) {
				// Update successful
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		resp.sendRedirect("employee-dashboard.html");
	}

}
