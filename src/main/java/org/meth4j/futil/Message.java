package org.meth4j.futil;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

public class Message implements Serializable {

	private Message() {
	}

	private static final long serialVersionUID = 2L;

	public static final FacesMessage info(IKey message) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, message.getLabel(), message.getLabel());
	}

	public static final FacesMessage warn(IKey message) {
		return new FacesMessage(FacesMessage.SEVERITY_WARN, message.getLabel(), message.getLabel());
	}

	public static final FacesMessage error(IKey message) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, message.getLabel(), message.getLabel());
	}

}
