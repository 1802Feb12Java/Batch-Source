package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import objects.Reimbursement;
import objects.RequestStatus;
import objects.RequestType;
import objects.User;

public class ReimbursementDAO {

	private Connection conn;

	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/* SQL - inserting request into the database */

	public Reimbursement insertReimb(User author, double amount, RequestType type, RequestStatus status,
			String description) throws SQLException {
		// TODO REMOVE
		conn = ConnFactory.getInstance().getConnection();

		System.out.println("ReimbDao: reached insertReimb()");

		String sql = "INSERT INTO ERS_REIMBURSEMENT("
				+ " REQUEST_ID, FIRST_NAME, LAST_NAME, AMOUNT, DESCRIPTION, RECEIPT, STATUS, DATE_SUBMITTED, DATE_RESOLVED, REQUEST_RESOLVER)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		stmt.setInt(1, type.type_id);
		stmt.setString(2, author.getFirst_name());
		stmt.setString(3, author.getLast_name());
		stmt.setDouble(4, amount);
		stmt.setString(5, description);
		stmt.setNull(6, java.sql.Types.BLOB);
		stmt.setString(7, status.getStatus());
		stmt.setTimestamp(8, ts);
		stmt.setTimestamp(9, null);
		stmt.setInt(10, 0);										//	ERROR LIES HERE

		ResultSet rs = stmt.executeQuery();

		if (rs.next())
			rs.next();
		int pk = rs.getInt(1);

		Reimbursement reimb = new Reimbursement(pk, amount, ts, null, description,
		author, null, status, type);
		
		System.out.println("This is the riembursement: "+ reimb);
		return reimb;
	}

	/* SQL - get list of all reimbursements */

	public List<Reimbursement> getAllReimbs() throws SQLException {

		List<Reimbursement> list = new ArrayList<Reimbursement>();
		
		String sql = "select * from ERS_REIMBURSEMENT";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapReimbs(rs, list);
		System.out.println("getAllReimbs(): List: " + list);
		System.out.println("List Size: " + list.size());
		return list;
	}

	/* SQL - get list of reimbursements of one user, given user id */

	public List<Reimbursement> getReimbByAuthor(int author_id) throws SQLException {
		
		String sql = "SELECT * FROM REQUESTS WHERE REQUEST_ID=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, author_id);
		stmt.executeQuery();
		//mapReimbs(rs, results);
		//System.out.println(results);
		return null;//results;
	}

	/*  */

	private void mapReimbs(ResultSet rs, List<Reimbursement> list) throws SQLException {

		int id;
		double amount;
		User author;
		RequestStatus status = null;
		RequestType type = null;
		String description;
		Reimbursement reimb;

		while (rs.next()) {
			id = rs.getInt("REQUEST_ID");
			amount = rs.getDouble("AMOUNT");
			//author = setUser(rs, true);
			System.out.println("got amount and id");
			description = rs.getString("DESCRIPTION");
			System.out.println("git des");
			author = new User();
			author.setFirst_name("Peter");
			author.setLast_name("Parker");
			System.out.println("got user for isAuthor: " + author);
			reimb = new Reimbursement(id, amount, null, null, description, author, null, status, type);
			System.out.println("mapReimbs(): Is author: " + reimb);
			list.add(reimb);
		}

	}

	/*  */

	private User setUser(ResultSet rs, boolean isAuthor) throws SQLException {

		int id = 0;
		String username, lastName, firstName, email;
		if (isAuthor) {
			//id = rs.getInt("AUTHOR_ID");
		//	username = rs.getString("AUTHOR_USERNAME");
		//	firstName = rs.getString("AUTHOR_FIRST_NAME");
		//	lastName = rs.getString("AUTHOR_LAST_NAME");
		//	email = rs.getString("AUTHOR_EMAIL");
			
			username = rs.getString("ERS_USERNAME");
			firstName = rs.getString("USER_FIRST_NAME");
			lastName = rs.getString("USER_LAST_NAME");
			email = rs.getString("USER_EMAIL");
		} else {
			if (rs.getString("ERS_USERNAME") == null)
				return null;
			id = rs.getInt("ERS_USERS_ID");
			username = rs.getString("ERS_USERNAME");
			firstName = rs.getString("USER_FIRST_NAME");
			lastName = rs.getString("USER_LAST_NAME");
			email = rs.getString("USER_EMAIL");
		}
		return new User(id, username, firstName, lastName, email, null);
	}

	/* SQL - updates reimbursement status and info when it is decided */

	public void update(Reimbursement reimb) throws SQLException {

		String sql = "UPDATE REQUESTS" + " SET STATUS = ?,"
				+ " WHERE REQUEST_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getStatus_id().status_id);
		stmt.setInt(2, reimb.getId());
		stmt.executeUpdate();
	}

	/*  */

	private void mapRows(ResultSet rs, List<Reimbursement> results) throws SQLException {

		while (rs.next()) {
			int id = rs.getInt("REQUEST_ID");
			Double amount = rs.getDouble("AMOUNT");
			String description = rs.getString("DESCRIPTION");

			RequestStatus status_id = new RequestStatus();
			status_id.setStatus_id(rs.getInt("reimb_status_id"));

			RequestType type_id = new RequestType();
			type_id.setType_id(rs.getInt("reimb_type_id"));

			Reimbursement obj = new Reimbursement(id, amount, null, null, description, null,null, status_id, type_id);
			results.add(obj);
		}
	}

	/* SQL - get the list of reimbursements, given a desired status */

