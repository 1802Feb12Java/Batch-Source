package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.revature.bean.Reimbursement;
import com.revature.bean.ReimbursementStatus;
import com.revature.bean.ReimbursementType;
import com.revature.bean.User;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;
import com.revature.dao.ReimbursementTypeDao;

/**
 * Servlet implementation class ReimbursementRequestServlet
 */
public class ReimbursementRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ServletContext ctx = getServletContext();
		File webinfPath = new File(ctx.getRealPath("WEB-INF"));
		Document basePage, fragPage;
		User user;
		
		// Check for active session.
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		user = (User)(session.getAttribute("loggeduser"));
		
		if(!user.getRole().getRole().equalsIgnoreCase("employee")) {
			// Redirect user with invalid role back home.
			response.sendRedirect("home");
			return;
		}
		
		// *Only employees can access*
		
		// HTML page setup
		basePage = Jsoup.parse(new File(webinfPath, "employee-base.html"), null);
		fragPage = Jsoup.parse(new File(webinfPath, "reimbursement-form.fragment.html"), null);
		basePage.getElementById("user").text(user.getUsername());
		basePage.head().appendElement("link").attr("rel", "stylesheet")
											 .attr("href", "resources/reimbursement-form.css");
		basePage.body().appendElement("script").attr("src","resources/reimbursement-form.js");
		
		Elements fragment = fragPage.getElementById("fragment").children();
		basePage.getElementById("content").append(fragment.outerHtml());
		
		response.setContentType("text/html");
		response.getWriter().println(basePage.outerHtml());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Submit a new reimbursement request.
		HttpSession session = request.getSession(false);
		User user;
		
		// User not logged in.
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		user = (User)(session.getAttribute("loggeduser"));
		
		// 
		try {
			ReimbursementDao rDao = new ReimbursementDao();
			ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
			Reimbursement rData = new Reimbursement();
			ReimbursementTypeDao rtDao = new ReimbursementTypeDao();
			
			// Get data
			ReimbursementType rType = rtDao.getReimbursementTypeById(
					Integer.parseInt(request.getParameter("rtype")));
			rData.setType(rType);
			rData.setAmount(new Double(
					request.getParameter("r-amount")));
			
			rData.setDescription(request.getParameter("desc-area"));
			
			// TODO receipt-file placeholder @ReimbursementRequestServlet
//			String receiptStr = receipt
			
			
			// Server-side auto-fill info
			rData.setAuthor(user);
			rData.setSubmitted(LocalDateTime.now());
			ReimbursementStatus rStatus = rsDao.getReimbursementStatusByName("pending");
			rData.setStatus(rStatus);
			
			// Insert into Database
			rDao.addNewReimbursement(rData);
			
			response.sendRedirect("home");
			return;
		} catch(SQLException | IOException ex) {
			response.sendRedirect("home");
			return;
		}
	}

}