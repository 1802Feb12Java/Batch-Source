package com.revature.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Reimbursement;
import com.revature.bean.ReimbursementStatus;
import com.revature.bean.ReimbursementType;
import com.revature.bean.User;

public class ReimbursementDao {
	private ConnectionManager cm;
	private ReimbursementTypeDao rtDao;
	private ReimbursementStatusDao rsDao;
	private UserDao userDao;
	
	public ReimbursementDao() throws SQLException, IOException {
		cm = ConnectionManager.getInstance();
		rtDao = new ReimbursementTypeDao();
		rsDao = new ReimbursementStatusDao();
		userDao = new UserDao();
	}
	
	//////////// Lookup functions ////////////
	/**
	 * Gets a list of all reimbursement requests.
	 * @return	A list of reimbursement requests.
	 * @throws SQLException Caused by query or connection.
	 */
	public List<Reimbursement> getAllReimbursements() throws SQLException {
		List<Reimbursement> rList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rSet = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_all_reimbursements();");
			rSet = ps.executeQuery();
			
			while(rSet.next()) {
				Reimbursement r = new Reimbursement();
				
				// Basic fields.
				r.setId(rSet.getInt("id"));
				r.setAmount(rSet.getDouble("amount"));
				r.setDescription(rSet.getString("description"));
				r.setSubmitted(rSet.getTimestamp("submitted").toLocalDateTime());
				r.setResolved(rSet.getTimestamp("resolved").toLocalDateTime());
				
				// Referenced fields.
				User user;
				user = userDao.getUserById(rSet.getInt("author_id"));
				r.setAuthor(user);
				
				user = userDao.getUserById(rSet.getInt("resolver_id"));
				r.setResolver(user);
				
				ReimbursementType type = rtDao.getReimbursementTypeById(rSet.getInt("r_type"));
				r.setType(type);
				
				ReimbursementStatus status = rsDao.getReimbursementStatusById(rSet.getInt("r_status"));
				r.setStatus(status);
				
				rList.add(r);
			}
		} finally {
			if(rSet != null) rSet.close();
			if(ps != null) ps.close();
		}
		
