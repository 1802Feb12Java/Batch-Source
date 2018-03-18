package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import objects.Reimbursement;
import objects.RequestStatus;
import objects.RequestType;
import objects.User;

public class DAO {
	private static final Logger logger = LogManager.getLogger(DAO.class);

	/* returns connection object */

	private static Connection getConnection() throws SQLException {

		return ConnFactory.getInstance().getConnection();
	}

	/* find the user in the database, provided the login info */

	public User getUserLoginInfo(String username) throws SQLException {

		Connection conn = getConnection();
		User user = new UserDAO(conn).getUserLoginInfo(username);
		conn.close();
		return user;
	}

	/* find reimbursement request by id of request */

	public static Reimbursement getRequestById(int reimb_id) throws SQLException {

		Connection conn = getConnection();
		Reimbursement request = new ReimbursementDAO(conn).getReimbById(reimb_id);
		conn.close();
		System.out.println("We are in method getRequestById(): " + request);
		return request;
	}

	/* get the list of types of reimbursements */

	public List<RequestType> getTypes() throws SQLException {

		Connection conn = getConnection();
		List<RequestType> list = new ReimbursementDAO(conn).getTypes();
		conn.close();
		System.out.println("We are in the method getTypes(): " + list);
		return list;
	}

	/* get the list of status's of reimbursements */

	public List<RequestStatus> getStatus() throws SQLException {

		Connection conn = getConnection();
		List<RequestStatus> list = new ReimbursementDAO(conn).getStatus();
		conn.close();
		System.out.println("We are in the method getStatus(): " + list);
		return list;
	}

	/* put in a reimbursement */

	public Reimbursement insertRequest(User author, double amount, RequestType type, RequestStatus status,
			String description) {

		Connection conn = null;
		try {
			conn = getConnection();
			ReimbursementDAO dao = new ReimbursementDAO(conn);
			Reimbursement reimb = dao.insertReimb(author, amount, type, status, description);
			return reimb;
		} catch (SQLException e) {
			logger.error(e);
		//	try {
		//	conn.rollback();
		//	} catch (SQLException e1) {
		//	// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//	}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * change the status of a pending reimbursement, given details of reimbursement
	 */

	public void updateStatus(Reimbursement reimb, User user, RequestStatus status) throws Exception {

		if (!user.getRole_id().getUser_role().equals("Manager")) {
			throw new Exception();
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		updateStatus(reimb.getId(), user.getUser_id(), status.getStatus_id(), ts);
		reimb.setStatus_id(new RequestStatus(status.getStatus_id(), status.getStatus()));
		reimb.setDate_resolved(ts);
		reimb.setResolver_id(user);

	}

	/* change the status of a pending reimbursement, given id of reimbursement */

	private void updateStatus(int reimb_id, int resolver, int status_id, Timestamp ts) {

		Connection conn = null;
		try {
			conn = getConnection();
			ReimbursementDAO dao = new ReimbursementDAO(conn);
			dao.updateStatus(reimb_id, resolver, status_id, ts);
			conn.commit();
		} catch (SQLException e) {
		//	try {
		//		conn.rollback();
		//	} catch (SQLException e1) {
				// TODO Auto-generated catch block
		//		e1.printStackTrace();
		//
			}
	//	} finally {
	//		try {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	//		} catch (SQLException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
			}

	/* get list of all reimbursements */

	public static List<Reimbursement> getAllReimbs() throws Exception {

		Connection conn = null;
		try {
			conn = getConnection();
			ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
			List<Reimbursement> list = reimbDao.getAllReimbs();
			conn.close();
			return list;
		} catch (SQLException e) {
			logger.error(e);
			throw new Exception();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
	}

	/* get list of all reimbursements of one user, given their id */

	public static List<Reimbursement> getReimbByAuthor(int author_id) throws Exception {

		Connection conn = null;
		try {
			conn = getConnection();
			ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
			List<Reimbursement> list = reimbDao.getReimbByAuthor(author_id);
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*  */

	public String getHash(String username) {

		Connection conn = null;
		try {
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			String hashedPassword = dao.getPassword(username);
			return hashedPassword;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* locate user in memory by their username */

	public static User getUser(String username) {
		try {
			System.out.println("in the method getUser()");
			UserDAO dao = new UserDAO(getConnection());
			return dao.getUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// User user = null;
		// Connection conn = null;
		// try{
		// conn = getConnection();
		// UserDAO dao = new UserDAO(conn);
		// user = dao.getUser(username);
		// return user;
		// }catch(SQLException e){
		// e.printStackTrace();
		// return null;
		// }finally{
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		return null;
	}

}