package com.revature.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.Reimbursements;

/**
 * Servlet implementation class PhotoServlet
 */
public class PhotoServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(PhotoServlet.class);
	private static final long serialVersionUID = 1L;

	private Connection con = com.revature.database.ConnectionFactory.getInstance().getConnection();

	public PhotoServlet() {
		super();
	}

	/**
	 * Used to get photo from DB
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("Received GET Request");
		String uri = request.getRequestURI();
		logger.debug("URI=" + uri);
		String num = uri.substring(uri.lastIndexOf("/") + 1);
		logger.debug("reimid =" + num);

		int reimId = Integer.parseInt(num);
		Blob blob = null;

		if ((blob = Reimbursements.getReceipt(con, reimId)) != null) {

			try {
				InputStream is = blob.getBinaryStream();
				logger.debug("blobl l" + blob.length());
				byte buf[] = new byte[(int) blob.length()];
				is.read(buf);

				byte[] encoded = Base64.getEncoder().encode(buf);
				String sending = new String(encoded);
				logger.info(sending);

				response.getWriter().print(sending);
			} catch (SQLException e) {
				logger.error(e);
			} // From 0

		}

	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
