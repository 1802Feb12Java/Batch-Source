package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.util.ConnFactory;

public class LoginServlet extends HttpServlet {
	
	private String username;
	private String password;
	private Logger logger = Logger.getLogger(LoginServlet.class);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		req.getRequestDispatcher("login.html").include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		
		String formUsername = req.getParameter("username");
		String formPassword = req.getParameter("password");
		
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
						logger.info("Logging in admin");
						session.setAttribute("username", username);
						session.setAttribute("adminId", userId);
						resp.sendRedirect("admin-dashboard");
					} else {
						logger.info("Logging in employee");
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

