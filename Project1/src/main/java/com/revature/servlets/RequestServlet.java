package com.revature.servlets;

import java.io.IOException;
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
import org.apache.log4j.Logger;

import com.revature.implement.EmployeeService;

public class RequestServlet extends HttpServlet{
	private static final long serialVersionUID = 2L;
	private static final Logger log = Logger.getLogger(LoginServlet.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		System.out.println("here");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> fileItems = null;
		Double amount = 0.0;
		String description ="";
		Integer type = 0;
		byte[] receiptUpload = null;
		
		try {
			fileItems = upload.parseRequest(req);
			for (FileItem item: fileItems) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					switch(fieldName) {
					case "amount":
						amount = new Double(fieldValue);
						break;
					case "description":
						description = fieldValue;
						break;
					case "type": 
						type = new Integer (fieldValue);
						break;
					default:
						break;
					}
				} else {
					receiptUpload = item.get();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in " + this.getClass() + " Check log for stacktrace");
			e.printStackTrace();
		}
		
		resp.setContentType("text/html");	
		Integer id = (Integer) session.getAttribute("id");
		String user = (String) session.getAttribute("username");
		EmployeeService Edao = new EmployeeService();
		Edao.reimburseReq(amount, description,receiptUpload, id, type);
		log.info(user + " put in a request for "+amount+" with a note :"+description);
		resp.sendRedirect("EmployeeProfile.html");
	}
}
