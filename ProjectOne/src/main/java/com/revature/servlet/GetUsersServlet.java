package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.ConnFactory;

public class GetUsersServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		resp.setContentType("application/json");
        ArrayList<String> users = new ArrayList<String>();

        try {

            // Conn factory
            ConnFactory cf = new ConnFactory();
            Connection conn = cf.getConnection();
        
            // Populate get users and roles
            String sqlGet = "SELECT * FROM ers_users LEFT JOIN ers_user_roles ";
            sqlGet += "ON ers_users.ur_id = ers_user_roles.ur_id ORDER BY u_id";

            // Create PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(sqlGet);
            
            // Create ResultSet object
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            	
            		int id = rs.getInt("u_id");
            		String username = rs.getString("u_username");
            		String firstName = rs.getString("u_firstname");
            		String lastName = rs.getString("u_lastname");
            		String email = rs.getString("u_email");
            		String role = rs.getString("ur_rol");

                // Create object as string
                String result = id+":"+username+":"+firstName+":"+lastName+":"+email+":"+role;
    
                // Add object to reimbursements array
                users.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // begin JSON output
        resp.getWriter().write("[");

        int count=0;
        for(String str:users) {
            count++;
            String[] split = str.split(":");
            resp.getWriter().append("{\"id\":\"" + split[0] + "\"," + "\"username\":\"" + split[1]);
            resp.getWriter().append("\"," + "\"firstName\":\"" + split[2] + "\"," + "\"lastName\":\"" + split[3]);
            resp.getWriter().append("\"," + "\"email\":\"" + split[4] + "\"," + "\"role\":\"" + split[5] + "\"}");

            if(count < users.size()) {
                resp.getWriter().append(",");
            } else {
                resp.getWriter().append("]");
            }
        }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
