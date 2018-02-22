package com.revature.accounts;

import java.util.ArrayList;

public class ListAccounts {
	public static void list(ArrayList<Account> accounts) {
		for (Account current : accounts) {
			current.toString();
		}
	}
}
