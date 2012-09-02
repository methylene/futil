package org.meth4j.futil;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.servlet.http.HttpServletRequest;

public abstract class ValidatingConverter implements Converter {

	private static final String ERRORS = "org.meth4j.futil.errors";
	private static final String ATTRIBUTE = "org.meth4j.futil.attribute";

	protected abstract void validate(FacesContext facesContext, UIComponent component, String value,
			UIComponent attribute) throws ValidatorException;

	protected abstract Object convert(String value) throws ValidatorException;

	@Override public final String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return null;
		} else {
			return arg2.toString();
		}
	}

	@Override public final Object getAsObject(FacesContext fc, UIComponent component, String value) {
		try {
			validate(fc, component, value, (UIComponent) component.getAttributes().get(ATTRIBUTE));
			return convert(value);
		} catch (ValidatorException e) {
			IKey key = e.getKey();
			HttpServletRequest r = (HttpServletRequest) fc.getExternalContext().getRequest();
			@SuppressWarnings("unchecked")
			Map<String, Integer> errors = (Map<String, Integer>) r.getAttribute(ERRORS);
			if (errors == null) {
				errors = new HashMap<String, Integer>();
				r.setAttribute(ERRORS, errors);
			}
			errors.put(component.getClientId(), 1);
			FacesMessage msg = new FacesMessage(key.getLabel());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
	}

}