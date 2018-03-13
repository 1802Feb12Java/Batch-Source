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

import com.google.gson.Gson;

import com.revature.DAOs.ReimbursementDAOClass;
import com.revature.beans.Reimbursement;
import com.revature.factory.ConnectionFactory;

public class ReimbursementsServlet extends HttpServlet {
	private ConnectionFactory cf = ConnectionFactory.getInstance();
	private ReimbursementDAOClass reDao = new ReimbursementDAOClass(cf.getConnection());
	private static final Logger log = Logger.getLogger(ReimbursementsServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers",
                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
		super.service(req, resp);
        resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Reimbursement> reimbursementList = null;
		try {
			reimbursementList = reDao.getAllReimbursements();
			Gson gson = new Gson();
			String json = gson.toJson(reimbursementList);
//			log.info("Reimbursement list JSON sent");
			resp.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		
		int reid = new Integer(req.getParameter("reqid"));
		int newStatus = new Integer(req.getParameter("newstatus"));
		int resolverUid = (Integer) session.getAttribute("uid");
		
		try {
			reDao.updateReimbursementStatus(reid, newStatus, resolverUid);
			if(newStatus == 2) {
				log.info("Updated Reimbursement "+reid+" to status: APPROVED");
			}
			else if(newStatus == 3) {
				log.info("Updated Reimbursement "+reid+" to status: DENIED");
			}
			resp.sendRedirect("http://localhost:4200/reimbursements");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
