package com.revature;

public class QuestionThirteen {

	public void printTriangle(int rows) {
		int currentRow = 1;
		int currentValue = 0;

		if (rows < 1) {
			System.out.println("rows not >= 1");
		} else {
			while (currentRow <= rows) {
				for (int i = 0; i < currentRow; i++) {
					System.out.print(currentValue);

					// alternates between 0 and 1
					if (currentValue == 0) {
						currentValue = 1;
					} else {
						currentValue = 0;
					}
				}
				// next row when # of printed numbers = row #
				System.out.println();
				currentRow++;
			}
		}
	}

}