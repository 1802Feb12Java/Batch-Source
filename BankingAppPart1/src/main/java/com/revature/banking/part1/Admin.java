package com.revature.banking.part1;

public class Admin extends Employee implements InterfaceAdmin {
	public static int numAdmins = 0;
	
	private static final long serialVersionUID = -926500983082199475L;
	
	public Admin(int ID, String username, String password) {
		super(ID, username, password, "Admin");
	}

	public void closeAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Admin [ ID=" + getID() + ", username=" + getUsername() + " ]";
	}
}
