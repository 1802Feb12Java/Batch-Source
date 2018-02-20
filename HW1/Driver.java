import java.io.IOException;
import java.util.Arrays;

public class Driver
{
    public static void main(String args[])
    {
        int q_number=1;
        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.BubbleSort q1=new RevatureHW1.BubbleSort();
        System.out.println(Arrays.toString(q1.compute(new int[]{1,0,5,6,3,2,3,7,9,8,4})));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Fibonacci fib=new RevatureHW1.Fibonacci(25);
        fib.compute();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.StringReverse reverse=new RevatureHW1.StringReverse();
        System.out.println(reverse.compute("Hello World"));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Factorial fact=new RevatureHW1.Factorial();
        System.out.println(fact.compute(7));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Substring sub=new RevatureHW1.Substring();
        System.out.println(sub.compute("Basketball",5));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Parity parity=new RevatureHW1.Parity();
        System.out.println(parity.compute(3));
        System.out.println(parity.compute(4));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Comparison compare=new RevatureHW1.Comparison();
        compare.run();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Palindrome pal=new RevatureHW1.Palindrome();
        pal.run();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.PrimeNumbers prime=new RevatureHW1.PrimeNumbers();
        prime.calculate(1,100);

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Ternary tern=new RevatureHW1.Ternary();
        System.out.println(tern.compare(1,2));
        System.out.println(tern.compare(3,2));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Package_Access pack=new RevatureHW1.Package_Access();
        pack.run();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.EvenNumbers even=new RevatureHW1.EvenNumbers();
        even.print();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Triangle tri=new RevatureHW1.Triangle();
        tri.print();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Switch sw=new RevatureHW1.Switch();
        sw.run(1);
        sw.run(2);
        sw.run(3);

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Int_Operate oper=new RevatureHW1.Int_Operate();
        System.out.println(oper.addition(2,2));
        System.out.println(oper.division(2,2));
        System.out.println(oper.multiplication(2,2));
        System.out.println(oper.subtraction(2,2));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Character_Num num=new RevatureHW1.Character_Num();
        System.out.println(num.run(new String[]{"Hello World"}));

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.Interest interest=new RevatureHW1.Interest();
        interest.run();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.subclass sb=new RevatureHW1.subclass();
        sb.method1();
        sb.method2();
        sb.method3();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.ArrayListDemonstration array=new RevatureHW1.ArrayListDemonstration();
        array.run();

        System.out.println("---Q"+q_number+"---");
        ++q_number;

        RevatureHW1.NoteData note=new RevatureHW1.NoteData();
        try {
            note.run();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
