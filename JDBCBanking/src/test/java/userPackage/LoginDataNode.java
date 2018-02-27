package userPackage;

import java.io.Serializable;

public class LoginDataNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8732856530879276415L;
	public double accountNumber;
	public String username;
	public String password;
	public double Balance;
	public LoginDataNode next;
	public String[] users;
	
	public String phone;
	public int age;
	public String fullName;
	public String birth;
			
	public LoginDataNode(String name, String pwd, double depo, String[] user) {
				
		this.username = name;
		this.password = pwd;
		this.Balance = depo;
		this.next = null;
		this.users = user;
	}
}
