package org.demo.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.crm.CustomerProfile;
import org.datanucleus.enhancer.DataNucleusEnhancer;
import org.erp.ProductNom;
import org.erp.SalesInvoices;
import org.office.access.ProductCategory;
import org.office.access.ProductInCategories;
import org.office.xls.AdvertisingExpense;

public class DemoDataEngine {
	static Logger logger = Logger.getLogger(DemoDataEngine.class.getName());
	
	//Workloads
	private List<ProductNom>  products;
	private List<SalesInvoices> sales;
	public String generateERPProductSales() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ERP_ORCL");
		EntityManager em = emf.createEntityManager();
		
		logger.info("INIT ERP_ORCL ...");
		
		ERPFactory erpFactory = new ERPFactory();
		products = erpFactory.generateProductLoad(10);
//		for(ProductNom p : products)
//			logger.info(">>>>>>> " + p);		
		sales = erpFactory.generateSalesLoad(150, products, null);
		for(SalesInvoices s: sales)
			logger.info(">>>>>>> " + s);		
		
		EntityRepository<ProductNom> productNomRepository = new EntityRepositoryBase<ProductNom>(em, ProductNom.class);
//		EntityRepository<ProductNom> productNomRepository = new EntityRepositoryBase<ProductNom>(em);
		EntityRepository<SalesInvoices> salesRepository = new EntityRepositoryBase<SalesInvoices>(em, SalesInvoices.class);
		
		em.getTransaction().begin();
		productNomRepository.addAll(products);
		salesRepository.addAll(sales);
		em.getTransaction().commit();
		
		logger.info("ERP_SERVER ... FINISH");
		em.close();
		return "Ok";
	}
	
	private List<CustomerProfile> customers;
	public String generateCRMCustomerProfiles(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM_PG");
		EntityManager em = emf.createEntityManager();
		
		logger.info("INIT CRM_PG ...");
		
		CRMFactory crmFactory = new CRMFactory();
		customers = crmFactory.generateCustomerProfileLoad(100);
//		for(CustomerProfile s: customers)
//			logger.info(">>>>>>> " + s);			
		
		EntityRepository<CustomerProfile> customerProfileRepository = new EntityRepositoryBase<CustomerProfile>(em, CustomerProfile.class);
		em.getTransaction().begin();
		customerProfileRepository.addAll(customers);
		em.getTransaction().commit();		
		
		logger.info("CRM_SERVER ... FINISH");
		em.close();
		return "Ok";
	}

	/*
	 * http://ucanaccess.sourceforge.net/site.html
	 * http://stackoverflow.com/questions/1749464/how-can-i-use-hibernate-with-ms-access
	 */
	private List<ProductCategory> categories;
	private List<ProductInCategories> productsInCategories;
	public String generateACCESSProductCategories(){
		// ACCESS_LOCAL
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ACCESS_LOCAL");
		EntityManager em = emf.createEntityManager();
		logger.info("INIT ACCESS_LOCAL ...");		
		
		OfficeAccessFactory officeAccessFactory = new OfficeAccessFactory();
		categories = officeAccessFactory.generateProductCategoryLoad(4);
//		for(ProductCategory s: categories)
//			logger.info(">>>>>>> " + s);		
		productsInCategories = officeAccessFactory.generateProductInCategoriesLoad(products);
//		for(ProductInCategories s: productsInCategories)
//			logger.info(">>>>>>> " + s);		
		
		EntityRepository<ProductCategory> productCategoryRepository = new EntityRepositoryBase<ProductCategory>(em, ProductCategory.class);
		EntityRepository<ProductInCategories> productInCategoriesRepository = new EntityRepositoryBase<ProductInCategories>(em, ProductInCategories.class);
		
		em.getTransaction().begin();
		productCategoryRepository.addAll(categories);
		productInCategoriesRepository.addAll(productsInCategories);
		em.getTransaction().commit();		
		
		logger.info("ACCESS_LOCAL ... FINISH");
		em.close();
		return "Ok";
	}
	
	/*
	 * Format XLS 97-2003
	 */
	private List<AdvertisingExpense> expenses;
	public String generateXLSAdvertisingExpenses() throws Exception{
		
		// XLS_LOCAL
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("XLS_LOCAL");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("DELETE FROM AdvertisingExpense a");
	    int numberInstancesDeleted = q.executeUpdate();
	    
		OfficeXLSFactory officeXLSFactory = new OfficeXLSFactory();
		expenses = officeXLSFactory.generateAdvertisingExpensesLoad(products);
//		for(AdvertisingExpense s: expenses)
//			logger.info(">>>>>>> " + s);		    
	    
		EntityRepository<AdvertisingExpense> advertisingExpenseRepository = new EntityRepositoryBase<AdvertisingExpense>(em, AdvertisingExpense.class);
		
		em.getTransaction().begin();
		advertisingExpenseRepository.addAll(expenses);
		em.getTransaction().commit();			
		
		logger.info("XLS_LOCAL ... FINISH");
		em.close();
		return "Ok";
	}
	/*
	 * Pregrammatically "enhance" DataNucleus Class Entities:
	 * dataEngine.enhanceDataNucleusPersistentUnitEntities();
	 */
	private void enhanceDataNucleusPersistentUnitEntities() throws Exception{
		DataNucleusEnhancer enhancer = new DataNucleusEnhancer("JPA", null);
		enhancer.setVerbose(true);
		enhancer.addPersistenceUnit("XLS_LOCAL");
		enhancer.enhance();
		logger.info("ENHANCED XLS_LOCAL ... FINISH");	
	}


	public static void main(String... args) throws Exception{
		logger.info("LOCKED DATA");
//		DemoDataEngine dataEngine = new DemoDataEngine();
//		dataEngine.generateERPProductSales();
//		dataEngine.generateCRMCustomerProfiles();
//		dataEngine.generateACCESSProductCategories();
//		dataEngine.generateXLSAdvertisingExpenses();		
	}
}