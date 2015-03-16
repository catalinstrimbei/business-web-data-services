package org.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.crm.CustomerProfile;

//import org.erp.ProductNom;

public class CRMFactory {
	private static Logger logger = Logger.getLogger(CRMFactory.class.getName());
	
	private List<CustomerProfile> customerProfileLoad = new ArrayList<>();
	private List<Integer> customerRefLoad = new ArrayList<>();
	private Integer countryLoadCount = 10;
	private Integer regionLoadMaxCount = 5;
	private Integer categoryLoadCount = 5;;
	private Integer startCountryChar = 65; // A
	private Integer startCategoryChar = 65; // A
	
	public List<CustomerProfile> generateCustomerProfileLoad(Integer customerCount) {
		// country region load
		Map<String, List<String>> countryRegionLoad = new TreeMap<>();
		List<String> regionLoad;
		for(int i=startCountryChar; i <= startCountryChar + countryLoadCount; i++){
			regionLoad = new ArrayList<>();
			for(int k=1; k <= regionLoadMaxCount; k++)
				regionLoad.add("REGION_" + ((char)i) + k);
			countryRegionLoad.put("COUNTRY_" + (char)i, regionLoad);
		}
		// customer category load
		List<String> categoryLoad = new ArrayList<>();
		for(int i=startCategoryChar; i <= startCategoryChar + categoryLoadCount; i++){
			categoryLoad.add("CUST_CATEG_" + ((char)i));
		}
		// customer load
		customerProfileLoad = new ArrayList<>();
		Integer custId = 0;
		Random countryRandomizer = new Random();
		Random regionRandomizer = new Random();
		Random categoryRandomizer = new Random();
		Integer countryIdx = 0;
		Integer regionIdx = 0;
		Integer categoryIdx = 0;
		List<String> countryLoad = new ArrayList<>();
		countryLoad.addAll(countryRegionLoad.keySet());
		String country; String region; String category;
		for (int i = 1; i <= customerCount; i++) {
			custId = 1000 + i;
			
			countryIdx = DataFactoryUtils.nextIntInRange(0, countryLoad.size()-1, countryRandomizer);
			country = countryLoad.get(countryIdx);
			
			regionLoad = countryRegionLoad.get(country);
			regionIdx = DataFactoryUtils.nextIntInRange(0, regionLoad.size()-1, regionRandomizer);
			region = regionLoad.get(regionIdx);
			
			categoryIdx = DataFactoryUtils.nextIntInRange(0, categoryLoad.size()-1, categoryRandomizer);
			category = categoryLoad.get(categoryIdx);
			
			customerProfileLoad.add(new CustomerProfile(custId,  "CUSTOMER " + i, region, "Address of " + "CUSTOMER " + i, country, category));
			customerRefLoad.add(custId);
		}

		return customerProfileLoad;
	}	
	
	public List<Integer> getCustomerReflLoad(){
		return customerRefLoad;
	}

	public CRMFactory() {
		super();
	}

	public CRMFactory(Integer countryLoadCount, Integer regionLoadMaxCount,
			Integer categoryLoadCount) {
		super();
		this.countryLoadCount = countryLoadCount;
		this.regionLoadMaxCount = regionLoadMaxCount;
		this.categoryLoadCount = categoryLoadCount;
	}
	
	
	
}
