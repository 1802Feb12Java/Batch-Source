package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.beans.User;
import com.revature.util.ConnFactory;

public class LoginServlet extends HttpServlet {
	
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		req.getRequestDispatcher("login.html").include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		String formUsername = req.getParameter("username");
		String formPassword = req.getParameter("password");
		String user = "";
		
		try {
			// Create ConnFactory object
			ConnFactory cf = new ConnFactory();
			
			// Create connection
			Connection conn = cf.getConnection();
			
			// Create get user query
			String sqlGet = "SELECT * FROM ers_users WHERE u_username = ?";
			
			// Create PreparedStatement object
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			// Set username value in statement
			ps.setString(1, formUsername);
			
			// Create ResultSet object
			ResultSet rs = ps.executeQuery();
			
			// Perform checks and redirects
			if(rs.next()) {
				
				username = rs.getString("u_username");
				password = rs.getString("u_password");
				int roleId = rs.getInt("ur_id");
				int userId = rs.getInt("u_id");
				
				if(formPassword.equals(password)) {
					if(roleId == 1) { 
						session.setAttribute("username", username);
						resp.sendRedirect("admin-dashboard");
					} else {
						session.setAttribute("username", username);
						session.setAttribute("userId", userId);
						resp.sendRedirect("employee-dashboard");
					}
				} else {
					resp.sendRedirect("login");
				}
				
			} else {
				resp.sendRedirect("login");
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}

