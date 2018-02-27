package com.revature.dao;

import java.util.ArrayList;

import com.revature.accounts.AdminAccount;

public interface AdminAccountDAO {

public ArrayList<AdminAccount> admins = new ArrayList<AdminAccount>();
	
	public ArrayList<AdminAccount> getAdmins();
	public void addAdmin(AdminAccount admin);
	public void deleteAdmin(AdminAccount admin);
	public void updateAdmin(AdminAccount admin);
	
}
