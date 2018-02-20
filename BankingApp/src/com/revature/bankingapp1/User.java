package com.revature.bankingapp1;
	/*
	 * This interface is basically being used just so I can generically enter either
	 * a customer object, a bankemployee object, or a bankadmin object without knowing which.
	 */

public interface User {	
	public String username;
	public String password;
	public static final String accessType;
	
}//end class
