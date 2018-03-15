package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAOUtilities;
import com.revature.EmployeeDAO;
import com.revature.ManagerDAO;
import com.revature.beans.User;

public class EmpInfoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		
		EmployeeDAO emp = DAOUtilities.getEmpDAO();
		ArrayList<User> user = new ArrayList<User>(); 
		int userid = (Integer)session.getAttribute("u_id");
		try {
			user = emp.viewInfo(userid);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.getWriter().write("[");

		
		for(User u : user) {
			response.getWriter().append("{\"u_id\":\"" + u.getUserID() + "\"," + "\"u_username\":\"" + u.getUsername() + "\"," +
					"\"u_password\":\"" + u.getPassword() + "\"," + "\"u_firstname\":\"" + u.getFirstname() + "\"," + 
					"\"u_lastname\":\"" + u.getLastname() + "\"," + "\"u_email\":\"" + u.getEmail() + "\"" + "}");
			response.getWriter().append("]");
		}
			
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userid = (Integer)session.getAttribute("u_id");
		
		EmployeeDAO eDao = DAOUtilities.getEmpDAO();
		
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		String fn = request.getParameter("firstname");
		String ln = request.getParameter("lastname");
		String em = request.getParameter("email");
		
		try {
			eDao.updateInfo(userid, un, pw, fn, ln, em);
			response.sendRedirect("EmpInfo.html");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
