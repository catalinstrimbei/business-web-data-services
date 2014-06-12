package org.demo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.erp.ProductNom;
import org.erp.SalesInvoices;

public class ERPFactory {
	private static Logger logger = Logger.getLogger(ERPFactory.class.getName());
	
	private List<ProductNom> productNomLoad = new ArrayList<>();
	private List<SalesInvoices> salesInvoicesLoad = new ArrayList<>();
	private List<Integer> productRefLoad = new ArrayList<>();
	private List<Integer> salesRefLoad = new ArrayList<>();
	
	public List<ProductNom> generateProductLoad(Integer prodCount) {
		productNomLoad = new ArrayList<>();
		Integer prodId = 0;
		for (int i = 1; i <= prodCount; i++) {
			prodId = 100 + i;
			productNomLoad.add(new ProductNom(prodId, "PROD " + i, "Product from ERP " + i));
			productRefLoad.add(prodId);
		}

		return productNomLoad;
	}

	public List<SalesInvoices> generateSalesLoad(Integer salesCount, List<ProductNom> productLoad, List<Integer> custLoad) throws Exception{
		// by default generate a cust load as 1/4 from sales load
		if (custLoad == null || custLoad.isEmpty()){
			custLoad = new ArrayList<Integer>();
			for (int i=1; i < salesCount/4; i++)
				custLoad.add(1000 + i);
		}
			
		
		// by default generate a product load as 1/3 from sales load
		if (productLoad == null || productLoad.isEmpty())
			productLoad = generateProductLoad(salesCount/3);
		
		// Transaction Date load must be generated: 3 months before current date
		Map<Date, Integer> dateLoad = new TreeMap<>();
		Calendar currentDate = Calendar.getInstance();
		Calendar beforeDate = Calendar.getInstance(); 
		beforeDate.add(Calendar.MONTH, -3);
		while(beforeDate.before(currentDate)){
			dateLoad.put(beforeDate.getTime(), null);
			beforeDate.add(Calendar.DAY_OF_MONTH, 1);
		}

		// Generate dayLoad for salesLoad
		salesInvoicesLoad = new ArrayList<>();
		// Maxim 50% more per day 
		Integer maxDayLoad = salesCount/dateLoad.keySet().size() * 150/100;
		Integer dayLoadCount = 0;
		Integer dayLoadRnd = 0;
		Random dayLoadRandomizer = new Random();
		Double percentDispached = 0.0;
		for(Date day: dateLoad.keySet()){
			dayLoadRnd = DataFactoryUtils.nextIntInRange(0, maxDayLoad, dayLoadRandomizer);
			if (dayLoadCount + dayLoadRnd >=  salesCount)
				dayLoadRnd = salesCount - dayLoadCount;
			dateLoad.put(day, dayLoadRnd);
			dayLoadCount += dayLoadRnd;
			if (dayLoadCount >=  salesCount)
				break;
		}
		
		percentDispached = new Double(dayLoadCount)/new Double(salesCount);
		Integer remainingDayLoad = salesCount - dayLoadCount;
		logger.info("=============== percentDispached = " + percentDispached);
		logger.info("=============== dayLoadCount = " + dayLoadCount + "+" + remainingDayLoad + "=" + salesCount);
		
		Integer dayLoadAdd = 0; dayLoadCount = 0;
		//if(percentDispached < 1.0){
		if(remainingDayLoad > 0){
			for(Date day: dateLoad.keySet()){
				dayLoadAdd = new Long(Math.round(dateLoad.get(day) * (1-percentDispached))).intValue() + 1;
				if (remainingDayLoad < dayLoadAdd)
					dayLoadAdd = remainingDayLoad;
				remainingDayLoad = remainingDayLoad - dayLoadAdd;
				dayLoadAdd += dateLoad.get(day);
				dateLoad.put(day, dayLoadAdd);
				if(remainingDayLoad==0)
					break;
			}
		}		
		// generate sales load
		Random prodLoadRandomizer = new Random();
		Random custLoadRandomizer = new Random();
		Random quatityLoadRandomizer = new Random();
		Random priceLoadRandomizer = new Random();
		Integer prodLoadIdx = 0;
		Integer custLoadIdx = 0;
		Integer invoiceNo = 0;
		Integer tranzId = 0;
		Double quatity = 0.0;
		Double price = 0.0;
		Double taxes = 0.0;
		Double amount = 0.0;		
		for(Date day: dateLoad.keySet()){
			for(int k=1; k <= dateLoad.get(day); k++){
				invoiceNo++;
				tranzId = new Double(day.getTime()/100000).intValue();
				prodLoadIdx = DataFactoryUtils.nextIntInRange(0, productLoad.size()-1, prodLoadRandomizer);
				custLoadIdx = DataFactoryUtils.nextIntInRange(0, custLoad.size()-1, custLoadRandomizer);
				quatity = DataFactoryUtils.truncateDecimal(DataFactoryUtils.nextIntInRange(10, 200, quatityLoadRandomizer).doubleValue(),2);
				price = DataFactoryUtils.truncateDecimal(DataFactoryUtils.nextIntInRange(150, 1500, priceLoadRandomizer)/1.25,2);
				taxes = DataFactoryUtils.truncateDecimal((quatity  * price) * 0.25,2);
				amount = DataFactoryUtils.truncateDecimal(quatity  * price + taxes,2);
				salesInvoicesLoad.add(new SalesInvoices(tranzId,
						day, 
						new Long(invoiceNo), 
						productLoad.get(prodLoadIdx).getProductId(), 
						custLoad.get(custLoadIdx), 
						quatity, price, taxes, amount));	
				salesRefLoad.add(tranzId);
			}
		}
		return salesInvoicesLoad;
	}

	public List<Integer> getProductRefLoad() {
		return productRefLoad;
	}

	public List<Integer> getSalesRefLoad() {
		return salesRefLoad;
	}
	
	
}
