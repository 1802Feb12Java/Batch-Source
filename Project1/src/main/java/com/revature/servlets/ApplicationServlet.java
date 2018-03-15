package com.revature.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.log4j.Logger;

import com.revature.DAOs.ReimbursementDAOClass;
import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionFactory;
import com.revature.util.FrontController;

public class ApplicationServlet extends HttpServlet {
	private ConnectionFactory cf = ConnectionFactory.getInstance();
	private Connection conn = cf.getConnection();
	private ReimbursementDAOClass reDao = new ReimbursementDAOClass(conn);
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ApplicationServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		FrontController.addHeader(resp);
		resp.setContentType("text/html");
		
		int authorID = (Integer) session.getAttribute("uid");		
		FileItemFactory fileFact = new DiskFileItemFactory();
		ServletFileUpload servfileup = new ServletFileUpload(fileFact);
		List<FileItem> formresults = null;
		try {
			formresults = servfileup.parseRequest(req);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		double amount = 0;
		String description = "";
		int type = 0;
		String base64ReceiptString = null;
		String receiptPath = "C:\\Users\\Trevor\\Documents\\workspace-sts\\Project1Test\\";
		
		for(FileItem result : formresults) {
			if(result.isFormField()) {
				if(result.getFieldName().equals("amount"))
					amount = Double.parseDouble(result.getString());
				else if(result.getFieldName().equals("description"))
					description = result.getString();
				else if(result.getFieldName().equals("type"))
					if(result.getString().equals("business"))
						type = 1;
					else if(result.getString().equals("travel"))
						type = 2;
					else if(result.getString().equals("medical"))
						type = 3;
			}
			else {
				byte[] fileByteArray = result.get();
				base64ReceiptString = Base64.encodeBase64String(fileByteArray);
			}
		}
		
		Reimbursement reimbursementToInsert = new Reimbursement(amount, description, base64ReceiptString, receiptPath, authorID, type, 1);
		
		try {
			reDao.createReimbursement(reimbursementToInsert);
			log.info("User "+authorID+" submitted a reimbursement request for $"+amount+" with description \""+description+"\"");
			resp.sendRedirect("http://localhost:4200/employeeHome");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
