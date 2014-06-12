package org.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.erp.ProductNom;
import org.office.access.ProductCategory;
import org.office.access.ProductInCategories;

public class OfficeAccessFactory {
	private static Logger logger = Logger.getLogger(OfficeAccessFactory.class.getName());
	
	private Map<String, List<String>> categoryLoad = new TreeMap<>();	
	private List<ProductCategory> productCategLoad = new ArrayList<>();
	private List<ProductInCategories> productInCategLoad = new ArrayList<>();
	private List<Integer> productCategRefLoad = new ArrayList<>();
	private Integer subcategLoadMaxCount = 5;
	private Integer startCategoryChar = 65; // A
	
	public List<ProductCategory> generateProductCategoryLoad(Integer prodCategoryCount) {
		// categories and sub-categories load
		List<String> subcategLoad;
		for(int i=startCategoryChar; i < startCategoryChar + prodCategoryCount; i++){
			subcategLoad = new ArrayList<>();
			for(int k=1; k <= subcategLoadMaxCount; k++)
				subcategLoad.add("SUB_CTG_" + ((char)i) + k);
			categoryLoad.put("SUPER_CTG_" + (char)i, subcategLoad);
		}
		// product category load
		productCategLoad = new ArrayList<>();
		Integer startIdCounter = 100;
		for (String category: categoryLoad.keySet()) {
			productCategLoad.add(new ProductCategory(++startIdCounter, category, "Product Categories from Office Access " + startIdCounter));
			productCategRefLoad.add(startIdCounter);
			for(String subcategory: categoryLoad.get(category)){
				productCategLoad.add(new ProductCategory(++startIdCounter, subcategory, "Product Sub-Categories from Office Access " + startIdCounter));
				productCategRefLoad.add(startIdCounter);				
			}
		}
		return productCategLoad;
	}
	
	public List<ProductInCategories> generateProductInCategoriesLoad(List<ProductNom> productLoad) {
		Random categoryRandomizer = new Random();
		Random subcategRandomizer = new Random();
		
		Integer categoryIdx = 0;
		Integer subcategIdx = 0;
		
		List<String> supercategLoad = new ArrayList<>();
		supercategLoad.addAll(categoryLoad.keySet());
		List<String> subcategLoad = new ArrayList<>();
		
		String category; String subcategory;
		for(ProductNom p: productLoad){
			categoryIdx = DataFactoryUtils.nextIntInRange(0, supercategLoad.size()-1, categoryRandomizer);
			category = supercategLoad.get(categoryIdx);
			
			subcategLoad = categoryLoad.get(category);
			subcategIdx = DataFactoryUtils.nextIntInRange(0, subcategLoad.size()-1, subcategRandomizer);
			subcategory = subcategLoad.get(subcategIdx);		
			
			productInCategLoad.add(new ProductInCategories(p.getProductId(), p.getName(), category, subcategory));
		}		
		return productInCategLoad;
	}
}