		return rList;
	}
	
	/**
	 * Get a list of all reimbursement requests by a specific user. 
	 * @param username	The user's username.
	 * @return	The list of the user's reimbursement requests.
	 * @throws SQLException	Caused by query or connection.
	 */
	public List<Reimbursement> getAllUserReimbursements(String username) throws SQLException {
		List<Reimbursement> rList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rSet = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_all_user_reimbursements(?);");
			ps.setString(1, username);
			rSet = ps.executeQuery();
			
			while(rSet.next()) {
				Reimbursement r = new Reimbursement();
				
				// Basic fields.
				r.setId(rSet.getInt("id"));
				r.setAmount(rSet.getDouble("amount"));
				r.setDescription(rSet.getString("description"));
				r.setSubmitted(rSet.getTimestamp("submitted").toLocalDateTime());
				r.setResolved(rSet.getTimestamp("resolved").toLocalDateTime());
				
				// Referenced fields.
				User user;
				user = userDao.getUserById(rSet.getInt("author_id"));
				r.setAuthor(user);
				
				user = userDao.getUserById(rSet.getInt("resolver_id"));
				r.setResolver(user);
				
				ReimbursementType type = rtDao.getReimbursementTypeById(rSet.getInt("r_type"));
				r.setType(type);
				
				ReimbursementStatus status = rsDao.getReimbursementStatusById(rSet.getInt("r_status"));
				r.setStatus(status);
				
				rList.add(r);
			}
		} finally {
			if(rSet != null) rSet.close();
			if(ps != null) ps.close();
		}
		
		return rList;
	}
	
	/**
	 * Get a list of all reimbursements with the given reimbursement type.
	 * @param rType	The type of reimbursement to get.
	 * @return	A list of a specified type of reimbursement request.
	 * @throws SQLException Caused by query or connection.
	 */
	public List<Reimbursement> getReimbursementsByType(ReimbursementType rType) throws SQLException {
		List<Reimbursement> rList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rSet = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_reimbursements_by_type(?);");
			ps.setInt(1, rType.getId());
			rSet = ps.executeQuery();
			
			while(rSet.next()) {
				Reimbursement r = new Reimbursement();
				
				// Basic fields.
				r.setId(rSet.getInt("id"));
				r.setAmount(rSet.getDouble("amount"));
				r.setDescription(rSet.getString("description"));
				r.setSubmitted(rSet.getTimestamp("submitted").toLocalDateTime());
				r.setResolved(rSet.getTimestamp("resolved").toLocalDateTime());
				
				// Referenced fields.
				User user;
				user = userDao.getUserById(rSet.getInt("author_id"));
				r.setAuthor(user);
				
				user = userDao.getUserById(rSet.getInt("resolver_id"));
				r.setResolver(user);
				
				ReimbursementType type = rtDao.getReimbursementTypeById(rSet.getInt("r_type"));
				r.setType(type);
				
				ReimbursementStatus status = rsDao.getReimbursementStatusById(rSet.getInt("r_status"));
				r.setStatus(status);
				
				rList.add(r);
			}
		} finally {
			if(rSet != null) rSet.close();
			if(ps != null) ps.close();
		}
		
		return rList;
	}
	
	/**
	 * Get a list of all reimbursements resolved by the specified user.
	 * @param resolverUsername	The request resolver's username.
	 * @return	A list of reimbursements resolved by the given user.
	 * @throws SQLException	Caused by query or connection.
	 */
	public List<Reimbursement> getReimbursementsResolvedBy(String resolverUsername) throws SQLException {
		List<Reimbursement> rList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rSet = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_reimbursements_resolved_by(?);");
			ps.setString(1, resolverUsername);
			rSet = ps.executeQuery();
			
			while(rSet.next()) {
				Reimbursement r = new Reimbursement();
				
				// Basic fields.
				r.setId(rSet.getInt("id"));
				r.setAmount(rSet.getDouble("amount"));
				r.setDescription(rSet.getString("description"));
				r.setSubmitted(rSet.getTimestamp("submitted").toLocalDateTime());
				r.setResolved(rSet.getTimestamp("resolved").toLocalDateTime());
				
				// Referenced fields.
				User user;
				user = userDao.getUserById(rSet.getInt("author_id"));
				r.setAuthor(user);
				
				user = userDao.getUserById(rSet.getInt("resolver_id"));
				r.setResolver(user);
				
				ReimbursementType type = rtDao.getReimbursementTypeById(rSet.getInt("r_type"));
				r.setType(type);
				
				ReimbursementStatus status = rsDao.getReimbursementStatusById(rSet.getInt("r_status"));
				r.setStatus(status);
				
				rList.add(r);
			}
		} finally {
			if(rSet != null) rSet.close();
			if(ps != null) ps.close();
		}
		
		return rList;
	}
	
	/**
	 * Get a reimbursement by its ID. 
	 * @param id The reimbursement ID.
	 * @return	The reimbursement with the given ID, if it exists. Otherwise, null.
	 * @throws SQLException Caused by query or connection.
	 */
	public Reimbursement getReimbursementById(int id) throws SQLException {
		Reimbursement r = null;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		
		try {
			ps = cm.getConnection().prepareStatement("SELECT * FROM get_reimbursement_by_id(?);");
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			
			if(rSet.next()) {
				r = new Reimbursement();
				
				// Basic fields.
				r.setId(rSet.getInt("id"));
				r.setAmount(rSet.getDouble("amount"));
				r.setDescription(rSet.getString("description"));
				r.setSubmitted(rSet.getTimestamp("submitted").toLocalDateTime());
				r.setResolved(rSet.getTimestamp("resolved").toLocalDateTime());
				
				// Referenced fields.
				User user;
				user = userDao.getUserById(rSet.getInt("author_id"));
				r.setAuthor(user);
				
				user = userDao.getUserById(rSet.getInt("resolver_id"));
				r.setResolver(user);
				
				ReimbursementType type = rtDao.getReimbursementTypeById(rSet.getInt("r_type"));
				r.setType(type);
				
				ReimbursementStatus status = rsDao.getReimbursementStatusById(rSet.getInt("r_status"));
				r.setStatus(status);
			}
		} finally {
			if(rSet != null) rSet.close();
			if(ps != null) ps.close();
		}
		
		return r;
	}

	/**
	 * Gets the bytes of the receipt file. 
	 * @param id The ID of the reimbursement request the receipt belongs to. 
	 * @return The receipt file's byte data.
	 * @throws SQLException	Caused by query or connection.
	 */
	public byte[] getReceipt(int id) throws SQLException {
		byte[] receipt = null;
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{? = call get_receipt(?)}");
			cs.registerOutParameter(1, Types.BLOB);
			cs.setInt(2, id);
			cs.execute();
			Blob blob = cs.getBlob(1);
			receipt = blob.getBytes(0, (int)(blob.length()));
		} finally {
			if(cs != null) cs.close();
		}
		
		return receipt;
	}
	
	
	//////////// Insert functions ////////////
	/**
	 * Adds a new reimbursement request.
	 * @param request	The reimbursement request to insert.
	 * 					The used fields are: amount, description, submitted, author, type, and status.
	 * 					All other fields are ignored.
	 * @throws SQLException	Caused by query or connection.
	 */
	public void addNewReimbursement(Reimbursement request) throws SQLException {
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call add_new_reimbursement(?, ?, ?, ?, ?, ?)}");
			cs.setDouble(1, request.getAmount());
			cs.setString(2, request.getDescription());
			cs.setTimestamp(3, java.sql.Timestamp.valueOf(request.getSubmitted()));
			cs.setInt(4, request.getAuthor().getId());
			cs.setInt(5, request.getType().getId());
			cs.setInt(6, request.getStatus().getId());
			cs.execute();
		} finally {
			if(cs != null) cs.close();
		}
	}
	
	//////////// Update functions ////////////
	/**
	 * Updates the reimbursement request with editable parts of the request.
	 * @param id	The ID of the reimbursement request to edit.
	 * @param req	Holds the reimbursement info. Editable fields are:
	 * 				amount, description, resolved, resolver, and status.
	 * 				Any editable fields set to null are ignored.
	 * @return true if reimbursement request is updated. Otherwise, false.
	 * @throws SQLException
	 */
	public boolean updateReimbursement(int id, Reimbursement req) throws SQLException {
		Reimbursement existingReq = getReimbursementById(id);
		CallableStatement cs = null;
		
		if(existingReq == null) {
			return false;
		}
		
		try {
			cs = cm.getConnection().prepareCall("{call update_reimbursement(?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, id);
			cs.setDouble(2, (req.getAmount() == null ? 
					existingReq.getAmount() : req.getAmount()));
			cs.setString(3, (req.getDescription() == null ? 
					existingReq.getDescription() : req.getDescription()));
			cs.setTimestamp(4, req.getResolved() == null ?
					java.sql.Timestamp.valueOf(existingReq.getResolved()) :
					java.sql.Timestamp.valueOf(req.getResolved())
					);
			cs.setInt(5, req.getResolver() == null ?
					existingReq.getResolver().getId() :
					req.getResolver().getId()
					);
			cs.setInt(6, req.getStatus() == null ?
					existingReq.getStatus().getId() :
					req.getStatus().getId()
					);
			cs.execute();
		} finally {
			if(cs != null) cs.close();
		}
		
		return true;
	}
	
	/**
	 * Updates the given reimbursement request with the receipt data.
	 * If the request doesn't exist, then nothing is done.
	 * @param id	The ID of the reimbursement request to update.
	 * @param receiptData	The byte data of the receipt to upload into the database.
	 * @throws SQLException	Caused by query or connection.
	 */
	public void updateReceipt(int id, byte[] receiptData) throws SQLException {
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call update_receipt(?, ?)}");
			cs.setInt(1, id);
			cs.setBytes(2, receiptData);
			cs.execute();
		} finally {
			if(cs != null) cs.close();
		}
	}
	
	
	//////////// Delete functions ////////////
	/**
	 * Deletes a reimbursement request.
	 * @param id	The ID of the reimbursement request to delete.
	 * @throws SQLException	Caused by query or connection.
	 */
	public void deleteReimbursement(int id) throws SQLException {
		CallableStatement cs = null;
		
		try {
			cs = cm.getConnection().prepareCall("{call delete_reimbursement(?)}");
			cs.setInt(1, id);
			cs.execute();
		} finally {
			if(cs != null) cs.close();
		}
	}
	
	
}
