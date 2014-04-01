package org.app.scrum.ejb;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.app.scrum.rest.CredentialBean;

public class SecurityInterceptor {
	private static Logger logger = Logger.getLogger(SecurityInterceptor.class.getName());
	
	@Inject
    private CredentialBean creds;	

	@AroundInvoke
	private Object interceptorAction(InvocationContext ctx) throws Exception{
		logger.info("DEBUG: SecurityInterceptor.creds =  " + creds + "\n");
		
		
		return ctx.proceed();
	}
}
