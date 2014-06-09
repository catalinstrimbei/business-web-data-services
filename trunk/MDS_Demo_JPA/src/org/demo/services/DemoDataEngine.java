package org.demo.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.util.HotSwapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.datanucleus.enhancer.DataNucleusEnhancer;
import org.office.access.ProductCategory;
//import org.office.xls.AdvertisingExpense;
import org.office.xls.AdvertisingExpense;

//import com.myarch.reloader.ClassCollectionController;
//import com.myarch.reloader.Reloader;


/*
 * Runtime configuration: -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus/datanucleus-core-3.2.5.jar"=-api=JPA,org.office.xls
 * <property name="javax.persistence.jdbc.url" value="ooxml:file://E:/Professional/NextData/MDS-Demo-Data/OfficeDataSource/ExcelTestAccess.xlsx"/>
 * <property name="javax.persistence.jdbc.url" value="xls:file://E:/Professional/NextData/MDS-Demo-Data/OfficeDataSource/ExcelTestAccess.xlsx"/>
 * <property name="javax.persistence.jdbc.url" value="odf:file:tutorial.ods"/>
 * <property name="javax.persistence.jdbc.url" value="excel:file://E:/Professional/NextData/MDS-Demo-Data/OfficeDataSource/ExcelTestAccess.xls"/>
 * -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus4m4/datanucleus-core-4.0.0-m4.jar"=-api=JPA,org.office.xls
 * -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus/datanucleus-core-3.2.5.jar"=-api=JPA,org.office.xls
 * -javaagent:"E:/Professional/NextData/MDS-Demo-Data/MDS_Demo_JPA/lib/datanucleus-3.3.8/datanucleus-core-3.2.13.jar"=-api=JPA,org.office.xls
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
//		ClassPool cp = ClassPool.getDefault();
//        CtClass cc = cp.get("org.office.xls.AdvertisingExpense");
//        byte[] classFile = cc.toBytecode();
//        HotSwapper hs = new HotSwapper(8000);
//        hs.reload("org.office.xls.AdvertisingExpense", classFile);
		logger.info("ENHANCED XLS_LOCAL ... FINISH");	
	}
	
	public static void main(String[] args) throws Exception{
		DemoDataEngine dataEngine = new DemoDataEngine();
//		dataEngine.generateERPProductSales();
//		dataEngine.generateCRMCustomerProfiles();
//		dataEngine.generateACCESSProductCategories();
		
//		dataEngine.enhanceDataNucleusPersistentUnitEntities();
		dataEngine.generateXLSAdvertisingExpenses();
	}
}