	public List<Reimbursement> getReimbByStatus(String reimb_status) throws SQLException {

		List<Reimbursement> results = new ArrayList<Reimbursement>();
		String sql = "select REQUESTS.reimb_status_id" + " from ers_reimbursement_status"
				+ " inner join REQUESTS"
				+ " on status.reimb_status = REQUESTS.STATUS"
				+ " where STATUS like '%" + reimb_status + "%'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		System.out.println("Get Reimbs By Status for: " + reimb_status + ": " + results);
		return results;
	}

	/* SQL - get the list of reimbursements and their status's */

	public List<RequestStatus> getStatus() throws SQLException {

		List<RequestStatus> results = new ArrayList<RequestStatus>();
		String sql = "SELECT REIMB_STATUS_ID, REIMB_STATUS" + " FROM ERS_REIMBURSEMENT_STATUS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int status_id = rs.getInt("REIMB_STATUS_ID");
			String status = rs.getString("REIMB_STATUS");
			RequestStatus obj = new RequestStatus(status_id, status);
			results.add(obj);
		}
		System.out.println("ReimbDAO: getStatus: " + results);
		return results;
	}

	/* SQL - get list of reimbursement types */

	public List<RequestType> getTypes() throws SQLException {

		List<RequestType> results = new ArrayList<RequestType>();
		String sql = "SELECT REIMB_TYPE_ID, REIMB_TYPE" + " FROM ERS_REIMBURSEMENT_TYPE";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int type_id = rs.getInt("REIMB_TYPE_ID");
			String type = rs.getString("REIMB_TYPE");
			RequestType obj = new RequestType(type_id, type);
			results.add(obj);
		}
		System.out.println("ReimbDAO: getTypes: " + results);
		return results;
	}

	/* SQL - get reimbursement type of a reimbursement, given it's id */

	public RequestType getTypeByID(int type_id) throws SQLException {

		String reimb_type = null;
		String sql = "SELECT REIMB_TYPE" + " FROM ERS_REIMBURSEMENT_TYPE" + " WHERE REIMB_TYPE_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, type_id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			reimb_type = rs.getString("REIMB_TYPE");
		}
		RequestType type = new RequestType(type_id, reimb_type);
		System.out.println("ReimbDAO: getTypeByID(): " + type);
		return type;
	}

	/* SQL - get the status of a reimbursement, given it's id */

	public RequestStatus getStatusByID(int status_id) throws SQLException {

		String reimb_status = null;

		String sql = "SELECT REIMB_STATUS" + " FROM ERS_REIMBURSEMENT_STATUS" + " WHERE REIMB_STATUS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, status_id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			reimb_status = rs.getString("REIMB_STATUS");
		}
		RequestStatus status = new RequestStatus(status_id, reimb_status);
		System.out.println("ReimbDAO: getTypeByID(): " + status);
		return status;
	}

	/* SQL - update the status of a reimbursement, given it's details */

	void updateStatus(int reimb_id, int resolver, int status_id, Timestamp ts) throws SQLException {

		String sql = "UPDATE REQUESTS" + " SET REIMB_STATUS_ID = ?" + " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, status_id);
		stmt.setInt(2, reimb_id);
		stmt.executeQuery();
	}

	/* SQL - get reimbursement request, given it's id */

	Reimbursement getReimbById(int reimb_id) throws SQLException {

//		String sql = "SELECT AMOUNT," + " s.REIMB_STATUS_ID, s.REIMB_STATUS," + " t.REIMB_TYPE_ID, t.REIMB_TYPE,"
//				+ " DESCRIPTION" + " auth.ERS_USERS_ID AS AUTHOR_ID,"
//				+ " auth.ERS_USERNAME AS AUTHOR_USERNAME," + " auth.USER_FIRST_NAME AS AUTHOR_FIRST_NAME,"
//				+ " auth.USER_LAST_NAME AS AUTHOR_LAST_NAME," + " auth.USER_EMAIL AS AUTHOR_EMAIL,"
//				+ " res.ERS_USERS_ID AS RESOLVER_ID," + " res.ERS_USERNAME AS RESOLVER_USERNAME,"
//				+ " res.USER_FIRST_NAME AS RESOLVER_FIRST_NAME," + " res.USER_LAST_NAME AS RESOLVER_LAST_NAME,"
//				+ " res.USER_EMAIL AS RESOLVER_EMAIL" + " FROM REQUESTS r" + " JOIN ERS_REIMBURSEMENT_TYPE t"
//				+ " ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID" + " JOIN ERS_REIMBURSEMENT_STATUS s"
//				+ " ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID" + " LEFT JOIN ERS_USERS auth"
//				+ " ON r.REIMB_AUTHOR = auth.ERS_USERS_ID" + " LEFT join ers_users res"
//				+ " ON r.reimb_resolver = res.ERS_USERS_ID" + " WHERE REIMB_ID=?";

		String sql = "SELECT * FROM REQUESTS WHERE REQUEST_ID = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb_id);

		double amount;
		User author;
		User resolver;
		RequestStatus status;
		RequestType type;
		String description;
		Reimbursement reimb = null;

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			amount = rs.getDouble("AMOUNT");
//			author = setUser(rs, true);
			author= new User();
			resolver = setUser(rs, false);
			status = new RequestStatus(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
			type = new RequestType(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
			description = rs.getString("DESCRIPTION");
			reimb = new Reimbursement(reimb_id, amount, null, null, description, author, resolver, status,
					type);
		}

		return reimb;
	}
}