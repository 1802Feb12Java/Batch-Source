package com.revature.expensereimbursement.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.expensereimbursement.model.ERSReimbursementType;

public interface InterfaceERSReimbursementTypeDAO {
	//Create
	public boolean addERSReimbursementType(ERSReimbursementType type) throws SQLException;
	//Read
	public List<ERSReimbursementType> getAllTypes() throws SQLException;
	public ERSReimbursementType getTypeById(int typeId) throws SQLException;
	public List<ERSReimbursementType> getTypeByType(String type) throws SQLException;	
	//Update
	public boolean updateType(ERSReimbursementType type) throws SQLException;	
	//delete
	public boolean deleteType(ERSReimbursementType type) throws SQLException;
}
