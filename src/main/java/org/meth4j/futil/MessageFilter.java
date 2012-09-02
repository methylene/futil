package org.meth4j.futil;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class MessageFilter implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		Messages.clearMessage(r);
	}

	@Override public void beforePhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		Messages.restoreMessage(r);
	}

	@Override public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
