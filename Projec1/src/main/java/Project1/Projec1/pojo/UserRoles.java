package Project1.Projec1.pojo;

public class UserRoles  {
	private int ur_id;
	private String ur_role;
	public int getUr_id() {
		return ur_id;
	}
	public String getUr_role() {
		return ur_role;
	}
	
	public UserRoles() {} //default constructor
	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}
	@Override
	public String toString() {
		return "UserRoles [ur_id=" + ur_id + ", ur_role=" + ur_role + "]";
	}
	public void setUr_role(String ur_role) {
		this.ur_role = ur_role;
	}

}
