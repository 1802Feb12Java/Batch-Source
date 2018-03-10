
import java.util.HashMap;

public class Account
{
    int userID;
    double moneyz=0;
    int accountID;

    Account(User user,int accountID)
    {
        this.userID=user.UserID;
        this.accountID=accountID;
    }

}
