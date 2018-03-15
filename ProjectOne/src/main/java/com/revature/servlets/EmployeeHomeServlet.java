package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeHomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("EmployeeHomeServlet doGet called");
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("employeeHome.html").forward(req, resp);
			
//			String username = (String) session.getAttribute("username");
//			req.getRequestDispatcher("Base.html").include(req, resp);
//			pw.println("Hello, "+username+". Welcome to your profile.");
//			pw.println("<a href=\"Index.html\">Go back!!</a>");
//			pw.write("</div></body></html>");
			
		} else {
			resp.sendRedirect("loginservlet");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("EmployeeHome doPost called");
		this.doGet(req, resp);
	}
	
}
