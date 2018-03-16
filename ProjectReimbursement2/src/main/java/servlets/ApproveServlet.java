package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Reimbursement;
import objects.RequestStatus;
import objects.User;
import pov.ReimFunc;
import webfunctions.RequestController;

/**
 * Servlet implementation class ApproveServlet
 */
@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		int reimbId = Integer.parseInt(req.getParameter("approveReimb"));
		System.out.println("Selected Reimbursement to update: " + reimbId);

		try {
			Reimbursement reimb = new ReimFunc().getReimbById(reimbId);
			System.out.println("Got reimb: " + reimb);
			// List<ReimbStatus> statusList = (List<ReimbStatus>)
			// session.getAttribute("statuses");
			// ReimbStatus status = validateStatus(statusList, "Approved");
			RequestStatus status = new RequestStatus(2, "Approved");
			User user = (User) session.getAttribute("user");
			new ReimFunc().updateStatus(reimb, user, status);

			new RequestController();
			RequestController.getReimbs(req, resp);
			resp.sendRedirect("ManagerHome.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
