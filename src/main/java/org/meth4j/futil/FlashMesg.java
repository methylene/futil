package org.meth4j.futil;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.meth4j.futil.Message.errMesg;
import static org.meth4j.futil.Message.infoMesg;
import static org.meth4j.futil.Message.warnMesg;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

public class FlashMesg implements Serializable {

	private static final long serialVersionUID = 14L;

	private static enum Severity {
		INFO, WARNING, TERROR
	}

	private final IKey key;
	private final Object[] params;
	private final Severity severity;

	private FlashMesg(final IKey key, final Severity severity, final Object[] params) {
		super();
		this.key = key;
		this.severity = severity;
		if (params == null || params.length == 0) {
			this.params = null;
		} else {
			this.params = params;
		}
	}

	public static FlashMesg flashInfo(final IKey key, final Object... params) {
		checkNotNull(key);
		return new FlashMesg(key, Severity.INFO, params);
	}

	public static FlashMesg flashWarn(final IKey key, final Object... params) {
		checkNotNull(key);
		return new FlashMesg(key, Severity.WARNING, params);
	}

	public static FlashMesg flashErr(final IKey key, final Object... params) {
		checkNotNull(key);
		return new FlashMesg(key, Severity.TERROR, params);
	}

	public FacesMessage toFacesMessage() {
		switch (severity) {
		case INFO:
			return infoMesg(key, getParams());
		case WARNING:
			return warnMesg(key, getParams());
		case TERROR:
			return errMesg(key, getParams());
		default:
			throw new IllegalStateException();
		}
	}

	public Object[] getParams() {
		if (params == null) {
			return null;
		} else {
			final Object[] copy = new Object[params.length];
			System.arraycopy(params, 0, copy, 0, params.length);
			return copy;
		}
	}

	public IKey getKey() {
		return key;
	}

	public Severity getSeverity() {
		return severity;
	}

}
