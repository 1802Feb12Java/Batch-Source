package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



/**
 * Servlet implementation class Ers_servlet
 */
@WebServlet("/Ers_servlet")
public class Ers_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ers_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("ERS_Login.html");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Dao conn = new Dao();
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
			ArrayList<String> auth = new ArrayList<>();
			Dao loginObj = new Dao(usernameCk, passwordCk);
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
				rd.include(request, response);
			}
			else if(usernameCk == auth.get(1) && passwordCk == auth.get(2))
			{
				RequestDispatcher rd = request.getRequestDispatcher("ERS_Employee.html");
				rd.include(request, response);
			}
			else
			{
				response.getWriter().append("Invalid password").append(request.getContextPath());
				RequestDispatcher rd = request.getRequestDispatcher("ERS_userInformation.html");
				rd.include(request, response);
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
			Dao createUsrObj = new Dao(username, password, first, last, email);
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
			Dao updateUsrObj = new Dao(username, password, first, last, email);
			try {
				updateUsrObj.updateUser(con, usernameCk, passwordCk);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("url") == "/ERS/getEmployees")
		{
			ArrayList<String> nameProperties = new ArrayList<>();
			nameProperties.add("firstname");
			nameProperties.add("lastname");
			ResultSet rs = null;
			JSONArray jsonArr = new JSONArray();
			JSONObject json = new JSONObject();
			Dao getEmpObj = new Dao();
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
					PrintWriter out = response.getWriter();
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
			ArrayList<String> properties = new ArrayList<>();
			properties.add("manager");
			properties.add("description");
			properties.add("amount");
			properties.add("status");
			String buff = null;
			ResultSet rs = null;
			JSONObject json = new JSONObject();
			Dao displayEmpObj = new Dao();
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
					PrintWriter out = response.getWriter();
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
			Dao apprDenyObj = new Dao(first, last, status);
			try {
				apprDenyObj.approveDenyEmp(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("url") == "/ERS/Reimbursement")
		{
			ArrayList<String> properties = new ArrayList<>();
			properties.add("description");
			properties.add("status");
			String buff = null;
			ResultSet rs = null;
			JSONObject json = new JSONObject();
			String amount = request.getParameter("amount");
			String desc = request.getParameter("description");
			String blob = request.getParameter("blob");
			String status = request.getParameter("status");
			Dao reObj = new Dao(amount, desc, blob, status);
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
					PrintWriter out = response.getWriter();
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
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
