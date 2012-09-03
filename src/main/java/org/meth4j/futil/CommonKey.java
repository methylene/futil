package org.meth4j.futil;

public enum CommonKey implements IKey {
	
//	@formatter:off
	ERROR_INTEGER,
	ERROR_POSITIVE_NATURAL_NUMBER,
	ERROR_GENERAL,
	ERROR_TOO_LARGE, 
	ERROR_REQUIRED, 
	ERROR_TOO_LONG, 
	ERROR_TOO_SHORT, 
	ERROR_EMAIL_PATTERN,
;//	@formatter:on
	

	@Override public String getLabel() {
		return Messages.getBundle().getString(getKey());
	}

	@Override public String getKey() {
		return name().toLowerCase();
	}
}
