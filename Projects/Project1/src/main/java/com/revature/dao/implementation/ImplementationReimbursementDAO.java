package com.revature.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.dao.ReimbursementDAO;

public class ImplementationReimbursementDAO implements ReimbursementDAO {

	public void submitReimbursement(String username, double amount, String description, int type) throws SQLException {
		Connection c0 = ConnFactory.getInstance().getConnection();
		CallableStatement cs0 = c0.prepareCall("{call REQUEST_REIMBURSEMENT(?, ?, ?, ?)");
		cs0.setString(1, username);
		cs0.setDouble(2, amount);
		cs0.setString(3, description);
		cs0.setInt(4, type);
		cs0.execute();
		c0.close();
	}

	public String getReimbursementsAll() throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Gson gsonBuilder = new GsonBuilder().create();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT A.R_ID, A.R_AMOUNT, A.R_DESCRIPTION, A.R_SUBMITTED, A.R_RESOLVED, B.U_USERNAME, C.U_USERNAME, D.RT_TYPE, E.RS_STATUS FROM ERS_REIMBURSEMENTS A INNER JOIN ERS_USERS B ON A.U_ID_AUTHOR=B.U_ID INNER JOIN ERS_USERS C ON A.U_ID_RESOLVER=C.U_ID INNER JOIN ERS_REIMBURSEMENT_TYPE D ON A.RT_TYPE=D.RT_ID INNER JOIN ERS_REIMBURSEMENT_STATUS E ON A.RS_STATUS=E.RS_ID ORDER BY R_SUBMITTED DESC";
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

		c1.close();
		return jsonFromJavaArrayList;
	}

	public String getReimbursements(String user) throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Gson gsonBuilder = new GsonBuilder().create();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT A.R_ID, A.R_AMOUNT, A.R_DESCRIPTION, A.R_SUBMITTED, A.R_RESOLVED, B.U_USERNAME, D.RT_TYPE, E.RS_STATUS FROM ERS_REIMBURSEMENTS A INNER JOIN ERS_USERS C ON A.U_ID_AUTHOR=C.U_ID INNER JOIN ERS_REIMBURSEMENT_TYPE D ON A.RT_TYPE=D.RT_ID INNER JOIN ERS_REIMBURSEMENT_STATUS E ON A.RS_STATUS=E.RS_ID INNER JOIN ERS_USERS B ON A.U_ID_RESOLVER=B.U_ID WHERE A.U_ID_AUTHOR=(SELECT U_ID FROM ERS_USERS WHERE U_USERNAME=?) ORDER BY R_SUBMITTED DESC";
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

		c1.close();
		return jsonFromJavaArrayList;
	}

	public ArrayList<String[]> getPendingReimbursementsAll() throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT R_ID, R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS, ERS_USERS WHERE RS_STATUS<2 ORDER BY R_SUBMITTED DESC";
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

		for (int j = 0; j < reimbursementList.size(); j++) {
			System.out.println("--------------------------------------------------");
			String[] returnedList = reimbursementList.get(j);
			for (String s : returnedList) {
				System.out.println(s);
			}
		}
		c1.close();
		return reimbursementList;
	}

	public ArrayList<String[]> getPendingReimbursements(String user) throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT R_ID, R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS, ERS_USERS WHERE U_USERNAME=? AND RS_STATUS<2 AND ERS_USERS.U_ID=ERS_REIMBURSEMENTS.U_ID_AUTHOR ORDER BY R_SUBMITTED DESC";
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

		for (int j = 0; j < reimbursementList.size(); j++) {
			System.out.println("--------------------------------------------------");
			String[] returnedList = reimbursementList.get(j);
			for (String s : returnedList) {
				System.out.println(s);
			}
		}
		c1.close();
		return reimbursementList;
	}

	public ArrayList<String[]> getResolvedAll() throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT R_ID, R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS, ERS_USERS WHERE RS_STATUS>1 ORDER BY R_SUBMITTED DESC";
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

		for (int j = 0; j < reimbursementList.size(); j++) {
			System.out.println("--------------------------------------------------");
			String[] returnedList = reimbursementList.get(j);
			for (String s : returnedList) {
				System.out.println(s);
			}
		}
		c1.close();
		return reimbursementList;
	}

	public ArrayList<String[]> getResolved(String user) throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT R_ID, R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS, ERS_USERS WHERE U_USERNAME=? AND RS_STATUS>1 AND ERS_USERS.U_ID=ERS_REIMBURSEMENTS.U_ID_AUTHOR ORDER BY R_SUBMITTED DESC";
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

		for (int j = 0; j < reimbursementList.size(); j++) {
			System.out.println("--------------------------------------------------");
			String[] returnedList = reimbursementList.get(j);
			for (String s : returnedList) {
				System.out.println(s);
			}
		}
		c1.close();
		return reimbursementList;
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

		c6.close();
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
		String jsonFromJava = gsonBuilder.toJson(totals);
		return jsonFromJava;
	}

}