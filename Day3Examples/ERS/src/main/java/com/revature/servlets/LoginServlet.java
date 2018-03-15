	package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.DAOUtilities;
import com.revature.ManagerDAO;
import com.revature.beans.User;

public class LoginServlet extends HttpServlet {
	
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
		
		ManagerDAO m = DAOUtilities.getManDAO();
		ArrayList<User> allUsers = new ArrayList<User>();
		
		try {
			allUsers = m.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		boolean success = false;
		User login = new User();
		
		for(int i =0; i<allUsers.size(); i++){
		       User temp = allUsers.get(i);
		       if(temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
		    	   success = true;
		    	   login = temp;
		       }    
		}
		if(success==true) {
			if(login.getRoleID()==1) {
				session.setAttribute("username", username);
				session.setAttribute("u_id", login.getUserID());
				session.setAttribute("problem", null);
				resp.sendRedirect("profile");
			}else if(login.getRoleID()==2) {
				session.setAttribute("username", username);
				session.setAttribute("u_id", login.getUserID());
				session.setAttribute("problem", null);
				resp.sendRedirect("manager");
			}
		}else {
			session.setAttribute("problem", "incorrect password");
			resp.sendRedirect("login");
		}
		
	}
	
	
}

