package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.SystemController;

public class EditProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("EditProfileServlet doPost called");
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
//			
//			System.out.println("param(userame): "+req.getParameter("profileFirstname") );
//			System.out.println("param(lastname): "+req.getParameter("profileLastname") );
//			System.out.println("param(email): "+req.getParameter("profileEmail") );
//			System.out.println("param(userame): "+req.getAttribute("profileFirstname") );

			try {
				SystemController.updateUserInfo(req.getParameter("profileUsername"), req.getParameter("profileFirstname"), req.getParameter("profileLastname"), req.getParameter("profileEmail"), req.getParameter("profilePassword"));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.getRequestDispatcher("employeeHome.html").forward(req, resp);
//			String json = SystemController.getJsonUser();
//			resp.getWriter().write(json);
			
		} else {
			resp.sendRedirect("loginservlet");
		}
		
		
		
		
	}
	
	
	
}
