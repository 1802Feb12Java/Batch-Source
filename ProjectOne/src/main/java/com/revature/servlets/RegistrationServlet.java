package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.daos.EmpDAO;

public class RegistrationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Registration Servlet doPost called");
		
		Employee emp = new Employee(req.getParameter("username"),
				req.getParameter("password"),
				req.getParameter("email"));
//		System.out.println("Params: " + req.getParameter("username") + ", " + req.getParameter("password"));
		try {
			EmpDAO.getInstance().addUserToTable(emp);
			
//			PrintWriter out = resp.getWriter();
//			out.print("Registration Successfully completed");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
