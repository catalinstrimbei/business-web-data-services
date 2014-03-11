package org.app.scrum.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.app.scrum.Proiect;
import org.app.scrum.ProiectBuilder;

public class TestScrumJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ScrumJPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Proiect proiect;
		for(int i=1; i <= 4; i++){
			proiect = new ProiectBuilder().buildProiect(i, "Proiect Test", i+2);
			em.persist(proiect);
		}
		
		System.out.println("Salvare proiecte!");
		
		em.getTransaction().commit();
		
		System.out.println("End");
	}

}
