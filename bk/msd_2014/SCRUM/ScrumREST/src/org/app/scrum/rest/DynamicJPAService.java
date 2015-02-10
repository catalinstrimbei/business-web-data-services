package org.app.scrum.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.dynamic.DynamicClassLoader;
import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.jpa.dynamic.JPADynamicHelper;
import org.eclipse.persistence.jpa.dynamic.JPADynamicTypeBuilder;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;

@Path("/dynamicjpa") /* http://localhost:8080/ScrumREST/dynamicjpa */
@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class DynamicJPAService {
	private static Logger logger = Logger.getLogger(DynamicJPAService.class.getName());

	@PersistenceContext(unitName="targetORCL")
	private EntityManager em;
	
	@Resource
	private SessionContext context; 
	
	@GET @Path("/create")
	public String testDynamicJPA(){
		// Create a dynamic class loader and create the types.
        DynamicClassLoader dcl = new DynamicClassLoader(Thread.currentThread().getContextClassLoader());
        DynamicType addressType = configureAddress(dcl, "example.entities");
        
        // Create JPA Dynamic Helper (with the emf above) and after the types
        JPADynamicHelper helper = new JPADynamicHelper(em);
        logger.info(">>>>> HELPER: NOT doing create table and fkey ....");
//        helper.addTypes(true, true, addressType);
        helper.addTypes(false, false, addressType);
        
        /* With PG transaction could be rolled back, and subsequent JPA actions will fail!*/
        /* With ORCL seems to be ok!*/
        
        // Create database and populate
        SchemaManager schemaManager = new SchemaManager(helper.getSession());
        logger.info(">>>>> SchemaManager: replace default tables ...");
        schemaManager.replaceDefaultTables();
        
        logger.info("address instance: " + saveDynamicEntity());
        
        return "OK Created";
	}
	
	@GET @Path("/save")	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String saveDynamicEntity(){
		// Create a dynamic class loader and create the types.
        DynamicClassLoader dcl = new DynamicClassLoader(Thread.currentThread().getContextClassLoader());
        DynamicType addressType = configureAddress(dcl, "example.entities");
        
        // Create JPA Dynamic Helper (with the emf above) and after the types
        JPADynamicHelper helper = new JPADynamicHelper(em);
        logger.info(">>>>> HELPER: register dynamic type ....");
        helper.addTypes(false, false, addressType); // no table generation, not fk generation		
		
		DynamicEntity address = createInstance(em, "Address");
		
		logger.info("address instance: " + address.get("city"));
		
		em.getTransaction().begin();
		em.persist(address);
		logger.info(">>>>> EM: insert dynamic instance ....");
		em.flush();
		em.getTransaction().commit();
	        
	    return "OK Saved";
	}
	
    public static EntityManagerFactory createEntityManagerFactory(DynamicClassLoader dcl, String persistenceUnit) {
        Map<Object, Object> properties = new HashMap<Object, Object>();
        properties.put("javax.persistence.jdbc.driver", "oracle.jdbc.OracleDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@10.10.0.7:1521:ORCL");
        properties.put("javax.persistence.jdbc.user", "scrum");
        properties.put("javax.persistence.jdbc.password", "scrum");

        properties.put(PersistenceUnitProperties.CLASSLOADER, dcl);
        properties.put(PersistenceUnitProperties.WEAVING, "static");
        return Persistence.createEntityManagerFactory(persistenceUnit, properties);
    }
    
    
    private static DynamicType configureAddress(DynamicClassLoader dcl, String packageName){
    	String packagePrefix = packageName.endsWith(".") ? packageName : packageName + ".";
    	Class<?> addressClass = dcl.createDynamicClass(packagePrefix + "Address");
    	JPADynamicTypeBuilder address = new JPADynamicTypeBuilder(addressClass, null, "D_ADDRESS");
    	
    	address.setPrimaryKeyFields("ADDR_ID");

        address.addDirectMapping("id", int.class, "ADDR_ID");
        address.addDirectMapping("street", String.class, "STREET");
        address.addDirectMapping("city", String.class, "CITY");
        address.addDirectMapping("province", String.class, "PROV");
        address.addDirectMapping("postalCode", String.class, "P_CODE");
        address.addDirectMapping("country_en", String.class, "COUNTRY_EN");

//        address.configureSequencing("ADDR_SEQ", "ADDR_ID");
        
        return address.getType();
    }

    
    private static DynamicEntity createInstance(EntityManager em, String entityAlias){
    	ClassDescriptor descriptor = JpaHelper.getEntityManager(em).getActiveSession().getDescriptorForAlias(entityAlias);
    	DynamicEntity address = (DynamicEntity) descriptor.getInstantiationPolicy().buildNewInstance();
    	address.set("id", 1);
        address.set("city", "IASI");
        address.set("postalCode", "123456");
        address.set("province", "IS");
        address.set("street", "Carol 1");
        address.set("country_en", "Romania");    	
        return address;
    }
}    


/*
<datasource jndi-name="java:/PGFeaaMSD" pool-name="PGFeaaMSD" enabled="true" use-java-context="true">
                    <connection-url>jdbc:postgresql://10.10.0.7:5432/postgres</connection-url>
                    <driver>postgresql</driver>
                    <security>
                        <user-name>scrum</user-name>
                        <password>scrum</password>
                    </security>
                </datasource>

<driver name="postgresql" module="org.postgresql">
                        <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
                    </driver>
*/

