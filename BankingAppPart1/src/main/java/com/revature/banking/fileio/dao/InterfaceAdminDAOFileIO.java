package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.model.Admin;

public interface InterfaceAdminDAO {
	public List<Admin> getAllAdmins();
	public Admin getAdminByAdminId(int adminId);
	public boolean updateAdmin(Admin admin);
	public boolean addAdmin(Admin admin);
	public boolean deleteAdmin(Admin admin);
	public int getNumAdmins();
}
