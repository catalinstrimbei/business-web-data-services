package org.app.scrum.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.scrum.Proiect;
import org.app.scrum.ProiectBuilder;
import org.app.scrum.ProiectView;

public class TestScrumJPAQL {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ScrumJPA");
		EntityManager em = emf.createEntityManager();
		
		/* Bug Eclipse */
//		List<Proiect> pList = em.createNativeQuery("SELECT p.nrproiect AS nrProiect, p.numeproiect, p.datastart FROM proiect p", Proiect.class)
//				.getResultList();
//		for(Proiect p: pList)
//			System.out.println("Proiect: " + p);
				
		/* OK */
//		List<Object[]> pList = em.createNativeQuery("SELECT p.nrproiect, p.numeproiect FROM proiect p").getResultList();
//		for(Object[] p: pList)
//			System.out.println("Proiect: " + p[0] + " - " + p[1]);		

		/* OK */
//		Integer nrProiecte = (Integer) em.createNativeQuery("SELECT COUNT(*) FROM proiect p").getSingleResult();
//		System.out.println("Nr proiecte: " + nrProiecte);
		
		List<ProiectView> pList = em.createQuery("SELECT NEW org.app.scrum.ProiectView(p.nrProiect, p.numeProiect) FROM Proiect p").getResultList();
		for(ProiectView p: pList)
			System.out.println("Proiect: " + p);		

		
		
		System.out.println("End");
	}

}
