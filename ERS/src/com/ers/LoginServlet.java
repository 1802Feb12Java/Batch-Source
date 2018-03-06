package com.ers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    
	    //response.getWriter().append("UserName: " + userName + "Password: " + password).append(request.getContextPath());
	    if (LoginDAO.validate(userName, password)) {
	    	String userType = LoginDAO.getUserType(LoginDAO.getUserTypeId(userName));
	    	if( userType.equals("MANAGER")) {
		    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("managerDash.html");  
		        requestDispatcher.forward(request,response); 
	    	}
	    	else {
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
