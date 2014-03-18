package org.app.scrum.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.FeatureCategory;
import org.app.scrum.Feature;
import org.app.scrum.BusinessFeature;
import org.app.scrum.TechnicalFeature;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.sprint.Task;

public class Test5_MostenireCerinte {
	
	public static void main(String[] args) {

		Feature c1 = new TechnicalFeature(1, "Cerinta 1", "cerinta test");
		c1.setCategorie(FeatureCategory.TEHNICA);
		
		BusinessFeature c2 = new BusinessFeature(1, "Cerinta 2", "cerinta test mostenire", "basic", "use case generic");
//		c2.setCategorie(CategorieCerinta.FUNCTIONALA);
		
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
	}
	
}
