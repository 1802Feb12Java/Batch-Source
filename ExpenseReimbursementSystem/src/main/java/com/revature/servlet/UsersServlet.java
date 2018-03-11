package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.revature.bean.User;
import com.revature.services.UserServices;

public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//don't use GET because it is insecure
	}
	
	//get/view user information
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			//TODO: get session id
			//int sessionUser = (int) request.getSession().getAttribute("userID");
			int sessionUser = 1;
			//System.out.println("UserID from Session: " + request.getSession().getAttribute("userID"));
			
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			//create gson for response
			Gson gson = new Gson();
			//create userServices 
			UserServices us = new UserServices();
			//call select
			User u = us.getUserByID(sessionUser);
			if(u != null) {
				String jsonString = gson.toJson(u);
				out.println(jsonString);
			}
			out.close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	//update user information
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//setup responses
		PrintWriter out = response.getWriter();
		//set response content type json
		response.setContentType("application/json");
		try {
			//grab json from put body
			BufferedReader reader = request.getReader();
			
			//gson object to translate json <-> object
			Gson gson = new Gson();
			User newUserInfo = gson.fromJson(reader, User.class);
//			System.out.println(newUserInfo.toString());
			//call update operation
			UserServices us = new UserServices();
			us.updateUser(newUserInfo);

			//write response
			out.println("{");
			out.println("\"sucess\":\"true\"");
			out.println("}");
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			out.println("{");
			out.println("\"sucess\":\"false\"");
			out.println("}");
		}
		catch(SQLException e) {//fail on call update procedure
			e.printStackTrace();
			out.println("{");
			out.println("\"sucess\":\"false\"");
			out.println("}");
		}
		out.close();
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//optional, save for later
	}
}
