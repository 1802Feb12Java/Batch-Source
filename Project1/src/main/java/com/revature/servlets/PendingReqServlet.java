package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.implement.ManagerService;
import com.revature.objects.Employee;
import com.revature.objects.Reimbursement;

public class PendingReqServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/HTML");
			ManagerService Mdao = new ManagerService();
			List<Reimbursement> ReqList = Mdao.pendingReq();
			for(Reimbursement accounts : ReqList){
				resp.getWriter().write(accounts.toString());
			}
			log.info("Manager viewed all pending reimbursement requests");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}