package q5.substring;

public class SubString {
	public String subString(String string, int index) {
		String subString = "";
		for(int i = 0; i<index; i++)
		{
			String mystr = String.valueOf(string.charAt(i));
			subString += mystr;
		}
		System.out.println("\n");
		System.out.println(subString);
		return subString;
	}
}
