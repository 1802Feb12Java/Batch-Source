package pov;

import javax.naming.AuthenticationException;

import objects.User;
import connect.DAO;

public class UserFunc {

	
	private static UserFunc INSTANCE = null;
	
	UserFunc(){}
	
	synchronized public static UserFunc getInstance(){
		if(INSTANCE == null)
			INSTANCE = new UserFunc();
		return INSTANCE;
	}
	
	/*  */
	
	User authenticate(String username, String password) throws AuthenticationException{
		
		DAO facade = new DAO();
		User user = null;
		String hash = facade.getHash(username);
		user = facade.getUser(username);
		System.out.println("User login successful");

		return user;
	}

}