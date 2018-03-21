package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.ConnectionFactory;
import com.revature.reimbursements.UserOn;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int UID;
	private int URID;

	public UserOn login(String usrnm, String pass) {
		System.out.println("in login");
		Connection con;
		try {
			con = ConnectionFactory.getInstance().getConnection();
			System.out.println(con);//null == con... connection not working. 
			PreparedStatement stmt = con.prepareStatement("Select U_ID, UR_ID, U_USERNAME, U_PASSWORD FROM ERS_USERS WHERE U_USERNAME=? AND U_PASSWORD = ?"); 
			System.out.println("stmt prepped");
			stmt.setString(1, usrnm);
			stmt.setString(2, pass);
			System.out.println(usrnm +" "+pass);
			ResultSet rs = stmt.executeQuery();
			//System.out.println(rs.next());
			UserOn input = new UserOn();
			if (rs.next()) {
				System.out.println("in rs.next");
				input.setU_id(rs.getInt(1));
				System.out.println("UID : " + UID);
				input.setUr_id(rs.getInt(2));
				System.out.println("Role ID: "+URID);
				//session add user object. 
				
				return input;				
			}else {
				System.out.println("not a vaild user");
			}		
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("unable to login, sql error");
		}
		return null;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("HI");
		
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		resp.sendRedirect("/Reimbursements/Login.html");
		//resp.setContentType("text/html");
		//req.getRequestDispatcher("Login.html").include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login DO POST");
		HttpSession session = req.getSession();

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		System.out.println(username+" "+password);
		
		UserOn valid = login(username, password);
		if (valid != null)
		{
			session.setAttribute("username", username);
			session.setAttribute("userID", valid.getU_id());
			session.setAttribute("roleID", valid.getUr_id());
			if (valid.getUr_id() == 1)
			{
				resp.sendRedirect("./ManagerHome.html");
			}else {
				resp.sendRedirect("./EmployeeHome.html");
			}
				
			resp.setStatus(211);
		}
		else {
			resp.getWriter().write("login flails");
			resp.setStatus(401);
		}

	}
}


		
		
//		Connection conn = null;
//		ConnectionFactory cf = ConnectionFactory.getConnFactory();
//		try {
//			conn = cf.getConnection();// returning null
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		User toCheck = null;
//		UserServices us = new UserServices();
//		
//		public void login(String usrnm, String pass) {
//			
//		}
//		ArrayList<User> allUsers = null;
//		allUsers = us.getAllUsers();
//		int size = allUsers.size();
//		System.out.println(allUsers.toString());
//		for (int i = 0; i < size; i++) {
//			System.out.println(username);
//			System.out.println(allUsers.get(i).getU_username());
//			if ((allUsers.get(i)).getU_username().equals(username)) {
//				if ((allUsers.get(i)).getU_password().equals(password)) {
//					try {
//						toCheck = us.readUser(conn, (allUsers.get(i)).getU_id());
//
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					// break;
//				}
//			}
//		}
//		//select uid uroleid from users where 
//
//		UserOn uo = new UserOn(toCheck.getU_id(), toCheck.getUr_id());
//		ObjectMapper om = new ObjectMapper();
//		String respjson = om.writeValueAsString(uo);
//		if (uo != null) {
//			System.out.println("in UO not null");
//			/*
//			 * pw.println("Welcome, "+username);
//			 * pw.println("<a href=\"Index.html\">Go back</a>");
////			 */
////			session.setAttribute("username", toCheck.getU_username());
////			session.setAttribute("problem", null);
//
//			// check for user types (manager or emp) and send to correct homepage.
//
//			// resp.sendRedirect("ManagerHome");
//			resp.getWriter().write(respjson);// needs to return a json obj... use object mapper.
//
//		} else {
//			System.out.println("uo was null");
//			resp.setStatus(401);
//		}
//
