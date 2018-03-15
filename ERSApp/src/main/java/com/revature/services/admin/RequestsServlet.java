package com.revature.services.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Reimbursements;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.services.JsonfierUtil;

/**
 * Servlet implementation class RequestServlet Used by manager pages to get all
 * requests, get user-specific requests and post an update to a request
 */
// @WebServlet("")
public class RequestsServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RequestsServlet.class);
	private static final long serialVersionUID = 1L;
	private Connection con = com.revature.database.ConnectionFactory.getInstance().getConnection();

	public RequestsServlet() {
		super();
	}

	/**
	 * Gets all requests, if no "userId" parameter was sent
	 * 
	 * otherwise gets all the requests from a user with sent id
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received a GET Request");

		String userId = request.getParameter("userId");

		ArrayList<Reimbursement> reqs = null;

		if (userId == null) {
			// return all requests
			// figure out what kind of requests
			String urlReq = request.getRequestURI();
			logger.debug(urlReq);
			// pending, all?
			if (urlReq.toLowerCase().contains("pending")) {
				reqs = (ArrayList<Reimbursement>) Reimbursements.getAllReimbursements(con, "Pending");
			} else {
				// default to all
				reqs = (ArrayList<Reimbursement>) Reimbursements.getAllReimbursements(con);
			}

		} else {
			logger.debug("Passed a userId " + userId);
			int userIdNum = -1;
			try {
				userIdNum = Integer.parseInt(userId);
			} catch (NumberFormatException e) {
				logger.error(e);
			}
			reqs = (ArrayList<Reimbursement>) Reimbursements.getAllReimbursements(con, userIdNum);
		}

		// jsonify them
		// write string to response
		String jsonReqs = JsonfierUtil.reimListJson(reqs);

		logger.info("Sending requests " + jsonReqs);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonReqs);
		response.setStatus(HttpServletResponse.SC_OK);

	}

	/**
	 * Updates Reimbursement to Approved/denied depending on url
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received a POST Request");

		// get reim ID
		int reimId = -1;
		try {
			reimId = Integer.parseInt(request.getParameter("reimId"));
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		logger.debug("Parsed reimId to " + reimId);

		// get logged-in manager id
		int approverId = ((User) request.getSession(false).getAttribute("user")).getUserId();

		String urlReq = request.getRequestURI().toLowerCase();
		logger.info(urlReq);

		String status = "";

		if (urlReq.contains("approve")) {
			status = "Approved";
		} else if (urlReq.contains("deny") || urlReq.contains("denied")) {
			status = "Denied";
		} else
			logger.error("Invalid request url option");

		// update the reimbursement with matching id
		if (Reimbursements.updateReimbursementStatus(con, reimId, status, approverId)) {
			logger.debug("Updated reim with status " + status);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			logger.error("Unable to update reim status");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void destroy() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	}

}
