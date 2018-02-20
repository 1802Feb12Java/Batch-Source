package q15.arithmeticinterface;

public class MyMath implements ArithmeticInterface {

	@Override
	public void add(int arg1, int arg2) {
		int result = arg1 + arg2;
		System.out.println(result);
		
	}

	@Override
	public void sub(int arg1, int arg2) {
		int result = arg1 - arg2;
		System.out.println(result);
		
	}

	@Override
	public void multiply(int arg1, int arg2) {
		int result = arg1 * arg2;
		System.out.println(result);
		
	}

	@Override
	public void divide(int arg1, int arg2) {
		int result = arg1 / arg2;
		System.out.println(result);
		
	}
	
}
