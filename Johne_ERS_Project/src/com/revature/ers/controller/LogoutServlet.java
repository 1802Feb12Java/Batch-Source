package com.revature.ers.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * log user out and kill their session
		 */
		request.setAttribute("msg", "You have logged out successfully");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		HttpSession session = request.getSession();
		session.invalidate();
		
		
		
	}

}
