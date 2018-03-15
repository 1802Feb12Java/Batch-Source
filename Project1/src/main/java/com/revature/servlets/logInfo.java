package com.revature.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String fileNameA = "Reimbursement.log";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String log = ""; 
		try {
			ObjectInputStream isa = new ObjectInputStream(new FileInputStream(fileNameA));
			log = (String) isa.readObject();
			System.out.println(log);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.getWriter().write(log);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
