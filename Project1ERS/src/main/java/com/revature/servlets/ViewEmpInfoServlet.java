package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class ViewEmpInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpSession session = req.getSession(false);
		// check to make sure user in session is authorized to access this page
		//int userId = (Integer) session.getAttribute("userId");
		resp.addHeader("Access-Control-Allow-Origin", "*");
		int filter = new Integer(req.getParameter("filter"));
		resp.setContentType("application/json");
		ArrayList<String> requests = new ArrayList<String>();
		try {
			requests = com.revature.dao.UserDAOImp.viewAllUserRecords(filter);

		} catch (SQLException e) {
			//log.debug("An SQL exception was thrown trying to call viewUserRequests.");
			e.printStackTrace();
		}
		if (requests.size() == 0) {
			resp.getWriter().write("[]");
		} else {
			resp.getWriter().write("[");

			int count = 0;
			for (String str : requests) {
				String[] s = str.split(":");
				int pending = 0;
				int total = 0;
				try {
					pending = com.revature.dao.ReimbursementDAOImp.countUserReqs(new Integer(s[0]), 0);
					total = com.revature.dao.ReimbursementDAOImp.countUserReqs(new Integer(s[0]), 1);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resp.getWriter().append("{\"u_id\":\"" + s[0] + "\"," + "\"u_username\":\"" + s[1] + "\","
						+ "\"u_name\":\"" + s[2] + "\"," + "\"u_email\":\"" + s[3] + "\","
						+ "\"u_role\":\"" + s[4] + "\"," 
						+ "\"r_total\":\"" + total + "\","
						+ "\"r_pend\":\"" + pending +"\"}");
				count++;

				if (count < requests.size()) {
					resp.getWriter().append(",");
				} else {
					resp.getWriter().append("]");
				}
			} // end for
		}
	}// end doGet
	
	
	
	
}//end class
