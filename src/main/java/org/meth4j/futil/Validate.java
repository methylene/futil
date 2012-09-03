package org.meth4j.futil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.google.common.primitives.Ints;

public class Validate {
	
	public static Integer integer(String value) {
		Integer num = Ints.tryParse(value.toString());
		if (num == null || num < 1) {
			throw new ValidatorException(CommonKey.ERROR_INTEGER);
		}
		return num;
	}
	
	public static Integer positive(Integer value) {
		if (value < 1) {
			throw new ValidatorException(CommonKey.ERROR_POSITIVE_NATURAL_NUMBER);
		}
		return value;
	}
	
	public static final void required(String value) throws ValidatorException {
		if (value == null || value.isEmpty()) {
			throw new ValidatorException(CommonKey.ERROR_REQUIRED);
		}
	}
	
	public static final void maxLength(String value, int maxLength) throws ValidatorException {
		if (value != null) {
			if (value.length() > maxLength) {
				throw new ValidatorException(CommonKey.ERROR_TOO_LONG);
			}
		}
	}
	
	public static final void minLength(String value, int minLength) throws ValidatorException {
		if (value != null) {
			if (value.length() < minLength) {
				throw new ValidatorException(CommonKey.ERROR_TOO_SHORT);
			}
		}
	}
	
	public static final void emailPattern(String value) throws ValidatorException {
		if (value != null) {
			try {
				InternetAddress ia = new InternetAddress(value);
				ia.validate();
			} catch (AddressException e) {
				throw new ValidatorException(CommonKey.ERROR_EMAIL_PATTERN);
			}
		}
	}
	
}
