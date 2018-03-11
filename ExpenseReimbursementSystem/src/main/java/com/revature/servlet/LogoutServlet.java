package com.revature.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();  
        session.invalidate();
        System.out.println("you are logged out");
	}
}
