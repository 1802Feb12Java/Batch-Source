package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.JSONwork;
import com.revature.UserServices;
import com.revature.Users;


public class UserTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServices us = new UserServices();
       
    public UserTableServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<Users> u = new ArrayList<Users>();
		try {
			//gets all the pending reimbursements
			u = us.getAllUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str = JSONwork.makeArrU(u);
		PrintWriter pw = response.getWriter();
		pw.print(str);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
