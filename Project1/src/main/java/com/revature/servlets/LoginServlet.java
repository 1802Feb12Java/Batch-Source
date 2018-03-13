package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.UserServices;
import com.revature.Users;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UserServices us = new UserServices();

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		HttpSession session = req.getSession();
////		PrintWriter pw = resp.getWriter();
//		resp.setContentType("text/html");
//		req.getRequestDispatcher("Login.html").include(req, resp);
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username + password);
		Users u = null;
		try {
			u = us.login(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(u != null) {
			pw.println("Welcome, "+u.getfName());
//			pw.println("<a href=\"Index.html\">Logout</a>");
			session.setAttribute("userobj", u);
			session.setAttribute("username", username);
			session.setAttribute("problem", null);
			session.setAttribute("firstname", u.getfName());
			session.setAttribute("lastname", u.getlName());
			if(u.getRoleID() ==  1) {
				resp.sendRedirect("./managers/profile.html");
			}else {
				resp.sendRedirect("./employees/Profile.html");
			}
		} else {
			pw.println("problem incorrect password");
			resp.sendRedirect("./index.html");
		}
		
	}
}
