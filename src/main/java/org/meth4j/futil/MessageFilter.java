package org.meth4j.futil;

import static javax.faces.event.PhaseId.RESTORE_VIEW;
import static org.meth4j.futil.Messages.clearMesg;
import static org.meth4j.futil.Messages.restoreMesg;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class MessageFilter implements PhaseListener {

	private static final long serialVersionUID = 14L;
	
	@Override public void afterPhase(PhaseEvent event) {
		final FacesContext fc = event.getFacesContext();
		final ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		clearMesg(r);
	}

	@Override public void beforePhase(PhaseEvent event) {
		final FacesContext fc = event.getFacesContext();
		final ExternalContext ec = fc.getExternalContext();
		final HttpServletRequest r = (HttpServletRequest) ec.getRequest();
		restoreMesg(r);
	}

	@Override public PhaseId getPhaseId() {
		return RESTORE_VIEW;
	}

}
