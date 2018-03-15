package servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class DenyServlet
 */
@WebServlet("/DenyServlet")
public class DenyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DenyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Deny Reimb: " + req.getParameter("denyReimb"));
		HttpSession session = req.getSession();
		int reimbId = Integer.parseInt(req.getParameter("denyReimb"));
		System.out.println("Selected Reimbursement to update: " + reimbId);

		try {
			Reimbursement reimb = new ReimFunc().getReimbById(reimbId);
			System.out.println("Got reimb: " + reimb);
			RequestStatus status = new RequestStatus(3, "Denied");
			User user = (User) session.getAttribute("user");
			new ReimFunc().updateStatus(reimb, user, status);

			RequestController.getReimbs(req, resp);
			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			req.getSession().setAttribute("reimbs", list);
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
