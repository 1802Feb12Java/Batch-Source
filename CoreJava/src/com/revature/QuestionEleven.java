package com.revature;

//used to access different package
import com.other.folder.Q11Floats;

public class QuestionEleven {

	public void printFloats() {
		Q11Floats qq = new Q11Floats();

		//access public variables directly
		System.out.println("Floats:\t" + qq.a + "\t" + qq.b);
	}

}