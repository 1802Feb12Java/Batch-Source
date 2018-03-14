package com.revature.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.ConnFactory;

/**
 * Servlet implementation class SessionServlet
 */
//@WebServlet("/SessionServlet")
public class EmployeeSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get current session
		HttpSession session = req.getSession(false);
		
		String result = "";
        
		if(session!=null){
        	
        		Integer sessId = (Integer) session.getAttribute("userId");

            try {
                // Create ConnFactory object
                ConnFactory cf = new ConnFactory();
                
                // Create connection
                Connection conn = cf.getConnection();
                
                // Create get user query
                String sqlGet = "SELECT * FROM ers_users WHERE u_id = ?";
                
                // Create PreparedStatement object
                PreparedStatement ps = conn.prepareStatement(sqlGet);
                
                // Set username value in statement
                ps.setInt(1, sessId);
                
                // Create ResultSet object
                ResultSet rs = ps.executeQuery();

                // Perform checks and redirects
                if(rs.next()) {
                    
					int userId = rs.getInt("u_id");
					String username = rs.getString("u_username");
					String firstName = rs.getString("u_firstname");
					String lastName = rs.getString("u_lastname");
					String email = rs.getString("u_email");

					result = userId+":"+username+":"+firstName+":"+lastName+":"+email;

                }
                
            } catch(SQLException e) {
                e.printStackTrace();
            }
            
            String[] split = result.split(":");
			resp.setContentType("application/json");
			resp.getWriter().write("{\"id\":\"" + split[0] + "\"," + "\"username\":\"" + split[1]);
            resp.getWriter().append("\"," + "\"firstName\":\"" + split[2] + "\"," + "\"lastName\":\"" + split[3]);
            resp.getWriter().append("\"," + "\"email\":\"" + split[4] + "\"}");
            
		} else {
			resp.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
