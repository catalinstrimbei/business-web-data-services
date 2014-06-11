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

	public List<ProductNom> generateProductLoad(Integer prodCount) {
		productNomLoad = new ArrayList<>();

		for (int i = 1; i < prodCount; i++) {
			productNomLoad.add(new ProductNom(100 + i, "PROD " + i, "Product from ERP " + i));
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
			dayLoadRnd = nextIntInRange(0, maxDayLoad, dayLoadRandomizer);
			if (dayLoadCount + dayLoadRnd >=  salesCount)
				dayLoadRnd = salesCount - dayLoadCount;
			dateLoad.put(day, dayLoadRnd);
			dayLoadCount += dayLoadRnd;
			if (dayLoadCount >=  salesCount)
				break;
		}
		
		percentDispached = new Double(dayLoadCount)/new Double(salesCount);
		logger.info("---> percentDispached = " + percentDispached);
		
		Integer dayLoadAdd = 0; dayLoadCount = 0;
		if(percentDispached < 1.0){
			for(Date day: dateLoad.keySet()){
				dayLoadAdd = new Long(Math.round(dateLoad.get(day) * (1-percentDispached))).intValue() + 1;
				dayLoadAdd += dateLoad.get(day);
//				logger.info("---> dayLoadCount before = " + dayLoadCount + " :: " + dayLoadAdd);				
				if(dayLoadCount + dayLoadAdd >= salesCount)
					dayLoadAdd = salesCount - dayLoadCount;
//				logger.info("---> dayLoadAdd = " + dayLoadAdd + " :: " + dateLoad.get(day));
				dateLoad.put(day, dayLoadAdd);
//				logger.info("---> dayLoadAdded = " + dayLoadAdd + " :: " + dateLoad.get(day));
				dayLoadCount += dayLoadAdd;
//				logger.info("---> dayLoadCount after = " + dayLoadCount + " :: " + dayLoadAdd);
				logger.info("=============== dayLoad = " + day + " ---> " + dateLoad.get(day) + " -----> " + dayLoadCount);
				if (dayLoadCount >=  salesCount)
					break;
			}
		}
		logger.info("=============== dayLoadCount = " + dayLoadCount);
//		for(Date day: dateLoad.keySet()){
//			logger.info("---> " + day + " --- " + dateLoad.get(day));
//		}		
		dayLoadCount = 0;
		for(Date day: dateLoad.keySet()){
			dayLoadCount += dateLoad.get(day);
		}
		logger.info("=============== dayLoadCount = " + dayLoadCount);
		
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
				prodLoadIdx = nextIntInRange(0, productLoad.size()-1, prodLoadRandomizer);
				custLoadIdx = nextIntInRange(0, custLoad.size()-1, custLoadRandomizer);
				quatity = truncateDecimal(nextIntInRange(10, 200, quatityLoadRandomizer).doubleValue(),2);
				price = truncateDecimal(nextIntInRange(150, 1500, priceLoadRandomizer)/1.25,2);
				taxes = truncateDecimal((quatity  * price) * 0.25,2);
				amount = truncateDecimal(quatity  * price + taxes,2);
				salesInvoicesLoad.add(new SalesInvoices(tranzId,
						day, 
						new Long(invoiceNo), 
						productLoad.get(prodLoadIdx).getProductId(), 
						custLoad.get(custLoadIdx), 
						quatity, price, taxes, amount));				
			}
		}
		
		
		return salesInvoicesLoad;
	}

	private Integer nextIntInRange(int min, int max, Random rng) {
		if (min > max) {
			throw new IllegalArgumentException(
					"Cannot draw random int from invalid range [" + min + ", "
							+ max + "].");
		}
		int diff = max - min;
		if (diff >= 0 && diff != Integer.MAX_VALUE) {
			return (min + rng.nextInt(diff + 1));
		}
		int i;
		do {
			i = rng.nextInt();
		} while (i < min || i > max);
		return i;
	}

	private static Double truncateDecimal(double x,int numberofDecimals){
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR).doubleValue();
	    } else {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING).doubleValue();
	    }
	}	
}
