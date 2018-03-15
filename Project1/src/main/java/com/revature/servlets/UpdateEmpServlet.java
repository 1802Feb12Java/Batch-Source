package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.implement.EmployeeService;

public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		System.out.println("getting here ok");
		Integer id = (Integer) session.getAttribute("id");
		String username = req.getParameter("usr");
		String password = req.getParameter("psw");
		String firstname = req.getParameter("fnm");
		String lastname = req.getParameter("lnm");
		String email = req.getParameter("eml");
		System.out.println(id+username+password+firstname+lastname+email);

		EmployeeService Edao = new EmployeeService();

		Edao.updateMan(id, username, password, firstname, lastname,email);
		log.info("User Id "+id+" updated their personal information");
		resp.sendRedirect("EmployeeAbout.html");
	}

}
