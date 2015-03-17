package org.app.scrum.rest;

import java.io.Serializable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethodInvoker;
//import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

//@Provider
//@ServerInterceptor
public class AuthorizationRestInterceptor implements PreProcessInterceptor, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(AuthorizationRestInterceptor.class);

    @Context
    HttpServletRequest servletRequest;
    
    @Inject
    private CredentialBean creds;
    
    /*
    public ServerResponse preProcess(HttpRequest request,
            ResourceMethod resourceMethod) throws Failure,
            WebApplicationException {

        String methodName = resourceMethod.getMethod().getName();
        logger.info("DEBUG Receiving request from: " + servletRequest.getRemoteAddr());
        logger.info("DEBUG Attempt to invoke method \"" + methodName + "\"");
        logger.info("DEBUG creds is " + creds + "\"");
        if(creds != null){
        	creds.setUsername("test-user");
        	creds.setCreds("test-pass");
        }
        return null;
    }
	*/
    
	@Override
	public ServerResponse preProcess(HttpRequest req,
			ResourceMethodInvoker rmi) throws Failure, WebApplicationException {
        String methodName = rmi.getMethod().getName();
        logger.info("DEBUG Receiving request from: " + servletRequest.getRemoteAddr());
        logger.info("DEBUG Attempt to invoke method \"" + methodName + "\"");
        logger.info("DEBUG creds is " + creds + "\"");
        if(creds != null){
        	creds.setUsername("test-user");
        	creds.setCreds("test-pass");
        }
        return null;
	}

}

/*
	<persistence-unit name="targetPG">
		<jta-data-source>java:/PGFeaaMSD</jta-data-source>
		
        <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
        <exclude-unlisted-classes>true</exclude-unlisted-classes>
        <properties>
            <property name="eclipselink.weaving" value="static"/>
        </properties>
    </persistence-unit>	
    
	<persistence-unit name="targetORCL">
		<jta-data-source>java:/ORCLFeaadmScrum</jta-data-source>
		
        <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
        <exclude-unlisted-classes>true</exclude-unlisted-classes>
        <properties>
            <property name="eclipselink.weaving" value="static"/>
        </properties>
    </persistence-unit>
*/
