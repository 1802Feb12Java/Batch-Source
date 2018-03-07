package com.revature.servlets;

//Import required java libraries
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Extend HttpServlet class
public class HelloWorld extends HttpServlet {

	private static final long serialVersionUID = 5816585295949587254L;

	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "Hello World";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Set response content type

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
	}

	public void destroy() {
		// do nothing.
	}
}