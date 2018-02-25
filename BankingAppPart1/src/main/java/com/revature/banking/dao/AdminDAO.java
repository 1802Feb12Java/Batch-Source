package com.revature.banking.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.model.Admin;
import com.revature.banking.utilities.DAOUtilities;

public class AdminDAO extends EmployeeDAO implements InterfaceAdminDAO {

	public List<Admin> getAllAdmins() {
		List<Admin> admins= new ArrayList<Admin>();
		for (File file : new File(DAOUtilities.adminsFolder).listFiles()) {
			Admin admin = (Admin) DAOUtilities.objectReadFile(file);
			if (admin!=null)
				admins.add(admin);
		}
		return admins;
	}
	public static Admin getAdmin(int adminId) {
		Admin admin = null;
		admin = (Admin) DAOUtilities.objectRead(DAOUtilities.adminsFolder + File.separator + adminId);
		return admin;
	}
	public static boolean saveAdmin(Admin admin) {
		return DAOUtilities.objectWrite(DAOUtilities.adminsFolder + File.separator + admin.getUserId(), admin);
	}
	public Admin getAdminByAdminId(int adminId) {
		Admin admin = AdminDAO.getAdmin(adminId);
		return admin;
	}
	public boolean updateAdmin(Admin admin) {
		return AdminDAO.saveAdmin(admin);
	}

	public boolean addAdmin(Admin admin) {
		return AdminDAO.saveAdmin(admin);
	}

	public boolean deleteAdmin(Admin admin) {
		return DAOUtilities.deleteObject(DAOUtilities.adminsFolder + File.separator + admin.getUserId());
	}

	public int getNumAdmins() {
		return new File(DAOUtilities.adminsFolder).listFiles().length;
	}

	
}
