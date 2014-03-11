package org.app.scrum.test;

import java.util.*;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

public class Test10Colectii_Operatii {

	
	public static void main(String[] args) {
		/* Operatii cu liste/seturi */
		
		// C1: list 
		List<Membru> listMembri = new ArrayList<>(10);
		Integer size = 10;
		for(int i=0; i < size; i++)
			listMembri.add(new Membru(size - i, 
					"Membru " + (size - i), Rol.DEVELOPER));
		
		
		// C2: set
		Set<Membru> setMembri = new TreeSet<>();
		int start, end = 12; 
		for(start = 5; start <= end; start++)
			setMembri.add(new Membru(start, "Membru " + start, Rol.DEVELOPER));
		
		
		// C3 = C1 union C2
		Set<Membru> unionMembri = new TreeSet<>(setMembri);
		unionMembri.addAll(listMembri);
		for(Membru m: unionMembri)
			System.out.println("union-el:  " + m);
		System.out.println("--------------------------------------------------------");
		
		// C4 = C1 minus C2
		Set<Membru> minusMembri = new TreeSet<>(setMembri);
		minusMembri.removeAll(listMembri);
		for(Membru m: minusMembri)
			System.out.println("minus-el:  " + m);
		System.out.println("--------------------------------------------------------");
		
		// C5 = C1 intersect C2
		Set<Membru> intersectMembri = new TreeSet<>(setMembri);
		intersectMembri.retainAll(listMembri);
		for(Membru m: intersectMembri)
			System.out.println("intersectMembri-el:  " + m);		
		System.out.println("--------------------------------------------------------");
		
		/* Cautare in colectii */
		// C1 get element de pe pozitie
		Membru element = listMembri.get(5);
		System.out.println("Element cautat: " + element);
		
		// C2 contains element
		Boolean rezultatSearch = setMembri.contains(element);
		if (rezultatSearch)
			System.out.println("setMembri contine " + element);
		else
			System.out.println("setMembri nu contine " + element);
		System.out.println("--------------------------------------------------------");
		
		// C3 binarySearch element
		Integer rezultatBinarySearch = Collections.binarySearch(listMembri, element, new ComparatorBS());
		if (rezultatBinarySearch >= 0)
			System.out.println("listMembri contine " + element);
		else
			System.out.println("listMembri nu contine " + element + " - " + rezultatBinarySearch);
		System.out.println("--------------------------------------------------------");
		/* Cautare si sortare */
		
		// C4 Collections.min/max element folosind comparator
		Membru membruIdMax = Collections.max(unionMembri, new ComparatorMembruIdMinMax());
		System.out.println("Insnta Membru cu id-ul cel mai mare este: " + membruIdMax);
		Membru membruIdMin = Collections.min(unionMembri, new ComparatorMembruIdMinMax());
		System.out.println("Insnta Membru cu id-ul cel mai mic este: " + membruIdMin);
		System.out.println("--------------------------------------------------------");
		
		// C2 TreeSet sort
		Set<Membru> setMembriSortNumePren = new TreeSet<>(new ComparatorMembruNumepren());
		setMembriSortNumePren.addAll(listMembri);
		for(Membru m: setMembriSortNumePren)
			System.out.println("setMembriSortNumePren-el:  " + m);			
		System.out.println("--------------------------------------------------------");
		
		// C1 Collections.sort
		Membru[] arrayMembri = unionMembri.toArray(new Membru[0]);
		List<Membru> listMembriSortId = Arrays.asList(arrayMembri);
		Collections.sort(listMembriSortId, new ComparatorMembruIdReverse());
		for(Membru m: listMembriSortId)
			System.out.println("listMembriSortIdReverse-el:  " + m);
		System.out.println("--------------------------------------------------------");
		
		// C1 indexare in Map(e) dupa criteriu de cautare
		Map<Integer, Membru> mapMembri = new TreeMap<Integer, Membru>();
		for(Membru m: unionMembri)
			mapMembri.put(m.getIdMembru(), m);
		Membru elSearchMapResult = mapMembri.get(element.getIdMembru());
		
		if (elSearchMapResult != null)
			System.out.println("Obiectul " + element + " a fost gasit in mapa dupa id!");
		else
			System.out.println("Obiectul " + element + " nu a fost gasit in mapa dupa id!");
	}

}

class ComparatorBS implements Comparator<Membru>{
	@Override
	public int compare(Membru m1, Membru m2) {
		// TODO Auto-generated method stub
		return m1.getIdMembru().compareTo(m2.getIdMembru());
	}
}

class ComparatorMembruNumepren implements Comparator<Membru>{
	@Override
	public int compare(Membru m1, Membru m2) {
		// TODO Auto-generated method stub
		return m1.getNumePrenume().compareTo(m2.getNumePrenume());
	}
}


class ComparatorMembruIdReverse implements Comparator<Membru>{
	@Override
	public int compare(Membru m1, Membru m2) {
		// TODO Auto-generated method stub
		return m2.getIdMembru().compareTo(m1.getIdMembru());
	}
}

class ComparatorMembruIdMinMax implements Comparator<Membru>{
	@Override
	public int compare(Membru m1, Membru m2) {
		// TODO Auto-generated method stub
		return m1.getIdMembru().compareTo(m2.getIdMembru());
	}
}