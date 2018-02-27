package JDBCBank.JDBCBank.customeraccount.bean;

public class CustomerAccount {

	private int cid;
	private int caid;
	private double balance;

	@Override
	public String toString() {
		return "CustomerAccount [cid=" + cid + ", caid=" + caid + ", balance=" + balance + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public CustomerAccount()
	{
		
	}
	public boolean checkDepositBalance(double amount)
	{
		if (amount < 0)
		{
			System.out.println("Can't deposit a negative amount.");
			return false;
		}
		return true;
	}
	public boolean checkWithdrawBalance(double amount)
	{
		if (amount < 0)
		{
			System.out.println("Can't withdraw a negative amount.");
			return false;
		}
		else if(amount > balance)
		{
			System.out.println("Balance is insufficient.");
			return false;
		}
		return true;
	}
	public CustomerAccount(int cid, int caid, double balance) {
		super();
		this.cid = cid;
		this.caid = caid;
		this.balance = balance;
	}

	public int getCaid() {
		return caid;
	}
	public void setCaid(int caid) {
		this.caid = caid;
	}

}
