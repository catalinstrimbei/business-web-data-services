package org.app.scrum.test;

import java.util.*;

import org.app.scrum.CategorieCerinta;
import org.app.scrum.Cerinta;
import org.app.scrum.CerintaFunctionala;

public class Test9_Colectii_Tipologie {
	public static void main(String[] args){
		/* Ex 1: Tipuri de colectii: *********************************************************/
		// Array-Collection-List-Set-Map-Set-Array
		// 1. Array simplu
		Cerinta[] arrayCerinte = new Cerinta[5];
		for(int i=0; i < arrayCerinte.length; i++)
			arrayCerinte[i] = new CerintaFunctionala(
					arrayCerinte.length - i, 
					"Cerinta " + (arrayCerinte.length - i), 
					null, null);
		
		// 2. Collection tipizate nedefinita ca implementare
		Collection<Cerinta> collectionCerinta = Arrays.asList(arrayCerinte);
		Iterator<Cerinta> iteratorCollectionCerinte = collectionCerinta.iterator();
		while(iteratorCollectionCerinte.hasNext())
			System.out.println("collection-cerinte next-element: " + iteratorCollectionCerinte.next());		
		// 3. Lista tipizata
		List<Cerinta> listCerinte = new ArrayList<>();
		listCerinte.addAll(collectionCerinta);
		// 4. Set tipizat
		Set<Cerinta> setCerinte = new TreeSet<>();
		setCerinte.addAll(listCerinte);
		// 5. Map-a tipizata
		Map<Integer, Cerinta> mapCerinte = new HashMap<Integer, Cerinta>();
		for(Cerinta c: setCerinte)
			mapCerinte.put(c.getIdCerinta(), c);
		// 6. Set din cheile-map
		Set<Integer> setIdCerinte = mapCerinte.keySet();
		// 7. Collection tipizata - revenire
		Cerinta[] mapArrayCerinte = mapCerinte.values().toArray(new Cerinta[1]);
		for(int i=0; i < mapArrayCerinte.length; i++){
			System.out.println("mapArrayCerinte[" + i +"]=" + mapArrayCerinte[i]);
		}
		
		
		
		/* Ex 3: Interogare/cautare colectii: ***********************************************/
		
		// Lista de membri - ordonare
		
		// Lista de cerinte - ordonare
		
		// Map de cerinte din lista - prioritizare
	}
}
