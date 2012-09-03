package org.meth4j.futil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticResourcesFilter implements Filter {
	
	private static final long ONE_DAY_SECONDS = 60 * 60 * 24;
	private static final long ONE_DAY_MILLIS = 1000 * ONE_DAY_SECONDS;
	private static final long SEVERAL_DAYS_SECONDS = ONE_DAY_SECONDS * 5;
	private static final long SEVERAL_DAYS_MILLIS = ONE_DAY_MILLIS * 5;
	
	private static final Logger LOG = LoggerFactory.getLogger(StaticResourcesFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		chain.doFilter(req, resp);
		response.setHeader("Expires", htmlExpiresDateFormat().format(new Date(System.currentTimeMillis() + SEVERAL_DAYS_MILLIS)));
		response.setHeader("Cache-Control", "PUBLIC, max-age=" + SEVERAL_DAYS_SECONDS);
	}
	
	public static SimpleDateFormat htmlExpiresDateFormat() {
		final SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return httpDateFormat;
	}
	
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOG.info("StaticResourcesFilter starting up...");
	}

	public void destroy() {
		LOG.info("StaticResourcesFilter shutting down...");
	}	
	

}
