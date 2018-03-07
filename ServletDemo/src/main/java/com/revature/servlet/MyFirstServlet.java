package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("GET Roll TIDE!");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		System.out.println("doGet: " + out.toString());
		
		//Person person = new Person();
		try {
			PersonDAO dao = new PersonDAO();
			String in = req.getQueryString();//.getParameter("idnumber");
			System.out.println(in);
			in = in.split("=")[1];
			System.out.println(in);
			String str = dao.getNameFromDB(Integer.parseInt(in));
			System.out.println(str);
			out.print(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Post ROLL TIDE");
		
		
		
		Person person = new Person(req.getParameter("fullname") , Integer.parseInt(req.getParameter("idnumber")));
		try {
			PersonDAO dao = new PersonDAO();
			dao.addPersonToDB(person);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("PUT ROLL TIDE");
		
		
		try {
			PersonDAO dao = new PersonDAO();
			Integer uId = Integer.parseInt(req.getParameter("idnumber"));
			String newName = req.getParameter("name");
			dao.updateUser(uId, newName);
			System.out.println("Updated User");
		} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Delete ROLL TIDE");

		PersonDAO dao;
		try {
			dao = new PersonDAO();
			dao.removeUser(Integer.parseInt(req.getParameter("idnumber")));
			System.out.println("Remove user");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
