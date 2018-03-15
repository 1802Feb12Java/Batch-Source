package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.bean.Reimbursement;
import com.revature.services.ReimbursementServices;

/**
 * Servlet implementation class TicketApprovalServlet
 */
public class TicketApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	//update: approve/deny tickets
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();
			
			Reimbursement rtoUpdate = gson.fromJson(reader, Reimbursement.class);
			
			rtoUpdate.setResolved(Timestamp.valueOf( LocalDateTime.now()));
			rtoUpdate.setResolver(3);
			rtoUpdate.setRStatus(2);
//			ReimbursementServices ts = new ReimbursementServices();
//			ts.updateTicket(rtoUpdate);
			
		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		catch(IOException e) {
			e.printStackTrace();
		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
	}

}
