package org.app.scrum.test;

import java.util.*;

import org.app.scrum.*;
import org.app.scrum.sprint.*;
import org.app.scrum.team.*;

public class Test10Colectii_Operatii {

	
	public static void main(String[] args) {
		/* Operatii cu liste/seturi */
		
		// C1: list 
		List<Member> listMembri = new ArrayList<>(10);
		Integer size = 10;
		for(int i=0; i < size; i++)
			listMembri.add(new Member(size - i, 
					"Membru " + (size - i), Role.DEVELOPER));
		
		
		// C2: set
		Set<Member> setMembri = new TreeSet<>();
		int start, end = 12; 
		for(start = 5; start <= end; start++)
			setMembri.add(new Member(start, "Membru " + start, Role.DEVELOPER));
		
		
		// C3 = C1 union C2
		Set<Member> unionMembri = new TreeSet<>(setMembri);
		unionMembri.addAll(listMembri);
		for(Member m: unionMembri)
			System.out.println("union-el:  " + m);
		System.out.println("--------------------------------------------------------");
		
		// C4 = C1 minus C2
		Set<Member> minusMembri = new TreeSet<>(setMembri);
		minusMembri.removeAll(listMembri);
		for(Member m: minusMembri)
			System.out.println("minus-el:  " + m);
		System.out.println("--------------------------------------------------------");
		
		// C5 = C1 intersect C2
		Set<Member> intersectMembri = new TreeSet<>(setMembri);
		intersectMembri.retainAll(listMembri);
		for(Member m: intersectMembri)
			System.out.println("intersectMembri-el:  " + m);		
		System.out.println("--------------------------------------------------------");
		
		/* Cautare in colectii */
		// C1 get element de pe pozitie
		Member element = listMembri.get(5);
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
		Member membruIdMax = Collections.max(unionMembri, new ComparatorMembruIdMinMax());
		System.out.println("Insnta Membru cu id-ul cel mai mare este: " + membruIdMax);
		Member membruIdMin = Collections.min(unionMembri, new ComparatorMembruIdMinMax());
		System.out.println("Insnta Membru cu id-ul cel mai mic este: " + membruIdMin);
		System.out.println("--------------------------------------------------------");
		
		// C2 TreeSet sort
		Set<Member> setMembriSortNumePren = new TreeSet<>(new ComparatorMembruNumepren());
		setMembriSortNumePren.addAll(listMembri);
		for(Member m: setMembriSortNumePren)
			System.out.println("setMembriSortNumePren-el:  " + m);			
		System.out.println("--------------------------------------------------------");
		
		// C1 Collections.sort
		Member[] arrayMembri = unionMembri.toArray(new Member[0]);
		List<Member> listMembriSortId = Arrays.asList(arrayMembri);
		Collections.sort(listMembriSortId, new ComparatorMembruIdReverse());
		for(Member m: listMembriSortId)
			System.out.println("listMembriSortIdReverse-el:  " + m);
		System.out.println("--------------------------------------------------------");
		
		// C1 indexare in Map(e) dupa criteriu de cautare
		Map<Integer, Member> mapMembri = new TreeMap<Integer, Member>();
		for(Member m: unionMembri)
			mapMembri.put(m.getIdMembru(), m);
		Member elSearchMapResult = mapMembri.get(element.getIdMembru());
		
		if (elSearchMapResult != null)
			System.out.println("Obiectul " + element + " a fost gasit in mapa dupa id!");
		else
			System.out.println("Obiectul " + element + " nu a fost gasit in mapa dupa id!");
	}

}

class ComparatorBS implements Comparator<Member>{
	@Override
	public int compare(Member m1, Member m2) {
		// TODO Auto-generated method stub
		return m1.getIdMembru().compareTo(m2.getIdMembru());
	}
}

class ComparatorMembruNumepren implements Comparator<Member>{
	@Override
	public int compare(Member m1, Member m2) {
		// TODO Auto-generated method stub
		return m1.getNumePrenume().compareTo(m2.getNumePrenume());
	}
}


class ComparatorMembruIdReverse implements Comparator<Member>{
	@Override
	public int compare(Member m1, Member m2) {
		// TODO Auto-generated method stub
		return m2.getIdMembru().compareTo(m1.getIdMembru());
	}
}

class ComparatorMembruIdMinMax implements Comparator<Member>{
	@Override
	public int compare(Member m1, Member m2) {
		// TODO Auto-generated method stub
		return m1.getIdMembru().compareTo(m2.getIdMembru());
	}
}