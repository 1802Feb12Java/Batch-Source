package Project1.Projec1.pojo;

import java.sql.Timestamp;

public class View {
	private int r_id;
	private double r_amount;
	private String r_description;
	private String r_submitted;
	private String r_resolved;
	private int r_resolver;
	private String rt_status;
	private String rt_type;
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
	public String getR_submitted() {
		return r_submitted;
	}
	public void setR_submitted(String r_submitted) {
		this.r_submitted = r_submitted;
	}
	public String getR_resolved() {
		return r_resolved;
	}
	public void setR_resolved(String r_resolved) {
		this.r_resolved = r_resolved;
	}
	public int getR_resolver() {
		return r_resolver;
	}
	public void setR_resolver(int r_resolver) {
		this.r_resolver = r_resolver;
	}
	
	public String getRt_status() {
		return rt_status;
	}
	public void setRt_status(String rt_status) {
		this.rt_status = rt_status;
	}
	public String getRt_type() {
		return rt_type;
	}
	public void setRt_type(String rt_type) {
		this.rt_type = rt_type;
	}

	public View() {
		
	}
	public View(int r_id, double r_amount, String r_description, String r_submitted, String r_resolved,
			int r_resolver, String rt_status, String rt_type) {
		super();
		this.r_id = r_id;
		this.r_amount = r_amount;
		this.r_description = r_description;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.r_resolver = r_resolver;
		this.rt_status = rt_status;
		this.rt_type = rt_type;
	}
	@Override
	public String toString() {
		return "View [r_id=" + r_id + ", r_amount=" + r_amount + ", r_description=" + r_description + ", r_submitted="
				+ r_submitted + ", r_resolved=" + r_resolved + ", r_resolver=" + r_resolver + ", rt_status=" + rt_status
				+ ", rt_type=" + rt_type + "]";
	}
	


}
