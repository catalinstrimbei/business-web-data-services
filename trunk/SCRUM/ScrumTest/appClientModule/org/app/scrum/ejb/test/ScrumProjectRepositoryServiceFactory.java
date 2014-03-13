package org.app.scrum.ejb.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.ejb.ScrumProjectRepositoryEJB;
import org.app.scrum.ejb.ScrumProjectRepositoryService;

public class ScrumProjectRepositoryServiceFactory {
	public static ScrumProjectRepositoryService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
//		return (ScrumProjectRepositoryService) getEJBReference(MODULE_NAME, 
//				ScrumProjectRepositoryEJB.class.getSimpleName(), 
//				ScrumProjectRepositoryService.class.getName(),
//				null);
	}
	
	private static String MODULE_NAME = "ScrumEJB";
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        
//        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
//        jndiProperties.put("remote.connections", "default");
//        jndiProperties.put("remote.connection.default.host", "localhost");
//        jndiProperties.put("remote.connection.default.port", "4447");
//        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        
        return (T) context.lookup("ejb:/ScrumEJB/ScrumProjectRepositoryEJB!org.app.scrum.ejb.ScrumProjectRepositoryService");
//        return (T) context.lookup("ejb:/ScrumEJB/ScrumEJB.jar//ScrumProjectRepositoryEJB!org.app.scrum.ejb.ScrumProjectRepositoryService");
//        return (T) context.lookup("ejb:/ScrumProjectRepositoryService/remote");
    }		
    
    
    public static Object getEJBReference(String moduleName, String className, String itfName, String server_ip) throws NamingException {
		if (server_ip == null || server_ip.equals("")){
			server_ip = "localhost";
		}
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        jndiProperties.put("remote.connections", "default");
        jndiProperties.put("remote.connection.default.host", "localhost");
        jndiProperties.put("remote.connection.default.port", "4447");
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        
		jndiProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
//		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//		jndiProperties.put(Context.PROVIDER_URL,"remote://" + server_ip + ":4447");
		
		final Context context = new InitialContext(jndiProperties);
		// naming
		final String app = "";
		final String module = moduleName;
		final String distinctName = "";
		final String bean = className;
		final String itf = itfName;
		// get EJB remote ref
		return context.lookup("ejb:" + app + "/"
				+ module + "/" + distinctName + "/" + bean + "!" + itf);
	}    
    
    
    private static <T> T old_lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        jndiProperties.put("remote.connections", "default");
        jndiProperties.put("remote.connection.default.host", "localhost");
        jndiProperties.put("remote.connection.default.port", "4447");
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
        
        
//        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "guest");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "password");
        
//        logger.info(Context.URL_PKG_PREFIXES);
        final Context context = new InitialContext(jndiProperties);
//        logger.info("LOOKUP FOR OpenERP_PROMAN/ProjectManagementImpl!org.open.erp.services.proman.ProjectManagementSrv");
//        return (T) context.lookup("ejb:/ScrumEJB/ScrumProjectRepositoryEJB!org.app.scrum.ejb.ScrumProjectRepositoryService");
        return (T) context.lookup("ejb:/jboss/exported/ScrumEJB/ScrumProjectRepositoryEJB!org.app.scrum.ejb.ScrumProjectRepositoryService");
    }		    
}
/*
remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED=false
remote.connections=default
remote.connection.default.host=localhost
remote.connection.default.port = 4447
remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS=false
*/