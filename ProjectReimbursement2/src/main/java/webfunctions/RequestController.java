package webfunctions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Reimbursement;
import objects.RequestStatus;
import objects.RequestType;
import objects.User;
import pov.ReimFunc;

public class RequestController {

	public void getTypes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			new ReimFunc();
			List<RequestType> type = ReimFunc.getTypes();
			request.getSession().setAttribute("types", type);
			response.sendRedirect("NewReimb.html");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			new ReimFunc();
			List<RequestStatus> status = ReimFunc.getStatus();
			request.getSession().setAttribute("statuses", status);
			request.getRequestDispatcher("managerHome.html").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getReimbs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new ReimFunc();
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		try {
			List<Reimbursement> reimb = ReimFunc.getReimbs(user);
			req.getSession().setAttribute("reimbs", reimb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertReimb(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("ReimbContoller: insertReimb(): Got amount: " + req.getParameter("amount"));
		System.out.println("ReimbContoller: insertReimb(): Got descr: " + req.getParameter("description"));
		System.out.println("ReimbContoller: insertReimb(): Got type: " + req.getParameter("type"));
		try {
			HttpSession session = req.getSession();

			@SuppressWarnings("unchecked")
			List<RequestType> typeList = (List<RequestType>) session.getAttribute("types");
			@SuppressWarnings("unchecked")
			List<RequestStatus> statusList = (List<RequestStatus>) session.getAttribute("status");

			double amount = Authenticate.validateAmount(req.getParameter("amount"));
			System.out.println("ReimbContoller: insertReimb(): Validated amount: " + amount);
			RequestType type = Authenticate.validateType(typeList, req.getParameter("type"));
			System.out.println("ReimbContoller: insertReimb(): Validated Type: " + type);
			RequestStatus status = Authenticate.setReimbstatus(statusList, "Pending");
			System.out.println("ReimbContoller: insertReimb(): Validated Status: " + status);
			User user = (User) session.getAttribute("user");
			String description = req.getParameter("description");

			Reimbursement reimb = ReimFunc.insertReimb(user, amount, type, status, description);

			// getReimbs(req, resp);

			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			System.out.println("ReimbContoller: insertReimb(): list of reimbursements:" + list);
			list.add(reimb);
			System.out.println("ReimbContoller: insertReimb(): Added reimb: " + reimb);
			session.setAttribute("reimbs", list);

			req.getRequestDispatcher("empHome.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String message = "Invalid input.";
				req.setAttribute("errorMessage", message);
				req.getRequestDispatcher("empHome.jsp").forward(req, resp);
				System.out.println("ReimbContoller: insertReimb(): Second forward.");
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void approveReimb(HttpServletRequest req, HttpServletResponse resp) {
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

			getReimbs(req, resp);
			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			req.getSession().setAttribute("reimbs", list);
			req.getRequestDispatcher("managerHome.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void denyReimb(HttpServletRequest req, HttpServletResponse resp) {
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

			getReimbs(req, resp);
			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			req.getSession().setAttribute("reimbs", list);
			req.getRequestDispatcher("managerHome.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}