package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.revature.bean.Reimbursement;
import com.revature.bean.ReimbursementStatus;
import com.revature.bean.User;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;
import com.revature.dao.UserDao;

/**
 * Servlet implementation class ReimbursementListServlet
 */
public class ReimbursementListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private String getEmployeeHtml(User user) throws SQLException, IOException {
		Document basePage, fragPage;
		ServletContext ctx = getServletContext();
		File webinfDir = new File(ctx.getRealPath("WEB-INF"));
		
		// Get fragments.
		basePage = Jsoup.parse(new File(webinfDir, "employee-base.html"), null);
		fragPage = Jsoup.parse(new File(webinfDir, "reimbursements-list.fragment.html"), null);
		basePage.getElementById("user").text(user.getUsername());
		basePage.head().appendElement("link").attr("rel", "stylesheet").attr("href", "resources/reimbursement-list.css");
		
		// Combine for full base page.
		Elements frags = fragPage.getElementById("fragment").children();
		basePage.getElementById("content").append(frags.outerHtml());
		
		// Get Reimbursements
		ReimbursementDao rDao = new ReimbursementDao();
		ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
		List<ReimbursementStatus> rsList = rsDao.getReimbursementStatuses();
		List<Reimbursement> rList;
		
		rList = rDao.getAllUserReimbursements(user.getUsername());
		
		// Get fragment to clone
		Document recordFragPage = Jsoup.parse(new File(webinfDir, "reimbursement-record.fragment.html"), null);
		
		// Construct status select element.
		final Element statusSelectElem = new Element("select").addClass("form-control");
		rsList.forEach((ReimbursementStatus rs) -> {
			statusSelectElem.appendElement("option")
				.attr("value", rs.getId().toString())
				.text(rs.getStatus());
		});
		
		// Construct & add rows to the table.
		Element recordTableBody = basePage.getElementById("reimbursement-records");
		final DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
		
