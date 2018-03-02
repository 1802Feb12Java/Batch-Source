package com.revature;

public class QuestionSix {

	// if anything past decimal point after divison by 2, not even
	public boolean isEven(int checkThis) {
		if (((float) checkThis / 2 - checkThis / 2) == 0) {
			return true;
		} else {
			return false;
		}
	}

}