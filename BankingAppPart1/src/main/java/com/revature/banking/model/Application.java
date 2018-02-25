package com.revature.banking.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Application implements Serializable {
	private static final long serialVersionUID = -8969389198209091787L;
	private final int applicationId;
	private String status;
	private int primaryCustomer, secondaryCustomer;
	private int employee=0;
	private int accountId=0;
	private boolean open = true;
	private final LocalDate applyDate;
	private LocalDate decisionDate;
	public Application(int ID, int  primaryCustomer) {
		this(ID, primaryCustomer,0);
	}
	public Application(int Id, int primaryCustomer, int  secondaryCustomer) {
		this.applicationId = Id;
		this.applyDate = LocalDate.now();
		this.status = "Processing";
		this.primaryCustomer=primaryCustomer;
		this.secondaryCustomer=secondaryCustomer;
	}
	public int getApplicationId() {
		return applicationId;
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
		return open;
	}
	public void approve(int employee, int accountNumber) {
		this.status = "Approved";
		this.employee=employee;
		this.accountId=accountNumber;
		this.open=false;
		setDecisionDate(LocalDate.now());
	}
	public void deny(int employee) {
		this.status = "Denied";
		this.employee=employee;
		this.open=false;
		setDecisionDate(LocalDate.now());
	}
	public int getEmployeeID() {
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
	public int  getSecondaryCustomer() {
		return secondaryCustomer;
	}
	public void setSecondaryCustomer(int  secondaryCustomer) {
		this.secondaryCustomer = secondaryCustomer;
	}
	public int getPrimaryCustomer() {
		return primaryCustomer;
	}
	public void setPrimaryCustomerID(int primaryCustomer) {
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
			return "Applications [Application status=" + status + ", Application Id="+ applicationId 
					+ ", Application Date=" + applyDate.toString() + ", Decision Date="
							+ decisionDate.toString() +
					", Primary Customer=" + primaryCustomer + 
					", Secondary Customer ID="	+ secondaryCustomer
					+ ", Employee ID=" + employee
					+   "]";
	}
	
}
