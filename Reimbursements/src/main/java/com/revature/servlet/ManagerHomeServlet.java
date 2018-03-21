package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerHomeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		req.getRequestDispatcher("ManagerHome.html").include(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String data = buffer.toString();
		
	    switch(data)
	    {
	    case "reimbtn":
	    	//create html table of reimbursements
	    	System.out.println("table of reimbursements: ");
	    	break;
	    case "empsbtn":
	    	//create html table of all employees
	    	System.out.println("table of employees: ");
	    	break;
	    	
	    	default:
	    		System.out.println("in default!");
	    }
	    resp.getWriter().write("you chose " +data);
	    //postman gets this... chrome is not sending correctly. 
	    // because it doesnt even print the default case. is it that the js isnt linking???
		
		
	}
	protected HttpServletResponse showReims(HttpServletResponse resp) throws ServletException, IOException{
		
		
		return resp;
	}
}
