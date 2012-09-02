package org.meth4j.futil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.meth4j.futil.Messages;
import org.slf4j.LoggerFactory;

public class MessageFilter implements Filter {

	public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest r = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;
		final String servletPath = r.getServletPath();
		if (servletPath.startsWith("/javax.faces.resource")) {
			chain.doFilter(req, resp);
		} else {
			Messages.restoreMessage(r);
			chain.doFilter(req, resp);
			if (response.getStatus() == HttpServletResponse.SC_OK) {
				Messages.clearMessage(r);
			}
		}
	}

	public void init(final FilterConfig filterConfig) throws ServletException {
		LoggerFactory.getLogger(getClass()).info("{} starting up...", getClass().getSimpleName());
	}

	public void destroy() {
		LoggerFactory.getLogger(getClass()).info("{} shutting down...", getClass().getSimpleName());
	}

}
