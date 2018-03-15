package Project1.Projec1.pojo;

public class Users {
	private int u_id;
	private String u_username;
	private String u_password;
	private String u_firstname;
	private String u_lastname;
	private String u_email;
	private int ur_id;
	public Users(int u_id, String u_username, String u_password, String u_firstname, String u_lastname, String u_email,
			int ur_id) {
		super();
		this.u_id = u_id;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_firstname = u_firstname;
		this.u_lastname = u_lastname;
		this.u_email = u_email;
		this.ur_id = ur_id;
	}
	public int getUr_id() {
		return ur_id;
	}
	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}

	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_username() {
		return u_username;
	}
	public void setU_username(String u_username) {
		this.u_username = u_username;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_firstname() {
		return u_firstname;
	}
	public void setU_firstname(String u_firstname) {
		this.u_firstname = u_firstname;
	}
	public String getU_lastname() {
		return u_lastname;
	}
	public void setU_lastname(String u_lastname) {
		this.u_lastname = u_lastname;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	@Override
	public String toString() {
		return "Users [u_id=" + u_id + ", u_username=" + u_username + ", u_password=" + u_password + ", u_firstname="
				+ u_firstname + ", u_lastname=" + u_lastname + ", u_email=" + u_email + ", ur_id=" + ur_id + "]";
	}
	public Users(int u_id, String u_username, String u_password, String u_firstname, String u_lastname,
			String u_email) {
		super();
		this.u_id = u_id;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_firstname = u_firstname;
		this.u_lastname = u_lastname;
		this.u_email = u_email;
	}
	public Users() {}//default constructor

}
