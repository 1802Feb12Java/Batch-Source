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

public class EmployeeTableServlet extends HttpServlet{
	UserServices us = new UserServices();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ArrayList<User> u = null;
		try {
			u = us.getAllUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jArr = new JSONArray();
        
        PrintWriter pw = resp.getWriter();

        for(int i = 0; i < u.size(); i++) {
        	JSONObject job = new JSONObject();
            job.append("U_FIRSTNAME", u.get(i).getU_firstname());
            job.append("U_LASTNAME", u.get(i).getU_lastname());
            job.append("U_ID", u.get(i).getU_id());
            job.append("U_EMAIL", u.get(i).getU_email());
            job.append("U_USERNAME", u.get(i).getU_username());
            job.append("password", "**");
            job.append("UR_ID", u.get(i).getUr_id());
            //add the json obj to the array to send out
            jArr.put(i, job);
        }
        
       pw.write(jArr.toString());
       resp.sendRedirect("http://localhost:8080/Reimbursements/EmployeeTable.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}

}
