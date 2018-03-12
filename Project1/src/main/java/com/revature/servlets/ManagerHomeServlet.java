package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ManagerHomeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("uid") != null){
			req.getRequestDispatcher("ManagerHome.html").forward(req, resp);
			
			int uid = (Integer) session.getAttribute("uid");
			pw.println("Hello, user "+uid+". Welcome to your profile.");
			pw.println("<a href=\"Index.html\">Go back!!</a>");
			pw.write("</div></body></html>");
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
