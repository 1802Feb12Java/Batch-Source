package com.revature.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.dao.ReimbursementDAO;

public class ImplementationReimbursementDAO implements ReimbursementDAO {
	static Logger log = Logger.getLogger(ImplementationReimbursementDAO.class.getName());

	public void submitReimbursement(String username, double amount, String description, int type) throws SQLException {
		Connection c0 = ConnFactory.getInstance().getConnection();
		CallableStatement cs0 = c0.prepareCall("{call REQUEST_REIMBURSEMENT(?, ?, ?, ?)");
		cs0.setString(1, username);
		cs0.setDouble(2, amount);
		cs0.setString(3, description);
		cs0.setInt(4, type);
		cs0.execute();
		log.info("User [" + username + "] submitted a reimbursement");
		c0.close();
		log.debug("Connection to database closed");
	}

	public String getReimbursementsAll() throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Gson gsonBuilder = new GsonBuilder().create();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_SUBMITTED, R.R_RESOLVED, U.U_USERNAME, U2.U_USERNAME, T.RT_TYPE, S.RS_STATUS FROM ERS_REIMBURSEMENTS R LEFT OUTER JOIN ERS_USERS U2 ON U2.U_ID=R.U_ID_RESOLVER INNER JOIN ERS_REIMBURSEMENT_STATUS S ON S.RS_ID=R.RS_STATUS INNER JOIN ERS_REIMBURSEMENT_TYPE T ON T.RT_ID=R.RT_TYPE INNER JOIN ERS_USERS U ON U.U_ID=R.U_ID_AUTHOR ORDER BY R.R_SUBMITTED DESC";
		PreparedStatement ps1 = c1.prepareStatement(sqlQ);
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			String[] oneReimbursement = new String[9];
			for (int i = 0; i < oneReimbursement.length; i++) {
				try {
					oneReimbursement[i] = rs1.getString(i + 1);
				} catch (Exception e) {
				}
			}
			reimbursementList.add(oneReimbursement);
		}

		String jsonFromJavaArrayList = gsonBuilder.toJson(reimbursementList);
		log.info("List of reimbursements viewed");
		c1.close();
		log.debug("Connection to database closed");
		return jsonFromJavaArrayList;
	}

	public String getReimbursements(String user) throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Gson gsonBuilder = new GsonBuilder().create();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT R.R_ID, R.R_AMOUNT, R.R_DESCRIPTION, R.R_SUBMITTED, R.R_RESOLVED, U2.U_USERNAME, T.RT_TYPE, S.RS_STATUS FROM ERS_REIMBURSEMENTS R LEFT OUTER JOIN ERS_USERS U2 ON U2.U_ID=R.U_ID_RESOLVER INNER JOIN ERS_REIMBURSEMENT_STATUS S ON S.RS_ID=R.RS_STATUS INNER JOIN ERS_REIMBURSEMENT_TYPE T ON T.RT_ID=R.RT_TYPE INNER JOIN ERS_USERS U ON U.U_ID=R.U_ID_AUTHOR WHERE U.U_USERNAME=? ORDER BY R.R_SUBMITTED DESC";
		PreparedStatement ps1 = c1.prepareStatement(sqlQ);
		ps1.setString(1, user);
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			String[] oneReimbursement = new String[9];
			for (int i = 0; i < oneReimbursement.length; i++) {
				try {
					oneReimbursement[i] = rs1.getString(i + 1);
				} catch (Exception e) {
				}
			}
			reimbursementList.add(oneReimbursement);
		}

		String jsonFromJavaArrayList = gsonBuilder.toJson(reimbursementList);
		log.info("User [" + user + "] has viewed their reimbursements");
		c1.close();
		log.debug("Connection to database closed");
		return jsonFromJavaArrayList;
	}

	public void uploadImage() throws SQLException {
		// TODO Auto-generated method stub

	}

	public void approveDenyApplication(int reqId, int approveDeny, String approver) throws SQLException {
		Connection c6 = ConnFactory.getInstance().getConnection();
		CallableStatement cs6 = c6.prepareCall("{call APPROVE_DENY(?, ?, ?)}");
		cs6.setInt(1, reqId);
		cs6.setInt(2, approveDeny);
		cs6.setString(3, approver);
		cs6.execute();

		String forLogging = "";
		if (approveDeny == 3) {
			forLogging = "denied";
		} else {
			forLogging = "approved";
		}
		log.info("Reimbursement [" + reqId + "] " + forLogging + " by manager [" + approver + "]");

		c6.close();
		log.debug("Connection to database closed");
	}

	public String getAppliedAmount() throws SQLException {
		double[] totals = new double[12];
		Gson gsonBuilder = new GsonBuilder().create();
		String sqlq = "SELECT SUM(R_AMOUNT) FROM ERS_REIMBURSEMENTS WHERE EXTRACT(YEAR FROM R_SUBMITTED)=(SELECT TO_CHAR(sysdate, 'YYYY') FROM DUAL) AND EXTRACT(MONTH FROM R_SUBMITTED)=?";
		Connection c7 = ConnFactory.getInstance().getConnection();
		PreparedStatement ps7 = c7.prepareStatement(sqlq);

		for (int i = 1; i <= 12; i++) {
			ps7.setInt(1, i);
			ResultSet rs7 = ps7.executeQuery();

			while (rs7.next()) {
				if (rs7.getString(1) != null) {
					totals[i - 1] = Double.valueOf(rs7.getString(1));
				} else {
					totals[i - 1] = 0;
				}
			}
		}

		c7.close();
		log.debug("Connection to database closed");
		String jsonFromJava = gsonBuilder.toJson(totals);
		log.info("Calculated total of reimbursements applied for");
		return jsonFromJava;
	}

	public String getApprovedAmount() throws SQLException {
		double[] totals = new double[12];
		Gson gsonBuilder = new GsonBuilder().create();
		String sqlq = "SELECT SUM(R_AMOUNT) FROM ERS_REIMBURSEMENTS WHERE EXTRACT(YEAR FROM R_SUBMITTED)=(SELECT TO_CHAR(sysdate, 'YYYY') FROM DUAL) AND RS_STATUS=2 AND EXTRACT(MONTH FROM R_SUBMITTED)=?";
		Connection c7 = ConnFactory.getInstance().getConnection();
		PreparedStatement ps7 = c7.prepareStatement(sqlq);

		for (int i = 1; i <= 12; i++) {
			ps7.setInt(1, i);
			ResultSet rs7 = ps7.executeQuery();

			while (rs7.next()) {
				if (rs7.getString(1) != null) {
					totals[i - 1] = Double.valueOf(rs7.getString(1));
				} else {
					totals[i - 1] = 0;
				}
			}
		}

		c7.close();
		log.debug("Connection to database closed");
		String jsonFromJava = gsonBuilder.toJson(totals);
		log.info("Calculated total of reimbursements approved");
		return jsonFromJava;
	}

}