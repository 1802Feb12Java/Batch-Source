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

public class LoginServlet extends HttpServlet {
	
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
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		ConnFactory cf = new ConnFactory();
		
		try(Connection conn = cf.getConnection()) {
			
			// Create get user query
			String sqlGet = "SELECT * FROM ers_users WHERE u_username = ?";
			
			// Instantiate ps
			PreparedStatement ps = conn.prepareStatement(sqlGet);
			
			// Set username value in statement
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resp.sendRedirect("index");
			} else {
				resp.sendRedirect("login");
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
//		if(password.equals("admin123")) {
//			/*pw.println("Welcome, "+username);
//			pw.println("<a href=\"Index.html\">Go back</a>");*/
//			session.setAttribute("username", username);
//			session.setAttribute("problem", null);
//			resp.sendRedirect("index");
//		} else {
////			pw.println("Lol no");
////			pw.println("<a href=\"Index.html\">Go back</a>");
//			session.setAttribute("problem", "incorrect password");
//			resp.sendRedirect("login");
//			
//		}
		
	}

}

