package Project1.Projec1.pojo;

import java.sql.Blob;

public class AdminView {
	private int r_id;
	private double r_amount;
	private String r_description;
	private String time_submitted;
	private String time_resolved;
	private String resolverName;
	private String rt_status;
	private String rt_type;
	private String author;
	private String image;
	



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




	public String getTime_submitted() {
		return time_submitted;
	}




	public void setTime_submitted(String time_submitted) {
		this.time_submitted = time_submitted;
	}




	public String getTime_resolved() {
		return time_resolved;
	}




	public void setTime_resolved(String time_resolved) {
		this.time_resolved = time_resolved;
	}




	public String getResolverName() {
		return resolverName;
	}




	public void setResolverName(String resolverName) {
		this.resolverName = resolverName;
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




	public String getAuthor() {
		return author;
	}




	public void setAuthor(String author) {
		this.author = author;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	@Override
	public String toString() {
		return "AdminView [r_id=" + r_id + ", r_amount=" + r_amount + ", r_description=" + r_description
				+ ", time_submitted=" + time_submitted + ", time_resolved=" + time_resolved + ", resolverName="
				+ resolverName + ", rt_status=" + rt_status + ", rt_type=" + rt_type + ", author=" + author + ", image="
				+ image + "]";
	}




	public AdminView() {
		
	}
}
