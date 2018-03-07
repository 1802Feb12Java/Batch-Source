package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet {

	private static final long serialVersionUID = -7277435585400150737L;

	// request handler
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Get Roll Tide...");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<title>Example</title>" + "<body bgcolor='#FFFFFF'>");

		out.println("<h2>Button Clicked</h2>");

		String DATA = request.getParameter("DATA");

		if (DATA != null) {
			out.println(DATA);
		} else {
			out.println("No text entered.");
		}

		out.println("<P>Return to <A HREF=\"../simpleHTML.html\">Form</A>");
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Post RT..");
	}
}
