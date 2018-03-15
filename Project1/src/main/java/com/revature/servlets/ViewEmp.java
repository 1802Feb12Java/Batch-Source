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

public class ViewEmp extends HttpServlet {
	private static final Logger log = Logger.getLogger(LoginServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
			resp.setContentType("text/HTML");
			ManagerService Mdao = new ManagerService();
			List<Employee> EmpList = Mdao.viewEmp();
			for(Employee accounts : EmpList){
				resp.getWriter().write(accounts.toString());
			}
			log.info("Manager viewed Employee reimbursement information");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	}
}