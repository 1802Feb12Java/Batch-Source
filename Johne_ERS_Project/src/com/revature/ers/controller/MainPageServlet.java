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

import com.revature.ers.dao.ERSUsersDAO;
import com.revature.ers.model.Users;

/**
 * Servlet implementation class MainPageServlet
 */
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * return current session if it exists,
		 * otherwise user will have to login before viewing the homepage
		 */
		List<Users> usersList = new ArrayList<Users>();
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			try {
				usersList = new ERSUsersDAO().getAllUsers();
				Users loginUser = null;
				//search for user in the list
				for(Users user : usersList) {
					if(username.equals(user.getU_username()) && password.equals(user.getU_password())) {
						loginUser = user;
					}
					request.setAttribute("firstName", loginUser.getU_firstName());
					request.setAttribute("lastName", loginUser.getU_lastName());
					
					//if user role id is 1, send them to employeeHomepage
					//otherwise send manager to the managerHomepage
					if(loginUser.getUr_id() == 1) {
						request.getRequestDispatcher("employeePages/employeeHomepage.jsp").include(request, response);
					} else {
						request.getRequestDispatcher("managerPages/managerHomepage.jsp").include(request, response);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			request.setAttribute("msg",	"login first");
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}

}
