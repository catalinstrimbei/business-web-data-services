package org.app.scrum.ejb;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.app.scrum.rest.CredentialBean;

public class SecurityInterceptor implements Serializable{
	private static Logger logger = Logger.getLogger(SecurityInterceptor.class.getName());
	
	@Inject
    private CredentialBean creds;
	
	@Resource
    protected SessionContext sessionCtx;	

	@AroundInvoke
	private Object interceptorAction(InvocationContext ctx) throws Exception{
		logger.info("DEBUG: SecurityInterceptor.creds =  " + creds + "\n");
		
		Principal principal = sessionCtx.getCallerPrincipal();
		if (principal != null) {
			logger.info("DEBUG: principal.name =  " + principal.getName() + "\n");
		}
		
		return ctx.proceed();
	}
}
