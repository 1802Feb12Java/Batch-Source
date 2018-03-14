package com.revature.servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class GetPendingServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(GetPendingServlet.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);
		//check to make sure user in session is authorized to access this page
		String userType = (String) session.getAttribute("usertype");
		if(userType.equals("employee")) {
			resp.sendRedirect("accessdenied.html");
		} else {
			
			resp.setContentType("application/json");
			ArrayList<String> pendingRequests = new ArrayList<String>();
			try {
				pendingRequests = com.revature.dao.ReimbursementDAOImp.viewAllPendingRequests();
				
			} catch (SQLException e) {
				log.debug("An SQL exception was thrown trying to call viewAllPendingRequests.");
				e.printStackTrace();
			}
			resp.getWriter().write("[");
			
			int count = 0;
			for(String str : pendingRequests) {
				String[] s = str.split(":");
				resp.getWriter().append("{\"r_id\":\"" + s[0] + "\"," + "\"r_amount\":\"" + s[1] + "\"," + "\"r_description\":\"" + s[2] + "\"," + "\"r_receipt\":\"" + s[3] + "\"," + "\"r_submitted\":\"" + s[4] + "\"," + "\"u_author\":\"" + s[5] + "\"," + "\"r_resolved\":\"null\"," + "\"u_resolver\":\"null\"," + "\"rt_type\":\"" + s[6] + "\"," + "\"rt_status\":\"0\"" + "}");
				
				count++;
				
				if(count < pendingRequests.size()) {
					resp.getWriter().append(",");
				} else {
					resp.getWriter().append("]");
				}
			}//end for
		}//end else
		
	}//end doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		//PrintWriter pw = resp.getWriter();
		
		int r_id = new Integer(req.getParameter("reqid"));
		int newStatus = new Integer(req.getParameter("newstatus"));
		int u_id = (Integer) session.getAttribute("userId");
		
		try {
			com.revature.dao.ReimbursementDAOImp.updateRequestStatus(r_id, newStatus, u_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug("There was an SQL exception thrown when trying to update a Request Status.");
			e.printStackTrace();
			
		}
		resp.sendRedirect("resolverequests.html");
		
	}//do post
	
	
}//end class
