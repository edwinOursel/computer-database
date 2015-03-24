package com.excilys.cdb.cli;

import com.excilys.cdb.validation.ComputerDatabaseValidator;

public class LocalDateTimeUtil {
    private static final String DEFAULT_TIME = "00:00:00";
    private static final String SEPARATOR = " ";

    /**
     * Convert a simple date to pattern for LocalDateTime.
     *
     * @param inputString
     * @return format for LocalDateTime
     */
    public static String convertToValidLocalDateTime(String inputString) {
        if (ComputerDatabaseValidator.INSTANCE.validateDateTime(inputString)) {
            return inputString;
        }
        if (ComputerDatabaseValidator.INSTANCE.validateSimpleDate(inputString)) {
            return inputString + SEPARATOR + DEFAULT_TIME;
        }
        throw new IllegalArgumentException();
    }
}
