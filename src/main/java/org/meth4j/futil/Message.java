package org.meth4j.futil;

import java.io.Serializable;

import com.google.common.base.Preconditions;

public class Message implements Serializable {

	public static enum Severity {
		
//		@formatter:off
		ERROR,
		WARN,
		INFO,
		INVALID_FIELDS,
;//		@formatter:on
		
	}

	private static final long serialVersionUID = 2L;

	private final IKey message;
	private final Severity severity;

	private Message(IKey message, Severity severity) {
		super();
		Preconditions.checkNotNull(message);
		Preconditions.checkNotNull(severity);
		this.message = message;
		this.severity = severity;
	}

	public String getSeverity() {
		return severity.name().toLowerCase();
	}

	public static final Message info(IKey message) {
		return new Message(message, Severity.INFO);
	}

	public static final Message warn(IKey message) {
		return new Message(message, Severity.WARN);
	}

	public static final Message error(IKey message) {
		return new Message(message, Severity.ERROR);
	}
	
	public static final Message invalidFields(IKey message) {
		return new Message(message, Severity.INVALID_FIELDS);
	}

	public String getMessage() {
		return message.getLabel();
	}

}
