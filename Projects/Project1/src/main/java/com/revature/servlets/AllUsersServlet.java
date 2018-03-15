package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.Manager;

public class AllUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		String jsa = null;

		try {
			jsa = Manager.viewInformationAll();
		} catch (SQLException e) {
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(jsa);
	}

}