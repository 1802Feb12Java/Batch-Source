package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.Reimbursement;
import com.revature.ReimbursementServices;
import com.revature.Users;

public class RemSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ReimbursementServices rs = new ReimbursementServices();
       
    public RemSubmitServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		Users u = (Users) req.getSession(false).getAttribute("userobj");
		String amount = req.getParameter("amount");
		String description = req.getParameter("desc");
		String type = req.getParameter("typechoice");
		double a = Double.parseDouble(amount);
		int t = Integer.parseInt(type);
		Reimbursement r = new Reimbursement(a, description, u.getId(), t);
		try {
			rs.addReimbursement(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
