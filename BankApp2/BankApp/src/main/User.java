import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.HashMap;

public abstract class User
{
                //UID,User
    static UserDB user_database = new UserDB();
                //accountID,account
    static AccountDB account_database=new AccountDB();
                //accountID,UID
    static ApplicationDB applications=new ApplicationDB();

    static Logger log = Logger.getLogger("User");
    int UserID;
    int level;
    String username;
    String password;
    String personal_info;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    User(String username,String password,int level)
    {
        this.password=password;
        this.username=username;
        this.level=level;
        if(!user_database.containsKey(username))
        user_database.put(UserID,this);

        UserID=user_database.getUID(username);
    }

    abstract void initialize_menu();

    //database options
}

