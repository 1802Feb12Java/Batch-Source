package JDBCBank.JDBCBank.employee.bean;

public class Employee {
	private String lname;
	private String fname;
	private int eId;
	private String userName;
	private String passWord;

	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	@Override
	public String toString() {
		return "Employee [lname=" + lname + ", fname=" + fname + ", eId=" + eId + ", userName=" + userName
				+ ", passWord=" + passWord + "]";
	}
	public Employee(String lname, String fname, int eId, String userName, String passWord) {
		super();
		this.lname = lname;
		this.fname = fname;
		this.eId = eId;
		this.userName = userName;
		this.passWord = passWord;
	}
	public Employee()//default constructor
	{
		
	}
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
