package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.reimbursements.User;
import com.revature.reimbursements.UserServices;

public class SingleEmployeeServlet extends HttpServlet{
	UserServices us = new UserServices();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		User u = null;
		HttpSession session = null;
		int toget = (int) session.getAttribute("userID");
		try {
			u = us.readUser(toget);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        PrintWriter pw = resp.getWriter();
       
        	JSONObject job = new JSONObject();
            job.append("U_FIRSTNAME", u.getU_firstname());
            job.append("U_LASTNAME", u.getU_lastname());
            job.append("U_ID", u.getU_id());
            job.append("U_EMAIL", u.getU_email());
            job.append("U_USERNAME", u.getU_username());
            job.append("password", "**");
            job.append("UR_ID", u.getUr_id());
            //add the json obj to the array to send out
           
        System.out.println(job.toString()); 
        pw.write(job.toString());
      // resp.sendRedirect("http://localhost:8080/Reimbursements/EmployeeTable.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}

}
