package org.meth4j.futil;

import static javax.faces.context.FacesContext.getCurrentInstance;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Messages {

	private static final String SESSION_ATTR_MESSAGE = "org.meth4j.futil.session.message";

//	@formatter:off
	private static final String BUNDLE_NAME = loadProperties(
			"/META-INF/services/org.meth4j.futil.properties")
			.getProperty("bundle_name", "org.meth4j.futil.messages");
//	@formatter:off

	public static final void addMesg(final FacesMessage message) {
		getCurrentInstance().addMessage(null, message);
	}

	public static ResourceBundle getBundle() {
		final FacesContext fc = getCurrentInstance();
		final ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		final Locale l = r.getLocale();
		return ResourceBundle.getBundle(BUNDLE_NAME, l);
	}

	public static void flashMesg(final FlashMesg message, final HttpServletRequest r) {
		r.getSession().setAttribute(SESSION_ATTR_MESSAGE, message);
	}

	public static void flashMesg(final FlashMesg message) {
		final FacesContext fc = getCurrentInstance();
		final HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
		flashMesg(message, r);
	}

	static FacesMessage restoreMesg(final HttpServletRequest r) {
		final HttpSession s = r.getSession();
		if (s != null) {
			final FlashMesg message = (FlashMesg) s.getAttribute(SESSION_ATTR_MESSAGE);
			if (message != null) {
				final FacesMessage facesMessage = message.toFacesMessage();
				addMesg(facesMessage);
				return facesMessage;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	static void clearMesg(final HttpServletRequest r) {
		r.getSession().removeAttribute(SESSION_ATTR_MESSAGE);
	}
	
	private static Properties loadProperties(final String name) {
		final Properties props = new Properties();
		InputStream in = null;
		try {
			in = Messages.class.getResourceAsStream(name);
			if (in != null) {
				props.load(in);
				return props;
			} else {
				return new Properties();
			}
		} catch (final Exception e) {
			getLogger(Messages.class).error("error",e);
			return new Properties();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final IOException e) {
					getLogger(Messages.class).error("error",e);
				}
			}
		}
	}

}
