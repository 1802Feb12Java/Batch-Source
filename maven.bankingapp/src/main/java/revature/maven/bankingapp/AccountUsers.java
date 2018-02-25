package revature.maven.bankingapp;

import java.util.ArrayList;

public class AccountUsers {
	
	private ArrayList<User> users = new ArrayList<User>();
	
	AccountUsers(ArrayList<User> users) {
		this.users=users;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}
