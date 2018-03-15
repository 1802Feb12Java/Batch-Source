package Project1.Projec1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Project1.Projec1.pojo.AdminView;
import Project1.Projec1.pojo.EmployeeView;
import Project1.Projec1.pojo.Users;
import Project1.Projec1.pojo.View;
import Project1.Projec1.requestdao.RequestDaoImpl;

@WebServlet("/adminrequest")

public class AdminRequestServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
		Users user = new Users();
		ObjectMapper json = new ObjectMapper();
		resp.setContentType("application/json"); //send json as a response

		user = (Users) req.getSession(false).getAttribute("user"); // get current user
		int userid = user.getU_id();
		PrintWriter out = resp.getWriter();
		RequestDaoImpl requestDao = new RequestDaoImpl();
		try {

			String button = req.getParameter("button");
			String idText = req.getParameter("appid");
			String userId = req.getParameter("userid");
			System.out.println(button);
			if(button.equals("pending") ) {
				ArrayList<AdminView> pendingList = requestDao.getPendingRequest();
				//convert object to json string
				String jsonInString = json.writeValueAsString(pendingList);
				out.print(jsonInString);//send json object back to client
			}
			
			if(button.equals("approve") ) {
				ArrayList<AdminView> approveList = requestDao.getApprovedRequest();
				//convert object to json string
				String jsonInString = json.writeValueAsString(approveList);
				out.print(jsonInString);//send json object back to client
			}
			if(button.equals("denied") ) {
				ArrayList<AdminView> approveList = requestDao.getDeniedRequest();
				//convert object to json string
				String jsonInString = json.writeValueAsString(approveList);
				out.print(jsonInString);//send json object back to client
			}
			if(button.equals("showemployee") ) {
				ArrayList<EmployeeView> list = requestDao.getEmployee();
				//convert object to json string
				String jsonInString = json.writeValueAsString(list);
				out.print(jsonInString);//send json object back to client
			}
			if(button.equals("search1app") ) {
				if(requestDao.searchAppId(Integer.parseInt(idText))) {
					ArrayList<AdminView> application = requestDao.getOneRequest(Integer.parseInt(idText));
					//convert object to json string
					String jsonInString = json.writeValueAsString(application);
					out.print(jsonInString);//send json object back to client
				}
				else {
					resp.setContentType("text/html; charset=utf-8"); //send json as a response
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
					out.write("Invalid application id");
					out.write("</div>");
					req.getRequestDispatcher("adminrequest.html").include(req, resp);  

				}
				
			}
			if(button.equals("search1user") ) {
				if(requestDao.searchAuthorId(Integer.parseInt(userId))) {
					ArrayList<AdminView> userApplication = requestDao.getOneUserRequest(Integer.parseInt(userId));
					//convert object to json string
					String jsonInString = json.writeValueAsString(userApplication);
					out.print(jsonInString);//send json object back to client
				}
				else {
					resp.setContentType("text/html; charset=utf-8"); //send json as a response
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
					out.write("Invalid application id");
					out.write("</div>");
					req.getRequestDispatcher("adminrequest.html").include(req, resp); 					
				}
				
			}
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	@Override
	protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
		Users user = new Users();
		user = (Users) req.getSession(false).getAttribute("user"); // get current user
		int userid = user.getU_id();
		resp.setContentType("text/html"); //send json as a response

		PrintWriter out = resp.getWriter();
		RequestDaoImpl requestDao = new RequestDaoImpl();
		String appid = req.getParameter("applicationid");
		String approveButton = req.getParameter("approvedrequest");
		String deniedButton = req.getParameter("deniedRequest");

		try {
			if(approveButton != null ) {
				if(requestDao.checkRequest(Integer.parseInt(appid))) {
					  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						requestDao.editOneRequest(Integer.parseInt(appid), userid,timestamp, "approvedRequest");
						out.write("<div class=\"alert alert-success\" role=\"alert\">");
						out.write("Aprroved request successfully");
						out.write("</div>");
						//out.write("alert(Request created)");
						req.getRequestDispatcher("adminrequest.html").include(req, resp);  
						//out.println("alert(Approved Request Success");
					//	resp.sendRedirect("adminrequest.html");//goes back to the page after submit info
				}
				else {
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
					out.write("Invalid application id");
					out.write("</div>");
					//out.write("alert(Request created)");
					req.getRequestDispatcher("adminrequest.html").include(req, resp);
				}
		      

			}
			
			if(deniedButton != null) {
				if(requestDao.checkRequest(Integer.parseInt(appid))) {
					 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						requestDao.editOneRequest(Integer.parseInt(appid), userid,timestamp, "deniedRequest");
						out.write("<div class=\"alert alert-success\" role=\"alert\">");
						out.write("Denied request successfully");
						out.write("</div>");
						req.getRequestDispatcher("adminrequest.html").include(req, resp); 
				} else {
					out.write("<div class=\"alert alert-danger\" role=\"alert\">");
					out.write("Invalid application id");
					out.write("</div>");
					req.getRequestDispatcher("adminrequest.html").include(req, resp);
				}
				

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}