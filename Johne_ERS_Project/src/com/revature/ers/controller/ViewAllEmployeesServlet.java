package com.revature.ers.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.revature.ers.dao.ERSUsersDAO;
import com.revature.ers.model.Users;

/**
 * Servlet implementation class ViewAllEmployeesServlet
 */
public class ViewAllEmployeesServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(ViewAllEmployeesServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Users> users = new ArrayList<Users>();
		try {
			users = new ERSUsersDAO().viewAllUsers();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		JSONArray allUsers = new JSONArray();
		
		/**
		 * set property values for JSON user object
		 * put each users in a JSON container and each index will represent a user and their properties
		 * add all users to the array
		 */
		System.out.println("get users");
		System.out.println(users);
		for(int i = 0; i < users.size(); i++) {
			JSONObject userSend = new JSONObject();
			userSend.put("ID", users.get(i).getU_id());
			userSend.put("Username", users.get(i).getU_username());
			userSend.put("FirstName", users.get(i).getU_firstName());
			userSend.put("LastName", users.get(i).getU_lastName());
			userSend.put("Email", users.get(i).getU_email());
			userSend.put("URole", users.get(i).getUr_role());
			
			//container.put(i, allUsers);
			allUsers.add(userSend);
		}
		json.put("UsersList", allUsers);
		response.setContentType("application/json");
        PrintWriter pw = response.getWriter(); 
        pw.print(json.toString());
        pw.close();
	} 

}
