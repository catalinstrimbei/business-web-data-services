package org.app.scrum.ejb.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.ejb.ScrumProjectRepositoryService;

public class ScrumProjectRepositoryServiceFactory {
	public static ScrumProjectRepositoryService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        jndiProperties.put("remote.connections", "default");
        jndiProperties.put("remote.connection.default.host", "localhost");
        jndiProperties.put("remote.connection.default.port", "4447");
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        logger.info(Context.URL_PKG_PREFIXES);
        final Context context = new InitialContext(jndiProperties);
//        logger.info("LOOKUP FOR OpenERP_PROMAN/ProjectManagementImpl!org.open.erp.services.proman.ProjectManagementSrv");
        return (T) context.lookup("ejb:/ScrumEJB/ScrumProjectRepositoryEJB!org.app.scrum.ejb.ScrumProjectRepositoryService");
    }		
}
