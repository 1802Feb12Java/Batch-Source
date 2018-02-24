package com.revature;

abstract class StringUtilBase {
	/**
	 * Checks if any uppercase characters exist in 
	 * @param str Input string to check.
	 * @return true if uppercase characters are present. 
	 * 			Otherwise, false.
	 */
	public abstract boolean hasUpperCase(String str);
	
	/**
	 * Converts the string to uppercase.
	 * @param str Input string to convert.
	 * @return An uppercase version of str.
	 */
	public abstract String toUpperCase(String str);
	
	/**
	 * Converts str to an integer val, adds 10 to it, and displays it to the console.
	 * @param str The string to operate on.
	 */
	public abstract void convertAndDisplayInt(String str);
}


class StringUtils extends StringUtilBase {
	
	
	@Override
	public boolean hasUpperCase(String str) {
		// ".*?" matches 0+ characters until a match appears for the following regex portion
		// "\\p{Lu}+" matches 1+ uppercase letters (includes Unicode)
		// ".*" matches 0+ characters that may be remaining
		return str.matches(".*?\\p{Lu}+.*");
	}

	@Override
	public String toUpperCase(String str) {
		
		return str.toUpperCase();
	}

	@Override
	public void convertAndDisplayInt(String str) {
		Integer val = Integer.parseInt(str);
		val += 10;
		
		System.out.print(val.toString());
	}
	
}

public class Q18 implements Runnable {
	public static final String TAB = "    ";
	
	private final String UPPER_CASE_CHECK_PARAM;
	private final String UPPER_CASE_CONVERT_PARAM;
	private final String PARSE_INT_OP_PARAM;
	
	public Q18(String upperCaseCheckParam,
			String upperCaseConvertParam,
			String parseIntOpParam) {
		UPPER_CASE_CHECK_PARAM = upperCaseCheckParam;
		UPPER_CASE_CONVERT_PARAM = upperCaseConvertParam;
		PARSE_INT_OP_PARAM = parseIntOpParam;
	}
	
	public static void main(String[] args) {
		System.out.println("Question 18: Abstract Class Inheritance and Basic String Operations");                                
		StringUtils utils = new StringUtils();
		System.out.println("\"" + args[0] + "\" has uppercase characters: " + utils.hasUpperCase(args[0]));
		System.out.println("\"" + args[1] + "\" to uppercase characters: " + utils.toUpperCase(args[1]));
		System.out.print("\"" + args[2] + "\" to integer + 10: ");
		utils.convertAndDisplayInt(args[2]);
		System.out.println();
	}
	
	@Override
	public void run() {
		Q18.main(new String[]{
				UPPER_CASE_CHECK_PARAM,
				UPPER_CASE_CONVERT_PARAM,
				PARSE_INT_OP_PARAM
		});
	}

}
