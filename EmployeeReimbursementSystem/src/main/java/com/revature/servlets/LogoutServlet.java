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

public class LogoutServlet extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2604736801510564352L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		
		req.getSession().invalidate();
		try {
			resp.sendRedirect("./ERS_Login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		
	}
}
