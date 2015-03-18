package com.excilys.cdb.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public enum ComputerDatabaseValidator {
	INSTANCE;
	
	/**
	 * Validate date.
	 *
	 * @param inputString The input string
	 * @return True, if successful
	 */
	public boolean validateDate(String inputString) {
		SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			format.parse(inputString);
			Pattern p = Pattern
					.compile("^\\d{4}[-]?\\d{1,2}[-]?\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");
			return p.matcher(inputString).matches();
		} catch (ParseException e) {
			System.out.println("ParseException");
			return false;
		}
	}

}

