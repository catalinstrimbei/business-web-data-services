package org.app.scrum.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebFilter(urlPatterns="*")
public class CORSFilter implements Filter{
	private static final Logger logger = Logger.getLogger(CORSFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		// CORS headers
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpResponse.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		String reqHead = httpRequest.getHeader("Access-Control-Request-Headers");
		if(null != reqHead){
			httpResponse.addHeader("Access-Control-Allow-Headers", reqHead);
        }
		logger.info("CORSFilter added headers");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("CORSFilter init");
	}

}
