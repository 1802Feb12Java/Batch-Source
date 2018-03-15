package com.revature.bean.helper;

import com.revature.bean.Reimbursement;

public class UpdateRequest {
	
	private Reimbursement rToUpdate;

	
	public UpdateRequest() {
		this.rToUpdate = null;
	}
	
	public UpdateRequest(Reimbursement rToUpdate) {
		this.rToUpdate = rToUpdate;
	}

	public Reimbursement getrToUpdate() {
		return rToUpdate;
	}

	public void setrToUpdate(Reimbursement rToUpdate) {
		this.rToUpdate = rToUpdate;
	}

	@Override
	public String toString() {
		return rToUpdate.toString();
	}
	
	
	
}
