package Project1.Projec1.pojo;

public class EmployeeView {
	private int u_id;
	private String username;
	private String fname;
	private String lname;
	private String email;
	public EmployeeView() {
		
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "EmployeeView [u_id=" + u_id + ", username=" + username + ", fname=" + fname + ", lname=" + lname
				+ ", email=" + email + "]";
	}
	
}
