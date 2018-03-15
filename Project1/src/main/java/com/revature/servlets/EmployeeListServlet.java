package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DAOs.UserDAOClass;
import com.revature.beans.User;
import com.revature.util.ConnectionFactory;
import com.revature.util.FrontController;

public class EmployeeListServlet extends HttpServlet {
	ConnectionFactory cf = ConnectionFactory.getInstance();
	private UserDAOClass userDao = new UserDAOClass(cf.getConnection());
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FrontController.addHeader(resp);
		resp.setContentType("application/json");
		
		ArrayList<User> userList = null;
		try {
			userList = userDao.getAllUsers();
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(userList);
			resp.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
