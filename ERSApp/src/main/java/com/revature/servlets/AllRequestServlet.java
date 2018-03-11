package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Reimbursements;
import com.revature.beans.Reimbursement;

/**
 * Servlet implementation class AllRequestServlet
 */
public class AllRequestServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(AllRequestServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 */
	public AllRequestServlet() {
		super();
	}

	/**
	 * Gets all the pending requests in DB
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Got Get Req");

		// figure out what kind of requests
		String urlReq = request.getRequestURI();
		logger.info(urlReq);
		// pending, all?
		ArrayList<Reimbursement> reqs = null;
		if (urlReq.toLowerCase().contains("pending")) {
			reqs = (ArrayList<Reimbursement>) Reimbursements.getAllReimbursements("Pending");
		} else {
			// default to all
			reqs = (ArrayList<Reimbursement>) Reimbursements.getAllReimbursements();

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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
