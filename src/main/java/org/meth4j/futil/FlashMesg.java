package org.meth4j.futil;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.meth4j.futil.Message.errMesg;
import static org.meth4j.futil.Message.infoMesg;
import static org.meth4j.futil.Message.warnMesg;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

public class FlashMesg implements Serializable {

	private static final long serialVersionUID = 13L;

	private static enum Severity {
		INFO, WARNING, TERROR
	}

	private final IKey key;
	private final Object[] params;
	private final Severity severity;

	private FlashMesg(IKey key, Severity severity, Object[] params) {
		super();
		this.key = key;
		this.severity = severity;
		if (params == null || params.length == 0) {
			this.params = null;	
		} else {
			this.params = params;
		}
	}

	public static FlashMesg flashInfo(IKey key, Object... params) {
		checkNotNull(key);
		return new FlashMesg(key, Severity.INFO, params);
	}

	public static FlashMesg flashWarn(IKey key, Object... params) {
		checkNotNull(key);
		return new FlashMesg(key, Severity.WARNING, params);
	}

	public static FlashMesg flashErr(IKey key, Object... params) {
		checkNotNull(key);
		return new FlashMesg(key, Severity.TERROR, params);
	}

	public FacesMessage toFacesMessage() {
		switch (severity) {
		case INFO:
			return infoMesg(key, params);
		case WARNING:
			return warnMesg(key, params);
		case TERROR:
			return errMesg(key, params);
		default:
			throw new IllegalStateException();
		}
	}

	public Object[] getParams() {
		return params;
	}

}
