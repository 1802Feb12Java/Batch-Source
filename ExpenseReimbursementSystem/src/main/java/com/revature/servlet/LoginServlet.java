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

//import org.json.JSONObject;
import com.google.gson.Gson;
import com.revature.bean.User;
import com.revature.bean.helper.Auth;
import com.revature.services.UserServices;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//only used for testing for sessions
		System.out.println(request.getSession().getAttribute("userID"));
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			//read content of request
	        BufferedReader reader = request.getReader();
	        //create gson 
	        Gson gson = new Gson();
	        Auth a = gson.fromJson(reader, Auth.class);
	        
	        //get value from gson
	        String usr = a.getUsername();
	        String pw = a.getPassword();
	        //create service
	        UserServices us = new UserServices();
			
	        User u = us.getUserForLogin(usr,pw);
	        
			if(u != null) {
				//user name and password matches
				//create response for successful login
				//create http session
				//System.out.println(request.getSession(false).getAttribute("userID"));
				HttpSession session = request.getSession(true);
				session.setAttribute("userID", u.getUserID());
				session.setAttribute("roleID", u.getRole());
				
				if(u.getRole().equals("employee"))
					response.setStatus(211);//success as employee
				else if(u.getRole().equals("manager"))
					response.setStatus(212);//success as admin
				//set uid in response body
//				System.out.println("Login Success!!");
//				System.out.println("User " + u.getFirstName() + " has logged in");
//				System.out.println("Session: " + session.getAttribute("userID"));
				//send uid,first,last,rid
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println("{");
				out.println("\"uid\":\""+u.getUserID()+"\",");
				out.println("\"firstname\":\""+u.getFirstName()+"\",");
				out.println("\"lastname\":\""+u.getLastName()+"\",");
				out.println("\"rid\":\""+u.getRole()+"\"");
				out.println("}");
			}
			else {
				//incorrect username and/or password
				response.setStatus(401);//unauthorized
				
				//System.out.println("Wrong Password!");
				//TODO logger here!
			}
		} catch (ClassNotFoundException e) {
			response.setStatus(500);
			e.printStackTrace();
			//TODO logger here!
		} catch (SQLException e) {
			response.setStatus(500);
			e.printStackTrace();
			//TODO logger here!
		}
		
	}
	
}
