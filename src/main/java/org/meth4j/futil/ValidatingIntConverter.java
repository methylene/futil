package org.meth4j.futil;



public abstract class ValidatingIntConverter extends ValidatingConverter {

	@Override protected Object convert(String value) throws ValidatorException {
		return Integer.valueOf(value);
	}

}
