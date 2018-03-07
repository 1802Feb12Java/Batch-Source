package com.ers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;    
   
    public LoginServlet() {
        super();
        Connection connection = DatabaseConnection.getDatabaseConnection();
        this.loginDAO = new LoginDAO(connection);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String userName = request.getParameter("username");  
	    String password = request.getParameter("password");  
	    
	    if (loginDAO.validate(userName, password)) {
	    	String userType = loginDAO.getUserType(loginDAO.getUserTypeId(userName));
	    	//set up session for a user with type manager
	    	if( userType.equals("MANAGER")) {
		        Cookie cookie = new Cookie("firstName", loginDAO.getUserFirstName(userName));
		        cookie.setMaxAge(60*60*24); //set cookie to live for one day
		        response.addCookie(cookie);
		        //session.setAttribute("username", userName);
		    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("managerDash.html");  
		        requestDispatcher.forward(request,response); 
	    	}
	    	//set up session for user with type employee
	    	else {  
		        Cookie cookie = new Cookie("firstName", loginDAO.getUserFirstName(userName));
		        cookie.setMaxAge(60*60*24); //set cookie to live for one day
		        response.addCookie(cookie);
		    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("empDash.html");  
		        requestDispatcher.forward(request,response); 
	    	} 
	    }
	    else{  
	        out.print("<h1 class='mx-auto'>Wrong username or password!</h1>");  
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");  
	        requestDispatcher.include(request,response);  
	    }  
	          
	    out.close();  
	}

}
