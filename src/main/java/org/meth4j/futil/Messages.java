package org.meth4j.futil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Messages {

	private static final String MESSAGE = "org.meth4j.futil.message";
	private static final String BUNDLE_NAME = ResourceLoader.loadProperties(
			"/META-INF/services/org.meth4j.futil.properties").getProperty("bundle_name", "org.meth4j.futil.messages");

	public static final void add(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static ResourceBundle getBundle() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		Locale l = r.getLocale();
		return ResourceBundle.getBundle(BUNDLE_NAME, l);
	}

	public static void flash(FacesMessage message, HttpServletRequest r) {
		r.getSession().setAttribute(MESSAGE, message);
	}

	public static void flash(FacesMessage message) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
		flash(message, r);
	}

	public static FacesMessage restoreMessage(HttpServletRequest r) {
		HttpSession s = r.getSession();
		FacesMessage message = (FacesMessage) s.getAttribute(MESSAGE);
		if (message != null) {
			add(message);
		}
		return message;
	}

	public static final void clearMessage(HttpServletRequest r) {
		r.getSession().removeAttribute(MESSAGE);
	}

}
