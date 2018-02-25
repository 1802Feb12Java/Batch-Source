package revature.maven.bankingapp;

public class User {
	
	private int id;
	private int roleId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String username;
	private String password;
	
	User(int id, int roleId, String firstname, String lastname, String email, String phone, String username, String password) {
		this.id=id;
		this.roleId=roleId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.phone=phone;
		this.username=username;
		this.password=password;
	}

}
