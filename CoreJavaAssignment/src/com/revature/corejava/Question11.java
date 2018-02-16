package com.revature.corejava;
import com.revature.otherpackage.OtherPackageClass;
public class Question11 {
	public static void accessOtherPackage() {
		OtherPackageClass opc = new OtherPackageClass();
		System.out.println("First float from other package is: " + opc.firstFloat);
		System.out.println("Second float from other package is: " + opc.secondFloat);
	}
}
