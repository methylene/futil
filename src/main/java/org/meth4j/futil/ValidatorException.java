package org.meth4j.futil;


public class ValidatorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final IKey key;

	public ValidatorException(IKey key) {
		super();
		this.key = key;
	}

	public IKey getKey() {
		return key;
	}
	

}
