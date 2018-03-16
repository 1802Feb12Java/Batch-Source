package com.talanian.servlets;

import com.talanian.beans.User;
import com.talanian.dao.SystemDAO;
import com.talanian.dao.SystemDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SystemDAO dao = new SystemDAOImpl();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			String id = request.getParameter("id");
			String userName = request.getParameter("user1");
			String firstName = request.getParameter("fname");
			String lastName = request.getParameter("lname");
			String email = request.getParameter("email");
			String password = request.getParameter("pass1");

			try {
				User user = new User(Integer.parseInt(id),userName,password,firstName,lastName,email);

				boolean success = dao.updateInfo(user);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
}