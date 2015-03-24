package com.excilys.cdb.validation;

import java.util.regex.Pattern;

public enum ComputerDatabaseValidator {
    INSTANCE;

    private static final String START = "^";
    private static final String END = "$";
    private static final String DATE = "\\d{4}-\\d{1,2}-\\d{1,2}";
    private static final String TIME = "(\\d{1,2}:){2}\\d{1,2}";
    private static final String SEPARATOR = " ";

    /**
     * Validate datetime.
     *
     * @param inputString The input string
     * @return True, if successful
     */
    public boolean validateDateTime(String inputString) {
        final Pattern p = Pattern
                .compile(START + DATE + SEPARATOR + TIME + END);
        return p.matcher(inputString).matches();
    }

    /**
     * Validate date.
     *
     * @param inputString The input string
     * @return True, if successful
     */
    public boolean validateSimpleDate(String inputString) {
        final Pattern p = Pattern
                .compile(START + DATE + END);
        return p.matcher(inputString).matches();
    }
}
