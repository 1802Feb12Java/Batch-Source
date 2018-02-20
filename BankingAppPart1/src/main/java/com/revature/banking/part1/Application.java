package com.revature.banking.part1;

import java.io.Serializable;
import java.time.LocalDate;

public class Application implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8969389198209091787L;

	private final int applicationID;

	private String status;
	private Customer primaryCustomer, secondaryCustomer;
	private Employee employee=null;
	private int accountNumber=0;
	private boolean open = true;
	private final LocalDate applyDate;
	private LocalDate decisionDate;
	public Application(int ID, Customer  primaryCustomer) {
		this(ID, primaryCustomer, null);
	}
	public Application(int ID, Customer primaryCustomer, Customer  secondaryCustomer) {
		this.applicationID = ID;
		this.applyDate = LocalDate.now();
		this.status = "processing";
		this.primaryCustomer=primaryCustomer;
		this.secondaryCustomer=secondaryCustomer;
	}
	public int getApplicationID() {
		return applicationID;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getStatus() {
		return status;
	}
	public boolean isOpen() {
		return open;
	}
	public void approve(Employee employee, int accountNumber) {
		this.status = "Approved";
		this.employee=employee;
		this.accountNumber=accountNumber;
		this.open=false;
		setDecisionDate(LocalDate.now());
	}
	public void deny(Employee employee) {
		this.status = "Denied";
		this.employee=employee;
		this.open=false;
		setDecisionDate(LocalDate.now());
	}
	public Employee getEmployeeID() {
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
	public Customer  getSecondaryCustomer() {
		return secondaryCustomer;
	}
	public void setSecondaryCustomer(Customer  secondaryCustomer) {
		this.secondaryCustomer = secondaryCustomer;
	}
	public Customer getPrimaryCustomer() {
		return primaryCustomer;
	}
	public void setPrimaryCustomerID(Customer primaryCustomer) {
		this.primaryCustomer = primaryCustomer;
	}
	@Override
	public String toString() {
		if(employee==null && secondaryCustomer == null)
			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
				+ ", Application Date=" + applyDate.toString() +
				", Primary Customer=" + primaryCustomer.toString() + 
				 "]";
		else if (employee==null)
			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
					+ ", Application Date=" + applyDate.toString() + 
					", Primary Customer=" + primaryCustomer.toString() + 
					", Secondary Customer ID="	+ secondaryCustomer.toString()
					+"]";
		else if (secondaryCustomer == null)
			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
					+ ", Application Date=" + applyDate.toString() + ", Decision Date="
							+ decisionDate.toString() +
					", Primary Customer=" + primaryCustomer.toString() + 
					", Employee ID=" + employee.toString()
					+   "]";
		else
			return "Applications [Application status=" + status + ", Application ID="+ applicationID 
					+ ", Application Date=" + applyDate.toString() + ", Decision Date="
							+ decisionDate.toString() +
					", Primary Customer=" + primaryCustomer.toString() + 
					", Secondary Customer ID="	+ secondaryCustomer.toString()
					+ ", Employee ID=" + employee.toString()
					+   "]";
	}
	
}
