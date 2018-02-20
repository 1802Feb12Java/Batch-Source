import java.util.InputMismatchException;
import java.util.Scanner;

public class Input
{

       String standard_message="Try again, or enter 0 to go back";

        public int integer(int number_of_options)
        {
                Scanner reader=new Scanner(System.in);
                boolean error;
                int x=-1;
                do
                {
                        error = false;
                        try
                        {
                                x = reader.nextInt();
                        }
                        catch (InputMismatchException e)
                        {
                                System.out.println("Bad Input. "+standard_message);
                                error = true;
                                reader.next();
                        }

                        if(error==false && (x > number_of_options || x < 0))
                        {
                                System.out.println("Not an option. "+standard_message);
                                error = true;
                        }
                }
                while(error==true);

                return x;
        }

        public String string()
        {
                Scanner reader=new Scanner(System.in);
                boolean error;
                String s="";
                do
                {
                        error = false;
                        try
                        {
                                s = reader.nextLine();
                        }
                        catch (InputMismatchException e)
                        {
                                System.out.println("Bad Input. " + standard_message);
                                error = true;
                                reader.next();
                        }
                }
                while(error==true);

                return s;
        }


}
