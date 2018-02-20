import test.Test_Class;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class RevatureHW1
{
        //1
        //Comments are a terrible
        public static class BubbleSort
        {
            int[] numbers;

            public int[] compute(int[] numbers)
            {
                this.numbers = numbers;
                int length = numbers.length, i, j, temp;
                for (i = 0; i < length; i++) {
                    for (j = 0; j < length - 1; j++) {
                        if (numbers[j] < numbers[j + 1]) {
                            temp = numbers[j];
                            numbers[j] = numbers[j + 1];
                            numbers[j + 1] = temp;
                        }
                    }
                }

                return numbers;
            }
        }

        //2
        public static class Fibonacci
        {
            int limit;

            public Fibonacci(int limit)
            {
                this.limit=limit;
            }

            public void compute()
            {
                int previous_sum=1,current_sum=0,temp;

                for(int i=0;i<=limit;i++)
                {
                    //always deal with the special case last
                    if(i==0)
                    {
                        System.out.println(i);
                    }
                    else
                    {
                        temp=current_sum;
                        current_sum = current_sum + previous_sum;
                        System.out.println(current_sum);
                        previous_sum = temp;
                    }
                }
            }

        }

        //3
        public static class StringReverse
        {
            public String compute(String target)
            {
                for (int i = 0; i < target.length(); i++)
                    target = target.substring(1, target.length() - i) + target.substring(0, 1) + target.substring(target.length() - i, target.length());
                return target;
            }
        }

        //4
        public static class Factorial
        {
            public int compute(int N)
            {
                int factorial=1;
                for(;N > 0; N--)
                {
                    factorial=N*factorial;
                }

                return factorial;
            }
        }

        //5
        public static class Substring
        {
            public String compute(String target, int limit)
            {
                char[] char_array=target.toCharArray();
                target="";
                for(int i=0;i<limit;i++)
                    target=target+char_array[i];
                return target;
            }
        }

        //6
        public static class Parity
        {
            public boolean compute(int target)
            {
                if((target & 1)==0)
                    return true;
                else
                    return false;
            }

        }

        //7
        public static class Comparison
        {
            public void run()
            {
                RevatureHW1.Comparison.Employee e1=new RevatureHW1.Comparison.Employee("David","HR",27);
                RevatureHW1.Comparison.Employee e2=new RevatureHW1.Comparison.Employee("Brantley","PR",52);
                RevatureHW1.Comparison.Employee e3=new RevatureHW1.Comparison.Employee("Brad Pitt","DR",62);
                ArrayList<Employee> list=new ArrayList();
                list.add(e1);
                list.add(e2);
                list.add(e3);
                Collections.sort(list,new RevatureHW1.Comparison.Sort_Employee_Age());
                for( RevatureHW1.Comparison.Employee e : list)
                    System.out.println(e.name + " " + e.department + " " + e.age);
                System.out.println();
                Collections.sort(list,new RevatureHW1.Comparison.Sort_Employee_Department());
                for( RevatureHW1.Comparison.Employee e : list)
                    System.out.println(e.name + " " + e.department + " " + e.age);
                System.out.println();
                Collections.sort(list,new RevatureHW1.Comparison.Sort_Employee_Name());
                for( RevatureHW1.Comparison.Employee e : list)
                    System.out.println(e.name + " " + e.department + " " + e.age);
            }

            public static class Employee
            {
                String name;
                String department;
                int age;

                public Employee(String name,String department, int age)
                {
                    this.name=name;
                    this.department=department;
                    this.age=age;
                }
            }

            public static class Sort_Employee_Name implements Comparator<Employee>
            {
                @Override
                public int compare(Employee o1, Employee o2)
                {
                    return o1.name.compareTo(o2.name);
                }
            }

            public static class Sort_Employee_Department implements Comparator<Employee>
            {
                @Override
                public int compare(Employee o1, Employee o2)
                {
                    return o1.department.compareTo(o2.department);
                }
            }

            public static class Sort_Employee_Age implements Comparator<Employee>
            {
                @Override
                public int compare(Employee o1, Employee o2)
                {
                    if(o1.age > o2.age)
                        return 1;
                    else if(o1.age==o2.age)
                        return 0;
                    else
                        return -1;
                }
            }


        }

        //8
        public static class Palindrome
        {
            public void run()
            {
                ArrayList<String> palindrome=new ArrayList<>();
                ArrayList<String> list=new ArrayList(Arrays.asList("karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john", "refer", "billy", "did"));
                for( String s : list )
                {
                    if(isPalindrome(s))
                        palindrome.add(s);
                }
                for( String s : palindrome )
                    System.out.println(s);
            }

            public boolean isPalindrome(String s)
            {
                boolean palindrome=true;

                for(int i=0, j=s.length()-1; i < s.length()/2; i++, j--)
                {
                    if(s.charAt(i)!=s.charAt(j))
                        palindrome=false;
                }

                return palindrome;
            }
        }

        //9
        public static class PrimeNumbers
        {
            public void calculate(int minimum,int maximum)
            {
                ArrayList<Integer> prime=new ArrayList<>();
                if(minimum < 3)
                {
                    prime.add(1);
                    prime.add(2);
                    minimum=3;
                }
                else if(minimum%2==0)
                    ++minimum;

                for(int i=minimum;i<=maximum;i+=2)
                {
                    if(isPrime(i))
                     prime.add(i);
                }

                System.out.println(Arrays.toString(prime.toArray()));

            }

            private boolean isPrime(int target)
            {

                for(int i=3;i<=Math.sqrt(target);i+=2)
                {
                    if(target%i==0)
                        return false;
                }

                return true;
            }
        }

        //10
        public static class Ternary
        {

            public int compare(int a, int b)
            {
                return (a>b)?b:a;
            }
        }

        //11
        public static class Package_Access
        {
            public void run()
            {
                test.Test_Class hi= new Test_Class();
                hi.x=4;
                hi.y=3;
                System.out.println(hi.x+hi.y);
            }


        }

        //12
        public static class EvenNumbers
        {
            public void print()
            {
                int[] array=new int[100];
                for(int i=0;i<100;i++)
                    array[i]=i+1;
                for(int x : array)
                {
                    if (x % 2 == 0)
                        System.out.println(x);
                }
            }
        }

        //13
        public static class Triangle
        {
            public void print()
            {
                boolean zero = true;
                for (int i = 0; i < 4; i++)
                {
                    for (int j = -1; j < i; j++)
                    {
                        if (zero)
                            System.out.print("0 ");
                        else
                            System.out.print("1 ");
                        zero = !zero;
                    }
                    System.out.println();
                }
            }
        }

        //14
        public static class Switch
        {
            public void run(int choice)
            {
                switch (choice)
                {
                    case 1: square_root();
                    break;
                    case 2: get_date();
                    break;
                    case 3: split_string();
                    break;

                }
            }

            private void split_string()
            {
                String s="I am learning core java";
                String[] arrays=s.split(" ");
                int i=0;
                for(String x : arrays)
                {
                    ++i;
                    System.out.println(i+". "+x);
                }
            }

            private void get_date()
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date));
            }

            private void square_root()
            {
                Random rand = new Random();
                int  n = rand.nextInt(50) + 1;
                System.out.println("The square root of "+n+" is "+Math.sqrt(n));
            }
        }

        //15
        public interface Operator<T>
        {
            T addition(T op1,T op2);
            T multiplication(T op1,T op2);
            T subtraction(T op1,T op2);
            T division(T op1, T op2);
        }

        public static class Int_Operate implements Operator<Integer>
        {

            @Override
            public Integer addition(Integer op1, Integer op2) {
                return op1+op2;
            }

            @Override
            public Integer multiplication(Integer op1, Integer op2) {
                return op1*op2;
            }

            @Override
            public Integer subtraction(Integer op1, Integer op2) {
                return op1-op2;
            }

            @Override
            public Integer division(Integer op1, Integer op2) {
                return op1/op2;
            }
        }

        //16
        public static class Character_Num
        {
            public int run(String[] args)
            {
                String s=args[0];
                return s.length();
            }
        }

        //17
        public static class Interest
        {
            public void run()
            {
                Scanner reader=new Scanner(System.in);
                System.out.println("Enter the principal: ");
                int principal=reader.nextInt();
                System.out.println("Enter the rate: ");
                int rate=reader.nextInt();
                System.out.println("Enter the time: ");
                int time=reader.nextInt();
                System.out.println(principal*rate*time);
            }
        }

        //18
        public static abstract class superclass
        {
            abstract void method1();
            abstract void method2();
            abstract void method3();
        }

        public static class subclass extends superclass
        {

            @Override
            void method1() {
                System.out.println("m1");
            }

            @Override
            void method2() {
                System.out.println("m2");
            }

            @Override
            void method3() {
                System.out.println("m3");
            }
        }

        //19
        public static class ArrayListDemonstration
        {
            public void run()
            {
                ArrayList<Integer> list=new ArrayList();
                for(int i=0;i<10;i++)
                list.add(i+1);
                System.out.println(Arrays.toString(list.toArray()));
                int even_sum=0,odd_sum=0;
                for(Integer x: list)
                {
                    if(x%2==0)
                        even_sum=even_sum+x;
                    else
                        odd_sum=odd_sum+x;

                }
                list.remove(1);
                for(int i=0;i<list.size();i++)
                {
                    if(isPrime(list.get(i)))
                    list.remove(i);
                }
                System.out.println("odd sum="+odd_sum);
                System.out.println("even sum="+even_sum);
                System.out.println("Non prime numbers");
                for(int i=0;i<list.size();i++)
                {
                    if(!isPrime(list.get(i)))
                        System.out.println(list.get(i));
                }
            }

            private boolean isPrime(int target)
            {
                if(target <= 3)
                    return true;

                for(int i=2;i<=Math.sqrt(target);i++)
                {
                    if(target%i==0)
                        return false;
                }

                return true;
            }
        }

        //20
        public static class NoteData
        {
            public void run() throws IOException {
                PrintWriter writer = new PrintWriter("database.txt", "UTF-8");
                writer.println("Mickey:Mouse:35:Arizona");
                writer.println("Hulk:Hogan:50:Virginia");
                writer.println("Roger:Rabbit:22:California");
                writer.println("Wonder:Woman:18:Montana");
                writer.close();

                FileReader fr=new FileReader("database.txt");
                BufferedReader reader=new BufferedReader(fr);
                String line;
                while((line=reader.readLine())!=null)
                {
                    String[] split=line.split(":");
                    System.out.println("Name: "+split[0]+" "+split[1]);
                    System.out.println("Age: "+split[2]+" years");
                    System.out.println("State: "+split[3]+" State");

                }


            }
        }
}
