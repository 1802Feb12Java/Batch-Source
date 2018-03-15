package com.revature.servlet;

import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.codec.binary.Base64;

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

        Integer userId = (Integer) session.getAttribute("userId");
        String sqlGet = "";

        try {
            // Conn factory
            ConnFactory cf = new ConnFactory();
            Connection conn = cf.getConnection();

            if(userId != null) {
                sqlGet = "SELECT * FROM ers_reimbursements LEFT JOIN ers_users ON ";
                sqlGet += "ers_reimbursements.u_id_resolver = ers_users.u_id ";
                sqlGet += "WHERE ers_reimbursements.u_id_author = ?";
                PreparedStatement ps = conn.prepareStatement(sqlGet);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("r_id");
                    double amount = rs.getDouble("r_amount");
                    String description = rs.getString("r_description");
                    byte[] receipt = rs.getBytes("r_receipt");
                    Date submitted = rs.getDate("r_submitted");
                    Date resolved = rs.getDate("r_resolved");
                    String resolverFirst = rs.getString("u_firstname");
                    String resolverLast = rs.getString("u_lastname");
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

                    String encodedReceipt = Base64.encodeBase64String(receipt);
                    
                    String result = id+":"+amount+":"+description+":"+encodedReceipt+":"+submitted+":";
                    result += resolved+":"+resolverFirst+" "+resolverLast+":"+rTypeTwo+":"+rStatusTwo+":"+"N/A";
                    reimbursements.add(result);
                }
            } else {
                sqlGet = "SELECT * FROM ers_reimbursements LEFT JOIN ers_users ON ";
                sqlGet += "ers_reimbursements.u_id_author = ers_users.u_id";
                PreparedStatement ps = conn.prepareStatement(sqlGet);
                ResultSet rs = ps.executeQuery();
                int resolverId = 0;
                while(rs.next()) {
                    int id = rs.getInt("r_id");
                    double amount = rs.getDouble("r_amount");
                    String description = rs.getString("r_description");
                    byte[] receipt = rs.getBytes("r_receipt");
                    Date submitted = rs.getDate("r_submitted");
                    Date resolved = rs.getDate("r_resolved");
                    resolverId = rs.getInt("u_id_resolver");
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

                    System.out.println(resolverId);

                    sqlGet = "SELECT * FROM ers_users WHERE u_id = ?";
                    ps = conn.prepareStatement(sqlGet);
                    ps.setInt(1, resolverId);
                    String resolverFirst = "";
                    String resolverLast = "";
                    if(rs.next()) {
                        resolverFirst = rs.getString("u_firstname");
                        resolverLast = rs.getString("u_lastname");
                    }

                    System.out.println(resolverFirst);
                    System.out.println(resolverLast);

                    String encodedReceipt = Base64.encodeBase64String(receipt);
                    
                    String result = id+":"+amount+":"+description+":"+encodedReceipt+":"+submitted+":";
                    result += resolved+":"+empFirst+" "+empLast+":"+rTypeTwo+":"+rStatusTwo+":"+resolverFirst+" "+resolverLast;
                    reimbursements.add(result);
                }
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
            resp.getWriter().append("\"," + "\"person\":\"" + split[6] + "\"," + "\"type\":\"" + split[7]);
            resp.getWriter().append("\"," + "\"status\":\"" + split[8] + "\"," + "\"resolver\":\"" + split[9] + "\"}");

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
