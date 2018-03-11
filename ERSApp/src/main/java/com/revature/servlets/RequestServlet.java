package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Reimbursements;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RequestServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("GOT REQUEST FORM POST REQ");

		Reimbursement reim = new Reimbursement();

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			logger.error("a multipart content form wasn't submitted");
			response.getWriter().print("INVALID FORM REQ");
			return;
		}

		// get all the regular form data
		List<FileItem> items;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {

					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();

					switch (fieldname) {
					case "amount":
						logger.info("amount inputted = " + fieldvalue);
						// not null -> required in form html
						reim.setAmount(new BigDecimal(fieldvalue));
						break;
					case "description":
						logger.info("description inputed = " + fieldvalue);
						reim.setDecription(fieldvalue);
						break;
					case "typechoice":
						logger.info("typechoice inputed = " + fieldvalue);
						reim.setType(fieldvalue);
						break;
					default:
						logger.error("Unknown form name " + fieldname);
					}
				} else {
					// Process form file field (input type="file").
					String fieldname = item.getFieldName();
					logger.info("Got a file from field " + fieldname);
					String filename = FilenameUtils.getName(item.getName());
					logger.info("Filename = " + filename);
					InputStream filecontent = item.getInputStream();
					byte[] contents;
					contents = IOUtils.toByteArray(filecontent);
					// Blob blob = null;
					// blob = new SerialBlob(contents);

					reim.setRecipt(contents);
					logger.info("Set blob object!");
				}
			}
		} catch (FileUploadException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		// set author id by parsing the user's session object userId
		int userId = ((User) request.getSession(false).getAttribute("user")).getUserId();
		logger.info("Got user from session with id " + userId);

		reim.setAuthorId(userId);
		// lastly submit the reimbursement
		if (!Reimbursements.addReimbursement(reim)) {
			try {
				response.getWriter().print("POST FAILED");
				logger.error("POST FAILED");
			} catch (IOException e) {
				logger.error(e);
			}
		} else {
			logger.info("NO SQL ERRORS PUTTING IN REIM!");

		}
		response.sendRedirect("./home.html");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Got Get Req");

		// get current user
		User u = (User) request.getSession(false).getAttribute("user");
		if (u == null) {
			logger.error("No user in session!");
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
		logger.debug("Current user id " + u.getUserId());

		// get this user's requests:
		String sending = JsonfierUtil.reimListJson(Reimbursements.getAllReimbursements(u.getUserId()));
		logger.info("Sending user's requests: " + sending);

		response.getWriter().print(sending);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
