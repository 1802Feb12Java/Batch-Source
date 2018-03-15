public class Admin extends User
{

    Admin(String username, String password)
    {
        super(username, password,1);
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
            System.out.println("3. Edit account");
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
                    edit_account(accountID);
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
                delete(accountID);
                zero = true;
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
            System.out.println("Withdraw succesful. "+formatter.format(account.moneyz)+" left in the account");
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
        System.out.println("Deposit succesful. "+formatter.format(account_database.get(accountID).moneyz)+" in the account");
        log.info("Deposit occurred in account "+accountID+" ID:"+this.UserID);
    }

    private void transfer(int accountID)
    {
        Input reader=new Input();
        System.out.print("Enter account to transfer to:");
        int accountTo=reader.integer();
        System.out.print("Enter amount to transfer:");
        double amount=reader.dble();

            Account account_from=account_database.get(accountID);
            Account account_recieving=account_database.get(accountTo);

            if(account_from.moneyz-amount >=0)
            {
                account_from.moneyz = account_from.moneyz - amount;
                account_recieving.moneyz = account_recieving.moneyz + amount;
                System.out.println("Transfer succesful. "+formatter.format(account_from.moneyz)+" in the account");

                account_database.remove(accountID);
                account_database.put(accountID,account_from);
                account_database.remove(accountTo);
                account_database.put(accountID,account_recieving);
                log.info("Transfer occurred from account "+accountID+" to account "+accountTo+" ID:"+this.UserID);
            }
            else
            {
                System.out.println("Cannot overdraw");
            }



    }

    private void delete(int accountID)
    {
        account_database.remove(accountID);
        log.info("Account "+accountID+" deleted ID:"+this.UserID);
        System.out.println("Account #"+accountID+" removed");
    }



}
