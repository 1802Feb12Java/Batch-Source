package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ProfileServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("Profile.html").forward(req, resp);
			/*
			String username = (String) session.getAttribute("username");
			req.getRequestDispatcher("Base.html").include(req, resp);
			pw.println("Hello, "+username+". Welcome to your profile.");
			pw.println("<a href=\"Index.html\">Go back!!</a>");
			pw.write("</div></body></html>");
			*/
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
