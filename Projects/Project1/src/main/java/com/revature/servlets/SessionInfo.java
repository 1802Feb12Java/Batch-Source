package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

public class SessionInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[Get] Session Info");

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		HttpSession session = req.getSession();

		String role = (String) session.getAttribute("role");
		System.out.println(role);
		String username = (String) session.getAttribute("user");
		System.out.println(username);

		JsonObject json = new JsonObject();
		json.addProperty("role", role);
		json.addProperty("user", username);

		out.print(json.toString());
		out.close();
	}

}