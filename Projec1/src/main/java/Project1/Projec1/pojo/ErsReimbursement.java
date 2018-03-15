package Project1.Projec1.pojo;

import java.sql.Blob;
import java.util.Date;

public class ErsReimbursement {
	private int r_id;
	private double r_amount;
	private String r_description;
	private Blob r_receipt; //blob , not sure what's going on
	private Date r_submitted; //timestamp
	private Date r_resolved; //timestamp
	private int u_id_author;
	private int u_id_resolver; 
	private int rt_type;
	private int rt_status;
	public ErsReimbursement() {};//defaut constructor
	public ErsReimbursement(int r_id, double r_amount, String r_description, Blob r_receipt, Date r_submitted,
			Date r_resolved, int u_id_author, int u_id_resolver, int rt_type, int rt_status) {
		super();
		this.r_id = r_id;
		this.r_amount = r_amount;
		this.r_description = r_description;
		this.r_receipt = r_receipt;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.u_id_author = u_id_author;
		this.u_id_resolver = u_id_resolver;
		this.rt_type = rt_type;
		this.rt_status = rt_status;
	}
	@Override
	public String toString() {
		return "ErsReimbursement [r_id=" + r_id + ", r_amount=" + r_amount + ", r_description=" + r_description
				+ ", r_receipt=" + r_receipt + ", r_submitted=" + r_submitted + ", r_resolved=" + r_resolved
				+ ", u_id_author=" + u_id_author + ", u_id_resolver=" + u_id_resolver + ", rt_type=" + rt_type
				+ ", rt_status=" + rt_status + "]";
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public double getR_amount() {
		return r_amount;
	}
	public void setR_amount(double r_amount) {
		this.r_amount = r_amount;
	}
	public String getR_description() {
		return r_description;
	}
	public void setR_description(String r_description) {
		this.r_description = r_description;
	}
	public Blob getR_receipt() {
		return r_receipt;
	}
	public void setR_receipt(Blob r_receipt) {
		this.r_receipt = r_receipt;
	}
	public Date getR_submitted() {
		return r_submitted;
	}
	public void setR_submitted(Date r_submitted) {
		this.r_submitted = r_submitted;
	}
	public Date getR_resolved() {
		return r_resolved;
	}
	public void setR_resolved(Date r_resolved) {
		this.r_resolved = r_resolved;
	}
	public int getU_id_author() {
		return u_id_author;
	}
	public void setU_id_author(int u_id_author) {
		this.u_id_author = u_id_author;
	}
	public int getU_id_resolver() {
		return u_id_resolver;
	}
	public void setU_id_resolver(int u_id_resolver) {
		this.u_id_resolver = u_id_resolver;
	}
	public int getRt_type() {
		return rt_type;
	}
	public void setRt_type(int rt_type) {
		this.rt_type = rt_type;
	}
	public int getRt_status() {
		return rt_status;
	}
	public void setRt_status(int rt_status) {
		this.rt_status = rt_status;
	}
	

}
