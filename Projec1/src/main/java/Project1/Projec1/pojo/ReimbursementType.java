package Project1.Projec1.pojo;

public class ReimbursementType {
	private int rt_id;
	private String rt_type;
	public int getRt_id() {
		return rt_id;
	}
	public void setRt_id(int rt_id) {
		this.rt_id = rt_id;
	}
	public String getRt_type() {
		return rt_type;
	}
	public void setRt_type(String rt_type) {
		this.rt_type = rt_type;
	}
	public ReimbursementType(int rt_id, String rt_type) {
		super();
		this.rt_id = rt_id;
		this.rt_type = rt_type;
	}
	public ReimbursementType() {}; //default constructor
	@Override
	public String toString() {
		return "ReimbursementType [rt_id=" + rt_id + ", rt_type=" + rt_type + "]";
	}
	
}
