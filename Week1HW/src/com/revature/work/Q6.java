package com.revature.work;

public class Q6 {
	
	// checking if its even 
	public boolean isEven (int proof) {
		// int has no decimal so dividing by 2 and multiplying it should result in different value for odds
		int newProof = proof/2;
		if (newProof*2 == proof) {
			return true;
		} else {
			return false;
		}
	}
}