		rList.forEach((Reimbursement r) -> {
			Document fragPageCp = recordFragPage.clone();
			
			fragPageCp.getElementsByClass("id-cell").get(0).text(r.getId().toString()); // PK
			fragPageCp.getElementsByClass("type-cell").get(0).text(r.getType().getType()); // NOT NULL
			
			Element statusSelect = statusSelectElem.clone();
			statusSelect.addClass("status-select");
			statusSelect.selectFirst("option[value=\"" + r.getStatus().getId() + "\"]").attr("selected value", true);
			statusSelect.attr("disabled", true);
			fragPageCp.getElementsByClass("status-cell").get(0).empty().appendChild(statusSelect); // NOT NULL
			
			fragPageCp.getElementsByClass("time-submitted-cell").get(0).text(r.getSubmitted().format(dtFmt)); // NOT NULL
			fragPageCp.getElementsByClass("submitted-by-cell").get(0).text(r.getAuthor().getUsername()); // NOT NULL
			
			LocalDateTime ldt = r.getResolved();
			fragPageCp.getElementsByClass("time-resolved-cell").get(0).text(ldt == null ? "-" : ldt.format(dtFmt));
			
			User resolver = r.getResolver();
			fragPageCp.getElementsByClass("resolved-by-cell").get(0).text(resolver == null ? "-" : resolver.getUsername());
			
			
			fragPageCp.getElementsByClass("amount-cell").get(0).text(r.getAmount().toString()); // NOT NULL
			
			String desc = r.getDescription();
			fragPageCp.getElementsByClass("desc-cell").get(0).text(desc == null || desc.isEmpty() ? "-" : desc);
			
			Element receiptCell = fragPageCp.getElementsByClass("receipt-cell").get(0).empty();
			Element receiptBtn = receiptCell.appendElement("form").attr("method", "post").attr("action", "get-receipt?id=" + r.getId())
					   .appendElement("input").attr("type", "submit").addClass("btn").addClass("btn-primary")
				   							  .attr("value", "Receipt").attr("disabled", true);
			
			
			// TODO Check if receipt exists & enable receiptBtn if it exists.
			
			
			// Add record to table.
			recordTableBody.appendChild(fragPageCp.selectFirst(".record"));
		});
		
		
		return basePage.outerHtml();
	}

	private String getManagerHtml(User user, String target) throws IOException, SQLException {
		Document basePage, fragPage;
		ServletContext ctx = getServletContext();
		File webinfDir = new File(ctx.getRealPath("WEB-INF"));
		
		// Get fragments.
		basePage = Jsoup.parse(new File(webinfDir, "manager-base.html"), null);
		fragPage = Jsoup.parse(new File(webinfDir, "reimbursements-list.fragment.html"), null);
		basePage.getElementById("user").text(user.getUsername());
		basePage.head().appendElement("link").attr("rel", "stylesheet").attr("href", "resources/reimbursement-list.css");
		
		// Combine for full base page.
		Elements frags = fragPage.getElementById("fragment").children();
		basePage.getElementById("content").append(frags.outerHtml());
		
		// Get Reimbursements
		ReimbursementDao rDao = new ReimbursementDao();
		ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
		UserDao usrDao = new UserDao();
		List<ReimbursementStatus> rsList = rsDao.getReimbursementStatuses();
		List<Reimbursement> rList;
		List<User> usrList = usrDao.getAllUsers();
		
		final String targetUsername = target == null || target.isEmpty() ? null : target;
		
		if(targetUsername == null) {
			rList = rDao.getAllReimbursements();
		} else {
			rList = rDao.getAllUserReimbursements(targetUsername);
		}
		
		// Construct the filter menu.
		Element usrFilterSelect = basePage.getElementById("user-select");
		Element opt = usrFilterSelect.appendElement("option")
				.text("All")
				.attr("value", null);
		
		if(targetUsername == null) {
			opt.attr("selected", true);
		}
		
		usrList.forEach((User u) -> {
			Element usrOpt = usrFilterSelect.appendElement("option")
					.attr("value", u.getUsername())
					.text(u.getUsername());
			if(targetUsername != null && u.getUsername().equals(targetUsername)) {
				usrOpt.attr("selected value", true);
			}
		});
		
		
		// Append filter javascript to end of page.
		basePage.body().appendElement("script").attr("src","resources/reimbursement-list-filter.js");
		
		// Get fragment to clone
		Document recordFragPage = Jsoup.parse(new File(webinfDir, "reimbursement-record.fragment.html"), null);
		
		// Construct status select element.
		final Element statusSelectElem = new Element("select").addClass("form-control");
		rsList.forEach((ReimbursementStatus rs) -> {
			statusSelectElem.appendElement("option")
				.attr("value", rs.getId().toString())
				.text(rs.getStatus());
		});
		
		// Construct & add rows to the table.
		Element recordTableBody = basePage.getElementById("reimbursement-records");
		final DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
		
		rList.forEach((Reimbursement r) -> {
			Document fragPageCp = recordFragPage.clone();
			
			fragPageCp.getElementsByClass("id-cell").get(0).text(r.getId().toString()); // PK
			fragPageCp.getElementsByClass("type-cell").get(0).text(r.getType().getType()); // NOT NULL
			
			Element statusSelect = statusSelectElem.clone();
			statusSelect.addClass("status-select");
			statusSelect.selectFirst("option[value=\"" + r.getStatus().getId() + "\"]").attr("selected value", true);
			if(!r.getStatus().getStatus().equalsIgnoreCase("pending")) {
				statusSelect.attr("disabled", true);
			}
			fragPageCp.getElementsByClass("status-cell").get(0).empty().appendChild(statusSelect); // NOT NULL
			
			fragPageCp.getElementsByClass("time-submitted-cell").get(0).text(r.getSubmitted().format(dtFmt)); // NOT NULL
			fragPageCp.getElementsByClass("submitted-by-cell").get(0).text(r.getAuthor().getUsername()); // NOT NULL
			
			LocalDateTime ldt = r.getResolved();
			fragPageCp.getElementsByClass("time-resolved-cell").get(0).text(ldt == null ? "-" : ldt.format(dtFmt));
			
			User resolver = r.getResolver();
			fragPageCp.getElementsByClass("resolved-by-cell").get(0).text(resolver == null ? "-" : resolver.getUsername());
			
			
			fragPageCp.getElementsByClass("amount-cell").get(0).text(r.getAmount().toString()); // NOT NULL
			
			String desc = r.getDescription();
			fragPageCp.getElementsByClass("desc-cell").get(0).text(desc == null || desc.isEmpty() ? "-" : desc);
			
			Element receiptCell = fragPageCp.getElementsByClass("receipt-cell").get(0).empty();
			Element receiptBtn = receiptCell.appendElement("form").attr("method", "post").attr("action", "get-receipt?id=" + r.getId())
					   .appendElement("input").attr("type", "submit").addClass("btn").addClass("btn-primary")
				   							  .attr("value", "Receipt").attr("disabled", true);
			
			
			// TODO Check if receipt exists & enable receiptBtn if it exists.
			
			
			// Add record to table.
			recordTableBody.appendChild(fragPageCp.selectFirst(".record"));
		});
		
		// Javascript to update records.
		basePage.body().appendElement("script").attr("src", "resources/reimbursement-list.js");
		
		return basePage.outerHtml();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user;
		String html = "";
		
		// No session.
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		user = (User)(session.getAttribute("loggeduser"));
		
		if(user.getRole().getRole().equalsIgnoreCase("employee")) {
			try {
				html = getEmployeeHtml(user);
			} catch (SQLException ex) {
			}
		} else if(user.getRole().getRole().equalsIgnoreCase("manager")) {
			try {
				html = getManagerHtml(user, null);
			} catch(SQLException ex) {
			}
		} else {
			response.sendRedirect("logout");
			return;
		}
		
		response.setContentType("text/html");
		response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user;
		
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		// Only manager
		user = (User)(session.getAttribute("loggeduser"));
		
		if(!user.getRole().getRole().equalsIgnoreCase("manager")) {
			response.sendRedirect("home");
			return;
		}
		
		String targetUser = request.getParameter("user-select");
		String html = "";
		
		try {
			html = getManagerHtml(user, targetUser);
		} catch(SQLException | IOException ex) {
			ex.printStackTrace();
		}
		
		
		response.setContentType("text/html");
		response.getWriter().println(html);
	}

}
