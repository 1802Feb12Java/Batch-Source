package com.revature.banking.jdbc.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Application implements Serializable {
	private static final long serialVersionUID = -8969389198209091787L;
	private int applicationId;
	private String status;
	private int primaryCustomer, secondaryCustomer;
	private int employee=0;
	private int accountId=0;
	private final LocalDate applyDate;
	private LocalDate decisionDate;
	public Application(int ID, int  primaryCustomer) {
		this(ID, primaryCustomer,0);
	}
	public Application(int applicationId, int primaryCustomer, int  secondaryCustomer) {
		this(applicationId, primaryCustomer, secondaryCustomer, "Processing", 0, 0, LocalDate.now(),LocalDate.now());
	}
	public Application(int applicationId, int primaryCustomer, int secondaryCustomer, String status,int employee,
			int accountId, LocalDate applyDate, LocalDate decisionDate) {
		super();
		this.applicationId = applicationId;
		this.status = status;
		this.primaryCustomer = primaryCustomer;
		this.secondaryCustomer = secondaryCustomer;
		this.employee = employee;
		this.accountId = accountId;
		this.applyDate = applyDate;
		this.decisionDate = decisionDate;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId=applicationId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getStatus() {
		return status;
	}
	public boolean isOpen() {
		return this.status.equals("Processing");
	}
	public void approve(int employee, int accountNumber) {
		this.status = "Approved";
		this.employee=employee;
		this.accountId=accountNumber;
		setDecisionDate(LocalDate.now());
	}
	public void deny(int employee) {
		this.status = "Denied";
		this.employee=employee;
		setDecisionDate(LocalDate.now());
	}
	
	public int getEmployeeId() {
		return employee;
	}
	public LocalDate getDecisionDate() {
		return decisionDate;
	}
	public void setDecisionDate(LocalDate decisionDate) {
		this.decisionDate = decisionDate;
	}
	public LocalDate getApplyDate() {
		return applyDate;
	}
	public int  getSecondaryCustomerId() {
		return secondaryCustomer;
	}
	public void setSecondaryCustomerId(int  secondaryCustomer) {
		this.secondaryCustomer = secondaryCustomer;
	}
	public int getPrimaryCustomerId() {
		return primaryCustomer;
	}
	public void setPrimaryCustomerId(int primaryCustomer) {
		this.primaryCustomer = primaryCustomer;
	}
	@Override
	public String toString() {
//		if(employee==0 && secondaryCustomer ==0)
//			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
//				+ ", Application Date=" + applyDate.toString() +
//				", Primary Customer=" + primaryCustomer + 
//				 "]";
//		else if (employee==null)
//			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
//					+ ", Application Date=" + applyDate.toString() + 
//					", Primary Customer=" + primaryCustomer + 
//					", Secondary Customer ID="	+ secondaryCustomer.toString()
//					+"]";
//		else if (secondaryCustomer == null)
//			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
//					+ ", Application Date=" + applyDate.toString() + ", Decision Date="
//							+ decisionDate.toString() +
//					", Primary Customer=" + primaryCustomer.toString() + 
//					", Employee ID=" + employee.toString()
//					+   "]";
//		else
			return "Applications [Status=" + status + ", Id="+ applicationId 
					+ ", ApplyDate=" + applyDate.toString() + ", DecisionDate="
							+ decisionDate.toString() +
					", primaryCustomerId=" + primaryCustomer + 
					", SecondaryCustomerId="	+ secondaryCustomer
					+ ", employeeId=" + employee
					+   "]";
	}
	
}
