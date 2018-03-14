package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateInfoServlet
 */
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("userId");
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		String[] s = name.split(" ");
		String firstname = s[0];
		String lastname = s[1];
		
		try {
			com.revature.dao.UserDAOImp.updateUserInfo(u_id, username, firstname, lastname, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = (String) session.getAttribute("usertype");
		if(type.equals("Employee")) {
			response.sendRedirect("myprofile.html");
		} else {
			response.sendRedirect("managerprofile.html");
		}
		
		
	}

}
