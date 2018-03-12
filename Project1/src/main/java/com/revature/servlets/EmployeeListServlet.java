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
import com.revature.factory.ConnectionFactory;

public class EmployeeListServlet extends HttpServlet {
	ConnectionFactory cf = ConnectionFactory.getInstance();
	private UserDAOClass userDao = new UserDAOClass(cf.getConnection());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers",
                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
