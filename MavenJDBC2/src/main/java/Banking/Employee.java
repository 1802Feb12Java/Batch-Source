package Banking;

import java.math.BigDecimal;

public class Employee
{
	/*
	 * The employee class is essentially an accesor for the customer class that returns customer information
	 * when passing in a customer object
	 * We don't need a constructor because all of these methods are static.
	 */
    public static final long serialVersionUID = BankingApplication.VERSION;
    //every time you change classes, you bump the number, and java's serialization api will be able to tell that any old files cant be deserialized

    public static BigDecimal viewCustomerBalance(Customer customer)
    {
        return customer.balance();
        
    }
    public static String viewCustomerName(Customer customer)
    {
        return customer.name();
    }
    public static String viewCustomerUsername(Customer customer)
    {
        return customer.username();
    }
    public static String viewCustomerPassword(Customer customer)
    {
        return customer.password();
    }
}