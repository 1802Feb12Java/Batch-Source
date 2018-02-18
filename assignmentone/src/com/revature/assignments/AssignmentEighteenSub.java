package com.revature.assignments;

public class AssignmentEighteenSub extends AssignmentEighteen {

	@Override
	public boolean containsUppercase(String in) {
		for (int i = 0; i < in.length(); i++)
			if (Character.isUpperCase(in.charAt(i)))
				return true;
		return false;
	}

	@Override
	public String convertToUpper(String in) {
		return in.toUpperCase();
	}

	@Override
	public void addTen(String num) {
		int number;
		try {
			number = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		System.out.println(num + " + 10 = " + (number + 10));
	}

}
