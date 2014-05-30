package org.app.patterns;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.dynamic.DynamicClassLoader;
import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.dynamic.DynamicType;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.jpa.dynamic.JPADynamicHelper;
import org.eclipse.persistence.jpa.dynamic.JPADynamicTypeBuilder;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;

public class Test_DynamicJPA {

	public static void main(String[] args) {
		// Create a dynamic class loader and create the types.
        DynamicClassLoader dcl = new DynamicClassLoader(Thread.currentThread().getContextClassLoader());
        DynamicType addressType = configureAddress(dcl, "example.entities");
        
        // Create an entity manager factory.
        EntityManagerFactory emf = createEntityManagerFactory(dcl, "target");
       
        // Create JPA Dynamic Helper (with the emf above) and after the types
        // have been created and add the types through the helper.
        JPADynamicHelper helper = new JPADynamicHelper(emf);
        helper.addTypes(true, true, addressType);
        
        // Create database and populate
        SchemaManager schemaManager = new SchemaManager(helper.getSession());
        schemaManager.replaceDefaultTables();

        //
        DynamicEntity address = createInstance(emf, "Address");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        em.clear();        
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
        address.addDirectMapping("country", String.class, "COUNTRY");

        address.configureSequencing("ADDR_SEQ", "ADDR_ID");
        
        return address.getType();
    }

    
    private static DynamicEntity createInstance(EntityManagerFactory emf, String entityAlias){
    	ClassDescriptor descriptor = JpaHelper.getServerSession(emf).getDescriptorForAlias(entityAlias);
    	
    	DynamicEntity address = (DynamicEntity) descriptor.getInstantiationPolicy().buildNewInstance();
        address.set("city", "Piatra-Neamt");
        address.set("postalCode", "123456");
        address.set("province", "NT");
        address.set("street", "Calea Sucevei");
        address.set("country", "Romania");    	
        
        return address;
    }
    

}


    
//    private static DynamicEntity createInstance(EntityManagerFactory emf, String entityAlias){
//    	ClassDescriptor descriptor = JpaHelper.getServerSession(emf).getDescriptorForAlias(entityAlias);
//    	
//    	DynamicEntity address = (DynamicEntity) descriptor.getInstantiationPolicy().buildNewInstance();
//        address.set("city", "Iasi");
//        address.set("postalCode", "300705");
//        address.set("province", "IS");
//        address.set("street", "Carol 1, 11");
//        address.set("country", "Romania");    	
//        
//        return address;
//    }


/*
javax.persistence.jdbc.driver=oracle.jdbc.OracleDriver
javax.persistence.jdbc.url=jdbc:oracle:thin:@10.10.0.7:1521:ORCL
javax.persistence.jdbc.user=scrum
javax.persistence.jdbc.password=scrum

eclipselink.jdbc.read-connections.min=1
eclipselink.jdbc.write-connections.min=1

eclipselink.logging.level=CONFIG
eclipselink.logging.timestamp=false
eclipselink.logging.thread=false
eclipselink.logging.session=false
eclipselink.logging.exceptions=false
eclipselink.logging.level.sql=FINE
eclipselink.logging.level.ejb_or_metadata=WARNING 

// properties.put("", "");   
File file = new File(filename);	
Properties exampleProps = new Properties();
InputStream in = new FileInputStream(file);
exampleProps.load(in);
in.close();
properties.putAll(exampleProps);
 */
