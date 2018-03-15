package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import objects.Reimbursement;
import objects.RequestStatus;
import objects.RequestType;
import objects.User;
import pov.ReimFunc;
import webfunctions.Authenticate;

/**
 * Servlet implementation class InsertReimbServlet
 */
@WebServlet("/InsertReimbServlet")
public class InsertReimbServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(InsertReimbServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertReimbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String amount = req.getParameter("amount");
		String descript = req.getParameter("description");
		String type = req.getParameter("type");
		logger.info("ReimbController: insertReimb(): Got amount: " + amount);
		System.out.println("ReimbController: insertReimb(): Got descr: " + descript);
		System.out.println("ReimbController: insertReimb(): Got type: " + type);
		try {
			HttpSession session = req.getSession(false);

			double amountD = Authenticate.validateAmount(amount);
			System.out.println("ReimbContoller: insertReimb(): Validated amount: " + amountD);
			RequestType typeR = Authenticate.validateType(Authenticate.getTypeList(), type);
			System.out.println("ReimbController: insertReimb(): Validated Type: " + type);
			RequestStatus status = Authenticate.setReimbstatus(Authenticate.getStatusList(), "Pending");
			System.out.println("ReimbContoller: insertReimb(): Validated Status: " + status);
			User user = (User) session.getAttribute("user");

			ReimFunc.insertReimb(user, amountD, typeR, status, descript);

			// getReimbs(req, resp);

			// @SuppressWarnings("unchecked")
			// List<Reimbursement> list = (List<Reimbursement>)
			// session.getAttribute("reimbs");
			// System.out.println("ReimbContoller: insertReimb(): list of reimbursements:" +
			// list);
			// list.add(reimb);
			// System.out.println("ReimbContoller: insertReimb(): Added reimb: " + reimb);
			// session.setAttribute("reimbs", list);

			resp.sendRedirect("/ers/EmpHome.html");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String message = "Invalid input.";
				req.setAttribute("errorMessage", message);
				resp.sendRedirect("EmpHome.html");
				System.out.println("ReimbController: insertReimb(): Second forward.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
