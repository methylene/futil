package org.meth4j.futil;

import static javax.faces.context.FacesContext.getCurrentInstance;

import static org.meth4j.futil.ResourceLoader.loadProperties;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Messages {

	private static final String MESSAGE = "org.meth4j.futil.message";
	
//	@formatter:off
	private static final String BUNDLE_NAME = loadProperties(
			"/META-INF/services/org.meth4j.futil.properties")
			.getProperty("bundle_name", "org.meth4j.futil.messages");
//	@formatter:off

	public static final void addMesg(FacesMessage message) {
		getCurrentInstance().addMessage(null, message);
	}

	public static ResourceBundle getBundle() {
		final FacesContext fc = getCurrentInstance();
		final ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		final Locale l = r.getLocale();
		return ResourceBundle.getBundle(BUNDLE_NAME, l);
	}

	public static void flashMesg(FlashMesg message, HttpServletRequest r) {
		r.getSession().setAttribute(MESSAGE, message);
	}

	public static void flashMesg(FlashMesg message) {
		final FacesContext fc = getCurrentInstance();
		final HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
		flashMesg(message, r);
	}

	static FacesMessage restoreMesg(HttpServletRequest r) {
		final HttpSession s = r.getSession();
		final FlashMesg message = (FlashMesg) s.getAttribute(MESSAGE);
		if (message != null) {
			final FacesMessage facesMessage = message.toFacesMessage();
			addMesg(facesMessage);
			return facesMessage;
		} else {
			return null;
		}
	}

	static final void clearMesg(HttpServletRequest r) {
		r.getSession().removeAttribute(MESSAGE);
	}

}
