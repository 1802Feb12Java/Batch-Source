package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAOUtilities;
import com.revature.ManagerDAO;
import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public class SingleEmpReqs extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
	
		HttpSession session = request.getSession();
		
		int id = (Integer)session.getAttribute("sel_uid");
		System.out.println(id);
		
		
		ManagerDAO m = DAOUtilities.getManDAO();
		ArrayList<Reimbursements> reqs = new ArrayList<Reimbursements>(); 
		try {
			reqs = m.viewSingleRequest(id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		response.getWriter().write("[");
		int count = 0;
		
		for(Reimbursements r : reqs) {
	
			response.getWriter().append("{\"r_id\":\"" + r.getR_id() + "\"," + "\"r_amount\":\"" + r.getAmount() + "\"," +
					"\"r_description\":\"" + r.getDescription() + "\"," + "\"r_submitted\":\"" + r.getSubmitted() + "\","+ "\"r_receipt\":\"" + r.getReceipt() + "\"," + 
					"\"r_resolved\":\"" + r.getResolved() + "\"," + "\"u_id_author\":\"" + r.getU_id_author() + "\"," + 
					"\"u_id_resolver\":\"" + r.getU_id_resolver() + "\"," + "\"rt_type\":\"" + r.getRt_type() + "\"," + 
					"\"rt_status\":\"" + r.getType() +  "\"" + "}");
			count++;
	
			if(count < reqs.size()) {
				response.getWriter().append(",");
				
			} else {
				response.getWriter().append("]");

			}
			
			
		}
		System.out.println("Should be leaving the for loop. Count is " + count);
	}

}
