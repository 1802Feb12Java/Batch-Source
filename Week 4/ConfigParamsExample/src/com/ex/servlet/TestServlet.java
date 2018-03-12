package com.ex.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//TODO: access ServletConfig params
		String firstParam = getServletConfig().getInitParameter("param1");
		String secondParam = getInitParameter("knsfkjsdnfkjdsfnk");
		
		System.out.println("firstParam: " + firstParam);
		System.out.println("secondParam: " + secondParam);
		
		
		getInitParameterNames();
		
		
		//TODO: access ServletContext params
		
		String favoriteColor = getServletConfig().getServletContext().getInitParameter("favoriteColor");
		String sport = getServletContext().getInitParameter("sport");
		
		System.out.println("favoriteColor: " + favoriteColor);
		System.out.println("sport: " + sport);
		
	}
	
}
