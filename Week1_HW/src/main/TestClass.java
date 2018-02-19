package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import q1.bubblesort.BubbleSort;
import q10.minimum.Minimum;
import q11.packagegetsfloats.PackageGetsFloats;
import q12.advancedforeven.AdvancedForEven;
import q13.mytriangle.MyTriangle;
import q14.myswitch.MySwitch;
import q15.arithmeticinterface.MyMath;
import q16.numofcharsinarg.NumOfCharsInArgs;
import q17.calcinterest.CalcInterest;
import q18.stringmanipulation.CodifyString;
import q19.addremoveprime.AddRemovePrime;
import q2.fibonacci.Fibonacci;
import q20.serialize.Serialize;
import q3.reverse.ReverseString;
import q4.nfactorial.Nfactorial;
import q5.substring.SubString;
import q6.isinteven.IsIntEven;
import q7.comparator.CompareEmployees;
import q8.palindromes.Palindromes;
import q9.primenumbers.PrimeNumbers;

public class TestClass {

	public static void main(String[] args) {
		
		//q1
		BubbleSort sort = new BubbleSort();
		sort.bubbleSort();
		//q2
		Fibonacci doFibonacci = new Fibonacci();
		doFibonacci.fibonacci();
		//q3
		ReverseString doReverse = new ReverseString();
		doReverse.reverseString();
		//q4
		Nfactorial doNfactorial = new Nfactorial();
		int result = doNfactorial.nFactorial(4);
		System.out.print(result);
		//q5
		SubString doSub = new SubString();
		doSub.subString("juggling", 4);
		//q6
		IsIntEven doEven = new IsIntEven();
		doEven.isIntEven();
		//q7
		CompareEmployees doSort = new CompareEmployees();
		doSort.sort();
		//q8
		Palindromes doPal = new Palindromes();
		doPal.palindromes();
		//q9
		PrimeNumbers doPrime = new PrimeNumbers();
		doPrime.primeNumbers();
		//q10
		Minimum doMin = new Minimum();
		doMin.minimum();
		//q11
		PackageGetsFloats doFloats = new PackageGetsFloats();
		doFloats.getfloats();
		//q12
		AdvancedForEven doAdvanced = new AdvancedForEven();
		doAdvanced.advancedForEven();
		//q13
		MyTriangle doTriangle = new MyTriangle();
		doTriangle.myTriangle();
		//q14
		MySwitch doSwitch = new MySwitch();
		doSwitch.mySwitch();
		//q15
		MyMath doMath = new MyMath();
		doMath.add(6, 2);
		doMath.divide(6, 2);
		//q16
		try 
		{
		NumOfCharsInArgs doCharsInArgs = new NumOfCharsInArgs(args);
		doCharsInArgs.countNumInArgs();
		}
		catch(NullPointerException E)
		{
			System.out.println("The args variable is null");
		}
		//q17
		CalcInterest doInterest = new CalcInterest();
		doInterest.calcInterest();
		//q18
		CodifyString doCodify = new CodifyString();
		doCodify.checkUppercase();
		doCodify.convertLowercaseToUppercase();
		doCodify.convertStringToInt();
		//q19
		AddRemovePrime doAddRemove = new AddRemovePrime();
		doAddRemove.addRemovePrime();
		//q20
		Serialize doSerialize = new Serialize();
		try {
			doSerialize.getData();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("File Not Found");
		}
	}

}
