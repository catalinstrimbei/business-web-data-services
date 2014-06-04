package org.demo.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.datanucleus.enhancer.DataNucleusEnhancer;
import org.office.access.ProductCategory;
import org.office.xls.AdvertisingExpense;



public class DemoDataEngine {
	Logger logger = Logger.getLogger(DemoDataEngine.class.getName());
	public String generateERPProductSales(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ERP_ORCL");
		EntityManager em = emf.createEntityManager();
		
		logger.info("INIT ERP_ORCL ...");
		
		return "Ok";
	}
	
	public String generateCRMCustomerProfiles(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM_PG");
		EntityManager em = emf.createEntityManager();
		
		logger.info("INIT CRM_PG ...");
		
		return "Ok";
	}

	/*
	 * http://ucanaccess.sourceforge.net/site.html
	 * http://stackoverflow.com/questions/1749464/how-can-i-use-hibernate-with-ms-access
	 */
	public String generateACCESSProductCategories(){
		// ACCESS_LOCAL
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ACCESS_LOCAL");
		EntityManager em = emf.createEntityManager();
		
		ProductCategory prodCtg = new ProductCategory();
		prodCtg.setCategoryId(1);
		prodCtg.setCategName("Test");
		prodCtg.setCategDescription("de test");
		em.persist(prodCtg);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		List<ProductCategory> ctgs = em.createQuery("SELECT c FROM ProductCategory c").getResultList();
		for(ProductCategory c: ctgs)
			logger.info(c.toString());
		
		logger.info("INIT ACCESS_LOCAL ...");		
		
		return "Ok";
	}
	/*
	 * Format XLS 97-2003
	 */
	public String generateXLSAdvertisingExpenses(){
		// XLS_LOCAL
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("XLS_LOCAL");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("DELETE FROM AdvertisingExpense a");
	    int numberInstancesDeleted = q.executeUpdate();		
		
		AdvertisingExpense expense = new AdvertisingExpense();
		expense.setProductCode(1001);
		expense.setAdvExpCateg("media test");
		expense.setAdvExpAmount(1000.0);
		
		em.persist(expense);
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		List<AdvertisingExpense> exps = em.createQuery("SELECT a FROM AdvertisingExpense a").getResultList();
		for(AdvertisingExpense a: exps)
			logger.info(a.toString());		
		
		logger.info("INIT XLS_LOCAL ... FINISH");	
		
		return "Ok";
	}
	private void enhanceDataNucleusPersistentUnitEntities(){
		DataNucleusEnhancer enhancer = new DataNucleusEnhancer("JPA", null);
		enhancer.setVerbose(true);
		enhancer.addPersistenceUnit("XLS_LOCAL");
		enhancer.enhance();
		
		ClassLoader classLoader = DemoDataEngine.class.getClassLoader();

	    try {
	        Class enhClass = classLoader.loadClass("org.office.xls.AdvertisingExpense");
	        logger.info("Enhanced class name = " + enhClass.getName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }		
	}
	
	public static void main(String[] args){
		DemoDataEngine dataEngine = new DemoDataEngine();
//		dataEngine.generateERPProductSales();
//		dataEngine.generateCRMCustomerProfiles();
//		dataEngine.generateACCESSProductCategories();
		
		dataEngine.enhanceDataNucleusPersistentUnitEntities();
		dataEngine.generateXLSAdvertisingExpenses();
	}
}

/*
<persistence-unit name="CRM_PG" transaction-type="RESOURCE_LOCAL">
		<provider>org.hibernate.ejb.HibernatePersistence</provider>
		<properties>
			<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://192.168.10.68:5432/postgres"/>
			<property name="javax.persistence.jdbc.user" value="crmdemo"/>
			<property name="javax.persistence.jdbc.password" value="crmdemo"/>
			<property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
			
         	<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
         	<property name="hibernate.hbm2ddl.auto" value="none"/>
		</properties>
	</persistence-unit>
	
<persistence-unit name="ACCESS_LOCAL" transaction-type="RESOURCE_LOCAL">
	</persistence-unit>	
	
	<persistence-unit name="XLS_LOCAL" transaction-type="RESOURCE_LOCAL">
	</persistence-unit>	

*/

/*
 * ACCESS:
 * 
 * http://www.javaxt.com/Tutorials/JDBC/How_to_Open_a_JDBC_Connection_to_Microsoft_Access
 * http://www.easysoft.com/applications/microsoft-access/jdbc-odbc.html
 * 
 * http://ucanaccess.sourceforge.net/site.html
<?xml version="1.0" encoding="utf-8"?>
<persistence>
    <persistence-unit name="traderMandate">
        <provider>org.hibernate.ejb.HibernatePersistence</provider>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.SQLServerDialect" />
            <property name="hibernate.connection.url" value="jdbc:ucanaccess://C:/MY.accdb;" />
            <property name="hibernate.connection.driver_class" value="net.ucanaccess.jdbc.UcanaccessDriver"/>
            <property name="hibernate.archive.autodetection" value="class" />
        </properties>
    </persistence-unit>
</persistence>    
 *
 * 
 * http://www.programmingforfuture.com/2011/06/how-to-use-ms-access-with-hibernate.html
 * 
 * http://javakafunda.blogspot.ro/2010/01/how-to-connect-hibernate-with-microsoft.html
 * 
 * 
 * 
 * http://java.dzone.com/articles/restify-your-sql-data?mz=62447-cloud
 * http://restsql.org/doc/Architecture.html
 * 
 * 
 * EXCEL:
 * http://datanucleus.blogspot.ro/2010/07/writing-data-to-excel-from-java-simple.html
 * http://www.datanucleus.org/products/datanucleus/jpa/guides/tutorial_excel.html
 * 
 */


/*
<properties>
	<property name="javax.persistence.jdbc.url" 
		value="jdbc:odbc:DSN=demo_access;DBQ=E:\Professional\NextData\MDS-Demo-Data\OfficeDataSource\MDSDemoAccessDB.accdb;DriverId=25;FIL=MS Access;MaxBufferSize=2048;PageTimeout=5;"/>
	<property name="javax.persistence.jdbc.driver" value="com.hxtt.sql.access.AccessDriver"/>				
 	<property name="hibernate.dialect" value="com.hxtt.support.hibernate.HxttAccessDialect"/>
 	<property name="hibernate.hbm2ddl.auto" value="create-drop"/>
</properties>
*/