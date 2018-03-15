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

public class ViewResolvedServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		//PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		
		ManagerDAO m = DAOUtilities.getManDAO();
		ArrayList<Reimbursements> resolved = new ArrayList<Reimbursements>(); 
		try {
			resolved = m.viewAllResolved();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		response.getWriter().write("[");

		int count = 0;
		
		for(Reimbursements r : resolved) {
			
			response.getWriter().append("{\"r_id\":\"" + r.getR_id() + "\"," + "\"r_amount\":\"" + r.getAmount() + "\"," +
					"\"r_description\":\"" + r.getDescription() + "\"," + "\"r_submitted\":\"" + r.getSubmitted() + "\","+ "\"r_receipt\":\"" + r.getReceipt() + "\"," + 
					"\"r_resolved\":\"" + r.getResolved() + "\"," + "\"u_id_author\":\"" + r.getU_id_author() + "\"," + 
					"\"u_id_resolver\":\"" + r.getU_id_resolver() + "\"," + "\"rt_type\":\"" + r.getRt_type() + "\"," + 
					"\"rt_status\":\"" + r.getType() +  "\"" + "}");
			count++;
			
			if(count < resolved.size()) {
				response.getWriter().append(",");
				
			} else {
				response.getWriter().append("]");
		
			}
			
			
		}
		
		
	}
}
