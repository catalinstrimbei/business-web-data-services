package org.demo.services;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.util.HotSwapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.datanucleus.enhancer.DataNucleusEnhancer;
import org.office.access.ProductCategory;
//import org.office.xls.AdvertisingExpense;






import org.office.xls.AdvertisingExpense;

import com.myarch.reloader.ClassCollectionController;
import com.myarch.reloader.Reloader;


/*
 * Runtime configuration: -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus/datanucleus-core-3.2.5.jar"=-api=JPA,org.office.xls
 */
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
	public String generateXLSAdvertisingExpenses() throws Exception{
		
		// XLS_LOCAL
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("XLS_LOCAL");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("DELETE FROM AdvertisingExpense a");
	    int numberInstancesDeleted = q.executeUpdate();
	    
	    
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		AdvertisingExpense expense = new AdvertisingExpense();
		expense.setProductCode(1001);
		expense.setAdvExpCateg("media test");
		expense.setAdvExpAmount(1000.0);
		expense.setPeriodStartDate(format.parse("01/05/2014"));
		expense.setPeriodEndDate(format.parse("01/06/2014"));
		expense.setProductSales(150.0);
		
		em.persist(expense);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		List<AdvertisingExpense> exps = em.createQuery("SELECT a FROM AdvertisingExpense a").getResultList();
		for(AdvertisingExpense a: exps)
			logger.info(a.toString());	
		
		
		
		logger.info("INIT XLS_LOCAL ... FINISH");	
		
		return "Ok";
	}
	private void enhanceDataNucleusPersistentUnitEntities() throws Exception{
		
		DataNucleusEnhancer enhancer = new DataNucleusEnhancer("JPA", null);
		enhancer.setVerbose(true);
		enhancer.addPersistenceUnit("XLS_LOCAL");
		enhancer.enhance();
		ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("org.office.xls.AdvertisingExpense");
        byte[] classFile = cc.toBytecode();
        HotSwapper hs = new HotSwapper(8000);
        hs.reload("org.office.xls.AdvertisingExpense", classFile);        
	}
	
	public static void main(String[] args) throws Exception{
		DemoDataEngine dataEngine = new DemoDataEngine();
		dataEngine.generateERPProductSales();
		dataEngine.generateCRMCustomerProfiles();
		dataEngine.generateACCESSProductCategories();
		
//		dataEngine.enhanceDataNucleusPersistentUnitEntities();
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


/*
CustomClassLoader cstLoader = new CustomClassLoader();
Object emf = null;
Object em = null;
Object q = null;

Method method = cstLoader.loadClass("javax.persistence.Persistence").getMethod("createEntityManagerFactory", String.class);
emf = method.invoke(null, "XLS_LOCAL");
logger.info(">>>>>>>>>>> DEBUG emf = " + emf);
method = cstLoader.loadClass("javax.persistence.EntityManagerFactory").getMethod("createEntityManager", null);
em = method.invoke(emf, null);
logger.info(">>>>>>>>>>> DEBUG em = " + em);
method = cstLoader.loadClass("javax.persistence.EntityManager").getMethod("createQuery", String.class);
q =  method.invoke(em, "DELETE FROM AdvertisingExpense a");
method = cstLoader.loadClass("javax.persistence.Query").getMethod("executeUpdate", null);
method.invoke(q, null);
//method = cstLoader.loadClass(EntityManager.class.getName()).getMethod("persist", Object.class);
//method.invoke(em, expense);		

CustomClassLoader cstLoader = new CustomClassLoader();
Class entityClass = cstLoader.loadClass("org.office.xls.AdvertisingExpense");
Object expense = entityClass.newInstance();

entityClass.getMethod("setProductCode", Integer.class).invoke(expense, 1001);
entityClass.getMethod("setAdvExpCateg", String.class).invoke(expense, "media test");
entityClass.getMethod("setAdvExpAmount", Double.class).invoke(expense, 1000.0)
List<Object> exps = em.createQuery("SELECT a FROM AdvertisingExpense a").getResultList();
for(Object a: exps)
	logger.info(a.toString());	
*/