package org.meth4j.futil;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

public class Messages {

	private static final String MESSAGE = "org.meth4j.futil.message";
	private static final String BUNDLE_NAME = "org.meth4j.futil.messages";

	public static final void add(Message message, HttpServletRequest r) {
		if (getMessage(r) != null) {
			LoggerFactory.getLogger(Messages.class).warn("add: Overwriting existing message {} with {}",
					getMessage(r).toString(), message.toString());
		}
		r.setAttribute(MESSAGE, message);
	}

	public static ResourceBundle getBundle() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		Locale l = r.getLocale();
		return ResourceBundle.getBundle(BUNDLE_NAME, l);
	}

	public static void add(Message message) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
		add(message, r);
	}

	public static void flash(Message message, HttpServletRequest r) {
		r.getSession().setAttribute(MESSAGE, message);
	}

	public static void flash(Message message) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
		flash(message, r);
	}

	private static Message getMessage(HttpServletRequest r) {
		return (Message) r.getAttribute(MESSAGE);
	}

	public static Message restoreMessage(HttpServletRequest r) {
		HttpSession s = r.getSession();
		Message message = (Message) s.getAttribute(Messages.MESSAGE);
		if (s != null) {
			add(message, r);
		}
		return message;
	}

	public static final void clearMessage(HttpServletRequest r) {
		r.getSession().removeAttribute(MESSAGE);
	}

}
