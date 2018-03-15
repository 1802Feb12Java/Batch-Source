package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.util.ConnFactory;

/**
 * Servlet implementation class SessionServlet
 */
//@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(AdminDashboardServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null){
			response.setContentType("application/json");
			int reimbursementsCount = 0;
			int usersCount = 0;
			
			try {
				ConnFactory cf = new ConnFactory();
				Connection conn = cf.getConnection();
				String sqlCountReimbursements = "SELECT COUNT(*) FROM ers_reimbursements"; 
				PreparedStatement ps = conn.prepareStatement(sqlCountReimbursements);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					reimbursementsCount = rs.getInt(1);
				}
				String sqlCountUsers = "SELECT COUNT(*) FROM ers_users";
				ps = conn.prepareStatement(sqlCountUsers);
				rs = ps.executeQuery();
				if(rs.next()) {
					usersCount = rs.getInt(1);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			logger.info("Writing admin session info in JSON format");
			response.getWriter().write("{\"username\":\"" + session.getAttribute("username") + "\"," + "\"reimbursementsCount\":\"" + reimbursementsCount + "\"," + "\"usersCount\":\"" + usersCount + "\"}"); 
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{\"username\":null}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
