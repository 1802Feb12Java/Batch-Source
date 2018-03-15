package objects;

import java.io.Serializable;

public class RequestStatus implements Serializable {

	/*
	 * request status object is labeled by an id, and contains a status for that
	 * id/reimbursement
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int status_id;
	public String status;

	@Override
	public String toString() {
		return "ReimbStatus [status_id=" + status_id + ", status=" + status + "]";
	}

	public RequestStatus(int status_id, String status) {
		super();
		this.status_id = status_id;
		this.status = status;
	}

	public RequestStatus() {
		super();
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}