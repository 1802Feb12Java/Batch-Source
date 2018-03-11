package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
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
				//username and password matches
				//create response for successful login
				//create http session
				//System.out.println(request.getSession(false).getAttribute("userID"));
				HttpSession session = request.getSession();
				session.setAttribute("userID", u.getUserID());
				System.out.println("Hello " + u.getFirstName());
			}
			else {
				//incorrect username and/or password
				System.out.println("Worng Password!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
