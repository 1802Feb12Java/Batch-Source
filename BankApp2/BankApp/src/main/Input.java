/*
import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input
{
        String[] tester={"1","Josh","B",""};
        static int iterator=0;
        ByteArrayInputStream in = new ByteArrayInputStream(tester[iterator].getBytes());


        String standard_message="Try again, or enter 0 to go back";
        String target;

        public int integer(int number_of_options)
        {
                System.setIn(in);
                Scanner reader=new Scanner(System.in);
                boolean error;
                int x=-1;
                do
                {
                        error = false;
                        try
                        {
                                target=reader.nextLine();
                                x = Integer.parseInt(target);
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
                ++iterator;
                return x;
        }

        public int integer()
        {
                System.setIn(in);
                Scanner reader=new Scanner(System.in);
                boolean error;
                int x=-1;
                do
                {
                        error = false;
                        try
                        {
                                target=reader.nextLine();
                                x = Integer.parseInt(target);
                        }
                        catch (InputMismatchException e)
                        {
                                System.out.println("Bad Input. "+standard_message);
                                error = true;
                                reader.next();
                        }
                }
                while(error==true);
                ++iterator;
                return x;
        }

        public double dble()
        {
                System.setIn(in);
                Scanner reader=new Scanner(System.in);
                boolean error;
                double x=0;
                do
                {
                        error = false;
                        try
                        {
                                target=reader.nextLine();
                                x = Double.parseDouble(target);
                        }
                        catch (InputMismatchException e)
                        {
                                System.out.println("Bad Input. "+standard_message);
                                error = true;
                                reader.nextDouble();
                        }
                }
                while(error==true);
                ++iterator;
                return x;
        }



        public String string()
        {
                System.setIn(in);
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
                ++iterator;
                return s;
        }


}*/
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

        public int integer()
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
                }
                while(error==true);

                return x;
        }

        public double dble()
        {
                Scanner reader=new Scanner(System.in);
                boolean error;
                double x=0;
                do
                {
                        error = false;
                        try
                        {
                                x = reader.nextDouble();
                        }
                        catch (InputMismatchException e)
                        {
                                System.out.println("Bad Input. "+standard_message);
                                error = true;
                                reader.nextDouble();
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

