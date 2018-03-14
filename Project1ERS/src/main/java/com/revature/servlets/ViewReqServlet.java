package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ViewReqServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ViewReqServlet.class.getName());
	
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
			ArrayList<String> requests = new ArrayList<String>();
			try {
				requests = com.revature.dao.ReimbursementDAOImp.viewAllRequests();
				
			} catch (SQLException e) {
				log.debug("An SQL exception was thrown trying to call viewAllRequests.");
				e.printStackTrace();
			}
			resp.getWriter().write("[");
			
			int count = 0;
			for(String str : requests) {
				String[] s = str.split(":");
				resp.getWriter().append("{\"r_id\":\"" + s[0] + "\"," + "\"r_amount\":\"" + s[1] + "\"," + "\"r_description\":\"" + s[2] + "\"," + "\"r_receipt\":\"" + s[3] + "\"," + "\"r_submitted\":\"" + s[4] + "\"," + "\"u_author\":\"" + s[5] + "\"," + "\"r_resolved\":\"" + s[6] + "\"," + "\"u_resolver\":\"" + s[7] + "\"," + "\"rt_type\":\"" + s[8] + "\"," + "\"rt_status\":\"" + s[9] + "\"" + "}");
				
				count++;
				
				if(count < requests.size()) {
					resp.getWriter().append(",");
				} else {
					resp.getWriter().append("]");
				}
			}//end for
		}//end else
		
	}//end doGet
	
	
}//end class
