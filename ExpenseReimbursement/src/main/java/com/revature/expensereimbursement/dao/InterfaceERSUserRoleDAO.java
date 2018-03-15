package com.revature.expensereimbursement.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.expensereimbursement.model.ERSUserRole;

public interface InterfaceERSUserRoleDAO {
	public List<ERSUserRole> getAllRoles() throws SQLException;

}
