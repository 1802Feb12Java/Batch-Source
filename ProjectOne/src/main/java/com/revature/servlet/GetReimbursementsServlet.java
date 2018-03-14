package com.revature.servlet;

import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.revature.util.ConnFactory;
import java.util.Date;
import java.sql.Blob;

import java.util.ArrayList;

public class GetReimbursementsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();
		resp.setContentType("application/json");
        ArrayList<String> reimbursements = new ArrayList<String>();

        try {

            // Conn factory
            ConnFactory cf = new ConnFactory();
            Connection conn = cf.getConnection();
        
            // Populate sql statement
            String sqlGet = "SELECT * FROM ers_reimbursements LEFT JOIN ers_users ON ";
            sqlGet += "ers_reimbursements.u_id_author = ers_users.u_id";
            
            // Create PreparedStatement object
            PreparedStatement ps = conn.prepareStatement(sqlGet);
            
            // Create ResultSet object
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("r_id");
                double amount = rs.getDouble("r_amount");
                String description = rs.getString("r_description");
                Blob receipt = rs.getBlob("r_receipt");
                Date submitted = rs.getDate("r_submitted");
                Date resolved = rs.getDate("r_resolved");
                String empFirst = rs.getString("u_firstname");
                String empLast = rs.getString("u_lastname");
                int rType = rs.getInt("rt_type");
                int rStatus = rs.getInt("rt_status");

                String rTypeTwo = "";
                if(rType == 1) {
                    rTypeTwo = "Food";
                } else if(rType == 2) {
                    rTypeTwo = "Supplies";
                } else {
                    rTypeTwo = "Transporation";
                }

                String rStatusTwo = "";
                if(rStatus == 1) {
                    rStatusTwo = "Pending";
                } else if(rStatus == 2) {
                    rStatusTwo = "Approved";
                } else {
                    rStatusTwo = "Denied";
                }

                // Create object as string
                String result = id+":"+amount+":"+description+":"+receipt+":"+submitted+":";
                result += resolved+":"+empFirst+" "+empLast+":"+rTypeTwo+":"+rStatusTwo;
    
                // Add object to reimbursements array
                reimbursements.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // begin JSON output
        resp.getWriter().write("[");

        int count=0;
        for(String str:reimbursements) {
            count++;
            String[] split = str.split(":");
            resp.getWriter().append("{\"id\":\"" + split[0] + "\"," + "\"amount\":\"" + split[1]);
            resp.getWriter().append("\"," + "\"description\":\"" + split[2] + "\"," + "\"receipt\":\"" + split[3]);
            resp.getWriter().append("\"," + "\"submitted\":\"" + split[4] + "\"," + "\"resolved\":\"" + split[5]);
            resp.getWriter().append("\"," + "\"employee\":\"" + split[6] + "\"," + "\"type\":\"" + split[7]);
            resp.getWriter().append("\"," + "\"status\":\"" + split[8] + "\"}");

            if(count < reimbursements.size()) {
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
