package com.ers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String userName = request.getParameter("username");  
	    String password = request.getParameter("password");  
	    
	    if (LoginDAO.validate(userName, password)) {
	    	String userType = LoginDAO.getUserType(LoginDAO.getUserTypeId(userName));
	    	//set up session for a user with type manager
	    	if( userType.equals("MANAGER")) {
		        HttpSession session = request.getSession();
		        Cookie cookie = new Cookie("firstName", LoginDAO.getUserFirstName(userName));
		        cookie.setMaxAge(60*60*24); //set cookie to live for one day
		        response.addCookie(cookie);
		        //session.setAttribute("username", userName);
		    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("managerDash.html");  
		        requestDispatcher.forward(request,response); 
	    	}
	    	//set up session for user with type employee
	    	else {
		        HttpSession session = request.getSession();  
		        Cookie cookie = new Cookie("firstName", LoginDAO.getUserFirstName(userName));
		        cookie.setMaxAge(60*60*24); //set cookie to live for one day
		        response.addCookie(cookie);
		    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("employeeDash.html");  
		        requestDispatcher.forward(request,response); 
	    	} 
	    }
	    else{  
	        out.print("Sorry username or password error");  
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");  
	        requestDispatcher.include(request,response);  
	    }  
	          
	    out.close();  
	}

}
