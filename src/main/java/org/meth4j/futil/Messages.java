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
		final FacesContext fc = FacesContext.getCurrentInstance();
		final ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		final Locale l = r.getLocale();
		return ResourceBundle.getBundle(BUNDLE_NAME, l);
	}

	public static void flash(FlashMesg message, HttpServletRequest r) {
		r.getSession().setAttribute(MESSAGE, message);
	}

	public static void flash(FlashMesg message) {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
		flash(message, r);
	}

	public static FacesMessage restoreMessage(HttpServletRequest r) {
		final HttpSession s = r.getSession();
		final FlashMesg message = (FlashMesg) s.getAttribute(MESSAGE);
		if (message != null) {
			final FacesMessage facesMessage = message.toFacesMessage();
			add(facesMessage);
			return facesMessage;
		} else {
			return null;
		}
	}

	public static final void clearMessage(HttpServletRequest r) {
		r.getSession().removeAttribute(MESSAGE);
	}

}
