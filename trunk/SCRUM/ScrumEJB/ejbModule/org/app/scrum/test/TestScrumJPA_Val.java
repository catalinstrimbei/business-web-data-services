package org.app.scrum.test;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.app.scrum.Proiect;
import org.app.scrum.ProiectBuilder;

public class TestScrumJPA_Val {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ScrumJPA2");
		EntityManager em = emf.createEntityManager();
		
		Proiect proiect = new ProiectBuilder().buildProiect(1, "P.t.", 3);
//		proiect.setReleaseCurent(proiect.getReleases().get(0));
		
		try{
			System.out.println("Saving proiect!");
			/* Invocare directa*/
//			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//			Validator validator = factory.getValidator();
//			Set<ConstraintViolation<Proiect>> constraintViolations = validator.validate(proiect);
//			handleValidation(constraintViolations);
			
			/* Invocare indirecta prin EntityManager*/
			em.getTransaction().begin();			
			em.persist(proiect);
//			em.getTransaction().commit();
			em.getTransaction().rollback();
			
		}catch(ConstraintViolationException e){
			System.out.println("Validation ex: " + e.getMessage());
			for (ConstraintViolation cv : e.getConstraintViolations()){
				System.out.println("ConstraintViolation: " + cv.getMessage());
				System.out.println("Invalid bean: " + cv.getRootBean());
			}	
		}
		System.out.println("End");
	}

	
	static void handleValidation(Set<ConstraintViolation<Proiect>> cvs){
		for (ConstraintViolation cv : cvs){
			System.out.println("ConstraintViolation: " + cv.getMessage());
			System.out.println("Invalid bean: " + cv.getRootBean());
//			System.out.println("Invalid bean: " + cv.getLeafBean());
		}		
	}
}
