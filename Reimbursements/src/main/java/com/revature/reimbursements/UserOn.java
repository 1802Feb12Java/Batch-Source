package com.revature.reimbursements;

public class UserOn {

	private int U_id;
	private int Ur_id;
	public UserOn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserOn(int u_id, int ur_id) {
		super();
		U_id = u_id;
		Ur_id = ur_id;
	}
	public int getU_id() {
		return U_id;
	}
	public void setU_id(int u_id) {
		U_id = u_id;
	}
	public int getUr_id() {
		return Ur_id;
	}
	public void setUr_id(int ur_id) {
		Ur_id = ur_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + U_id;
		result = prime * result + Ur_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserOn other = (UserOn) obj;
		if (U_id != other.U_id)
			return false;
		if (Ur_id != other.Ur_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserOn [U_id=" + U_id + ", Ur_id=" + Ur_id + "]";
	}
	
}
