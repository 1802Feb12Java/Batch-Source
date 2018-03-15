package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.controllers.SystemController;

public class NewReimbursementServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("New Reimbursement Servlet doPost called");
//		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		HttpSession session = req.getSession(false);
		Integer empId = Integer.parseInt(session.getAttribute("empId").toString());
		try {
			SystemController.addReimbursementToDB(Double.parseDouble(req.getParameter("amount"))
					, req.getParameter("description"), sqlDate,
					empId, Integer.parseInt(req.getParameter("reimbType")), (req.getParameter("imageinput")).getBytes());
			resp.sendRedirect("employeeHome.html");
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
