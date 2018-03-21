package com.revature;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.revature.DaoUsers;



/**
 * Servlet implementation class Ers_servlet
 */

public class ErsServlet extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public ErsServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.setContentType("text/html");
		

		try {
			String nextHtml = "/ERSLogin.html";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextHtml);
			dispatcher.forward(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			response.sendRedirect("/EmploymentReimbursementSystem/ERSLogin.html");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		// TODO Auto-generated method stub
		DaoUsers conn = new DaoUsers();
		Connection con = conn.connectionFactory();
		doGet(request, response);
		String usernameCk = null;
		String passwordCk = null;
		if(request.getParameter("url") == "/ERS/Login")
		{
			response.setContentType("text/html");
			usernameCk = request.getParameter("username");
			passwordCk = request.getParameter("password");
			//Create new cookies and send them to client
			Cookie ck = new Cookie("username", usernameCk);
			Cookie ck2 = new Cookie("password", passwordCk);
			response.addCookie(ck);
			response.addCookie(ck2);
			ResultSet rs = null;
			ArrayList<String> auth = new ArrayList<String>();
			DaoUsers loginObj = new DaoUsers(usernameCk, passwordCk);
			try {
				rs = loginObj.login(con);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int i = 1;
			try {
				while(rs.next())
				{
					String buff = rs.getString(i);
					auth.add(buff);
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(usernameCk == "admin" && passwordCk == "password")
			{
				RequestDispatcher rd = request.getRequestDispatcher("ERS_Manager.html");
				try {
					rd.include(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(usernameCk == auth.get(1) && passwordCk == auth.get(2))
			{
				RequestDispatcher rd = request.getRequestDispatcher("ERS_Employee.html");
				try {
					rd.include(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try {
					response.getWriter().append("Invalid password").append(request.getContextPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rd = request.getRequestDispatcher("ERS_userInformation.html");
				try {
					rd.include(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else if(request.getParameter("url") == "/ERS/createUser")
		{
			Cookie[] cks = request.getCookies();
			usernameCk = cks[0].toString();
			passwordCk = cks[1].toString();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String first = request.getParameter("firstname");
			String last = request.getParameter("lastname");
			String email = request.getParameter("email");
			DaoUsers createUsrObj = new DaoUsers(username, password, first, last, email);
			try {
				createUsrObj.createUser(con, usernameCk, passwordCk);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("url") == "/ERS/updateUser")
		{
			Cookie[] cks = request.getCookies();
			usernameCk = cks[0].toString();
			passwordCk = cks[1].toString();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String first = request.getParameter("firstname");
			String last = request.getParameter("lastname");
			String email = request.getParameter("email");
			DaoUsers updateUsrObj = new DaoUsers(username, password, first, last, email);
			try {
				updateUsrObj.updateUser(con, usernameCk, passwordCk);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("url") == "/ERS/getEmployees")
		{
			ArrayList<String> nameProperties = new ArrayList<String>();
			nameProperties.add("firstname");
			nameProperties.add("lastname");
			ResultSet rs = null;
			JSONArray jsonArr = new JSONArray();
			JSONObject json = new JSONObject();
			DaoUsers getEmpObj = new DaoUsers();
			response.setContentType("application/json");
			try {
				rs = getEmpObj.getEmp(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String buff;
			int i = 1;
			try {
				while(rs.next())
				{
					buff = rs.getString(i);
					json = (JSONObject) json.put(nameProperties.get(i-1), buff);
					PrintWriter out = null;
					try {
						out = response.getWriter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print(json);
					out.flush();
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else if(request.getParameter("url") == "/ERS/displayEmployee")
		{
			ArrayList<String> properties = new ArrayList<String>();
			properties.add("manager");
			properties.add("description");
			properties.add("amount");
			properties.add("status");
			String buff = null;
			ResultSet rs = null;
			JSONObject json = new JSONObject();
			DaoUsers displayEmpObj = new DaoUsers();
			response.setContentType("application/json");
			try {
				rs = displayEmpObj.displayEmp(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 1;
			try {
				while(rs.next())
				{
					buff = rs.getString(i);
					json = (JSONObject) json.put(properties.get(i-1),buff);
					PrintWriter out = null;
					try {
						out = response.getWriter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print(json);
					out.flush();
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("url") == "/ERS/approveDenyEmployee")
		{
			String first = request.getParameter("firstname");
			String last = request.getParameter("lastname");
			String status = request.getParameter("status");
			DaoUsers apprDenyObj = new DaoUsers(first, last, status);
			try {
				apprDenyObj.approveDenyEmp(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("url") == "/ERS/Reimbursement")
		{
			ArrayList<String> properties = new ArrayList<String>();
			properties.add("description");
			properties.add("status");
			String buff = null;
			ResultSet rs = null;
			JSONObject json = new JSONObject();
			String amount = request.getParameter("amount");
			String desc = request.getParameter("description");
			String blob = request.getParameter("blob");
			String status = request.getParameter("status");
			DaoUsers reObj = new DaoUsers(amount, desc, blob, status);
			try {
				rs = reObj.sendRe(con, usernameCk, passwordCk);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 1;
			try {
				while(rs.next())
				{
					buff = rs.getString(i);
					json = (JSONObject) json.put(properties.get(i-1), buff);
					PrintWriter out = null;
					try {
						out = response.getWriter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print(json.toString());
					out.flush();
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		// TODO Auto-generated method stub
	}

}
