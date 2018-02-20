import java.util.HashMap;

public abstract class User
{
    //TODO:possible hashmap of file locations
                    //username,User
    static HashMap<String, User> user_database = new HashMap<>();
                    //accountID,account
    static HashMap<Integer, Account> account_database=new HashMap<>();
                    //accountID,username
    static HashMap<Integer,String> applications=new HashMap<>();

    int accesslevel;
    String username;
    String password;

    User(String username,String password)
    {
        this.password=password;
        this.username=username;
        user_database.put(username,this);
    }


    abstract void initialize_menu();



}
