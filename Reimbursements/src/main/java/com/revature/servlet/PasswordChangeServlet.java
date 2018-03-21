package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.reimbursements.UserServices;

public class PasswordChangeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String newPass = req.getParameter("newPassword");
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		UserServices us = new UserServices();
		int toget = (int) session.getAttribute("userID"); 
		try {
			us.updateUser("U_PASSWORD", newPass, toget);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("/EmployeeHome");
		
	}
	
}
