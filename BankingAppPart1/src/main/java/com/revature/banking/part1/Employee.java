package com.revature.banking.part1;

public class Employee extends User implements InterfaceEmployee {
	
	private static final long serialVersionUID = -6787239680576469833L;
	
	public Employee(int ID, String username, String password) {
		super(ID, username, password, "Employee");
		// TODO Auto-generated constructor stub
	}
	public Employee(int ID, String username, String password, String role) {
		super(ID, username, password, role);
		// TODO Auto-generated constructor stub
	}
	
	public void checkApplications() {
		// TODO Auto-generated method stub
		
	}


	public void approveApplication(Employee employee, Application application) {
		
	}
	public void denyApplication(Employee employee, Application application) {
		
	}
	public void getAllCustomers() {
		// TODO Auto-generated method stub
		
	}
	public void getAccountByAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "Employee [ID=" + getID() + ", username=" + getUsername() + ", ]";
	}



}
