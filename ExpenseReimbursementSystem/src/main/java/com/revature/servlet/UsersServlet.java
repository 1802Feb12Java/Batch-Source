package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.bean.User;
import com.revature.services.UserServices;

public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{}
	
	//get/view user information
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			HttpSession session = request.getSession(false);
			int sessionUser = (int)(session.getAttribute("userID"));
			//int sessionUser = 1;
			//System.out.println("UserID from Session: " + request.getSession().getAttribute("userID"));
			
			//create gson for response
			Gson gson = new Gson();
			//create userServices 
			UserServices us = new UserServices();
			//call select
			User u = us.getUserByID(sessionUser);
			//setup response
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			if(u != null) {
				String jsonString = gson.toJson(u);
				out.println(jsonString);
			}
			out.close();
			response.setStatus(200);
		
		} catch (ClassNotFoundException e) {
			response.setStatus(500);
			e.printStackTrace();
		} catch (SQLException e) {
			response.setStatus(500);
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
			response.setStatus(200);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			response.setStatus(500);
		}
		catch(SQLException e) {//fail on call update procedure
			e.printStackTrace();
			response.setStatus(500);
		}
		out.close();
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//optional, save for later
	}
}
