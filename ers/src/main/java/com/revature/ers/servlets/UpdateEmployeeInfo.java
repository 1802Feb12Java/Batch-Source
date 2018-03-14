package com.revature.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.revature.ers.jsonifiers.ParseJSON;
import com.revature.ers.users.User;
import com.revature.ers.users.UserServices;

/**
 * Servlet implementation class UpdateEmployeeInfo
 */
public class UpdateEmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployeeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = Logger.getRootLogger();
		UserServices us = new UserServices();
		
		//parse the object into JSON
		JSONObject employee = ParseJSON.parsePost(request);
		Integer u_ID = (Integer) request.getSession(false).getAttribute("userID");
		int id = u_ID.intValue();
		
		User user = null;
		System.out.println(u_ID.intValue());
		//get the user
		try {
			user = us.getUser(id);
		} catch (SQLException e1) {
			log.error("There was a problem getting the user object.");
			e1.printStackTrace();
		}
	
		//update the user
		System.out.println(user.getU_password());
		try {
			user.setU_firstName((String) employee.get("fname"));
			user.setU_lastName((String) employee.get("lname"));
			user.setU_email((String) employee.get("email"));
			us.updateUser(user);
			log.info(employee.get("fname") + " " + employee.get("lname") + " updated user information");
		} catch (SQLException e) {
			log.error("Problem updating the user");
			e.printStackTrace();
		}		
   }
}
