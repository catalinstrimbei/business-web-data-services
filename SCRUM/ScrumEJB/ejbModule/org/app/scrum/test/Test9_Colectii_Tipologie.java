package org.app.scrum.test;

import java.util.*;

import org.app.scrum.FeatureCategory;
import org.app.scrum.Feature;
import org.app.scrum.BusinessFeature;

public class Test9_Colectii_Tipologie {
	public static void main(String[] args){
		/* Ex 1: Tipuri de colectii: *********************************************************/
		// Array-Collection-List-Set-Map-Set-Array
		// 1. Array simplu
		Feature[] arrayCerinte = new Feature[5];
		for(int i=0; i < arrayCerinte.length; i++)
			arrayCerinte[i] = new BusinessFeature(
					arrayCerinte.length - i, 
					"Cerinta " + (arrayCerinte.length - i), 
					null, null);
		
		// 2. Collection tipizate nedefinita ca implementare
		Collection<Feature> collectionCerinta = Arrays.asList(arrayCerinte);
		Iterator<Feature> iteratorCollectionCerinte = collectionCerinta.iterator();
		while(iteratorCollectionCerinte.hasNext())
			System.out.println("collection-cerinte next-element: " + iteratorCollectionCerinte.next());		
		// 3. Lista tipizata
		List<Feature> listCerinte = new ArrayList<>();
		listCerinte.addAll(collectionCerinta);
		// 4. Set tipizat
		Set<Feature> setCerinte = new TreeSet<>();
		setCerinte.addAll(listCerinte);
		// 5. Map-a tipizata
		Map<Integer, Feature> mapCerinte = new HashMap<Integer, Feature>();
		for(Feature c: setCerinte)
			mapCerinte.put(c.getIdCerinta(), c);
		// 6. Set din cheile-map
		Set<Integer> setIdCerinte = mapCerinte.keySet();
		// 7. Collection tipizata - revenire
		Feature[] mapArrayCerinte = mapCerinte.values().toArray(new Feature[1]);
		for(int i=0; i < mapArrayCerinte.length; i++){
			System.out.println("mapArrayCerinte[" + i +"]=" + mapArrayCerinte[i]);
		}
		
		
		
		/* Ex 3: Interogare/cautare colectii: ***********************************************/
		
		// Lista de membri - ordonare
		
		// Lista de cerinte - ordonare
		
		// Map de cerinte din lista - prioritizare
	}
}
