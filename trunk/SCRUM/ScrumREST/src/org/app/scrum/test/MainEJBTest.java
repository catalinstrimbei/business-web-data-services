package org.app.scrum.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.app.scrum.ejb.ScrumProjectDataServiceEJB;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainEJBTest {
	static Logger logger = Logger.getLogger(MainEJBTest.class.getName());

	private static EJBContainer ec;
	private static Context ctx;

	@BeforeClass
	public static void initContainer() throws Exception {
		System.setProperty("org.jboss.as.embedded.ejb3.BARREN", "true");
		System.setProperty("jboss.home", "D:/_jboss-as-7.1.1.Final"); 
		System.setProperty("jboss.home.dir", "D:/_jboss-as-7.1.1.Final");
		Map<String, Object> props = new HashMap<String, Object>();  
		File[] ejbModules = new File[1];  
		ejbModules[0] = new File("build/classes");
		props.put(EJBContainer.MODULES, ejbModules); 
		EJBContainer container = EJBContainer.createEJBContainer(props);  
		
//		 Map properties = new HashMap();
//		 File ejbFiles = new File("build/classes");
//		 logger.info("DEBUG " + ejbFiles.getAbsolutePath());
//		 properties.put(EJBContainer.MODULES, new File("build/classes"));
//		 ec = EJBContainer.createEJBContainer(properties);
//		 ctx = ec.getContext();

//		 ec = EJBContainer.createEJBContainer();
//		 ctx = new InitialContext();

//		String jbossHomeDir = "E:\\dev_station\\java_station\\Serveurs\\jboss-as-7.1.1.Final";
//		System.setProperty("jboss.home.dir", jbossHomeDir);
//
//		StandaloneServer server = EmbeddedServerFactory.create(new File(
//				jbossHomeDir), System.getProperties(), System.getenv(),
//				"org.jboss.logmanager");
//
//		server.start();
//		server.deploy(new File("target/classes"));
//		Context namingContext = server.getContext();

	}

	@AfterClass
	public static void closeContainer() throws Exception {
		if (ec != null)
			ec.close();
	}

	@Test
	public void shouldDisplayHelloWorld() throws Exception {
		// Looks up the EJB
		ScrumProjectDataServiceEJB mainEjb = (ScrumProjectDataServiceEJB) ctx
				.lookup("java:global/ScrumREST/ScrumProjectDataServiceEJB!org.app.scrum.ejb.ScrumProjectDataServiceEJBB");

		String message = "Cool test";
		assertEquals("should say Hello World !!!", message
				+ " ... from remote ScrumProjectDataServiceEJB!",
				mainEjb.sayMessage(message));
	}
}
