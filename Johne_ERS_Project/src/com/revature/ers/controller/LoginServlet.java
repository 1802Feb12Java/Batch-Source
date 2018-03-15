package com.revature.ers.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.ers.dao.ERSUsersDAO;
import com.revature.ers.model.Users;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. Get parameters from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//get the session, if a session does not exist create one
		HttpSession session = request.getSession(true);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		List<Users> usersList = new ArrayList<Users>();
		
		if(username.trim().isEmpty() || password.trim().isEmpty()) {
			request.setAttribute("msg", "Username or Password is empty");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			
			boolean isValidUsername = false;
			boolean isValidPassword = false;
			Users loginUser = null;
			
			try {
				usersList = new ERSUsersDAO().getAllUsers();
				
				for(Users user : usersList) {
					if(username.equals(user.getU_username())) {
						isValidUsername = true;
					}
					if (password.equals(user.getU_password())) {
						isValidPassword = true;
						loginUser = user;
					}
				}
				
				// 2. validate and 3. do it
				if(isValidUsername == false || isValidPassword == false) {
					request.setAttribute("msg", "Username or Password is invalid");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					// 4. forward control
					if(loginUser.getUr_id() == 1) {
						request.setAttribute("firstName", loginUser.getU_firstName());
						request.setAttribute("lastName", loginUser.getU_lastName());
						request.getRequestDispatcher("employeePages/employeeHomepage.jsp").forward(request, response);
					} else {
						request.setAttribute("firstName", loginUser.getU_firstName());
						request.setAttribute("lastName", loginUser.getU_lastName());
						request.getRequestDispatcher("managerPages/managerHomepage.jsp").forward(request, response);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
