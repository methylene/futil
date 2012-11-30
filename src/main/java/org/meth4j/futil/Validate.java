package org.meth4j.futil;

import static org.meth4j.futil.CommonKey.ERROR_INTEGER;
import static org.meth4j.futil.CommonKey.ERROR_POSITIVE_NATURAL_NUMBER;
import static org.meth4j.futil.CommonKey.ERROR_REQUIRED;
import static org.meth4j.futil.CommonKey.ERROR_TOO_LONG;
import static org.meth4j.futil.CommonKey.ERROR_TOO_SHORT;
import static org.meth4j.futil.Message.errMesg;

import javax.faces.validator.ValidatorException;

/**
 * All methods in this class throw a {@code javax.faces.validator.ValidatorException} in case of errors.
 */
public final class Validate {

	/** This class has no instances. */
	private Validate() {
	}

	/**
	 * Validate that {@code value} can be parsed as an {@code int} (base 10). {@code null} input is not allowed. 
	 * Effectively this method does the same as {@code java.lang.Integer.parseInt(value)}, 
	 * except that a {@code javax.faces.validator.ValidatorException} is thrown instead of a {@code java.lang.NumberFormatException}
	 * in case of a parsing error.
	 * @param value
	 * @return The result of parsing {@code value} as an {@code int}, if possible. Otherwise an exception is thrown.
	 */
	public static int parseInt(final String value) {
		try {
			return Integer.parseInt(value);
		} catch (final RuntimeException e) {
			throw new ValidatorException(errMesg(ERROR_INTEGER, value));
		}
	}

	/**
	 * Validate that {@code value} is greater than zero.
	 * @param value
	 * @return value, if validation succeeds. Otherwise an exception is thrown.
	 */
	public static int positiveInt(final int value) {
		if (value < 1) {
			throw new ValidatorException(errMesg(ERROR_POSITIVE_NATURAL_NUMBER, Integer.valueOf(value)));
		} else {
			return value;
		}
	}

	/**
	 * Validate that {@code value} is greater than or equal to zero.
	 * @param value
	 * @return value, if validation succeeds. Otherwise an exception is thrown.
	 */
	public static int notNegativeInt(final int value) {
		if (value < 0) {
			throw new ValidatorException(errMesg(ERROR_POSITIVE_NATURAL_NUMBER, Integer.valueOf(value)));
		} else {
			return value;
		}
	}

	/**
	 * Validate that value is not null.
	 * @param value
	 * @return {@code value.toString()}, if validation succeeds. Otherwise an exception is thrown.
	 */
	public static String notNullString(final Object value) {
		if (value == null) {
			throw new ValidatorException(errMesg(ERROR_REQUIRED, value));
		} else {
			return value.toString();
		}
	}
	
	/**
	 * Returns {@code value.toString()}, or {@code null} if {@code value} is {@code null}.
	 * @param value
	 * @return {@code value.toString()}, if {@code value != null}, or {@code null} if {@code value == null}.
	 */
	public static String toStringNoEx(final Object value) {
		if (value == null) {
			return null;
		} else {
			return value.toString();
		}
	}

	/**
	 * Validate that value is non-null, is an instance of java.lang.String, and is not empty.
	 * @param value
	 * @return value, if validation succeeds. Otherwise an exception is thrown.
	 */
	public static String nonemptyString(final Object value) {
		final String s = notNullString(value);
		if (s.isEmpty()) {
			throw new ValidatorException(errMesg(ERROR_REQUIRED, value));
		} else {
			return s;
		}
	}

	/**
	 * Validate that value is non-null, is an instance of java.lang.String, and is not empty after {@code value.trim()}.
	 * @param value
	 * @return {@code value.trim()}, if validation succeeds. Otherwise an exception is thrown.
	 */
	public static String nonemptyTrimmedString(final Object value) {
		final String s = notNullString(value);
		final String trimmed = s.trim();
		if (trimmed.isEmpty()) {
			throw new ValidatorException(errMesg(ERROR_REQUIRED, value));
		} else {
			return trimmed;
		}
	}


	/**
	 * Validate length of {@code value}.
	 * {@code value == null} is only allowed if {@code minLength == 0}.
	 * @param value
	 * @param minLength must not be negative
	 * @param maxLength
	 * @return value, if validations succeeds. Otherwise an exception is thrown.
	 */
	public static String checkLength(final String value, final int minLength, final int maxLength) {
		assert minLength >= 0 && maxLength >= 0 : "arguments minLength and maxLength must not be negative";
		assert minLength <= maxLength : "argument minLength must not be greater than argument maxLength";
		if (value != null) {
			final int length = value.length();
			if (length < minLength) {
				throw new ValidatorException(errMesg(ERROR_TOO_SHORT, value == null ? "" : value, Integer.valueOf(minLength), Integer.valueOf(maxLength)));
			} else if (length > maxLength) {
				throw new ValidatorException(errMesg(ERROR_TOO_LONG, value == null ? "" : value, Integer.valueOf(minLength), Integer.valueOf(maxLength)));
			} else {
				return value;
			}
		} else {
			if (minLength > 0) {
				throw new ValidatorException(errMesg(ERROR_TOO_SHORT, value == null ? "" : value, Integer.valueOf(minLength), Integer.valueOf(maxLength)));
			} else {
				return value;
			}
		}
	}

	/**
	 * Validate length of {@code value}.
	 * {@code value == null} is only allowed if {@code minLength == 0}.
	 * @param value
	 * @param minLength
	 * @return value, if validations succeeds. Otherwise an exception is thrown.
	 */
	public static String checkMinLength(final String value, final int minLength) {
		assert minLength >= 0 : "argument minLength must not be negative";
		return checkLength(value, minLength, Integer.MAX_VALUE);
	}

	/**
	 * Validate length of {@code value}.
	 * {@code value == null} is allowed.
	 * @param value
	 * @param minLength
	 * @return value, if validations succeeds. Otherwise an exception is thrown.
	 */
	public static String checkMaxLength(final String value, final int maxLength) {
		assert maxLength >= 0 : "argument maxLength must not be negative";
		return checkLength(value, 0, maxLength);
	}

}
