
public class Employee extends User
{

    Employee(String username, String password)
    {
        super(username, password,2);
    }

    @Override
    void initialize_menu()
    {
        boolean zero=false;
        int choice;
        Input reader=new Input();

        while(!zero)
        {
            System.out.println("Logged in as " + username);
            System.out.println("1. Approve account");
            System.out.println("2. Deny account");
            System.out.println("3. View account");
            //TODO: make the full menu

            choice = reader.integer(3);

            if (choice == 1)
                approve();
            else if (choice == 2)
                deny();
            else if (choice == 3)
            {
                System.out.print("Enter the accountID:");
                int accountID=reader.integer();
                if(account_database.containsKey(accountID))
                    view(accountID);
                else
                    System.out.println("Account not found");
            }
            else
                zero = true;
        }
    }

    private void approve()
    {
        Input reader=new Input();
        int accountID;
        System.out.print("Enter the accountID:");
        accountID=reader.integer();
        if(applications.containsKey(accountID))
        {
            Account account;
            if(account_database.containsKey(accountID))
            {
                account=account_database.get(accountID);
                account.userID=applications.get(accountID);
                log.info("Account "+accountID+" approved ID:"+this.UserID);
            }
            else
            {
                account=new Account(user_database.get(applications.get(accountID)),accountID);
            }

            account_database.put(accountID,account);
            applications.remove(accountID);
            System.out.println("Account created");

        }
        else
        {
            System.out.println("Account not found");
        }

    }

    private void deny()
    {
        Input reader=new Input();
        int accountID;
        System.out.print("Enter the accountID:");
        accountID=reader.integer();
        if(applications.containsKey(accountID))
        {
            System.out.println("Account Denied");
            log.info("Account "+accountID+" denied ID:"+this.UserID);
            applications.remove(accountID);
        }
        else
            System.out.println("Account not found");

    }

    private void view(int accountID)
    {
        account_database.view(accountID);
    }

}
