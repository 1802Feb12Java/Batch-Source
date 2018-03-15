package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.controllers.SystemController;

public class LoginServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet doPost called");
		HttpSession session = req.getSession();
		
		log.info("LOGGED IN");
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = SystemController.validateUser(username, password);
		if(user != null) {
			if(user.getAccess() == 0) {
				session.setAttribute("username", username);
				session.setAttribute("empId", user.getEmpId());
				//System.out.println("Redirect to employeehomeservlet");
				resp.sendRedirect("employeehomeservlet");
			} else {
				session.setAttribute("username", username);
				session.setAttribute("empId", user.getEmpId());
				resp.sendRedirect("managerhomeservlet");
			}
			
		} else {
			pw.write("Username or Password was incorrect. Try Again");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
