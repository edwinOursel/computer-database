package com.excilys.cdb.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class LocaleDateValidator implements ConstraintValidator<LocaleDate, String> {

	Logger logger = LoggerFactory.getLogger(LocaleDateValidator.class);
	
	@Autowired
	ResourceBundleMessageSource messageSource;
	
	@Override
	public void initialize(LocaleDate constraintAnnotation) {}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		logger.info("date validator called for : " + date);
		if (date == null) {
			return false;
		}
		return Pattern.compile(messageSource.getMessage("datePattern", null, LocaleContextHolder.getLocale())).matcher(date).matches();
	}

}
