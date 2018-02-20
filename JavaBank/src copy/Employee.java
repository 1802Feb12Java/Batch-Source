public class Employee extends User
{
    Employee(String username, String password)
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
            System.out.println("1. Approve");
            System.out.println("2. Deny account");
            System.out.println("3. View");
            System.out.println("4. Cancel account");

            choice = reader.integer(4);

            if (choice == 1)
                approve();
            else if (choice == 2)
                deny();
            else if (choice == 3)
                edit();
            else if (choice==4)
                cancel();
            else
                zero = true;
        }
    }

    private void cancel()
    {
        Input reader=new Input();
        int accountID;
        System.out.print("Enter the accountID:");
        accountID=reader.integer(Integer.MAX_VALUE);
        account_database.remove(accountID);

    }

    private void edit()
    {
        Input reader=new Input();
        int choice,accountID;
        boolean zero=false;
        while(!zero)
        {
            System.out.print("Enter accountID:");
            accountID=reader.integer(Integer.MAX_VALUE);
            if(!account_database.containsKey(accountID))
            {
                System.out.println("Account not found");
                zero=true;
            }
            else
            {
                System.out.println("1.Withdraw");
                System.out.println("2.Deposit");
                System.out.println("3.Transfer");
                choice = reader.integer(3);

                if (choice == 1)
                    withdraw(accountID);
                else if (choice == 2)
                    deposit(accountID);
                else if (choice == 3)
                    transfer(accountID);
                else if (choice == 0)
                    zero = true;
            }

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
        if(account_database.containsKey(accountTo) && account_database.containsKey(accountTo))
        {
            if (account_database.get(accountID).moneyz - amount >= 0) {
                account_database.get(accountID).moneyz = account_database.get(accountID).moneyz - amount;
                account_database.get(accountTo).moneyz = account_database.get(accountTo).moneyz + amount;
                System.out.println("Transfer succesful");
            } else {
                System.out.println("Cannot overdraw");
            }
        }
        else
        {
            System.out.println("Account not found");
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

    private void deny()
    {
        Input reader=new Input();
        int accountID;
        System.out.print("Enter the accountID:");
        accountID=reader.integer(Integer.MAX_VALUE);
        if(applications.containsKey(accountID))
            applications.remove(accountID);
        else
            System.out.println("Account not found");

    }

    private void approve()
    {
        Input reader=new Input();
        int accountID;
        System.out.print("Enter the accountID:");
        accountID=reader.integer(Integer.MAX_VALUE);
        if(applications.containsKey(accountID))
        {
            Customer user=(Customer)user_database.get(applications.get(accountID));
            Account account;
            if(!account_database.containsKey(accountID))
                account=new Account(user);
            else
                account=account_database.get(accountID);
            account.approved=true;
            account.account_holders.put(user.username,user);
            user.my_accounts.put(accountID,account);
            if(!account_database.containsKey(accountID))
                account_database.put(accountID,account);
            System.out.println("Account created");
        }
        else
        {
            System.out.println("Account not found");
        }

    }

}
