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

public class EmpViewReqServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ViewReqServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);
		// check to make sure user in session is authorized to access this page
		int userId = (Integer) session.getAttribute("userId");
		int filter = new Integer(req.getParameter("filter"));
		resp.setContentType("application/json");
		ArrayList<String> requests = new ArrayList<String>();
		try {
			requests = com.revature.dao.ReimbursementDAOImp.viewUserRequests(userId, filter);

		} catch (SQLException e) {
			log.debug("An SQL exception was thrown trying to call viewUserRequests.");
			e.printStackTrace();
		}
		if (requests.size() == 0) {
			resp.getWriter().write("[]");
		} else {
			resp.getWriter().write("[");

			int count = 0;
			for (String str : requests) {
				String[] s = str.split(":");
				resp.getWriter().append("{\"r_id\":\"" + s[0] + "\"," + "\"r_amount\":\"" + s[1] + "\","
						+ "\"r_description\":\"" + s[2] + "\"," + "\"r_receipt\":\"" + s[3] + "\","
						+ "\"r_submitted\":\"" + s[4] + "\"," 
						+ "\"r_resolved\":\"" + s[5] + "\"," + "\"u_resolver\":\"" + s[6] + "\"," + "\"rt_type\":\""
						+ s[7] + "\"," + "\"rt_status\":\"" + s[8] + "\"}");
				count++;

				if (count < requests.size()) {
					resp.getWriter().append(",");
				} else {
					resp.getWriter().append("]");
				}
			} // end for
		}
	}// end doGet

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}// do post
}// end class