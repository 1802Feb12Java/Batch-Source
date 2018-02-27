package JDBCBank.JDBCBank.customer.bean;

public class Customer {
	private int cId;
	private String lname;
	private String fname;
	private String address;
	private String userName;
	private String passWord;
	private String phoneNumber;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Customer(int cId, String lname, String fname, String address, String userName, String passWord,
			String phoneNumber) {
		super();
		this.cId = cId;
		this.lname = lname;
		this.fname = fname;
		this.address = address;
		this.userName = userName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
	}
	public Customer()
	{
		
	}
	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", lname=" + lname + ", fname=" + fname + ", address=" + address + ", userName="
				+ userName + ", passWord=" + passWord + ", phoneNumber=" + phoneNumber + "]";
	}
	

}
