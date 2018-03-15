package com.revature.bean.helper;

import com.revature.bean.Reimbursement;

public class UpdateTicket {
	private String type;
	private Reimbursement ticket;
	
	public UpdateTicket() {
		this.type = "";
		this.ticket = null;
	}
	
	public UpdateTicket(String type, Reimbursement t) {
		this.type = type;
		this.ticket = t;
	}
	
	public UpdateTicket(UpdateTicket ut) {
		this.type = ut.type;
		this.ticket = ut.ticket;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Reimbursement getTicket() {
		return ticket;
	}
	public void setR(Reimbursement t) {
		this.ticket = t;
	}
	
	@Override
	public String toString() {
		return "UpdateTicket [type=" + type + ", r=" + ticket.toString() + "]";
	}
	
}
