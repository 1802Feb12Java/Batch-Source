package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.reimbursements.Reimbursement;
import com.revature.reimbursements.ReimbursementServices;
import com.revature.reimbursements.User;
import com.revature.reimbursements.UserServices;

public class ReimburseTableSevlet extends HttpServlet {
	ReimbursementServices us = new ReimbursementServices();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ArrayList<Reimbursement> u = null;
		try {
			u = us.getAllReimbursement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jArr = new JSONArray();
        
        PrintWriter pw = resp.getWriter();

        for(int i = 0; i < u.size(); i++) {
        	JSONObject job = new JSONObject();
            job.append("R_ID", u.get(i).getR_id());
            job.append("R_AMOUNT", u.get(i).getR_amount());
            job.append("R_DESCRIPTION", u.get(i).getR_description());
            job.append("R_SUBMITTED", u.get(i).getR_submitted());
            job.append("R_RESOLVED", u.get(i).getR_resolved());
            job.append("U_ID_AUTHOR", u.get(i).getU_id_author());
            job.append("U_ID_RESOLVER", u.get(i).getU_id_resolver());
            job.append("RT_TYPE", u.get(i).getRt_type());
            job.append("RT_STATUS", u.get(i).getRt_status());
            //add the json obj to the array to send out
            jArr.put(i, job);
        }
        
       pw.write(jArr.toString());
      // resp.sendRedirect("http://localhost:8080/Reimbursements/EmployeeTable.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}

}
