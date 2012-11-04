package org.meth4j.futil;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

public abstract class ValidatingConverter implements Validator {

	private static final String ERRORS = "org.meth4j.futil.errors";
	private static final String ATTRIBUTE = "org.meth4j.futil.attribute";

	protected abstract void validate(FacesContext facesContext, UIComponent component, Object value,
			UIComponent attribute) throws ValidatorException;

	@Override public final void validate(FacesContext fc, UIComponent component, Object value) {
		try {
			validate(fc, component, value, (UIComponent) component.getAttributes().get(ATTRIBUTE));
		} catch (final ValidatorException e) {
			final HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
			@SuppressWarnings("unchecked")
			Map<String, Integer> errors = (Map<String, Integer>) r.getAttribute(ERRORS);
			if (errors == null) {
				errors = new HashMap<String, Integer>();
				r.setAttribute(ERRORS, errors);
			}
			errors.put(component.getClientId(), 1);
			throw e;
		}
	}

}