package com.revature.util;

import java.sql.Date;
import java.util.function.Function;

public interface Convert {

	Function<Date, String> DateToString = (Date d) -> {
		if (d == null)
			return "NULL";
		return d.toString();
	};
	Function<Boolean, Integer> BoolToInt = (Boolean b) -> {
		if (b == null)
			return 0;
		return (b) ? 1 : 0;
	};
}
