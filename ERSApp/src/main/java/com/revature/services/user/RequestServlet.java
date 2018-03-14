package com.revature.services.user;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Reimbursements;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.services.JsonfierUtil;

/**
 * Servlet implementation class RequestServlet2
 */
public class RequestServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(RequestServlet.class);

	private static final long serialVersionUID = 1L;

	private Connection con = com.revature.database.ConnectionFactory.getInstance().getConnection();

	public RequestServlet() {
		super();
	}

	/**
	 * Get the current user's requests
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received GET Request");

		// get current user
		User u = (User) request.getSession(false).getAttribute("user");
		if (u == null) {
			logger.error("No user in session!");
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
		logger.debug("Current user id " + u.getUserId());

		// get this user's requests:
		String sending = JsonfierUtil.reimListJson(Reimbursements.getAllReimbursements(con, u.getUserId()));
		logger.debug("Sending user's requests: " + sending);

		response.getWriter().print(sending);

	}

	/**
	 * Post new request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received POST Request");

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
						logger.debug("amount inputted = " + fieldvalue);
						// not null -> required in form html
						reim.setAmount(new BigDecimal(fieldvalue));
						break;
					case "description":
						logger.debug("description inputed = " + fieldvalue);
						reim.setDecription(fieldvalue);
						break;
					case "typechoice":
						logger.debug("typechoice inputed = " + fieldvalue);
						reim.setType(fieldvalue);
						break;
					default:
						logger.error("Unknown form name " + fieldname);
					}
				} else {
					// Process form file field (input type="file").
					String fieldname = item.getFieldName();
					logger.debug("Got a file from field " + fieldname);
					String filename = FilenameUtils.getName(item.getName());
					logger.debug("Filename = " + filename);
					InputStream filecontent = item.getInputStream();

					reim.setRecipt(filecontent);
					logger.debug("Set blob object!");
				}
			}
		} catch (FileUploadException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		// set author id by parsing the user's session object userId
		int userId = ((User) request.getSession(false).getAttribute("user")).getUserId();
		logger.debug("Got user from session with id " + userId);

		reim.setAuthorId(userId);
		// lastly submit the reimbursement
		if (!Reimbursements.addReimbursement(con, reim)) {
			try {
				response.getWriter().print("POST FAILED");
				logger.error("POST FAILED");
			} catch (IOException e) {
				logger.error(e);
			}
		} else {
			logger.debug("NO SQL ERRORS PUTTING IN REIM!");

		}
		response.sendRedirect("/ERSApp/secure/home.html");

	}

	@Override
	public void destroy() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	}

}
