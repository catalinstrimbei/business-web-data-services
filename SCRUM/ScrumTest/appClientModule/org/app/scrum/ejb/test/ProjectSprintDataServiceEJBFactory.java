package org.app.scrum.ejb.test;

import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.services.ProjectSprintDataService;
import org.app.scrum.services.ProjectSprintDataServiceEJB;

public class ProjectSprintDataServiceEJBFactory {
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceEJBFactory.class.getName());
	
	public static ProjectSprintDataService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "ScrumREST";
	
	private static String SERVICE_NAME = ProjectSprintDataServiceEJB.class.getSimpleName();
	
	private static String REMOTE_INTERFACE_NAME = ProjectSprintDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws Exception {
		// lookup ejb
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}


/*
    	// workaround 
    	URL url = new URL("http://localhost:8080/ScrumREST/projects");
		URLConnection conn = url.openConnection();
		conn.getInputStream();
*/
		
		

