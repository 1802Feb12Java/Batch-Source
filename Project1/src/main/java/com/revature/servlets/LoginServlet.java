package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.DAOs.UserDAOClass;
import com.revature.beans.User;
import com.revature.factory.ConnectionFactory;

public class LoginServlet extends HttpServlet {
	
	private ConnectionFactory cf = ConnectionFactory.getInstance();
	private UserDAOClass userDAO = new UserDAOClass(cf.getConnection());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		req.getRequestDispatcher("Login.html").include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		ArrayList<User> allUsers = null;
		
		try {
			allUsers = userDAO.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User userWithGivenUsername = null;
		int indexOfUser = -1;
		for(User u : allUsers) {
			if(u.getUsername().equals(username)) {
				userWithGivenUsername = u;
				indexOfUser = allUsers.indexOf(u);
				break;
			}
		}
		if(userWithGivenUsername == null) {
			session.setAttribute("problem", "incorrect password");
			resp.sendRedirect("login");
		}
		else {
			if(allUsers.get(indexOfUser).getPassword().equals(password)) {
				session.setAttribute("uid", allUsers.get(indexOfUser).getId());
				session.setAttribute("problem", null);	//should this be password and password?
				if(allUsers.get(indexOfUser).getRoleId() == 1) {	//if manager
					session.setAttribute("role", "manager");
					resp.sendRedirect("http://localhost:4200/managerHome");
				}
				else {	//if employee
					session.setAttribute("role", "employee");
					resp.sendRedirect("http://localhost:4200/employeeHome");
				}
			}
			else {
				session.setAttribute("problem", "incorrect password");
				resp.sendRedirect("login");
			}
		}
	}

}