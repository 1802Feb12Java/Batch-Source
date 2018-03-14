package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.beans.User;
import com.revature.dao.UserDAOImpl;

public class GetAllEmployees extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		//User user = (User) req.getSession(false).getAttribute("user");
		
		ArrayList<User> employees = new ArrayList<User>();
		UserDAOImpl userDao = new UserDAOImpl();
		
		employees = userDao.getEmployees();
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(employees);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.print(json);
	        pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		System.out.println("post");		
	}
}