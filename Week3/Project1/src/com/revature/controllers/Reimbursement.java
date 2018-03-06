package com.revature.controllers;

public class Reimbursement {

	public int rId;
	public double rAmount;
	public String rDescription;
	public String rSubmitted;
	public String rResolved;
	public String uAuthor;
	public String uResolver;
	public String rType;
	public String rStatus;

	@Override
	public String toString() {
		return "ReimbursementBean\n\trId:\t" + rId + "\n\trAmount:\t" + rAmount + "\n\trDescription:\t" + rDescription
				+ "\n\trSubmitted:\t" + rSubmitted + "\n\trResolved:\t" + rResolved + "\n\tuAuthor:\t" + uAuthor
				+ "\n\tuResolver:\t" + uResolver + "\n\trType:\t" + rType + "\n\trStatus:\t" + rStatus;
	}
	
	
}