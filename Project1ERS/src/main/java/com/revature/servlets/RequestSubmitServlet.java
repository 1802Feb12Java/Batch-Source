package com.revature.servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.beans.Reimbursement;

public class RequestSubmitServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = req.getSession(false);
		
		resp.setContentType("text/html");
//		PrintWriter pw = resp.getWriter();
		
//		int requesttype = new Integer( req.getParameter("requesttype"));
//		Double amount = new Double(req.getParameter("requestamount"));
//		double newamount = amount;
//		String description = req.getParameter("requestdescription");
		int userId = (Integer) session.getAttribute("userId");
		int requesttype = 0;
		double amount = 0.0;
		String description = "";
		//trying to figure out receipt stuff below
		byte[] receipt = null;
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
	                if(fieldName.equals("requesttype")) {
	                	requesttype = new Integer(fieldValue);
	                } else if (fieldName.equals("requestamount")) {
	                	amount = new Double(fieldValue);
	                } else if (fieldName.equals("requestdescription")) {
	                	description = fieldValue;
	                }
	                
	            } else {
	                // get bytes from file upload
	                receipt= item.get();
	            }
			}
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			new Reimbursement(amount, description, userId, requesttype, receipt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("employeehome.html");
	}//do post
	
	
}//end class
