package com.revature.banking.fileio.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.fileio.model.AdminFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class AdminDAOFileIO extends EmployeeDAOFileIO implements InterfaceAdminDAOFileIO {

	public List<AdminFileIO> getAllAdmins() {
		List<AdminFileIO> admins= new ArrayList<AdminFileIO>();
		for (File file : new File(DAOUtilitiesFileIO.adminsFolder).listFiles()) {
			AdminFileIO admin = (AdminFileIO) DAOUtilitiesFileIO.objectReadFile(file);
			if (admin!=null)
				admins.add(admin);
		}
		return admins;
	}
	public static AdminFileIO getAdmin(int adminId) {
		AdminFileIO admin = null;
		admin = (AdminFileIO) DAOUtilitiesFileIO.objectRead(DAOUtilitiesFileIO.adminsFolder + File.separator + adminId);
		return admin;
	}
	public static boolean saveAdmin(AdminFileIO admin) {
		return DAOUtilitiesFileIO.objectWrite(DAOUtilitiesFileIO.adminsFolder + File.separator + admin.getUserId(), admin);
	}
	public AdminFileIO getAdminByAdminId(int adminId) {
		AdminFileIO admin = AdminDAOFileIO.getAdmin(adminId);
		return admin;
	}
	public boolean updateAdmin(AdminFileIO admin) {
		return AdminDAOFileIO.saveAdmin(admin);
	}

	public boolean addAdmin(AdminFileIO admin) {
		return AdminDAOFileIO.saveAdmin(admin);
	}

	public boolean deleteAdmin(AdminFileIO admin) {
		return DAOUtilitiesFileIO.deleteObject(DAOUtilitiesFileIO.adminsFolder + File.separator + admin.getUserId());
	}

	public int getNumAdmins() {
		return new File(DAOUtilitiesFileIO.adminsFolder).listFiles().length;
	}

	
}
