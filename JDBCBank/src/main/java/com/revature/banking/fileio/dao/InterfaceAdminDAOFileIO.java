package com.revature.banking.fileio.dao;

import java.util.List;

import com.revature.banking.fileio.model.AdminFileIO;

public interface InterfaceAdminDAOFileIO {
	public List<AdminFileIO> getAllAdmins();
	public AdminFileIO getAdminByAdminId(int adminId);
	public boolean updateAdmin(AdminFileIO admin);
	public boolean addAdmin(AdminFileIO admin);
	public boolean deleteAdmin(AdminFileIO admin);
	public int getNumAdmins();
}
