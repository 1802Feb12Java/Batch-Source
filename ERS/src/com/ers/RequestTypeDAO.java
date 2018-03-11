package com.ers;


public interface RequestTypeDAO {
	public String getRequestTypes();
	public void addReimbursmentType(String type);
	public void addReimbursment(float amount, String description,
			byte[] receipt, int requestTypeId, int authorId);
}
