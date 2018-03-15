package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.UserServices;
import com.revature.Users;


public class NewPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServices us = new UserServices();
	
    public NewPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		Users u = (Users) req.getSession(false).getAttribute("userobj");
		String oldpass = req.getParameter("oldpass");
		String newpass = req.getParameter("newpass");
		if(u.getPassword().equals(oldpass)) {
			u.setPassword(newpass);
		
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
		} else {
			PrintWriter pw = response.getWriter();
			pw.print("wrong password");
		}
//		if(u.getRoleID() == 2) {
//			response.sendRedirect("employees/updateuser.html");
//		} else {
//			response.sendRedirect("managers/UpdateUser.html");
//		}
	}

}
