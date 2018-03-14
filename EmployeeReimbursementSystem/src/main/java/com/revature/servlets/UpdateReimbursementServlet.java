package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.beans.User;
import com.revature.dao.UserDAOImpl;

public class UpdateReimbursementServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1320697891363873277L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		System.out.println("get>>>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{

		UserDAOImpl userDAO = new UserDAOImpl();
		User user = (User) req.getSession(false).getAttribute("user");
		
		System.out.println("test");

		
		if(!(req.getParameter("approve") == null)) {
			System.out.println(req.getParameter("approve"));
		}
		else {
			System.out.println(req.getParameter("deny"));
		}
//		user.setUsername(req.getParameter("usernameText")); 
//		user.setPassword(req.getParameter("passwordText"));
//		user.setFirstName(req.getParameter("firstName"));
//		user.setLastName(req.getParameter("lastName"));
//		user.setEmail(req.getParameter("email"));
//		
//		userDAO.updateUser(user);
//		req.getSession().setAttribute("user", user);
		
		try {
			resp.sendRedirect("./ManagerReimbursementView.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
