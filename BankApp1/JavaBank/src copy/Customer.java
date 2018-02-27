import java.util.HashMap;

public class Customer extends User
{
            //accountID,Account
    HashMap<Integer,Account> my_accounts=new HashMap<>();

    Customer(String username, String password)
    {
        super(username, password);
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
            System.out.println("1. Apply for a new account");
            System.out.println("2. Open existing account");

            choice = reader.integer(2);

            if (choice == 1)
                apply();
            else if (choice == 2)
                account_verification();
            else
                zero = true;
        }

    }

    private void apply()
    {
        int accountID,initial_amount;
        Input reader=new Input();
        System.out.print("Enter the accountID:");
        accountID=reader.integer(Integer.MAX_VALUE);
        //TODO fix the error statement
        if(accountID==0)
            System.out.println("Error");
        else if(account_database.containsKey(accountID))
        {
            applications.put(accountID,username);
            System.out.println("Account already exists. Joint application sent");
            //TODO put a static database of applications. Make it concrete
        }
        else
        {
            applications.put(accountID,username);
            System.out.println("New account application created");
        }

    }

    private void account_verification()
    {
        boolean zero=false;
        int accountID;
        Input reader=new Input();
        while(!zero)
        {
            System.out.print("Enter the accountID:");
            accountID=reader.integer(Integer.MAX_VALUE);

            if(accountID==0)
                zero=true;
            else if (!verify_access(accountID))
                System.out.println("Account not available. Try again or press 0 to return");
            else
            {
                edit_account(accountID);
                zero=true;
            }
        }


    }

    private void edit_account(int accountID)
    {
        Input reader=new Input();
        int choice;
        boolean zero=false;
        while(!zero)
        {
            System.out.println("Account access verified. Select an option, or zero to exit");
            System.out.println("1.Withdraw");
            System.out.println("2.Deposit");
            System.out.println("3.Transfer");
            choice=reader.integer(3);

            if(choice==1)
                withdraw(accountID);
            else if(choice==2)
                deposit(accountID);
            else if(choice==3)
                transfer(accountID);
            else if(choice==0)
                zero=true;

        }

    }

    private void transfer(int accountID)
    {
        Input reader=new Input();
        System.out.print("Enter account to transfer to:");
        //TODO overload integer to disinclude choices
        int accountTo=reader.integer(Integer.MAX_VALUE);
        System.out.print("Enter amount to transfer:");
        int amount=reader.integer(Integer.MAX_VALUE);
        if(verify_access(accountTo))
        {
            if(account_database.get(accountID).moneyz-amount >=0)
            {
                account_database.get(accountID).moneyz = account_database.get(accountID).moneyz - amount;
                account_database.get(accountTo).moneyz = account_database.get(accountTo).moneyz + amount;
                System.out.println("Transfer succesful");
            }
            else
            {
                System.out.println("Cannot overdraw");
            }
        }
        else
        {
            System.out.println("Account not accesible");
        }

    }

    private void deposit(int accountID)
    {
        Input reader=new Input();
        int amount;
        System.out.print("Enter amount to deposit:");
        amount=reader.integer(Integer.MAX_VALUE);
        account_database.get(accountID).moneyz=account_database.get(accountID).moneyz+amount;
        System.out.println("Deposit succesful. $"+account_database.get(accountID).moneyz+" in the account");

    }

    private void withdraw(int accountID)
    {
        Input reader=new Input();
        int amount;
        System.out.print("Enter amount to withdraw:");
        amount=reader.integer(Integer.MAX_VALUE);

        if(account_database.get(accountID).moneyz-amount >= 0)
        {
            account_database.get(accountID).moneyz=account_database.get(accountID).moneyz-amount;
            System.out.println("Withdraw succesful. $"+account_database.get(accountID).moneyz+" left in the account");
        }
        else
            System.out.println("Cannot overdraw");
    }

    private boolean verify_access(int accountID)
    {
        if(account_database.containsKey(accountID))
            if(account_database.get(accountID).approved)
                if(my_accounts.containsKey(accountID))
                    return true;

        return false;

    }

}
