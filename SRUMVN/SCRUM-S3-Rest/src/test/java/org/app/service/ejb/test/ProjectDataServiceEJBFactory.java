package org.app.service.ejb.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.service.ejb.ProjectDataService;
import org.app.service.ejb.ProjectDataServiceEJB;

public class ProjectDataServiceEJBFactory {
	private static Logger logger = Logger.getLogger(ProjectDataServiceEJBFactory.class.getName());
	
	public static ProjectDataService getService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "SCRUM-S3";
	private static String SERVICE_NAME = ProjectDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ProjectDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}