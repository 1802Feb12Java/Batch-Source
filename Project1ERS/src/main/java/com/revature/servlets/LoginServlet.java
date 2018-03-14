package com.revature.servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class.getName());
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		PrintWriter pw = resp.getWriter();
//		resp.setContentType("text/html");
//		req.getRequestDispatcher("login.html").include(req, resp);
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(true);
		log.info("\n--Beginning of a New Session--\n");
		
		resp.setContentType("text/html");
		//PrintWriter pw = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("pass");
		
		int verifResponse = 3;
		try {
			verifResponse = com.revature.beans.Verification.loginVerify(username, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(verifResponse == 1) {
			session.setAttribute("username", username);
			session.setAttribute("problem", null);
			session.setAttribute("usertype", "employee");
			try {
				session.setAttribute("userId", com.revature.dao.UserDAOImp.getUserID(username));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resp.sendRedirect("employeehome.html");
		} else if (verifResponse == 2) {
			session.setAttribute("username", username);
			session.setAttribute("problem", null);
			session.setAttribute("usertype", "manager");
			try {
				session.setAttribute("userId", com.revature.dao.UserDAOImp.getUserID(username));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resp.sendRedirect("managerhome.html");
		} else if (verifResponse == 0) {
			session.setAttribute("problem", "incorrect password");
			resp.sendRedirect("login.html");
		} else if (verifResponse == 3) {
			session.setAttribute("problem", "username doesn't exist");
			resp.sendRedirect("login.html");
		}
		
		
	}//do post
	
}//end class
