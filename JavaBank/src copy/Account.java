import java.util.HashMap;

public class Account
{
    boolean approved=false;
            //username,customer
    HashMap<String, User> account_holders=new HashMap<>();
    double moneyz=0;

    Account(User user)
    {
        account_holders.put(user.username,user);
    }

}
