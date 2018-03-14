package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.revature.DAOs.UserDAOClass;
import com.revature.beans.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.FrontController;

public class LoginServlet extends HttpServlet {
	
	private ConnectionFactory cf = ConnectionFactory.getInstance();
	private UserDAOClass userDAO = new UserDAOClass(cf.getConnection());
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LoginServlet.class);

	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FrontController.addHeader(resp);
		resp.setContentType("text/html");
		
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		req.getRequestDispatcher("Login.html").include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		FrontController.addHeader(resp);
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
			log.info("User tried to log with invalid username");
			resp.sendRedirect("http://localhost:4200/login");
		}
		else {
			if(allUsers.get(indexOfUser).getPassword().equals(password)) {
				session.setAttribute("uid", allUsers.get(indexOfUser).getId());
				session.setAttribute("problem", null);	//should this be password and password?
				if(allUsers.get(indexOfUser).getRoleId() == 1) {	//if manager
					session.setAttribute("role", "manager");
					log.info("Manager logged in. User ID = "+session.getAttribute("uid"));
					resp.sendRedirect("http://localhost:4200/managerHome");
				}
				else {	//if employee
					session.setAttribute("role", "employee");
					log.info("Employee logged in. User ID = "+session.getAttribute("uid"));
					resp.sendRedirect("http://localhost:4200/employeeHome");
				}
			}
			else {
				session.setAttribute("problem", "incorrect password");
				log.info("User tried to log with incorrect password for username "+username);
				resp.sendRedirect("http://localhost:4200/login");
			}
		}
	}

}