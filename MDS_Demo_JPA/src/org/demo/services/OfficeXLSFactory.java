package org.demo.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.erp.ProductNom;
import org.office.access.ProductCategory;
import org.office.xls.AdvertisingExpense;

public class OfficeXLSFactory {
	private static Logger logger = Logger.getLogger(OfficeXLSFactory.class.getName());
	
	private List<AdvertisingExpense> advertisingExpensesLoad = new ArrayList<>();
	private List<String> categoryLoad = new ArrayList<>();
	private Integer expCategoryCount = 5;
	private Integer startCategoryChar = 65; // A
	private Map<Date, Date> periods = new TreeMap<>();
	private Integer expPeriodCount = 3; 
	
	public List<AdvertisingExpense> generateAdvertisingExpensesLoad(List<ProductNom> productLoad) {
		// categories and sub-categories load
		List<String> subcategLoad;
		for(int i=startCategoryChar; i < startCategoryChar + expCategoryCount; i++){
			categoryLoad.add("ADVERT_EXP_CTG_" + (char)i);
		}
		// periods loads: 3 periods, 1 Month
		Calendar currentDate = Calendar.getInstance();
		Calendar periodDate = Calendar.getInstance(); 
		periodDate.add(Calendar.MONTH, -expPeriodCount);
		Date startDate = null;
		Date endDate = null;
		for(int i=1; i <=expPeriodCount; i++){
			startDate = periodDate.getTime();
			periodDate.add(Calendar.MONTH, +1); 
			endDate = periodDate.getTime();
			periods.put(startDate, endDate);
		}
		
		// generate expenses
		Random categoryRandomizer = new Random();
		Random amountLoadRandomizer = new Random();
		Random salesLoadRandomizer = new Random();
		Integer categoryIdx = 0;
		String advExpCateg;
		Double advExpAmount; Double productSales;
		Integer expId = 1000;
		for(ProductNom p : productLoad){
			for(Date periodStartDate: periods.keySet()){
				categoryIdx = DataFactoryUtils.nextIntInRange(0, categoryLoad.size()-1, categoryRandomizer);
				advExpCateg = categoryLoad.get(categoryIdx);
				advExpAmount = DataFactoryUtils.truncateDecimal(DataFactoryUtils.nextIntInRange(1500, 95000, amountLoadRandomizer)/1.25,2);
				productSales = DataFactoryUtils.truncateDecimal(DataFactoryUtils.nextIntInRange(10, 200, salesLoadRandomizer).doubleValue(),2);
				advertisingExpensesLoad.add(new AdvertisingExpense(
						++expId,
						p.getProductId(), 
						periodStartDate, periods.get(periodStartDate), 
						advExpCateg, advExpAmount, 
						productSales));
			}
		}
		return advertisingExpensesLoad;
	}
	
}
