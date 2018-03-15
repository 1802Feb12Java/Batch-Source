import java.util.HashMap;

public class Customer extends User
{
    Customer(String username, String password)
    {
        super(username, password,3);
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
        int accountID;
        Input reader=new Input();
        System.out.print("Enter the accountID:");
        accountID=reader.integer();
        if(accountID==0)
        {}
        else if(applications.containsKey(accountID,this.UserID))
        {
            System.out.println("Application already exists.");
        }
        else if(account_database.rowExists(accountID,this.UserID))
        {
            System.out.println("Account already exists");
        }
        else if(account_database.containsKey(accountID))
        {
            applications.put(accountID,this.UserID);
            System.out.println("Account already exists. Joint application sent");
        }
        else
        {
            applications.put(accountID,this.UserID);
            System.out.println("New account application created");
        }

    }

    private void account_verification()
    {
        boolean zero=false;
        int accountID;
        if(account_database.containsUIDKey(this.UserID))
        account_database.view_available_accounts(this.UserID);
        Input reader=new Input();
        while(!zero)
        {
            System.out.print("Enter the accountID:");
            accountID=reader.integer();

            if(accountID==0)
                zero=true;
            else if (!account_database.access(this.UserID,accountID))
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
            System.out.println("1.View");
            System.out.println("2.Withdraw");
            System.out.println("3.Deposit");
            System.out.println("4.Transfer");
            System.out.println("5.Delete");
            choice=reader.integer(5);
            if (choice==1)
                view(accountID);
            else if(choice==2)
                withdraw(accountID);
            else if(choice==3)
                deposit(accountID);
            else if(choice==4)
                transfer(accountID);
            else if(choice==5)
            {
                if(account_database.get(accountID).moneyz > 0)
                    System.out.println("Cannot delete "+accountID+". Account must be devoid of funds");
                else
                {
                    delete(accountID);
                    zero = true;
                }
            }
            else if(choice==0)
                zero=true;

        }

    }

    private void view(int accountID)
    {
        account_database.view(accountID);
    }

    private void withdraw(int accountID)
    {
        Input reader=new Input();
        double amount;
        System.out.print("Enter amount to withdraw:");
        amount=reader.dble();
        Account account=account_database.get(accountID);
        if(account.moneyz-amount >= 0)
        {
            account.moneyz=account.moneyz-amount;
            System.out.println("Withdraw successful. "+formatter.format(account.moneyz)+" left in the account");
            account_database.remove(accountID);
            account_database.put(accountID,account);
            log.info("Withdraw occurred in account "+accountID+" ID:"+this.UserID);
        }
        else
            System.out.println("Cannot overdraw");
    }

    private void deposit(int accountID)
    {
        Input reader=new Input();
        double amount;
        System.out.print("Enter amount to deposit:");
        amount=reader.dble();
        Account target=account_database.get(accountID);
        target.moneyz=target.moneyz+amount;
        account_database.remove(accountID);
        account_database.put(accountID,target);
        System.out.println("Deposit successful. "+formatter.format(account_database.get(accountID).moneyz)+" in the account");
        log.info("Deposit occurred in account "+accountID+" ID:"+this.UserID);
    }

    private void transfer(int accountID)
    {
        Input reader=new Input();
        System.out.print("Enter account to transfer to:");
        int accountTo=reader.integer();
        System.out.print("Enter amount to transfer:");
        double amount=reader.dble();
        if(account_database.access(this.UserID,accountID))
        {
            Account account_from=account_database.get(accountID);
            Account account_recieving=account_database.get(accountTo);

            if(account_from.moneyz-amount >=0)
            {
                account_from.moneyz = account_from.moneyz - amount;
                account_recieving.moneyz = account_recieving.moneyz + amount;
                System.out.println("Transfer successful. "+formatter.format(account_from.moneyz)+" in the account");
                log.info("Transfer occurred from account "+accountID+" to account "+accountTo+" ID:"+this.UserID);

                account_database.remove(accountID);
                account_database.put(accountID,account_from);
                account_database.remove(accountTo);
                account_database.put(accountTo,account_recieving);
            }
            else
            {
                System.out.println("Cannot overdraw");
            }
        }
        else
        {
            System.out.println("Account not accessible");
        }

    }

    private void delete(int accountID)
    {
        account_database.remove(accountID);
        log.info("Account "+accountID+" deleted ID:"+this.UserID);
        System.out.println("Account #"+accountID+" removed");
    }


}
