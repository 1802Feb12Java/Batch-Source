package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.JSONwork;
import com.revature.Reimbursement;
import com.revature.UserServices;
import com.revature.Users;


public class AllReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServices us = new UserServices();
	
    public AllReceiptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//grab the users obj from the session
				Users u = (Users) request.getSession(false).getAttribute("userobj");
				ArrayList<Reimbursement> r = new ArrayList<Reimbursement>();
				try {
					//gets all the pending reimbursements
					r = us.getAll(u);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String str = JSONwork.makeArrR(r);
				PrintWriter pw = response.getWriter();
				pw.print(str);
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
