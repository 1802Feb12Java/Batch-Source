package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.UserServices;
import com.revature.Users;


public class NewEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserServices us = new UserServices();
    
    public NewEmailServlet() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		Users u = (Users) req.getSession(false).getAttribute("userobj");
		String email = req.getParameter("newemail");
		u.setEmail(email);
		try {
			us.updateUserEmail(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(u.getRoleID() == 2) {
			response.sendRedirect("employees/updateuser.html");
		} else {
			response.sendRedirect("managers/UpdateUser.html");
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		Users u = (Users) req.getSession(false).getAttribute("userobj");
		String email = req.getParameter("newemail");
		u.setEmail(email);
		try {
			us.updateUserEmail(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(u.getRoleID() == 2) {
			response.sendRedirect("employees/updateuser.html");
		} else {
			response.sendRedirect("managers/UpdateUser.html");
		}
	}

}
