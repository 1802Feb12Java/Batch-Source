package com.revature.dao;

import java.util.List;

import com.revature.objects.Employee;
import com.revature.objects.Reimbursement;

public interface ManagerDao {
	
	public void newMan(String us, String psw, String fn, String ln, String email);
	public boolean login(String us, String psw);
	// view all pending requests from all employees
	public List<Reimbursement> pendingReq();
	// view all resolved requests from all employees and see which manager resolved
	public List<Reimbursement> resolvedReq();
	// view all employees
	public List<Employee> viewEmp();
	// view reimbursement requests from a single employee
	public List<Reimbursement> empReq(Integer empId);
	// get UserID from the username
	Integer getId(String Username);
	// approve/deny reimbursement requests
	void resolve(Integer type, Integer ManagerId, Integer ReimbursementId);
	// get String Type from the integer type
	String getType(Integer type);
	// get String Status from the integer status
	String getStatus(Integer status);
	// get User Role from their Id
	String getRole(Integer Id);
	// get UserName from their Id
	String getName (Integer Id);
	// view all reimbursement requests
	List<Reimbursement> allReq();
}
