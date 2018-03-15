package com.revature.expensereimbursement.dao;
import java.sql.SQLException;
import java.util.List;

import com.revature.expensereimbursement.model.ERSUser;
public interface InterfaceERSUsersDAO {
	public List<ERSUser> getAllUsers()  throws SQLException;
	public ERSUser getUserByUserId(int id) throws SQLException;
	public ERSUser getUserByUsername(String username) throws SQLException;
	
	public boolean addUser(ERSUser user) throws SQLException;
	
	public boolean updateUser(ERSUser user) throws SQLException;
	
	public boolean deleteUser(ERSUser user) throws SQLException;
}
