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
		String sqlQ = "SELECT DISTINCT R_ID, R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS, ERS_USERS ORDER BY R_SUBMITTED DESC";
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

		/* for (int j = 0; j < reimbursementList.size(); j++) {
			System.out.println("--------------------------------------------------");
			String[] returnedList = reimbursementList.get(j);
			for (String s : returnedList) {
				System.out.println(s);
			}
		} */
		c1.close();
		return jsonFromJavaArrayList;
	}

	public ArrayList<String[]> getReimbursements(String user) throws SQLException {
		ArrayList<String[]> reimbursementList = new ArrayList<String[]>();
		Connection c1 = ConnFactory.getInstance().getConnection();
		String sqlQ = "SELECT DISTINCT R_ID, R_AMOUNT, R_DESCRIPTION, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RS_STATUS FROM ERS_REIMBURSEMENTS, ERS_USERS WHERE U_USERNAME=? AND ERS_USERS.U_ID=ERS_REIMBURSEMENTS.U_ID_AUTHOR ORDER BY R_SUBMITTED DESC";
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

	public void approveDenyApplication(int reqId, int approveDeny) throws SQLException {
		Connection c6 = ConnFactory.getInstance().getConnection();
		CallableStatement cs6 = c6.prepareCall("{call APPROVE_DENY(?, ?)}");
		cs6.setInt(1, reqId);
		cs6.setInt(2,  approveDeny);
		cs6.execute();
		
		c6.close();
	}

}