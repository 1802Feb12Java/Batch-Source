package com.revature.banking;

public class AccountInfo {

	private final String accountId;
	private final String personId;

	public AccountInfo(String accountId, String personId) {
		super();
		this.accountId = accountId;
		this.personId = personId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getPersonId() {
		return personId;
	}

	@Override
	public String toString() {
		return "AccountInfo [accountId=" + accountId + ", personId=" + personId + "]";
	}

}
