package Banking;

import java.math.BigDecimal;

public class Administrator extends Employee
{
	/*
	 * The administrator class is filled with static methods to handle customers, similar to the Employee class
	 * This reduces the need to instantiate multiple administrator objects
	 * Instead just a single instance is created of this class
	 */
    public static final long serialVersionUID = BankingApplication.VERSION;
    //every time you change classes, you bump the number, and java's serialization api will be able to tell that any old files cant be deserialized

    public static void depositCustomerBalance(Customer customer, BigDecimal amount)
    {
        customer.deposit(amount);
    }

    public static boolean withdrawCustomerBalance(Customer customer, BigDecimal amount)
    {
        return customer.withdraw(amount);
    }

    public static boolean transferCustomerBalance(Customer x, Customer y, BigDecimal amount)
    {
        return x.transfer(y, amount);
    }

    public static void approveAccount(Customer customer)
    {
        customer.setApproved(1);
    }

    public static void denyAccount(Customer customer)
    {
        customer.setApproved(0);
    }

    public static void cancelAccount(Customer customer)
    {
        customer.setCancelled(1);
    }
}