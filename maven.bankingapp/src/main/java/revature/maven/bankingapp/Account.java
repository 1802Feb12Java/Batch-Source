package revature.maven.bankingapp;

public class Account {
	
	private int id;
	private int is_approved;
	private double accountBalance;
	
	public Account(int id, int is_approved, double accountBalance) {
		this.id=id;
		this.is_approved=is_approved;
		this.accountBalance=accountBalance;
	}

}
