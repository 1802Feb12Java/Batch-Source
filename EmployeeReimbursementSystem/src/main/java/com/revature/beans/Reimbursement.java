package com.revature.beans;

public class Reimbursement {

	private int reiumbursementID;
	private double amount;
	private String description;
	private String reciept;
	private String submitted;
	private String resolved;
	private int userIDAuthor;
	private int userIDResolver;
	private int reimbursementTypeID;
	private int reimbursementStatusID;
	
	private String userIDAuthorString;
	private String userIDResolverString;
	private String reimbursementTypeIDString;
	private String reimbursementStatusIDString;
	
	public String getUserIDAuthorString() {
		return userIDAuthorString;
	}
	public void setUserIDAuthorString(String userIDAuthorString) {
		this.userIDAuthorString = userIDAuthorString;
	}
	public String getUserIDResolverString() {
		return userIDResolverString;
	}
	public void setUserIDResolverString(String userIDResolverString) {
		this.userIDResolverString = userIDResolverString;
	}
	public String getReimbursementTypeIDString() {
		return reimbursementTypeIDString;
	}
	public void setReimbursementTypeIDString(String reimbursementTypeIDString) {
		this.reimbursementTypeIDString = reimbursementTypeIDString;
	}
	public String getReimbursementStatusIDString() {
		return reimbursementStatusIDString;
	}
	public void setReimbursementStatusIDString(String reimbursementStatusIDString) {
		this.reimbursementStatusIDString = reimbursementStatusIDString;
	}
	public int getReiumbursementID() {
		return reiumbursementID;
	}
	public void setReiumbursementID(int reiumbursementID) {
		this.reiumbursementID = reiumbursementID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReciept() {
		return reciept;
	}
	public void setReciept(String reciept) {
		this.reciept = reciept;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	public int getUserIDAuthor() {
		return userIDAuthor;
	}
	public void setUserIDAuthor(int userIDAuthor) {
		this.userIDAuthor = userIDAuthor;
	}
	public int getUserIDResolver() {
		return userIDResolver;
	}
	public void setUserIDResolver(int userIDResolver) {
		this.userIDResolver = userIDResolver;
	}
	public int getReimbursementTypeID() {
		return reimbursementTypeID;
	}
	public void setReimbursementTypeID(int reimbursementTypeID) {
		this.reimbursementTypeID = reimbursementTypeID;
	}
	public int getReimbursementStatusID() {
		return reimbursementStatusID;
	}
	public void setReimbursementStatusID(int reimbursementStatusID) {
		this.reimbursementStatusID = reimbursementStatusID;
	}
	
	
}
