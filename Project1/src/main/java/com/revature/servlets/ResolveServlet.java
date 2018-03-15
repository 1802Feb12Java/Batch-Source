package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.implement.EmployeeService;
import com.revature.implement.ManagerService;

public class ResolveServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		Integer reid = new Integer (req.getParameter("reid"));
		Integer type = new Integer (req.getParameter("type"));
		Integer id = (Integer) session.getAttribute("id");
		String user = (String) session.getAttribute("username");
		String ret = "";
		if (type == 1) {
			ret = "approved";
		} else if (type == 2) {
			ret = "denied";
		}
		ManagerService Mdao = new ManagerService();
		Mdao.resolve(type, id, reid);
		log.info(user+" "+ret+" reimbursement " +reid);
		resp.sendRedirect("ManagerProfile.html");
	}
}
