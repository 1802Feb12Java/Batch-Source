package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.User;
import com.revature.dao.UserDAOImpl;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6746639099992838174L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		System.out.println("get");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		System.out.println("Post");
		
		UserDAOImpl userDAO = new UserDAOImpl();
		User user = new User();
		String username = req.getParameter("usernameText");
		String password = req.getParameter("passwordText");
		
		user = userDAO.getUserByUserPass(username, password);
		
		System.out.println(user);
		
		try {
			if(user.equals(null)) {
				try {
					resp.sendRedirect("./ERS_Login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				
				
				try {
					if(user.getUserRoleID() == 0) {
						resp.sendRedirect("./EmployeeHome.html");
					}
					else {
						resp.sendRedirect("./ManagerHome.html");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(NullPointerException e){
			try {
				resp.sendRedirect("./ERS_Login.html");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		

		
	}
}
