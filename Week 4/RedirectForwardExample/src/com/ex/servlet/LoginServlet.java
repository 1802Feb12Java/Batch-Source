package com.ex.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Serve login.html
		System.out.println("doGet of LoginServlet");
		RequestDispatcher rd = req.getRequestDispatcher("login.html");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Authenticate user
		System.out.println("doPost of LoginServlet");
		//TODO: redirect /home
//		resp.sendRedirect("home");
		req.getRequestDispatcher("home.html").forward(req, resp);
	}
	
}
