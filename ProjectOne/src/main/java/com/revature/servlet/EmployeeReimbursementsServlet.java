package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.revature.util.ConnFactory;

public class EmployeeReimbursementsServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(AdminDashboardServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);

		if(session!=null && session.getAttribute("username") != null){
			req.getRequestDispatcher("employee-reimbursements.html").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get user id from session
		HttpSession session = req.getSession();
		Integer sessId = (Integer) session.getAttribute("userId");
		
		// create form variables
		int type = 0;
		double amount = 0;
		String description = "";
		
		// get receipt image
		byte[] receipt = null;
		String encodedReceipt = Base64.encodeBase64String(receipt);
		FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems;
        
        try {
            fileItems = upload.parseRequest(req);

            for(FileItem item : fileItems) {

                if (item.isFormField()) {
                    //process all other form fields
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    if(fieldName.equals("type")) {
                        type = new Integer(fieldValue);
                    } else if (fieldName.equals("amount")) {
                        amount = new Double(fieldValue);
                    } else if (fieldName.equals("description")) {
                        description = fieldValue;
                    }

                } else {
                    // get bytes from file upload
                    receipt = item.get();
                }
            }
        } catch (FileUploadException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		try {
			// Create ConnFactory object
			ConnFactory cf = new ConnFactory();
			
			// Create connection
			Connection conn = cf.getConnection();
			
			// Create get user query
			String sqlInsert = "INSERT INTO ers_reimbursements VALUES(reimbursementSeq.NEXTVAL, ?, ?, ?, ?, null, ?, null, ?, ?)";
			
			// Create PreparedStatement object
			PreparedStatement ps = conn.prepareStatement(sqlInsert);
			
			// Set username value in statement
			ps.setDouble(1, amount);
			ps.setString(2, description);
			ps.setBytes(3, receipt);
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, sessId);
			ps.setInt(6, type);
			ps.setInt(7, 1);
			
			// Execute update
			int rowsInserted = ps.executeUpdate();
			if(rowsInserted > 0) {
				logger.info("Inserted new employee reimbursement request to ers_reimbursements table");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		resp.sendRedirect("employee-reimbursements");
	}

}
