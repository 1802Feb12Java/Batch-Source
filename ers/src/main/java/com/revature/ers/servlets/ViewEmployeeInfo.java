package com.revature.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.ers.jsonifiers.ToJSON;
import com.revature.ers.users.User;
import com.revature.ers.users.UserServices;

/**
 * Servlet implementation class ViewEmployeeInfo
 */
public class ViewEmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger = Logger.getRootLogger();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer u_ID = (Integer) request.getSession(false).getAttribute("userID");
		JSONObject json = new JSONObject();	
		User user = null;
		UserServices us = new UserServices();
		
		try {
			user = us.getUser(u_ID.intValue());
		} catch (SQLException e) {
			logger.error("There was an error communicating with the database.");
			e.printStackTrace();
		}		
		json = ToJSON.employee(user);
		response.getWriter().print(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
