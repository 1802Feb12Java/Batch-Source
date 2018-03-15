package objects;

public class UserType {

	/* user type object is labeled by id, and has a role for the user at that id */
	
	private int id;
	private String user_role;
	
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user_role=" + user_role + "]";
	}
	
	public UserType(int id, String user_role) {
		super();
		this.id = id;
		this.user_role = user_role;
	}
	
	public UserType() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}	
}