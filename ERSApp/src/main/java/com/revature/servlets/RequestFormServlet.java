package com.revature.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.Reimbursement;

/**
 * Servlet implementation class RequestFormServlet
 */
public class RequestFormServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RequestFormServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestFormServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		Reimbursement reim = new Reimbursement();

		// get all the regular form data
		String amount = request.getParameter("fAmount");
		String descript = request.getParameter("fDescription");
		String type = request.getParameter("fType");
		logger.info("Form amount " + amount);
		logger.info("Form descript " + descript);
		logger.info("Form type " + type);
		// logger.info("Form receipt " + receipt);

		// file upload part
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			logger.info("You are not trying to upload a receipt");
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			logger.info("Number of fields: " + fields.size());
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				logger.info("No fields found");
				return;
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					logger.info("Regular form field, FIELD NAME: " + fileItem.getFieldName() + "\nSTRING: "
							+ fileItem.getString());
				} else {
					logger.info("File from form with FIELD NAME: " + fileItem.getFieldName() + "\nSTRING: "
							+ fileItem.getString() + "\nNAME: " + fileItem.getName() + "\nCONTENT TYPE: "
							+ fileItem.getContentType() + "\nSIZE (BYTES): " + fileItem.getSize() + "\nTO STRING: "
							+ fileItem.toString());
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// using form input attempt to create a reimbursement object
		reim.setAmount(new BigDecimal(amount));
		reim.setDecription(descript);
		reim.setType(type);
		// reim.setRecipt(receipt);
		// set author id by parsing the user's session object userId
		int userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());

		logger.info("Got user ID " + userId);

		reim.setAuthorId(userId);
		// lastly submit the reimbursement
		// if (!Reimbursements.addReimbursement(reim)) {
		// try {
		// response.getWriter().print("POST FAILED");
		// logger.error("POST FAILED");
		// } catch (IOException e) {
		// logger.error(e);
		// }
		// }
		// // doGet(request, response);

	}

}
