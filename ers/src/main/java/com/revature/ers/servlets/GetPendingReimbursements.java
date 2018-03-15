package com.revature.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.revature.ers.jsonifiers.ToJSON;
import com.revature.ers.reimbursements.Reimbursement;
import com.revature.ers.reimbursements.ReimbursementServices;
import com.revature.ers.users.UserServices;

/**
 * Servlet implementation class GetPendingReimbursements
 */
public class GetPendingReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPendingReimbursements() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log = Logger.getRootLogger();
		ArrayList<Reimbursement> list = null;
		ReimbursementServices rs = new ReimbursementServices();
		UserServices us = new UserServices();
		Integer u_ID = (Integer) request.getSession(false).getAttribute("userID");
		Reimbursement test = new Reimbursement();

		try {
			int ur_role = us.getUserRole(u_ID.intValue());
			list = rs.getPendingReimbursements(u_ID, ur_role);
			System.out.println("Back from get pending");
			for (Reimbursement i : list) {
				System.out.println(i.getR_id());
			}
			JSONArray jArray = ToJSON.reimbursements(list);
			
			PrintWriter pw = response.getWriter();
			pw.print(jArray.toString());
		} catch (SQLException e) {
			log.error("Unable to get the reimbursements");
			e.printStackTrace();
		}
		

	}

}
