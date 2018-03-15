package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.Employee;
import com.revature.controllers.User;

public class OneReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 1 user's reimbursements, not 1 single reimbursement
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);
		String username = (String) session.getAttribute("user");
		String jsa = null;

		try {
			jsa = User.viewReimbursementRequests(username);
		} catch (SQLException e) {
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(jsa);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);

		String username = (String) session.getAttribute("user");
		double amount = Double.valueOf(req.getParameter("amnt"));
		String description = req.getParameter("desc");
		int type = Integer.valueOf(req.getParameter("type"));

		try {
			Employee.submitRequest(username, amount, description, type);
		} catch (SQLException e) {
		}

		resp.sendRedirect("employee.html");
	}

}