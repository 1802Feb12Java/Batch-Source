package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.revature.DAOUtilities;
import com.revature.EmployeeDAO;
import com.revature.ManagerDAO;
import com.revature.beans.Reimbursements;
import java.util.List;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class EmpPendingReqServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		HttpSession session = request.getSession();
		int userid = (Integer)session.getAttribute("u_id");
		
		EmployeeDAO eDao = DAOUtilities.getEmpDAO();
		ArrayList<Reimbursements> pending = new ArrayList<Reimbursements>(); 
		try {
			pending = eDao.viewPending(userid);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		response.getWriter().write("[");
		int count = 0;
		
		for(Reimbursements r : pending) {
			response.getWriter().append("{\"r_id\":\"" + r.getR_id() + "\"," + "\"r_amount\":\"" + r.getAmount() + "\"," +
					"\"r_description\":\"" + r.getDescription() + "\"," + "\"r_submitted\":\"" + r.getSubmitted() + "\","+ 
					"\"r_receipt\":\"" + r.getReceipt() + "\"," + 
					"\"rt_type\":\"" + r.getType() +  "\"" + "}");
			count++;
			if(count < pending.size()) {
				response.getWriter().append(",");
			} else {
				response.getWriter().append("]");
			}
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO eDao = DAOUtilities.getEmpDAO();
		
		FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = null;
        byte[] bytes = null;

        Integer rid=0;
        try {
            fileItems = upload.parseRequest(request);
            for (FileItem item: fileItems) {
                if (item.isFormField()) {
                	String name = item.getFieldName();
                    String fieldValue = item.getString();
                    switch(name) {
                    case "rid":
                        rid = new Integer(fieldValue); 
                        break;
                    default:
                    	break;
                    }
                } else {
                    bytes = item.get();
                }
            }
        }catch (Exception e) {
        	e.printStackTrace();
        }
	    
	    try {
			eDao.uploadReceipt(rid, bytes);
			response.sendRedirect("EmpPendReqs.html");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
        
	}
	
}
