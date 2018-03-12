package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DAOs.ReimbursementDAOClass;
import com.revature.beans.Reimbursement;
import com.revature.factory.ConnectionFactory;

public class ReimbursementsServlet extends HttpServlet {
	ConnectionFactory cf = ConnectionFactory.getInstance();
	private ReimbursementDAOClass reDao = new ReimbursementDAOClass(cf.getConnection());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.service(req, resp);
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers",
                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Reimbursement> reimbursementList = null;
		try {
			reimbursementList = reDao.getAllReimbursements();
//			ObjectMapper om = new ObjectMapper();
//			String json = om.writeValueAsString(reimbursementList);
//			resp.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().write("[");
		for(int i=0; i<reimbursementList.size(); i++) {
			Reimbursement r = reimbursementList.get(i);
			resp.getWriter().write("{\"id\":"+r.getId()+",");
			resp.getWriter().write("\"amount\":"+r.getAmount()+",");
			resp.getWriter().write("\"description\":\""+r.getDescription()+"\",");
			resp.getWriter().write("\"submitted\":\""+r.getSubmitted()+"\",");
			if(r.getResolved() != null) {
				resp.getWriter().write("\"resolved\":\""+r.getResolved()+"\",");
			}
			else {
				resp.getWriter().write("\"resolved\":null,");
			}
			if(r.getReceipt() != null) {
				resp.getWriter().write("\"receipt\":\""+r.getReceipt()+"\",");
			}
			else {
				resp.getWriter().write("\"receipt\":null,");
			}
			resp.getWriter().write("\"authorId\":"+r.getAuthorId()+",");
			resp.getWriter().write("\"resolverId\":"+r.getResolverId()+",");
			resp.getWriter().write("\"typeId\":"+r.getTypeId()+",");
			if(i!=reimbursementList.size()-1) {
				resp.getWriter().write("\"statusID\":"+r.getStatusID()+"},");
			}
			else {
				resp.getWriter().write("\"statusID\":"+r.getStatusID()+"}]");
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		resp.setContentType("text/html");
		
		int reid = new Integer(req.getParameter("reqid"));
		int newStatus = new Integer(req.getParameter("newstatus"));
		int resolverUid = (Integer) session.getAttribute("uid");

//		System.out.println("===New Attempt===");
//		System.out.println("reid = " + reid);
//		System.out.println("newStatus = " + newStatus);
//		System.out.println("resolverUid = " + resolverUid);
		
		try {
			reDao.updateReimbursementStatus(reid, newStatus, resolverUid);
			resp.sendRedirect("http://localhost:4200/reimbursements");
		} catch (SQLException e) {
			//log.debug("There was an SQL exception thrown when trying to update a Request Status.");
			e.printStackTrace();
		}

	}

}
