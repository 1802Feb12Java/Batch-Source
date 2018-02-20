package com.revature.banking;

import java.io.Serializable;

public interface Transactional<T> extends Serializable {

	public boolean withdraw(T amount);

	public boolean deposit(T amount);

	public boolean transfer(T amount, String recipientAccount);

}
