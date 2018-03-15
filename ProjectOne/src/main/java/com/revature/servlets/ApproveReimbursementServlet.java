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

public class ApproveReimbursementServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ApproveReimbusementServlet doGet called");
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session!=null && session.getAttribute("username") != null){
			
			try {
				SystemController.updateReimbursement(Integer.parseInt(req.getParameter("ur_id")), Integer.parseInt(req.getParameter("status")));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			System.out.println("req id: "+req.getParameter("ur_id"));
//			System.out.println("new status: "+req.getParameter("status"));
			
		} else {
			resp.sendRedirect("loginservlet");
		}
	}
	
	
	
}
