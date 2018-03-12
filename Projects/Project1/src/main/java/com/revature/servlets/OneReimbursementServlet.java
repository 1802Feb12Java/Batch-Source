package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.controllers.Employee;

public class OneReimbursementServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Submit 1 reimbursement
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[Post] One Reimburesment");

		String username = req.getParameter("user");
		double amount = Double.valueOf(req.getParameter("amnt"));
		String description = req.getParameter("desc");
		int type = Integer.valueOf(req.getParameter("type"));

		try {
			Employee.submitRequest(username, amount, description, type);
		} catch (SQLException e) {
		}
	}

}
