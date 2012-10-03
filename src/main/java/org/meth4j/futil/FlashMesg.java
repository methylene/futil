package org.meth4j.futil;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

public class FlashMesg implements Serializable {

	private static final long serialVersionUID = 1L;

	private static enum Severity {
		INFO, WARNING, TERROR
	}

	private final IKey key;
	private final Severity severity;

	private FlashMesg(IKey key, Severity severity) {
		super();
		this.key = key;
		this.severity = severity;
	}

	public static FlashMesg info(IKey key) {
		return new FlashMesg(key, Severity.INFO);
	}

	public static FlashMesg warn(IKey key) {
		return new FlashMesg(key, Severity.WARNING);
	}

	public static FlashMesg error(IKey key) {
		return new FlashMesg(key, Severity.TERROR);
	}

	public FacesMessage toFacesMessage() {
		switch (severity) {
		case INFO:
			return Message.info(key);
		case WARNING:
			return Message.warn(key);
		case TERROR:
			return Message.error(key);
		default:
			throw new IllegalStateException();
		}
	}

}
