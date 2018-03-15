public class BankApp
{
    static User super_user =new Admin("admin","Password1");

    public void run()
    {
        Input reader=new Input();
        int choice;
        boolean exit=false;


        while(!exit)
        {
            System.out.println("Welcome to Fells Wargo! Please select a numerical option, or press 0 to exit");
            System.out.println("1. Login");
            System.out.println("2. New User");
            choice=reader.integer(2);

            if(choice==1)
                login();
            else if(choice==2)
                register();
            else
                exit=true;
        }

    }

    private static void login()
    {
        Input reader=new Input();
        boolean zero=false;
        String username, password;
        while(!zero)
        {
            System.out.print("Username:");
            username = reader.string();
            if(username.equals("0"))
                zero=true;
            else if (super_user.user_database.containsKey(username) && !username.equals("admin"))
            {
                System.out.print("Password:");
                password = reader.string();

                if (super_user.user_database.get(username).password.equals(password))
                {
                    super_user.user_database.get(username).initialize_menu();
                    zero=true;
                }
                else
                    System.out.println("Password is incorrect. Please try again, or press 0 to exit");
            }
            else
                System.out.println("Username is unavailable. Please try again, or press 0 to exit");
        }

    }

    private static void register()
    {
        String username, password;
        int access_level;
        Input reader = new Input();
        boolean zero = false;

        while (!zero)
        {
            System.out.print("Username:");
            username = reader.string();
            if(username.equals("0"))
                zero=true;
            else if (super_user.user_database.containsKey(username) || username.equals("admin"))
                System.out.println(username+" already exists. Please try again, or press 0 to exit");
            else
            {
                System.out.print("Password:");
                password = reader.string();
                System.out.println("Access Level:");
                System.out.println("1.Admin");
                System.out.println("2.Employee");
                System.out.println("3.Customer");

                access_level = reader.integer(3);

                if (access_level == 1)
                {
                    Admin user = new Admin(username, password);
                    user.initialize_menu();
                    System.out.println("Account created");
                }
                else if (access_level == 2)
                {
                    Employee user = new Employee(username, password);
                    user.initialize_menu();
                    System.out.println("Account created");
                }
                else if (access_level == 3)
                {
                    Customer user = new Customer(username, password);
                    user.initialize_menu();
                    System.out.println("Account created");
                }
                zero=true;
            }
        }

    }
}

