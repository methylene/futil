package org.meth4j.futil;

import static com.google.common.primitives.Ints.tryParse;
import static org.meth4j.futil.CommonKey.ERROR_INTEGER;
import static org.meth4j.futil.CommonKey.ERROR_POSITIVE_NATURAL_NUMBER;
import static org.meth4j.futil.CommonKey.ERROR_REQUIRED;
import static org.meth4j.futil.Message.errMesg;

import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validate {
	
	public static Integer integer(String value) {
		final Integer num = tryParse(value);
		if (num == null || num < 1) {
			throw new ValidatorException(errMesg(ERROR_INTEGER));
		} else {
			return num;
		}
	}
	
	public static Integer positive(Integer value) {
		if (value < 1) {
			throw new ValidatorException(errMesg(ERROR_POSITIVE_NATURAL_NUMBER));
		} else {
			return value;
		}
	}
	
	public static final String requiredString(Object value) throws ValidatorException {
		if (value == null || value.toString().isEmpty()) {
			throw new ValidatorException(errMesg(ERROR_REQUIRED));
		} else {
			return value.toString();
		}
	}
	
	public static final void required(String value) throws ValidatorException {
		if (value == null || value.isEmpty()) {
			throw new ValidatorException(errMesg(CommonKey.ERROR_REQUIRED));
		}
	}
	
	public static final void maxLength(String value, int maxLength) throws ValidatorException {
		if (value != null) {
			if (value.length() > maxLength) {
				throw new ValidatorException(errMesg(CommonKey.ERROR_TOO_LONG));
			}
		}
	}
	
	public static final void minLength(String value, int minLength) throws ValidatorException {
		if (value != null) {
			if (value.length() < minLength) {
				throw new ValidatorException(errMesg(CommonKey.ERROR_TOO_SHORT));
			}
		}
	}
	
	public static final void emailPattern(String value) throws ValidatorException {
		if (value != null) {
			try {
				final InternetAddress ia = new InternetAddress(value);
				ia.validate();
			} catch (final AddressException e) {
				throw new ValidatorException(errMesg(CommonKey.ERROR_EMAIL_PATTERN));
			}
		}
	}
	
}
