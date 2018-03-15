package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		req.getRequestDispatcher("Base.html").include(req, resp);
		HttpSession session = req.getSession(false);
		if (session != null){
			session.invalidate();
		}
		pw.println("<h3>You have successfully logged out!</h3><br/>");
	
		pw.println("<button class='btn btn-custom btn-full-width' type='submit'><a href=\"login\">Go to Login Page</a></body>");
		pw.write("</div></body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}


}
