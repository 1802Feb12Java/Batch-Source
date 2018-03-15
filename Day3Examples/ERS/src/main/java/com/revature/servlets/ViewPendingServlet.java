package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.DAOUtilities;
import com.revature.ManagerDAO;
import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public class ViewPendingServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		
		ManagerDAO m = DAOUtilities.getManDAO();
		ArrayList<Reimbursements> pending = new ArrayList<Reimbursements>(); 
		try {
			pending = m.viewAllPending();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		response.getWriter().write("[");
		int count = 0;
		
		for(Reimbursements r : pending) {
			//String[] s = str.split(":");
			response.getWriter().append("{\"r_id\":\"" + r.getR_id() + "\"," + "\"r_amount\":\"" + r.getAmount() + "\"," +
					"\"r_description\":\"" + r.getDescription() + "\"," + "\"r_submitted\":\"" + r.getSubmitted() + "\","+ 
					"\"r_receipt\":\"" + r.getReceipt() + "\"," + "\"u_id_author\":\"" + r.getU_id_author() + "\"," + 
					"\"rt_type\":\"" + r.getType() + "\"" +  "}");
			count++;
			if(count < pending.size()) {
				response.getWriter().append(",");
			} else {
				response.getWriter().append("]");
			}
					
		}	
		
	}
	
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User u = new User(); 
		int userid = (Integer)session.getAttribute("u_id");
		
		int rid = Integer.parseInt(request.getParameter("rid"));
		int new_rs = Integer.parseInt(request.getParameter("new_rs"));
		
		ManagerDAO m = DAOUtilities.getManDAO();
		
		if(new_rs==1) {
			try {
				m.approveRequest(rid, userid);
				response.sendRedirect("PendingRequests.html");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(new_rs==2) {
			try {
				m.denyRequest(rid, userid);
				response.sendRedirect("PendingRequests.html");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	
}
