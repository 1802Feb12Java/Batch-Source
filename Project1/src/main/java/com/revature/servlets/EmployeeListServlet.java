package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.DAOs.ReimbursementDAOClass;
import com.revature.DAOs.UserDAOClass;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.factory.ConnectionFactory;

public class EmployeeListServlet extends HttpServlet {
	ConnectionFactory cf = ConnectionFactory.getInstance();
	private UserDAOClass userDao = new UserDAOClass(cf.getConnection());
	
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
		ArrayList<User> userList = null;
		try {
			userList = userDao.getAllUsers();
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(userList);
			resp.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		resp.getWriter().write("[");
//		for(int i=0; i<userList.size(); i++) {
//			Reimbursement r = userList.get(i);
//			resp.getWriter().write("{\"id\":"+r.getId()+",");
//			resp.getWriter().write("\"amount\":"+r.getAmount()+",");
//			resp.getWriter().write("\"description\":\""+r.getDescription()+"\",");
//			resp.getWriter().write("\"submitted\":\""+r.getSubmitted()+"\",");
//			if(r.getResolved() != null) {
//				resp.getWriter().write("\"resolved\":\""+r.getResolved()+"\",");
//			}
//			else {
//				resp.getWriter().write("\"resolved\":null,");
//			}
//			if(r.getReceipt() != null) {
//				resp.getWriter().write("\"receipt\":\""+r.getReceipt()+"\",");
//			}
//			else {
//				resp.getWriter().write("\"receipt\":null,");
//			}
//			resp.getWriter().write("\"authorId\":"+r.getAuthorId()+",");
//			resp.getWriter().write("\"resolverId\":"+r.getResolverId()+",");
//			resp.getWriter().write("\"typeId\":"+r.getTypeId()+",");
//			if(i!=userList.size()-1) {
//				resp.getWriter().write("\"statusID\":"+r.getStatusID()+"},");
//			}
//			else {
//				resp.getWriter().write("\"statusID\":"+r.getStatusID()+"}]");
//			}
//		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
