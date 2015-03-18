package com.excilys.cdb.validation;

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
		Pattern p = Pattern
				.compile("^\\d{4}[-]?(0[1-9]|1[0-2])[-]?([0-2][1-9]|3[0-1]) ([0-1][1-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
		return p.matcher(inputString).matches();		
	}

}

