package org.app.scrum.ejb.test;

import java.util.Hashtable;

import javax.naming.Context;

import org.apache.log4j.Logger;
import org.app.service.entities.TestSprintDataService;
import org.app.service.entities.TestSprintDataServiceEJB;
import org.jboss.as.naming.InitialContext;

public class TestSprintDataServiceEJBFactory {

	private  static Logger logger = Logger.getLogger(TestSprintDataServiceEJBFactory.class.getName());
	public static TestSprintDataService getScrumTestRepositoryService() throws Exception{
		return lookUpEJBService();
	}
	
	private static String MODULE_NAME="SrumREST";
	
	private static String SERVICE_NAME=TestSprintDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME=TestSprintDataService.class.getName();
	
	private static <T> T lookUpEJBService() throws Exception {
	
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context= new InitialContext(jndiProperties);
		
		String lookUpURL="ejb:/" + MODULE_NAME+ "//" + SERVICE_NAME +  "!" + REMOTE_INTERFACE_NAME;
		logger.info("! DEBUG: lookUpURL= " + lookUpURL + "\n");
		return (T) context.lookup(lookUpURL);
	}
	
	
	
	
	private static TestSprintDataService lookupEJBService() {
		// TODO Auto-generated method stub
		return null;
	}
}
