package org.meth4j.futil;

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

}
