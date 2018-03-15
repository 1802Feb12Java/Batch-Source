package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.Manager;

public class ApproveDenyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);
		int requestId = Integer.valueOf(req.getParameter("reqid"));
		int appOrD = Integer.valueOf(req.getParameter("aod"));
		String approver = (String) session.getAttribute("user");

		try {
			Manager.approveOrDeny(requestId, appOrD, approver);
		} catch (SQLException e) {
		}

		req.getRequestDispatcher("manager-approve.html").forward(req, resp);
	}

}