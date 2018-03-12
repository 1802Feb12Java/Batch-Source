package q13.mytriangle;
import java.util.ArrayList;
import java.util.Arrays;

public class MyTriangle {
	public void myTriangle()
	{
		String a = "0";
		String b = "1 0";
		String c = "1 0 1";
		String d = "0 1 0 1";
		ArrayList<String> myTriangle = new ArrayList<>();
		myTriangle.addAll(Arrays.asList(a, b, c, d));
		for(String value: myTriangle)
		{
			System.out.println(value);
		}
	}
}
