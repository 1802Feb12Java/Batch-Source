package com.revature.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.revature.bean.Reimbursement;
import com.revature.bean.ReimbursementStatus;
import com.revature.bean.User;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementStatusDao;

/**
 * Servlet implementation class UpdateReimbursementServlet
 */
public class UpdateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties servletProps = ServletProperties.getProperties();
		
		if(servletProps != null) {
			request.setCharacterEncoding(servletProps.getProperty("request.enc"));
			response.setCharacterEncoding(servletProps.getProperty("response.enc"));
		}
		
		HttpSession session = request.getSession(false);
		User user;
		
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		// Get user
		user = (User)(session.getAttribute("loggeduser"));
		
		if(!user.getRole().getRole().equalsIgnoreCase("manager")) {
			response.sendRedirect("home");
			return;
		}
		
		// Manager only //
		
		// Update the database
		int id = Integer.parseInt(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		
		Reimbursement r;
		ReimbursementStatus rStatus;
		String html = "";
		
		try {
			ReimbursementDao rDao = new ReimbursementDao();
			ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
			
			// Set update info
			r = new Reimbursement();
			r.setResolver(user);
			r.setResolved(LocalDateTime.now());
			rStatus = rsDao.getReimbursementStatusById(status);
			r.setStatus(rStatus);
			
			rDao.updateReimbursement(id, r);
			r = rDao.getReimbursementById(id);
			
			////////////////////////////////////
			// Create updated html
			File webinfDir = new File(getServletContext().getRealPath("WEB-INF"));
			Document recordFragDoc = Jsoup.parse(new File(webinfDir, "reimbursement-record.fragment.html"), null);
			List<ReimbursementStatus> rsList = rsDao.getReimbursementStatuses();
			
			// Update record frag
			final DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
			
			recordFragDoc.getElementsByClass("id-cell").get(0).text(r.getId().toString()); // PK
			recordFragDoc.getElementsByClass("type-cell").get(0).text(r.getType().getType()); // NOT NULL
			Element statusSelect = new Element("select").addClass("form-control");
			rsList.forEach((ReimbursementStatus rs) -> {
				statusSelect.appendElement("option")
					.attr("value", rs.getId().toString())
					.text(rs.getStatus());
			});
			statusSelect.addClass("status-select");
			statusSelect.selectFirst("option[value=\"" + r.getStatus().getId() + "\"]").attr("selected value", true);
			if(!r.getStatus().getStatus().equalsIgnoreCase("pending")) {
				statusSelect.attr("disabled", true);
			}
			recordFragDoc.getElementsByClass("status-cell").get(0).empty().appendChild(statusSelect); // NOT NULL
			
			recordFragDoc.getElementsByClass("time-submitted-cell").get(0).text(r.getSubmitted().format(dtFmt)); // NOT NULL
			recordFragDoc.getElementsByClass("submitted-by-cell").get(0).text(r.getAuthor().getUsername()); // NOT NULL
			
			LocalDateTime ldt = r.getResolved();
			recordFragDoc.getElementsByClass("time-resolved-cell").get(0).text(ldt == null ? "-" : ldt.format(dtFmt));
			
			User resolver = r.getResolver();
			recordFragDoc.getElementsByClass("resolved-by-cell").get(0).text(resolver == null ? "-" : resolver.getUsername());
			
			
			recordFragDoc.getElementsByClass("amount-cell").get(0).text(r.getAmount().toString()); // NOT NULL
			
			String desc = r.getDescription();
			recordFragDoc.getElementsByClass("desc-cell").get(0).text(desc == null || desc.isEmpty() ? "-" : desc);
			
			Element receiptCell = recordFragDoc.getElementsByClass("receipt-cell").get(0).empty();
			Element receiptBtn = receiptCell.appendElement("form").attr("method", "post").attr("action", "get-receipt?id=" + r.getId())
					   .appendElement("input").attr("type", "submit").addClass("btn").addClass("btn-primary")
				   							  .attr("value", "Receipt").attr("disabled", true);
			
			
			// Enable download button if receipt exists.
			try {
				Map.Entry<String, Integer> receiptMetadata = rDao.getReceiptMetadata(r.getId());
				
				if(receiptMetadata != null) {
				    final String[] units = {"B", "kB", "MB", "GB", "TB" };
				    
					// Calculate size.
					double filesize = receiptMetadata.getValue();
					int digitGroups = (int) (Math.log10(filesize)/Math.log10(1024));
				    String filesizeStr = new DecimalFormat("#,##0.#")
				    		.format(filesize/Math.pow(1024, digitGroups)) 
				    		+ " " + units[digitGroups];
					
					receiptBtn.attr("disabled", false).attr("value", "Receipt (" + filesizeStr + ")");
				}
				
			} catch(SQLException ex) {
				// Do nothing.
			}
			
			
			Element fragRecord = recordFragDoc.selectFirst("#fragment .record");
			html = fragRecord.html();
			
		} catch(SQLException | IOException ex) {
			ex.printStackTrace();
		}
		
		// Write new html to response
		response.setContentType("text/html");
		response.getWriter().println(html);
	}

}
