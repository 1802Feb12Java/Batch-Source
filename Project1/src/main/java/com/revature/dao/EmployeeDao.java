package com.revature.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.Part;

import com.revature.objects.Employee;
import com.revature.objects.Reimbursement;

public interface EmployeeDao {
	
	public void newEmp(String us, String psw, String fn, String ln, String email);
	public boolean login(String us, String psw);
	// employee submits a reimbursement request
	void reimburseReq(Double amount, String Description, byte[] Receipt, Integer Author, Integer Type) throws IOException;
	// employee can view their pending request
	public List<Reimbursement> pendingReq(Integer empId);
	// employee can view their resolved request
	public List<Reimbursement> resolvedReq(Integer empId);
	// employee can view their information
	public Employee viewInfo(Integer EmpId);
	// employee can update their information
	// make it a function in the website.
	//public void updateInfo();
	//void reimburseReq(Double amount, String Description, Part pic, Integer Author, Integer Type);
	// update Employee
	void updateMan(Integer num, String us, String psw, String fn, String ln, String email);
  
	//public void resetPass();
}
