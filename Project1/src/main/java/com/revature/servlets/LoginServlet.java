package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.implement.EmployeeService;
import com.revature.implement.ManagerService;
import com.revature.objects.Employee;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		req.getRequestDispatcher("Login.html").include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		//PrintWriter pw = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		ManagerService Mdao = new ManagerService();
		EmployeeService Edao = new EmployeeService();

		if(Mdao.login(username, password)){
			session.setAttribute("username", username);
			session.setAttribute("problem", null);
			session.setAttribute("id",Mdao.getId(username));
			log.info(username+" logged in.");
			resp.sendRedirect("ManagerProfile.html");
		} else if(Edao.login(username, password)) {
			session.setAttribute("username", username);
			session.setAttribute("problem", null);
			session.setAttribute("id",Mdao.getId(username));
			resp.sendRedirect("EmployeeProfile.html");
			log.info(username+" logged in.");
		} else {
			session.setAttribute("problem", "incorrect password");
			log.info("No login with Username: "+username+" and Password: "+password);
			resp.sendRedirect("Login.html");
		}
		
	}
}
