package org.app.scrum.rest;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.app.scrum.ejb.ScrumProjectDataServiceEJB;
import org.app.scrum.ejb.ScrumTeamDataServiceEJB;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

//@Provider
//@ServerInterceptor
public class LoggingInterceptor implements PreProcessInterceptor, Serializable {
    Logger logger = Logger.getLogger(LoggingInterceptor.class);

    @Context
    HttpServletRequest servletRequest;
    
    @Inject
    private CredentialBean creds;

//    @Resource
//    SessionContext ctx;    
    
//    @Resource
//    private TransactionSynchronizationRegistry tsr;    
    
//    @EJB
//    private ScrumProjectDataServiceEJB scrumEJB;
    
//    @EJB
//	private ScrumTeamDataServiceEJB teamRepository;    
    
    public ServerResponse preProcess(HttpRequest request,
            ResourceMethod resourceMethod) throws Failure,
            WebApplicationException {

        String methodName = resourceMethod.getMethod().getName();
        logger.info("DEBUG Receiving request from: " + servletRequest.getRemoteAddr());
        logger.info("DEBUG Attempt to invoke method \"" + methodName + "\"");
        /*
        if (methodName.equals("calculateAllBasicTrigonometricFunctions")) {
            logger.info("\tCalculate will be performed with parameters:");
            logger.info("\tAdjacent: "
                    + request.getFormParameters().get("adjacent"));
            logger.info("\tOpposite: "
                    + request.getFormParameters().get("opposite"));
            logger.info("\tHypotenusa: "
                    + request.getFormParameters().get("hypotenusa"));
        }
        if (methodName.equals("history")) {
            logger.info("Retrieving history...");
        }
        if (methodName.equals("clearAll")) {
            logger.info("User " + servletRequest.getRemoteUser()
                    + " is trying to clear the history...");
        }
        */
//        ctx.getClass();
//        resourceMethod.getMethod().
//        logger.info("DEBUG TransactionSynchronizationRegistry is " + tsr + "\"");
//        logger.info("DEBUG teamRepository is " + teamRepository + "\"");
        
        // if(request.getPreprocessedPath().startsWith("/secure")){}
        // perhaps you will limit it to a special path

        // Then get the HTTP-Authorization header and base64 decode it
        // request.getHttpHeaders().getRequestHeader("Authorization");

        // check whatever you want with your EJB
        // if it fails, 
        // throw new UnauthorizedException("Username/Password does not match");
        
        logger.info("DEBUG creds is " + creds + "\"");
        if(creds != null)
        	creds.setCreds("test-User");
        
        return null;
    }
}

