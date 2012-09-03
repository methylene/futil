package org.meth4j.futil;



public abstract class ValidatingStringConverter extends ValidatingConverter {

	@Override protected Object convert(String value) throws ValidatorException {
		return value;
	}

}
