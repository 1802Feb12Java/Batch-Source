package q3.reverse;

public class ReverseString {
	public void reverseString() 
	{
		String a = "1", b = "2", c = "3", d = "4";
		String bufferInOrderStr = a+b+c+d;
		String bufferInReverse = "";
		for(int i = 3; i > -1; i--)
		{
			
			bufferInReverse += String.valueOf(bufferInOrderStr.charAt(i));
			
		}
		System.out.println("\n");
		System.out.println(bufferInReverse);
	}
}
